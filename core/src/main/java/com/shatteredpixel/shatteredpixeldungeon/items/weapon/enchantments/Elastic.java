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

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.Random;

public class Elastic extends Weapon.Enchantment {
	
	private static ItemSprite.Glowing PINK = new ItemSprite.Glowing( 0xFF00FF );
	
	@Override
	public int proc(Weapon weapon, Char attacker, Char defender, int damage ) {
		
		int lvl = Math.max( 0, weapon.level() );
		
		float chanceA = (lvl + damage) * ONE_PERCENT;
		float chanceB = (lvl + 1) * LEVEL_SCALING;
		
		//A% chance to knockback defender 2 tiles
		//B% chance to knockback 1 extra
		if (Random.Float() < chanceA) {
			//trace a ballistica to our target (which will also extend past them)
			Ballistica trajectory = new Ballistica(attacker.pos, defender.pos, Ballistica.STOP_TARGET);
			//trim it to just be the part that goes past them
			trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
			//knock them back along that ballistica
			
			int knockback = 2;
			if (Random.Float() < chanceB) {
				knockback++;
			}
			
			WandOfBlastWave.throwChar(defender, trajectory, knockback);
			
		}
		
		return damage;
	}
	
	@Override
	public ItemSprite.Glowing glowing() {
		return PINK;
	}

}
