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

package com.shatteredpixel.shatteredpixeldungeon.items.bombs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ParalyticGas;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfElectricity;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.AlchemicalCatalyst;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class ParalyticBomb extends Bomb {
	
	{
		image = ItemSpriteSheet.PIPE_GOLDEN;
	}
	
	@Override
	public void explode(int cell) {
		super.explode(cell);
		
		GameScene.add( Blob.seed( cell, 1000, ParalyticGas.class ) );
		for (int i : PathFinder.NEIGHBOURS9) {
			GameScene.add(Blob.seed(cell+i, 10, Electricity.class));
		}
		Sample.INSTANCE.play(Assets.SND_LIGHTNING);
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
					} else if (ingredient instanceof PotionOfElectricity) {
						main = true;
					} else if (ingredient instanceof AlchemicalCatalyst) {
						material = true;
					}
				}
			}
			
			return bomb && main && material;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 6;
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
			return new ParalyticBomb();
		}
	}
}
