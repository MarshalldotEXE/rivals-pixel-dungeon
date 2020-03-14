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

import com.watabou.utils.Bundle;

public class Statistics {
	
	public static int goldCollected;
	public static int deepestFloor;
	public static int enemiesSlain;
	public static int foodEaten;
	public static int itemsBought;
	public static int itemsCrafted;
	
	//used for challenge badges
	public static boolean healingUsed;
	public static boolean upgradeUsed;
	public static boolean armorEquipped;
	
	public static float duration;
	
	public static boolean amuletObtained = false;
	
	public static void reset() {
		
		goldCollected	= 0;
		deepestFloor	= 0;
		enemiesSlain	= 0;
		foodEaten		= 0;
		itemsBought		= 0;
		itemsCrafted	= 0;
		
		healingUsed = false;
		upgradeUsed = false;
		armorEquipped = false;
		
		duration		= 0;
		
		amuletObtained = false;
		
	}
	
	private static final String GOLD		= "score";
	private static final String DEEPEST		= "maxDepth";
	private static final String SLAIN		= "enemiesSlain";
	private static final String FOOD		= "foodEaten";
	private static final String MERCHANT	= "itemsBought";
	private static final String ALCHEMY		= "itemsCrafted";
	
	private static final String HEALING		= "healingUsed";
	private static final String UPGRADE		= "upgradeUsed";
	private static final String ARMOR		= "armorEquipped";
	
	private static final String DURATION	= "duration";
	
	private static final String AMULET		= "amuletObtained";
	
	public static void storeInBundle( Bundle bundle ) {
		bundle.put( GOLD,		goldCollected );
		bundle.put( DEEPEST,	deepestFloor );
		bundle.put( SLAIN,		enemiesSlain );
		bundle.put( FOOD,		foodEaten );
		bundle.put( MERCHANT,	itemsBought );
		bundle.put( ALCHEMY,	itemsCrafted );
		
		bundle.put( HEALING,	healingUsed );
		bundle.put( UPGRADE,	upgradeUsed );
		bundle.put( ARMOR,		armorEquipped );
		
		bundle.put( DURATION,	duration );
		
		bundle.put( AMULET,		amuletObtained );
	}
	
	public static void restoreFromBundle( Bundle bundle ) {
		goldCollected	= bundle.getInt( GOLD );
		deepestFloor	= bundle.getInt( DEEPEST );
		enemiesSlain	= bundle.getInt( SLAIN );
		foodEaten		= bundle.getInt( FOOD );
		itemsBought		= bundle.getInt( MERCHANT );
		itemsCrafted	= bundle.getInt( ALCHEMY );
		
		healingUsed		= bundle.getBoolean( HEALING );
		upgradeUsed		= bundle.getBoolean( UPGRADE );
		armorEquipped	= bundle.getBoolean( ARMOR );
		
		duration		= bundle.getFloat( DURATION );
		
		amuletObtained	= bundle.getBoolean( AMULET );
	}
	
	public static void preview( GamesInProgress.Info info, Bundle bundle ){
		info.goldCollected  = bundle.getInt( GOLD );
		info.maxDepth       = bundle.getInt( DEEPEST );
	}

}
