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
import com.shatteredpixel.shatteredpixeldungeon.items.armor.HalfPlateArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.MailArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.PlateArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ScaleArmor;
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
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLevitation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Greatsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Greataxe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Bludgeon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Longsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.BattleAxe;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Mace;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WarHammer;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.Kunai;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.Javelin;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.Shuriken;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ThrowingHammer;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.Trident;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.GlaiveStar;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ForceCube;
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
		
		switch (Dungeon.depth) {
		case 6:
			itemsToSpawn.add( (Random.Int( 2 ) == 0 ? new Sword().identify() : new Mace().identify()) );
			itemsToSpawn.add( Random.Int( 2 ) == 0 ?
					new Shuriken().quantity(2) :
					new Kunai().quantity(2));
			itemsToSpawn.add( new MailArmor().identify() );
			break;
			
		case 11:
			itemsToSpawn.add( (Random.Int( 2 ) == 0 ?
					new Longsword().identify() :
					new Bludgeon().identify() ));
			itemsToSpawn.add( Random.Int( 2 ) == 0 ?
					new Javelin().quantity(2) :
					new ThrowingHammer().quantity(2));
			itemsToSpawn.add( new ScaleArmor().identify() );
			break;
			
		case 16:
			itemsToSpawn.add( (Random.Int( 2 ) == 0 ?
					new Greatsword().identify() :
					new WarHammer().identify() ));
			itemsToSpawn.add( Random.Int( 2 ) == 0 ?
					new Trident().quantity(2) :
					new GlaiveStar().quantity(2));
			itemsToSpawn.add( (Random.Int( 2 ) == 0 ?
					new PlateArmor().identify() :
					new HalfPlateArmor().identify() ));
			break;
			
		case 21:
			itemsToSpawn.add( (Random.Int( 2 ) == 0 ?
					new Greatsword().identify().upgrade() :
					new WarHammer().identify().upgrade() ));
			itemsToSpawn.add( Random.Int(2) == 0 ?
					new Trident().quantity(2) :
					new GlaiveStar().quantity(2));
			itemsToSpawn.add( (Random.Int( 2 ) == 0 ?
					new PlateArmor().identify().upgrade() :
					new HalfPlateArmor().identify().upgrade() ));
			itemsToSpawn.add( new Torch() );
			itemsToSpawn.add( new Torch() );
			itemsToSpawn.add( new Torch() );
			break;
		}
		
		itemsToSpawn.add( new Dart.DoubleDart() );

		itemsToSpawn.add(ChooseBag(Dungeon.hero.belongings));


		itemsToSpawn.add( new PotionOfHealing() );
		switch (Random.Int(3)){
			case 0:
				itemsToSpawn.add( new PotionOfLiquidFlame() );
				break;
			case 1:
				itemsToSpawn.add( new PotionOfLevitation() );
				break;
			case 2:
				itemsToSpawn.add( new PotionOfInvisibility() );
				break;
		}
		itemsToSpawn.add( Generator.random( Generator.Category.POTION ) );
		itemsToSpawn.add( Generator.random( Generator.Category.EXOTIC_POTION ) );

		itemsToSpawn.add( new ScrollOfIdentify() );
		itemsToSpawn.add( new ScrollOfRemoveCurse() );
		itemsToSpawn.add( Generator.random( Generator.Category.SCROLL ) );
		itemsToSpawn.add( Generator.random( Generator.Category.EXOTIC_SCROLL ) );

		itemsToSpawn.add( new SmallRation() );
		itemsToSpawn.add( new SmallRation() );
		
		switch (Random.Int(3)){
			case 0:
				itemsToSpawn.add( new Bomb() );
				break;
			case 1:
			case 2:
				itemsToSpawn.add( new Bomb.DoubleBomb() );
				break;
		}

		itemsToSpawn.add( new Ankh() );
		itemsToSpawn.add( new Stylus() );
		itemsToSpawn.add( new Honeypot() );

		Item rare;
		switch (Random.Int(3)){
			case 0:
				rare = Generator.random( Generator.Category.WAND );
				rare.level( 0 );
				break;
			case 1:
				rare = Generator.random( Generator.Category.RING );
				rare.level( 0 );
				break;
			default:
				rare = Generator.random( Generator.Category.ARTIFACT );
				break;
		}
		rare.cursed = false;
		rare.cursedKnown = true;
		itemsToSpawn.add( rare );

		switch (Random.Int(2)){
			case 0:
				itemsToSpawn.add( new AlchemicalCatalyst() );
				break;
			case 1:
				itemsToSpawn.add( new ArcaneCatalyst() );
				break;
		}
		
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
	
		//0=pouch, 1=holder, 2=bandolier, 3=holster
		int[] bagItems = new int[4];

		//count up items in the main bag
		for (Item item : pack.backpack.items) {
			if (item instanceof Plant.Seed)                                 bagItems[0]++;
			if (item instanceof Scroll)                                     bagItems[1]++;
			if (item instanceof Potion)                                     bagItems[2]++;
			if (item instanceof Wand || item instanceof MissileWeapon)      bagItems[3]++;
		}
		
		//disqualify bags that have already been dropped
		if (Dungeon.LimitedDrops.VELVET_POUCH.dropped())                    bagItems[0] = -1;
		if (Dungeon.LimitedDrops.SCROLL_HOLDER.dropped())                   bagItems[1] = -1;
		if (Dungeon.LimitedDrops.POTION_BANDOLIER.dropped())                bagItems[2] = -1;
		if (Dungeon.LimitedDrops.MAGICAL_HOLSTER.dropped())                 bagItems[3] = -1;
		
		//find the best bag to drop. This does give a preference to later bags, if counts are equal
		int bestBagIdx = 0;
		for (int i = 1; i <= 3; i++){
			if (bagItems[bestBagIdx] <= bagItems[i]){
				bestBagIdx = i;
			}
		}
		
		//drop it, or return nothing if no bag works
		if (bagItems[bestBagIdx] == -1) return null;
		switch (bestBagIdx){
			case 0: default:
				Dungeon.LimitedDrops.VELVET_POUCH.drop();
				return new VelvetPouch();
			case 1:
				Dungeon.LimitedDrops.SCROLL_HOLDER.drop();
				return new ScrollHolder();
			case 2:
				Dungeon.LimitedDrops.POTION_BANDOLIER.drop();
				return new PotionBandolier();
			case 3:
				Dungeon.LimitedDrops.MAGICAL_HOLSTER.drop();
				return new MagicalHolster();
		}

	}

}
