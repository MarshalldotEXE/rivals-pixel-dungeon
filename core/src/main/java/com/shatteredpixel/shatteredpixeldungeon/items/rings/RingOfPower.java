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

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;

import java.text.DecimalFormat;

public class RingOfPower extends Ring {
	
	public String statsInfo() {
		if (isIdentified()){
			return Messages.get(this, "stats", soloBonus());
		} else {
			return Messages.get(this, "typical_stats", 1);
		}
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Power();
	}
	
	public static int levelDamageBonus( Char target ){
		return getBonus(target, RingOfPower.Power.class);
	}

	public class Power extends RingBuff {
	}
}