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
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Door;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MonsterBoxSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.List;

public class MonsterBox extends Mimic {

	{
		spriteClass = MonsterBoxSprite.class;
		
		properties.add(Property.DEMONIC);
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( level + 1, level + 7 );
	}
	
	@Override
	public void adjustStats( int level ) {
		this.level = level;
		
		HP = HT = (3 + level) * 10 - 8;
		EXP = 1 + 5 * level / 4;
		maxLvl = (1 + level / 4) * 4;
		defenseSkill = (4 + level) / 2;
		
		enemySeen = true;
	}
	
	@Override
	public void die(Object cause) {
		
		//first pick a random number of enemies to spawn
		//67% 2 mobs, 33% 3 mobs
		int numSpawns = 2;
		if (Random.Int( 3 ) == 0) {
			numSpawns++;
		}
		
		ArrayList<Integer> candidates = new ArrayList<>();
		boolean[] solid = Dungeon.level.solid;
		
		//then find suitable spawn locations and spawn random enemies from the bestiary
		for (int i = numSpawns; i > 0; i--) {
			
			int[] neighbours = {pos + 1, pos - 1, pos + Dungeon.level.width(), pos - Dungeon.level.width()};
			for (int n : neighbours) {
				if (!solid[n] && Actor.findChar( n ) == null) {
					candidates.add( n );
				}
			}
			
			if (candidates.size() > 0) {
				
				Mob newSpawn = Dungeon.level.createMob();
				
				newSpawn.pos = Random.element( candidates );
				newSpawn.state = newSpawn.HUNTING;
					
				if (Dungeon.level.map[newSpawn.pos] == Terrain.DOOR) {
					Door.enter( newSpawn.pos );
				}
					
				GameScene.add( newSpawn );
				Actor.addDelayed( new Pushing( newSpawn, pos, newSpawn.pos ), -1 );
				
				candidates.clear();
			}
		}
		
		super.die(cause);
		
		//generate some extra rewards for killing the mimic
		//50% 2 mats, 30% 3 mats, 20% 4 mats
		int mats = 2 + Random.chances(new float[]{5, 3, 2});
		for (int j = 0; j < mats; j++){
			int ofs;
			do {
				ofs = PathFinder.NEIGHBOURS8[Random.Int(8)];
			} while (!Dungeon.level.passable[pos + ofs]);
			Dungeon.level.drop( Generator.random(Generator.Category.MATERIAL), pos + ofs ).sprite.drop( pos );
		}
	}
	
	public static MonsterBox spawnAt( int pos, List<Item> items ) {
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
		
		MonsterBox m = new MonsterBox();
		m.items = new ArrayList<>( items );
		m.adjustStats( Dungeon.depth );
		m.pos = pos;
		m.state = m.HUNTING;
		GameScene.add( m, 1 );
		
		m.sprite.turnTo( pos, Dungeon.hero.pos );
		
		if (Dungeon.level.heroFOV[m.pos]) {
			CellEmitter.get( pos ).burst( Speck.factory( Speck.MASTERY ), 12 );
			Sample.INSTANCE.play( Assets.SND_MIMIC );
		}
		
		return m;
	}
	
}
