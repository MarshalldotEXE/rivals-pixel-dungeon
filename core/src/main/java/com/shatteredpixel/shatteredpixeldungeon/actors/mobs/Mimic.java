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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Pushing;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MimicSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Mimic extends Mob {
	
	protected int level;
	
	{
		spriteClass = MimicSprite.class;
	}
	
	public ArrayList<Item> items;
	
	private static final String LEVEL	= "level";
	private static final String ITEMS	= "items";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		if (items != null) bundle.put( ITEMS, items );
		bundle.put( LEVEL, level );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		if (bundle.contains( ITEMS )) {
			items = new ArrayList<>((Collection<Item>) ((Collection<?>) bundle.getCollection(ITEMS)));
		}
		adjustStats( bundle.getInt( LEVEL ) );
		super.restoreFromBundle(bundle);
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( level, level + 5 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 2 * (9 + level);
	}
	
	public void adjustStats( int level ) {
		this.level = level;
		
		HP = HT = (3 + level) * 6 - 4;
		EXP = 1 + 3 * level / 4;
		maxLvl = (1 + level / 4) * 4;
		defenseSkill = (4 + level) / 2;
		
		enemySeen = true;
	}
	
	@Override
	public void rollToDropLoot(){
		
		if (items != null) {
			for (Item item : items) {
				Dungeon.level.drop( item, pos ).sprite.drop();
			}
			items = null;
		}
		super.rollToDropLoot();
	}
	
	@Override
	public boolean reset() {
		state = WANDERING;
		return true;
	}

	public static Mimic spawnAt( int pos, List<Item> items ) {
		if (Dungeon.level.pit[pos]) return null;
		Char ch = Actor.findChar( pos );
		if (ch != null) {
			ArrayList<Integer> candidates = new ArrayList<>();
			for (int n : PathFinder.NEIGHBOURS8) {
				int cell = pos + n;
				if ((Dungeon.level.passable[cell] || Dungeon.level.avoid[cell]) && Actor.findChar( cell ) == null) {
					candidates.add( cell );
				}
			}
			if (candidates.size() > 0) {
				int newPos = Random.element( candidates );
				Actor.addDelayed( new Pushing( ch, ch.pos, newPos ), -1 );
				
				ch.pos = newPos;
				Dungeon.level.press( newPos, ch );
				
			} else {
				return null;
			}
		}
		
		Mimic m = new Mimic();
		m.items = new ArrayList<>( items );
		m.adjustStats( Dungeon.depth );
		m.pos = pos;
		m.state = m.HUNTING;
		GameScene.add( m, 1 );
		
		m.sprite.turnTo( pos, Dungeon.hero.pos );
		
		if (Dungeon.level.heroFOV[m.pos]) {
			CellEmitter.get( pos ).burst( Speck.factory( Speck.STAR ), 10 );
			Sample.INSTANCE.play( Assets.SND_MIMIC );
		}

		//generate an extra reward for killing the mimic
		Item reward = null;
		do {
			switch (Random.Int(4)) {
				case 0:
					reward = new Gold().random();
					break;
				case 1:
					reward = Generator.randomMissile();
					break;
				case 2:
					reward = Generator.randomArmor();
					break;
				case 3:
					reward = Generator.randomWeapon();
					break;
			}
		} while (reward == null);
		m.items.add(reward);
		
		return m;
	}
	
	{
		immunities.add( ScrollOfRetribution.class );
		immunities.add( ScrollOfPsionicBlast.class );
	}
}
