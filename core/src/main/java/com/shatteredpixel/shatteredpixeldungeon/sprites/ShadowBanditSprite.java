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
import com.watabou.noosa.TextureFilm;

public class ShadowBanditSprite extends MobSprite {
	
	public ShadowBanditSprite() {
		super();
		
		texture( Assets.SHADOWBANDIT );
		TextureFilm film = new TextureFilm( texture, 12, 14 );
		
		idle = new Animation( 1, true );
		idle.frames( film, 0, 0, 1, 2, 0, 0, 0, 1, 2 );
		
		run = new Animation( 15, true );
		run.frames( film, 0, 0, 3, 4, 4, 5 );
		
		die = new Animation( 10, false );
		die.frames( film, 6, 7, 8, 9, 10, 11 );
		
		attack = new Animation( 12, false );
		attack.frames( film, 12, 13, 14, 15, 0 );
		
		idle();
	}
	
	@Override
	public void link( Char ch ) {
		super.link( ch );
		add( CharSprite.State.LEVITATING );
	}
	
	@Override
	public void die() {
		super.die();
		remove( CharSprite.State.LEVITATING );
	}
	
	@Override
	public int blood() {
		return 0xFF000000;
	}
}
