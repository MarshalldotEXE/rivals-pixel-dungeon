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

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SpectralFire;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.sprites.EvilImpSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class EvilImp extends Mob {
	
	private boolean fear = false;
	
	{
		spriteClass = EvilImpSprite.class;
		
		HP = HT = 60;
		defenseSkill = 24;
		baseSpeed = 2f;
		
		EXP = 11;
		maxLvl = 20;
		
		flying = true;
		
		properties.add(Property.DEMONIC);
	}
	
	@Override
	public int damageRoll() {
		return (fear) ? Random.NormalIntRange( 7, 17 ) : Random.NormalIntRange( 14, 21 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return (fear) ? 21 : 25;
	}
	
	@Override
	public int drRoll() {
		return (fear) ? 0 : Random.NormalIntRange(0, 8);
	}
	
	/*@Override
	public boolean act() {
		if (enemy != null) {
			for (Mob mob : Dungeon.level.mobs) {
				if (mob instanceof EvilImp && mob.state != mob.HUNTING) {
					mob.beckon( enemy.pos );
				} else if (Dungeon.level.distance(pos, mob.pos) <= 8 && mob.state != mob.HUNTING) {
					mob.beckon( enemy.pos );
				}
			}
		}
		return super.act();
	}*/
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		switch(Random.Int( 20 )) {
			case 0:
				Buff.prolong( enemy, Blindness.class, 1.1f );
				break;
			case 1:
				Buff.prolong( enemy, Cripple.class, 1.1f );
				break;
			case 2:
				Buff.prolong( enemy, Paralysis.class, 1.1f );
				break;
			case 3:
				Buff.prolong( enemy, Slow.class, 1.1f );
				break;
			case 4:
				Buff.prolong( enemy, Weakness.class, 1.1f );
				break;
		}
		switch(Random.Int( 20 )) {
			case 0:
				Buff.prolong( enemy, Blindness.class, 1.1f );
				break;
			case 1:
				Buff.prolong( enemy, Cripple.class, 1.1f );
				break;
			case 2:
				Buff.prolong( enemy, Paralysis.class, 1.1f );
				break;
			case 3:
				Buff.prolong( enemy, Slow.class, 1.1f );
				break;
			case 4:
				Buff.prolong( enemy, Weakness.class, 1.1f );
				break;
		}
		return damage;
	}
	
	@Override
	public void damage(int dmg, Object src) {
		fear = (HP*4 <= HT);
		super.damage(dmg, src);
		if (enemy != null) {
			for (Mob mob : Dungeon.level.mobs) {
				if (mob instanceof EvilImp && mob.state != mob.HUNTING) {
					mob.beckon( enemy.pos );
				} else if (Dungeon.level.distance(pos, mob.pos) <= 8 && mob.state != mob.HUNTING) {
					mob.beckon( enemy.pos );
				}
			}
		}
		if ((HP*4 <= HT) && !fear) {
			state = FLEEING;
		}
	}
	
	private static final String FEAR = "fear";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( FEAR, fear );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		fear = bundle.getBoolean( FEAR );
	}
	
}
