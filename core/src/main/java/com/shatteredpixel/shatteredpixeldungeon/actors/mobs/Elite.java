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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.sprites.EliteSprite;
import com.watabou.utils.Random;

public class Elite extends Tribesman {
	
	{
		spriteClass = EliteSprite.class;
		
		HP = HT = 24;
		
		EXP = 6;
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 7, 19 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 24;
	}
	
	@Override
	protected float attackDelay() {
		return super.attackDelay() * 2f;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 4);
	}
	
	{
		immunities.add( Terror.class );
		immunities.add( Sleep.class );
	}
}
