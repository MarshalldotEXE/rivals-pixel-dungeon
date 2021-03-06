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

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class SmallShield extends MeleeWeapon {

	{
		image = ItemSpriteSheet.SMALL_SHIELD;

		tier = 1;
	}

	@Override
	public int max(int lvl) {
		return  Math.round(3f*(tier+1)) +     //6 base, down from 10
				lvl*Math.round(0.5f*(tier));  //+0.5 per level, down from +1
	}

	@Override
	public int defenseFactor( Char owner ) {
		return (int)(4+Math.round(0.5*level()));    //4 extra defence, plus 0.5 per level;
	}
	
	public String statsInfo(){
		if (isIdentified()){
			return Messages.get(this, "stats_desc", 4+Math.round(0.5*level()));
		} else {
			return Messages.get(this, "typical_stats_desc", 4);
		}
	}
}