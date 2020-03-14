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

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Blooming extends Weapon.Enchantment {
	
	private static ItemSprite.Glowing DARK_GREEN = new ItemSprite.Glowing( 0x009900 );
	
	@Override
	public int proc(Weapon weapon, Char attacker, Char defender, int damage) {
		
		int lvl = Math.max( 0, weapon.level() );
		
		float chanceA = (lvl + damage) * ONE_PERCENT;
		float chanceB = (lvl + 1) * LEVEL_SCALING;
		
		//A% chance to spawn 2 grass
		//B% chance to spawn 1 extra
		if (Random.Float() < chanceA) {
			
			boolean secondGrass = true;
			boolean thirdGrass = Random.Float() < chanceB;
			
			plantGrass(defender.pos, lvl + 1);
			
			ArrayList<Integer> positions = new ArrayList<>();
			for (int i : PathFinder.NEIGHBOURS8){
				positions.add(i);
			}
			Random.shuffle( positions );
			for (int i : positions){
				if (!thirdGrass && !secondGrass) return damage;
				if (!thirdGrass && secondGrass) secondGrass = false;
				if (thirdGrass) thirdGrass = false;
				plantGrass(defender.pos + i, lvl + 1);
			}
			
		}
		
		return damage;
	}
	
	private void plantGrass(int cell, int particleAmount){
		int c = Dungeon.level.map[cell];
		if ( c == Terrain.EMPTY || c == Terrain.EMPTY_DECO
				|| c == Terrain.EMBERS || c == Terrain.GRASS){
			Level.set(cell, Terrain.HIGH_GRASS);
			GameScene.updateMap(cell);
			CellEmitter.get( cell ).burst( LeafParticle.LEVEL_SPECIFIC, particleAmount );
		}
	}
	
	@Override
	public ItemSprite.Glowing glowing() {
		return DARK_GREEN;
	}
}
