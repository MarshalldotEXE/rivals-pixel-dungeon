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

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ShieldBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

public class Brimstone extends Armor.Glyph {

	private static ItemSprite.Glowing ORANGE = new ItemSprite.Glowing( 0xFF3300 );

	@Override
	public int proc(Armor armor, Char attacker, Char defender, int damage) {
		
		int lvl = Math.max( 0, armor.level() );
		
		int magma = (int)(lvl * 1.3 + 2);
		
		Buff.affect(defender, MagmaArmor.class).set(magma);
		
		return damage;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return ORANGE;
	}

	//pre-0.7.4 saves
	public static class BrimstoneShield extends ShieldBuff {
		
		{
			type = buffType.POSITIVE;
		}

		@Override
		public boolean act() {
			Hero hero = (Hero)target;

			if (hero.belongings.armor == null || !hero.belongings.armor.hasGlyph(Brimstone.class, hero)) {
				detach();
				return true;
			}

			if (shielding() > 0){
				decShield();

				//shield decays at a rate of 1 per turn.
				spend(TICK);
			} else {
				detach();
			}

			return true;
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			//pre-0.7.0
			if (bundle.contains("added")){
				setShield(bundle.getInt("added"));
			}
		}
	}
	
	public static class MagmaArmor extends Buff {
	
		{
			type = buffType.POSITIVE;
		}

		private int strength = 0;
	
		@Override
		public boolean act() {
			if (target.isAlive()) {

				spend( 1 );
				if (--strength <= 0) {
					detach();
				}
			
			} else {
				
				detach();
				
			}
			
			return true;
		}
	
		public int strength() {
			return strength;
		}
	
		public void set( int value ) {
			if (strength < value)
				strength = value;
		}
		
		@Override
		public int icon() {
			return BuffIndicator.ARMOR;
		}
		
		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(1f, 0.4f, 0.4f);
		}
		
		@Override
		public String toString() {
			return Messages.get(this, "name");
		}
		
		@Override
		public String desc() {
			return Messages.get(this, "desc", strength);
		}
		
		private static final String STRENGTH	= "strength";
		
		@Override
		public void storeInBundle( Bundle bundle ) {
			super.storeInBundle( bundle );
			bundle.put( STRENGTH, strength );
		}
		
		@Override
		public void restoreFromBundle( Bundle bundle ) {
			super.restoreFromBundle( bundle );
			strength = bundle.getInt( STRENGTH );
		}
	}
}
