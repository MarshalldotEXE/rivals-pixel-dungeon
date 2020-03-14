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

package com.shatteredpixel.shatteredpixeldungeon.items.bombs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPassage;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.ArcaneCatalyst;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.BArray;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class DisplacingBomb extends Bomb {
	
	{
		image = ItemSpriteSheet.BOMB_MAGENTA;
	}
	
	@Override
	public void explode(int cell) {
		super.explode(cell);
		
		for (int i : PathFinder.NEIGHBOURS9) {
			if (!Dungeon.level.solid[cell+i]) {
				Char ch = Actor.findChar(cell+i);
				
				if (ch != null) {
					
					if (ch instanceof Hero) {
						ScrollOfTeleportation.teleportHero((Hero) ch);
					} else {
						int count = 10;
						int pos;
						do {
							pos = Dungeon.level.randomRespawnCell();
							if (count-- <= 0) {
								break;
							}
						} while (pos == -1);
						
						if (!(pos == -1 && Dungeon.bossLevel())) {
							
							ch.pos = pos;
							
							ch.sprite.place(ch.pos);
							ch.sprite.visible = Dungeon.level.heroFOV[pos];
						}
					}
				}
				
				CellEmitter.get(cell+i).start(Speck.factory(Speck.LIGHT), 0.2f, 3);
			}
		}
		Sample.INSTANCE.play(Assets.SND_TELEPORT);
	}
	
	@Override
	public int price() {
		return 25 * quantity;
	}
	
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {
		
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			boolean bomb = false;
			boolean main = false;
			boolean material = false;
			
			for (Item ingredient : ingredients){
				if (ingredient.quantity() > 0) {
					if (ingredient.getClass().equals(Bomb.class)) {
						bomb = true;
					} else if (ingredient instanceof ScrollOfTeleportation
							|| ingredient instanceof ScrollOfPassage) {
						main = true;
					} else if (ingredient instanceof ArcaneCatalyst) {
						material = true;
					}
				}
			}
			
			return bomb && main && material;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 7;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			for (Item ingredient : ingredients){
				ingredient.quantity(ingredient.quantity() - 1);
			}
			
			Statistics.itemsCrafted++;
			
			return sampleOutput(null);
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return new DisplacingBomb();
		}
	}
}
