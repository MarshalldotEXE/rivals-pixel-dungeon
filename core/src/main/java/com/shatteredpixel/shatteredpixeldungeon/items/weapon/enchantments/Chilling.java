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
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.utils.Random;

public class Chilling extends Weapon.Enchantment {

	private static ItemSprite.Glowing TEAL = new ItemSprite.Glowing( 0x0099FF );
	
	@Override
	public int proc( Weapon weapon, Char attacker, Char defender, int damage ) {
		
		int lvl = Math.max( 0, weapon.level() );
		
		float chanceA = (lvl + damage) * ONE_PERCENT;
		float chanceB = (lvl + 1) * LEVEL_SCALING;
		
		//A% chance to add 2 turns of chilling
		//B% chance to add 1 extra
		if (Random.Float() < chanceA) {
			
			float duration = 2f;
			if (Random.Float() < chanceB) {
				duration++;
			}
			
			Chill existing = defender.buff(Chill.class);
			if (existing != null){
				duration += existing.cooldown();
			}
			
			Buff.affect( defender, Chill.class, duration );
			Splash.at( defender.sprite.center(), 0xFFB2D6FF, lvl + 1 );

		}

		return damage;
	}
	
	@Override
	public Glowing glowing() {
		return TEAL;
	}

}
