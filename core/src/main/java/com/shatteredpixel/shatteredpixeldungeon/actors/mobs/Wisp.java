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
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SpectralFire;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFirebolt;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.WispSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Random;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class Wisp extends Mob implements Callback {

	private static final float TIME_TO_ZAP	= 1f;

	{
		spriteClass = WispSprite.class;
		
		HP = HT = 80;
		defenseSkill = 22;
		
		EXP = 15;
		maxLvl = 20;
		
		flying = true;
		
		properties.add(Property.FIERY);
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 12, 22 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 27;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 9);
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {
		Ballistica attack = new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT);
		return Dungeon.level.distance(pos, enemy.pos) <= 2 && attack.collisionPos == enemy.pos;
	}
	
	//used so resistances can differentiate between melee and magical attacks
	public static class SpectralFireBolt{}
	
	@Override
	protected boolean doAttack( Char enemy ) {

		if (Dungeon.level.distance( pos, enemy.pos ) <= 1) {
			
			return super.doAttack( enemy );
			
		} else if (Dungeon.level.distance( pos, enemy.pos ) <= 2) {
			
			boolean visible = fieldOfView[pos] || fieldOfView[enemy.pos];
			if (visible) {
				sprite.zap( enemy.pos );
			} else {
				zap();
			}
			
			return !visible;
		} else
			return super.canAttack(enemy);
	}
	
	private void zap() {
		spend( TIME_TO_ZAP );
		
		if (hit( this, enemy, true )) {
			int dmg = Random.NormalIntRange(10, 20);
			
			if (Random.Int( 4 ) == 0) {
				Buff.affect( enemy, SpectralFire.class ).reignite( enemy );
			}
			
			enemy.damage( dmg, new SpectralFireBolt() );
				
			if (!enemy.isAlive() && enemy == Dungeon.hero) {
				Dungeon.fail( getClass() );
				GLog.n( Messages.get(this, "spectral_kill") );
			}
		} else {
			enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
		}
	}
	
	public void onZapComplete() {
		zap();
		next();
	}
	
	@Override
	public void call() {
		next();
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		if (Random.Int( 2 ) == 0) {
			Buff.affect( enemy, SpectralFire.class ).reignite( enemy );
		}
		
		return damage;
	}
	
	{
		resistances.add( WandOfFirebolt.class );
		immunities.add( SpectralFire.class );
	}
	
}
