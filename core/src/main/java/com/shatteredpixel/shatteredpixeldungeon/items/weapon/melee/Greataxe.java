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

public class Greataxe extends MeleeWeapon {

	{
		image = ItemSpriteSheet.GREATAXE;

		tier = 4;
		DLY = 2f; //0.5x speed
	}

	@Override
	public int min(int lvl) {
		return  2*tier +  //8 base, up from 4
				2*lvl;    //+2 per level, up from +1
	}
	
	@Override
	public int max(int lvl) {
		return  Math.round(10*(tier+1.5f)) +    //55 base, up from 25
				lvl*Math.round(2f*(tier+0.5f));   //+9 per level, up from +4
	}

	@Override
	public int STRReq(int lvl) {
		lvl = Math.max(0, lvl);
		//17 base strength req, up from 16
		return (9 + tier * 2) - (int)(lvl);
	}

}