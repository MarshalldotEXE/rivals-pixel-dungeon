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

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class MailArmor extends Armor {

	{
		image = ItemSpriteSheet.ARMOR_MAIL;
		
		EVA = 1.2f; //20% boost to evasion
	}
	
	public MailArmor() {
		super( 2 );
	}
	
	@Override
	public int DRMax(int lvl) {
		return Math.round(0.8f * (tier * 2)) +  //3.2 base, down from 4
			   Math.round(0.8f * (tier * lvl)); //+1.6 per level, down from +2
	}
	
	@Override
	public boolean doEquip( Hero hero ) {
		Statistics.armorEquipped = true;
		
		return super.doEquip(hero);
	}
}
