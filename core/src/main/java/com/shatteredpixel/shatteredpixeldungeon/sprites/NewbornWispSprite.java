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

package com.shatteredpixel.shatteredpixeldungeon.sprites;


import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.NewbornWisp;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SacrificialParticle;
import com.watabou.noosa.MovieClip;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class NewbornWispSprite extends MobSprite{

	public NewbornWispSprite() {
		super();

		texture( Assets.WISP );

		int ofs = 21;

		TextureFilm frames = new TextureFilm( texture, 12, 14 );

		idle = new MovieClip.Animation( 10, true );
		idle.frames( frames, ofs+0, ofs+1, ofs+2 );

		run = new MovieClip.Animation( 12, true );
		run.frames( frames, ofs+0, ofs+1, ofs+3 );

		attack = new MovieClip.Animation( 15, false );
		attack.frames( frames, ofs+4, ofs+5, ofs+6 );

		die = new MovieClip.Animation( 15, false );
		die.frames( frames, ofs+7, ofs+8, ofs+9, ofs+10, ofs+11, ofs+12 );

		play( idle );
	}

	public void zap( int cell ) {
		
		turnTo( ch.pos , cell );
		play( zap );

		MagicMissile.boltFromChar( parent,
				MagicMissile.SACRIFICE,
				this,
				cell,
				new Callback() {
					@Override
					public void call() {
						((NewbornWisp)ch).onZapComplete();
					}
				} );
		Sample.INSTANCE.play( Assets.SND_ZAP );
	}
	
	@Override
	public void link( Char ch ) {
		super.link( ch );
		add( CharSprite.State.SACRIFICE );
	}

	@Override
	public void die() {
		super.die();
		remove( CharSprite.State.SACRIFICE );
	}
	
	@Override
	public void onComplete( Animation anim ) {
		if (anim == zap) {
			idle();
		} else if (anim == die) {
			emitter().burst( SacrificialParticle.FACTORY, 4 );
		}
		super.onComplete( anim );
	}
	
	@Override
	public int blood() {
		return 0xFF33F657;
	}

}
