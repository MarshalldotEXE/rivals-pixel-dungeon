/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Rivals Pixel Dungeon
 * Copyright (C) 2019-2020 Marshall M.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.BlastParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SmokeParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.BArray;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class Nova extends Armor.Glyph {

	private static ItemSprite.Glowing BLACK = new ItemSprite.Glowing( 0x000000 );

	@Override
	public int proc(Armor armor, Char attacker, Char defender, int damage) {
		
		NovaBomb nova = Buff.affect(defender, NovaBomb.class);
		nova.recharge(damage);
		
		return damage;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return BLACK;
	}
	
	public static void doNovaBomb(Hero hero) {
		
		//if there's no armor equipped, stop the nova
		if (hero.belongings.armor == null)
			return;
		
		int lvl = hero.belongings.armor.level();
		BrokenSeal seal = hero.belongings.armor.checkSeal();
		
		Sample.INSTANCE.play( Assets.SND_BLAST );
		
		ArrayList<Char> affected = new ArrayList<>();
		
		//first detect all enemies in a 5x5
		PathFinder.buildDistanceMap( hero.pos, BArray.not( Dungeon.level.solid, null ), 2 );
		for (int i = 0; i < PathFinder.distance.length; i++) {
			if (PathFinder.distance[i] < Integer.MAX_VALUE) {
				if (Dungeon.level.heroFOV[i]) {
					CellEmitter.get(i).burst(SmokeParticle.FACTORY, 4);
				}
				Char ch = Actor.findChar(i);
				if (ch != null && !(ch instanceof Hero)){
					affected.add(ch);
				}
			}
		}
		CellEmitter.center(hero.pos).burst(BlastParticle.FACTORY, 83);
		
		//then damage them
		for (Char ch : affected){
			if (lvl < 4)
				ch.damage(99, Nova.class);
			else if (lvl < 8)
				ch.damage(999, Nova.class);
			else
				ch.damage(9999, Nova.class);
		}
		
		hero.belongings.armor = null;
		((HeroSprite)hero.sprite).updateArmor();
		if (seal != null)
			Dungeon.level.drop(seal, hero.pos);
		
		hero.buff(NovaBomb.class).detach();
	}
	
	public static class NovaBomb extends Buff {
	
		{
			type = buffType.POSITIVE;
		}

		private int charge = 0;
	
		@Override
		public boolean act() {
			if (target.isAlive()) {

				spend( 1 );
				if (--charge <= 0) {
					detach();
				}
			
			} else {
				
				detach();
				
			}
			
			return true;
		}
	
		public int charge() {
			return charge;
		}
	
		public void recharge( int damage ) {
			this.charge += damage * 10;
		}
		
		@Override
		public int icon() {
			return BuffIndicator.NOVA;
		}
		
		@Override
		public String toString() {
			return Messages.get(this, "name");
		}
		
		@Override
		public String desc() {
			return Messages.get(this, "desc", (int)(charge/3330f*100) );
		}
		
		private static final String CHARGE	= "charge";
		
		@Override
		public void storeInBundle( Bundle bundle ) {
			super.storeInBundle( bundle );
			bundle.put( CHARGE, charge );
		}
		
		@Override
		public void restoreFromBundle( Bundle bundle ) {
			super.restoreFromBundle( bundle );
			charge = bundle.getInt( CHARGE );
		}
	}
}
