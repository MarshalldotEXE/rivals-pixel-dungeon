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

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Shopkeeper;
import com.shatteredpixel.shatteredpixeldungeon.items.Ankh;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Honeypot;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.MerchantsBeacon;
import com.shatteredpixel.shatteredpixeldungeon.items.Stylus;
import com.shatteredpixel.shatteredpixeldungeon.items.Torch;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.VelvetPouch;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.food.SmallRation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfShielding;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.Dart;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.AlchemicalCatalyst;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.ArcaneCatalyst;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.RosePetal;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.MagicSandBag;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.GooBlob;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.MetalShard;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ShopRoom extends SpecialRoom {

	private ArrayList<Item> itemsToSpawn;
	
	@Override
	public int minWidth() {
		if (itemsToSpawn == null) itemsToSpawn = generateItems();
		return Math.max(7, (int)(Math.sqrt(itemsToSpawn.size())+3));
	}
	
	@Override
	public int minHeight() {
		if (itemsToSpawn == null) itemsToSpawn = generateItems();
		return Math.max(7, (int)(Math.sqrt(itemsToSpawn.size())+3));
	}
	
	public void paint( Level level ) {
		
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY_SP );

		placeShopkeeper( level );

		placeItems( level );
		
		for (Door door : connected.values()) {
			door.set( Door.Type.REGULAR );
		}

	}

	protected void placeShopkeeper( Level level ) {

		int pos = level.pointToCell(center());

		Mob shopkeeper = new Shopkeeper();
		shopkeeper.pos = pos;
		level.mobs.add( shopkeeper );

	}

	protected void placeItems( Level level ){

		if (itemsToSpawn == null)
			itemsToSpawn = generateItems();

		Point itemPlacement = new Point(entrance());
		if (itemPlacement.y == top){
			itemPlacement.y++;
		} else if (itemPlacement.y == bottom) {
			itemPlacement.y--;
		} else if (itemPlacement.x == left){
			itemPlacement.x++;
		} else {
			itemPlacement.x--;
		}

		for (Item item : itemsToSpawn) {

			if (itemPlacement.x == left+1 && itemPlacement.y != top+1){
				itemPlacement.y--;
			} else if (itemPlacement.y == top+1 && itemPlacement.x != right-1){
				itemPlacement.x++;
			} else if (itemPlacement.x == right-1 && itemPlacement.y != bottom-1){
				itemPlacement.y++;
			} else {
				itemPlacement.x--;
			}

			int cell = level.pointToCell(itemPlacement);

			if (level.heaps.get( cell ) != null) {
				do {
					cell = level.pointToCell(random());
				} while (level.heaps.get( cell ) != null || level.findMob( cell ) != null);
			}

			level.drop( item, cell ).type = Heap.Type.FOR_SALE;
		}

	}
	
	protected static ArrayList<Item> generateItems() {

		ArrayList<Item> itemsToSpawn = new ArrayList<>();
		
		//weapon
		Item weapon;
		switch(Dungeon.depth) {
			case 5:
				weapon = Generator.random( Generator.Category.WEP_T2 );
				break;
			case 9:
				weapon = Generator.random( Generator.Category.WEP_T3 );
				break;
			case 13:
				weapon = Generator.random( Generator.Category.WEP_T4 );
				break;
			case 17: default:
				weapon = Generator.random( Generator.Category.WEP_T4 );
				break;
		}
		//imp items are always at least +1
		if (Dungeon.depth == 17) {
			weapon.level( Math.max( 1, weapon.level() ) );
		}
		if (weapon.cursed) {
			((Weapon)weapon).enchant(null);
			weapon.cursed = false;
		}
		weapon.identify();
		itemsToSpawn.add( weapon );
		
		//armor
		Item armor;
		switch(Dungeon.depth) {
			case 5:
				armor = Generator.random( Generator.Category.ARMOR_T2 );
				break;
			case 9:
				armor = Generator.random( Generator.Category.ARMOR_T3 );
				break;
			case 13:
				armor = Generator.random( Generator.Category.ARMOR_T4 );
				break;
			case 17: default:
				armor = Generator.random( Generator.Category.ARMOR_T4 );
				break;
		}
		//imp items are always at least +1
		if (Dungeon.depth == 17) {
			armor.level( Math.max( 1, armor.level() ) );
		}
		if (armor.cursed) {
			((Armor)armor).inscribe(null);
			armor.cursed = false;
		}
		armor.identify();
		itemsToSpawn.add( armor );
		
		//missile
		Item missile;
		switch(Dungeon.depth) {
			case 5:
				missile = Generator.random( Generator.Category.MIS_T2 );
				break;
			case 9:
				missile = Generator.random( Generator.Category.MIS_T3 );
				break;
			case 13:
				missile = Generator.random( Generator.Category.MIS_T4 );
				break;
			case 17: default:
				missile = Generator.random( Generator.Category.MIS_T4 );
				break;
		}
		//imp items are always at least +1
		//only upgrade missile weapons that are in quantities of 1 or 2
		if (Dungeon.depth == 17 && missile.quantity() <= 2) {
			missile.level( Math.max( 1, missile.level() ) );
		}
		itemsToSpawn.add( missile );
		
		//wand
		Item wand;
		wand = Generator.random( Generator.Category.WAND );
		//imp items are always at least +1
		if (Dungeon.depth == 17) {
			wand.level( Math.max( 1, wand.level() ) );
		}
		wand.cursed = false;
		wand.identify();
		itemsToSpawn.add( wand );
		
		//ring
		Item ring;
		ring = Generator.random( Generator.Category.RING );
		//imp items are always at least +1
		if (Dungeon.depth == 17) {
			ring.level( Math.max( 1, ring.level() ) );
		}
		ring.cursed = false;
		ring.identify();
		itemsToSpawn.add( ring );
		
		//artifact
		Item artifact;
		artifact = Generator.random( Generator.Category.ARTIFACT );
		artifact.cursed = false;
		artifact.identify();
		itemsToSpawn.add( artifact );
		
		//depth-specific
		switch(Dungeon.depth) {
			case 5:
				itemsToSpawn.add( ChooseBag( Dungeon.hero.belongings ) );
				break;
			case 9:
				itemsToSpawn.add( ChooseBag( Dungeon.hero.belongings ) );
				break;
			case 13:
				Dungeon.LimitedDrops.MAGICAL_HOLSTER.drop();
				itemsToSpawn.add( new MagicalHolster() );
				break;
			case 17:
				itemsToSpawn.add( new Torch() );
				itemsToSpawn.add( new Torch() );
				break;
		}
		
		itemsToSpawn.add( new Dart().quantity(2) );
		itemsToSpawn.add( new Bomb.DoubleBomb() );

		itemsToSpawn.add( new PotionOfHealing() );
		itemsToSpawn.add( new PotionOfShielding() );
		itemsToSpawn.add( Generator.random( Generator.Category.POTION ) );

		itemsToSpawn.add( new ScrollOfIdentify() );
		itemsToSpawn.add( new ScrollOfRemoveCurse() );
		itemsToSpawn.add( Generator.random( Generator.Category.SCROLL ) );

		itemsToSpawn.add( Generator.random( Generator.Category.FOOD ) );
		
		itemsToSpawn.add( new Torch() );
		itemsToSpawn.add( new Honeypot() );

		itemsToSpawn.add( new AlchemicalCatalyst() );
		itemsToSpawn.add( new ArcaneCatalyst() );
		
		switch (Random.Int(4)){
			case 0:
				itemsToSpawn.add( new RosePetal() );
				break;
			case 1:
				itemsToSpawn.add( new MagicSandBag() );
				break;
			case 2:
				itemsToSpawn.add( new GooBlob() );
				break;
			case 3:
				itemsToSpawn.add( new MetalShard() );
				break;
		}
		
		//hard limit is 63 items + 1 shopkeeper, as shops can't be bigger than 8x8=64 internally
		if (itemsToSpawn.size() > 63)
			throw new RuntimeException("Shop attempted to carry more than 63 items!");

		Random.shuffle(itemsToSpawn);
		return itemsToSpawn;
	}

	protected static Bag ChooseBag(Belongings pack){
	
		//0=holder, 1=bandolier
		int[] bagItems = new int[2];

		//count up items in the main bag
		for (Item item : pack.backpack.items) {
			if (item instanceof Scroll)                                     bagItems[0]++;
			if (item instanceof Potion)                                     bagItems[1]++;
		}
		
		//disqualify bags that have already been dropped
		if (Dungeon.LimitedDrops.SCROLL_HOLDER.dropped())                   bagItems[0] = -1;
		if (Dungeon.LimitedDrops.POTION_BANDOLIER.dropped())                bagItems[1] = -1;
		
		//find the best bag to drop. This does give a preference to later bags, if counts are equal
		int bestBagIdx = 0;
		for (int i = 1; i <= 1; i++){
			if (bagItems[bestBagIdx] <= bagItems[i]){
				bestBagIdx = i;
			}
		}
		
		//drop it, or return nothing if no bag works
		if (bagItems[bestBagIdx] == -1) return null;
		switch (bestBagIdx){
			case 0: default:
				Dungeon.LimitedDrops.SCROLL_HOLDER.drop();
				return new ScrollHolder();
			case 1:
				Dungeon.LimitedDrops.POTION_BANDOLIER.drop();
				return new PotionBandolier();
		}

	}

}
