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

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Spear extends MeleeWeapon {

	{
		image = ItemSpriteSheet.SPEAR;

		tier = 2;
		DLY = 1.5f; //0.67x speed
		RCH = 2;    //extra reach
	}

	@Override
	public int min(int lvl) {
		return  Math.round(1.5f*tier) +  //3 base, up from 2
				Math.round(1.5f*lvl);    //+1.5 per level, up from +1
	}
	
	@Override
	public int max(int lvl) {
		return  Math.round(6.67f*(tier+1)) +    //20 base, up from 15
				lvl*Math.round(1.33f*(tier)); //+3 per level, up from +2
	}

}
