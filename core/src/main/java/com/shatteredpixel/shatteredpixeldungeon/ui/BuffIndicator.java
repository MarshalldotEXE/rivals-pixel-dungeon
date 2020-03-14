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

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndInfoBuff;
import com.watabou.gltextures.SmartTexture;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.Image;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.noosa.ui.Button;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BuffIndicator extends Component {
	
	//transparent icon
	public static final int NONE	= 71;
	
	public static final int BURN		= 0;
	public static final int SPECTRAL	= 1;
	public static final int SACRIFICE	= 2;
	public static final int CHILL		= 3;
	public static final int FREEZE		= 4;
	public static final int PARALYSIS	= 5;
	public static final int POISON		= 6;
	public static final int OOZE		= 7;
	public static final int BLEED		= 8;
	public static final int SLOW		= 9;
	public static final int CRIPPLE		= 10;
	public static final int ROOTS		= 11;
	public static final int WEAK		= 12;
	public static final int FRAIL		= 13;
	public static final int VERTIGO		= 14;
	
	public static final int LEVITATION	= 18;
	public static final int INVISIBLE	= 19;
	public static final int SHADOWMELD	= 20;
	public static final int VISION		= 21;
	public static final int BLIND		= 22;
	public static final int DROWSY		= 23;
	public static final int SLEEP		= 24;
	public static final int PURITY		= 25;
	public static final int TOXIC		= 26;
	public static final int HASTE		= 27;
	public static final int FUROR		= 28;
	public static final int CHALLENGE	= 29;
	public static final int TERROR		= 30;
	public static final int CHARM		= 31;
	public static final int RECHARGE	= 32;
	public static final int LIGHT		= 33;
	public static final int BLESS		= 34;
	public static final int REGEN		= 35;
	public static final int HEALTH		= 36;
	public static final int ARMOR		= 37;
	public static final int BARKSKIN	= 38;
	public static final int MAGIC_IMMUNE= 39;
	public static final int GUARD		= 40;
	
	public static final int FOOD_GREEN	= 54;
	public static final int FOOD_YELLOW	= 55;
	public static final int FOOD_ORANGE	= 56;
	public static final int FOOD_RED	= 57;
	public static final int KINETIC		= 58;
	public static final int NOVA		= 59;
	public static final int VISCOSITY	= 60;
	public static final int FORESIGHT	= 61;
	public static final int COMBO		= 62;
	public static final int RAGE		= 63;
	public static final int CORRUPT		= 64;
	public static final int MOMENTUM	= 65;
	public static final int PREPARATION	= 66;
	public static final int MARK		= 67;

	public static final int SIZE	= 7;
	
	private static BuffIndicator heroInstance;
	
	private SmartTexture texture;
	private TextureFilm film;
	
	private LinkedHashMap<Buff, BuffIcon> buffIcons = new LinkedHashMap<>();
	private boolean needsRefresh;
	private Char ch;
	
	public BuffIndicator( Char ch ) {
		super();
		
		this.ch = ch;
		if (ch == Dungeon.hero) {
			heroInstance = this;
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		if (this == heroInstance) {
			heroInstance = null;
		}
	}
	
	@Override
	protected void createChildren() {
		texture = TextureCache.get( Assets.BUFFS_SMALL );
		film = new TextureFilm( texture, SIZE, SIZE );
	}
	
	@Override
	public synchronized void update() {
		super.update();
		if (needsRefresh){
			needsRefresh = false;
			layout();
		}
	}
	
	@Override
	protected void layout() {
		
		ArrayList<Buff> newBuffs = new ArrayList<>();
		for (Buff buff : ch.buffs()) {
			if (buff.icon() != NONE) {
				newBuffs.add(buff);
			}
		}
		
		//remove any icons no longer present
		for (Buff buff : buffIcons.keySet().toArray(new Buff[0])){
			if (!newBuffs.contains(buff)){
				Image icon = buffIcons.get( buff ).icon;
				icon.origin.set( SIZE / 2 );
				add( icon );
				add( new AlphaTweener( icon, 0, 0.6f ) {
					@Override
					protected void updateValues( float progress ) {
						super.updateValues( progress );
						image.scale.set( 1 + 5 * progress );
					}
					
					@Override
					protected void onComplete() {
						image.killAndErase();
					}
				} );
				
				buffIcons.get( buff ).destroy();
				remove(buffIcons.get( buff ));
				buffIcons.remove( buff );
			}
		}
		
		//add new icons
		for (Buff buff : newBuffs) {
			if (!buffIcons.containsKey(buff)) {
				BuffIcon icon = new BuffIcon( buff );
				add(icon);
				buffIcons.put( buff, icon );
			}
		}
		
		//layout
		int pos = 0;
		for (BuffIcon icon : buffIcons.values()){
			icon.updateIcon();
			icon.setRect(x + pos * (SIZE + 2), y, 9, 12);
			pos++;
		}
	}

	private class BuffIcon extends Button {

		private Buff buff;

		public Image icon;

		public BuffIcon( Buff buff ){
			super();
			this.buff = buff;

			icon = new Image( texture );
			icon.frame( film.get( buff.icon() ) );
			add( icon );
		}
		
		public void updateIcon(){
			icon.frame( film.get( buff.icon() ) );
			buff.tintIcon(icon);
		}

		@Override
		protected void layout() {
			super.layout();
			icon.x = this.x+1;
			icon.y = this.y+2;
		}

		@Override
		protected void onClick() {
			if (buff.icon() != NONE)
				GameScene.show(new WndInfoBuff(buff));
		}
	}
	
	public static void refreshHero() {
		if (heroInstance != null) {
			heroInstance.needsRefresh = true;
		}
	}
}
