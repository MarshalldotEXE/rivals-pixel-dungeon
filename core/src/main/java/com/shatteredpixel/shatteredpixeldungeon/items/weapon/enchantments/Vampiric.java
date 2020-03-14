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
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.utils.Random;

public class Vampiric extends Weapon.Enchantment {

	private static ItemSprite.Glowing RED = new ItemSprite.Glowing( 0x660033 );
	
	@Override
	public int proc( Weapon weapon, Char attacker, Char defender, int damage ) {
		
		int lvl = Math.max( 0, weapon.level() );
		
		float chanceA = (lvl + 1) * LEVEL_SCALING;
		float numberB = (attacker.HT - attacker.HP) / (float)attacker.HT;
		
		//A% chance to heal attacker
		//B% of damage = heal amount
		if (Random.Float() < chanceA){
			
			int healAmount = Math.round(damage * numberB);
			healAmount = Math.min( healAmount, attacker.HT - attacker.HP );
			
			if (healAmount > 0 && attacker.isAlive()) {
				
				attacker.HP += healAmount;
				attacker.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
				attacker.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmount ) );
				
			}
		}

		return damage;
	}
	
	@Override
	public Glowing glowing() {
		return RED;
	}
}
