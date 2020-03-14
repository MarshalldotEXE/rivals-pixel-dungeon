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

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.rivals.Rival;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class RivalSprite extends MobSprite {

	public RivalSprite() {
		super();
		
		int ofs = 21 * 9;
		
		texture( Assets.ROGUE );

		TextureFilm frames = new TextureFilm( texture, 12, 15 );

		idle = new Animation( 1, true );
		idle.frames( frames, 0+ofs, 0+ofs, 0+ofs, 1+ofs, 0+ofs, 0+ofs, 1+ofs, 1+ofs );
		
		run = new Animation( 20, true );
		run.frames( frames, 2+ofs, 3+ofs, 4+ofs, 5+ofs, 6+ofs, 7+ofs );
		
		attack = new Animation( 15, false );
		attack.frames( frames, 13+ofs, 14+ofs, 15+ofs, 0+ofs );
		
		zap = attack.clone();
		
		toss = attack.clone();
		
		die = new Animation( 20, false );
		die.frames( frames, 8+ofs, 9+ofs, 10+ofs, 11+ofs, 12+ofs, 11+ofs );

		play( idle );
	}
	
	public void zap( int cell ) {
		
		turnTo( ch.pos , cell );
		play( zap );
		
		final Ballistica shot = new Ballistica( ch.pos, cell, ((Rival)ch).wand.collisionProperties);
		
		((Rival)ch).wand.fx(shot, ch, new Callback() {
			public void call() {
				((Rival)ch).onZapComplete();
			}
		});
	}
	
	public void toss( int cell ) {
		
		turnTo( ch.pos , cell );
		play( toss );
		
		((MissileSprite)parent.recycle( MissileSprite.class )).
		reset(ch.pos, cell, ((Rival)ch).missile, new Callback() {
			public void call() {
				((Rival)ch).onTossComplete();
			}
		});
	}
	
	@Override
	public void onComplete( Animation anim ) {
		if (anim == zap || anim == toss) {
			idle();
		}
		super.onComplete( anim );
	}
}
