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
import com.watabou.noosa.TextureFilm;

public class EbonyMimicSprite extends MobSprite {
	
	public EbonyMimicSprite() {
		super();
		
		texture( Assets.MIMIC );
		
		int ofs = 48;
		
		TextureFilm frames = new TextureFilm( texture, 16, 16 );
		
		idle = new Animation( 5, true );
		idle.frames( frames, ofs+0, ofs+0, ofs+0, ofs+1, ofs+1 );
		
		run = new Animation( 10, true );
		run.frames( frames, ofs+0, ofs+1, ofs+2, ofs+3, ofs+3, ofs+2, ofs+1 );
		
		attack = new Animation( 10, false );
		attack.frames( frames, ofs+0, ofs+4, ofs+5, ofs+6 );
		
		die = new Animation( 5, false );
		die.frames( frames, ofs+7, ofs+8, ofs+9 );
		
		play( idle );
	}
	
	@Override
	public int blood() {
		return 0xFF666666;
	}
}
