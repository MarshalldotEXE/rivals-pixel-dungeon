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

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.BlastParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.MetalShard;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ShadowCaster;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ShrapnelBomb extends Bomb {
	
	{
		image = ItemSpriteSheet.SHRAPNEL_BOMB;
	}
	
	@Override
	public boolean explodesDestructively() {
		return false;
	}
	
	@Override
	public void explode(int cell) {
		super.explode(cell);
		
		boolean[] FOV = new boolean[Dungeon.level.length()];
		Point c = Dungeon.level.cellToPoint(cell);
		ShadowCaster.castShadow(c.x, c.y, FOV, Dungeon.level.losBlocking, 8);
		
		ArrayList<Char> affected = new ArrayList<>();
		
		for (int i = 0; i < FOV.length; i++) {
			if (FOV[i]) {
				if (Dungeon.level.heroFOV[i] && !Dungeon.level.solid[i]) {
					//TODO better vfx?
					CellEmitter.center( i ).burst( BlastParticle.FACTORY, 5 );
				}
				Char ch = Actor.findChar(i);
				if (ch != null){
					affected.add(ch);
				}
			}
		}
		
		for (Char ch : affected){
			//regular bomb damage, which falls off at a rate of 5% per tile of distance
			int damage = Math.round(Random.NormalIntRange( Dungeon.depth+5, 10 + Dungeon.depth * 2 ));
			damage = Math.round(damage * (1f - .05f*Dungeon.level.distance(cell, ch.pos)));
			damage -= ch.drRoll();
			ch.damage(damage, this);
			if (ch == Dungeon.hero && !ch.isAlive()) {
				Dungeon.fail(Bomb.class);
			}
		}
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
					} else if (ingredient instanceof ScrollOfRetribution
							|| ingredient instanceof ScrollOfPsionicBlast) {
						main = true;
					} else if (ingredient instanceof MetalShard) {
						material = true;
					}
				}
			}
			
			return bomb && main && material;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 8;
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
			return new ShrapnelBomb();
		}
	}
}
