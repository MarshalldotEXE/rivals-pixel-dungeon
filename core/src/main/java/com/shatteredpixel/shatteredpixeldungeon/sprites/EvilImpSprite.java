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
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.EvilImp;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ElmoParticle;
import com.watabou.noosa.TextureFilm;

public class EvilImpSprite extends MobSprite {
	
	public EvilImpSprite() {
		super();
		
		texture( Assets.IMP );
		
		int ofs = 10;
		
		TextureFilm frames = new TextureFilm( texture, 12, 14 );
		
		idle = new Animation( 10, true );
		idle.frames( frames,
			0+ofs, 1+ofs, 2+ofs, 3+ofs, 0+ofs, 1+ofs, 2+ofs, 3+ofs, 0+ofs, 0+ofs, 0+ofs, 4+ofs, 4+ofs, 0+ofs, 0+ofs, 4+ofs, 4+ofs, 0+ofs, 0+ofs, 4+ofs, 4+ofs,
			0+ofs, 1+ofs, 2+ofs, 3+ofs, 0+ofs, 1+ofs, 2+ofs, 3+ofs, 0+ofs, 1+ofs, 3+ofs, 0+ofs, 0+ofs, 0+ofs, 4+ofs, 4+ofs, 0+ofs, 0+ofs, 0+ofs, 0+ofs, 4+ofs, 4+ofs, 0+ofs, 0+ofs, 0+ofs, 4+ofs, 4+ofs, 0+ofs, 0+ofs, 0+ofs, 0+ofs, 4+ofs, 4+ofs
		);
		
		run = new Animation( 10, true );
		run.frames( frames,
			0+ofs, 1+ofs, 2+ofs, 3+ofs, 0+ofs, 1+ofs, 2+ofs, 3+ofs, 0+ofs, 0+ofs, 0+ofs,
			0+ofs, 1+ofs, 2+ofs, 3+ofs, 0+ofs, 1+ofs, 2+ofs, 3+ofs, 0+ofs, 1+ofs, 3+ofs, 0+ofs, 0+ofs, 0+ofs
		);
		
		attack = new Animation( 15, false );
		attack.frames( frames, 5+ofs, 6+ofs, 7+ofs, 8+ofs );
		
		die = new Animation( 10, false );
		die.frames( frames, 9+ofs );
		
		play( idle );
	}
	
	@Override
	public void link( Char ch ) {
		super.link( ch );
		
		if (ch instanceof EvilImp) {
			alpha( 0.8f );
		}
	}
	
	@Override
	public void play(Animation anim) {
		if (anim == attack) {
			
			centerEmitter().start( ElmoParticle.FACTORY, 0.2f, 3 );
			
		}
		super.play(anim);
	}
	
	@Override
	public void die() {
		super.die();
		emitter().burst( Speck.factory( Speck.WOOL ), 15 );
	}
}
