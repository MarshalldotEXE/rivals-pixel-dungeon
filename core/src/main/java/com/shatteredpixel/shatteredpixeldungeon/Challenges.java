/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
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

package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.items.Dewdrop;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Blandfruit;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.items.food.SmallRation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;

public class Challenges {

	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int BARREN_LAND			= 1;
	public static final int SWARM_INTELLIGENCE	= 2;
	public static final int INTO_DARKNESS		= 4;

	public static final int MAX_VALUE           = 127;

	public static final String[] NAME_IDS = {
			"barren_land",
			"swarm_intelligence",
			"into_darkness"
	};

	public static final int[] MASKS = {
			BARREN_LAND, SWARM_INTELLIGENCE, INTO_DARKNESS
	};

	public static boolean isItemBlocked( Item item ){
		
		if (Dungeon.isChallenged(BARREN_LAND)){
			if (item instanceof Dewdrop) {
				return true;
			}
		}
		
		return false;
	}

}