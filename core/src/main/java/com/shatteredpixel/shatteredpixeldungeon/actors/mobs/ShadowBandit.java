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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bleeding;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShadowBanditSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class ShadowBandit extends Mob {
	
	{
		spriteClass = ShadowBanditSprite.class;
		
		HP = HT = 60;
		defenseSkill = 23;
		
		EXP = 12;
		maxLvl = 16;
		
		flying = true;

		properties.add(Property.DEMONIC);
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 6, 24 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 22;
	}
	
	@Override
	protected float attackDelay() {
		return super.attackDelay()*0.5f;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 3);
	}
	
	private int banditCombo = 0;
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		if (Random.Int( 5 ) <= banditCombo) {
			Buff.prolong( enemy, Blindness.class, Random.Int( 1 + banditCombo, 2 + banditCombo ));
			Buff.affect( enemy, Bleeding.class ).set( damage + banditCombo );
		}
		banditCombo++;
		
		return damage;
	}
	
	{
		immunities.add( Amok.class );
		immunities.add( Blindness.class );
		immunities.add( Charm.class );
		immunities.add( Cripple.class );
		immunities.add( Sleep.class );
		immunities.add( Terror.class );
		immunities.add( Vertigo.class );
	}

	private static String BANDITCOMBO = "banditCombo";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(BANDITCOMBO, banditCombo);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		banditCombo = bundle.getInt(BANDITCOMBO);
	}
}
