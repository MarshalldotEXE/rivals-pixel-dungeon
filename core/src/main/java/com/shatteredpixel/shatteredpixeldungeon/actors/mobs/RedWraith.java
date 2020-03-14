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
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Grim;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Eye;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Shaman;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Warlock;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Yog;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfDisintegration;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFireblast;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLightning;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfMagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfWarding;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.LloydsBeacon;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFirebolt;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SpectralFire;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.DisintegrationTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GrimTrap;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RedWraithSprite;
import com.watabou.utils.Random;

public class RedWraith extends Mob {

	{
		spriteClass = RedWraithSprite.class;
		
		HP = HT = 1;
		defenseSkill = 120;
		
		EXP = 0;
		maxLvl = -2;
		
		flying = true;

		properties.add(Property.UNDEAD);
		properties.add(Property.INORGANIC);
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 14, 28 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 24;
	}
	
	{
		immunities.add( Shaman.LightningBolt.class );
		immunities.add( Warlock.DarkBolt.class );
		immunities.add( Eye.DeathGaze.class );
		immunities.add( Yog.BurningFist.DarkBolt.class );
		immunities.add( WandOfDisintegration.class );
		immunities.add( WandOfFireblast.class );
		immunities.add( WandOfFrost.class );
		immunities.add( WandOfLightning.class );
		immunities.add( WandOfMagicMissile.class );
		immunities.add( WandOfWarding.Ward.class );
		immunities.add( LloydsBeacon.class );
		immunities.add( WandOfFirebolt.class );
		immunities.add( DisintegrationTrap.class );
		immunities.add( GrimTrap.class );
		immunities.add( Grim.class );
		immunities.add( Terror.class );
		immunities.add( SpectralFire.class );
	}
}
