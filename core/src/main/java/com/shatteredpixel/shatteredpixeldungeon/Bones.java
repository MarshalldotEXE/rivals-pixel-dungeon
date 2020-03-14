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

package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Dewdrop;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;
import com.watabou.utils.Random;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Bones {

	private static final String BONES_FILE	= "bones.dat";
	
	private static final String LEVEL	= "level";
	private static final String ITEM	= "item";

	private static int depth = -1;
	private static Item item;
	
	public static void leave() {

		depth = Dungeon.depth;

		//heroes which have won the game or who are challenged drop no bones.
		if (Statistics.amuletObtained || Dungeon.challenges > 0) {
			depth = -1;
			return;
		}

		item = pickItem(Dungeon.hero);

		Bundle bundle = new Bundle();
		bundle.put( LEVEL, depth );
		bundle.put( ITEM, item );

		try {
			FileUtils.bundleToFile( BONES_FILE, bundle );
		} catch (IOException e) {
			ShatteredPixelDungeon.reportException(e);
		}
	}

	private static Item pickItem(Hero hero){
		ArrayList<Item> items = new ArrayList<Item>();
		Iterator<Item> iterator = hero.belongings.backpack.iterator();
		Item curItem;
		Item item;
		
		//put all belongings into an arraylist
		curItem = hero.belongings.weapon;
		if (curItem != null && curItem.bones)
			items.add(curItem);
		curItem = hero.belongings.armor;
		if (curItem != null && curItem.bones)
			items.add(curItem);
		curItem = hero.belongings.misc1;
		if (curItem != null && curItem.bones)
			items.add(curItem);
		curItem = hero.belongings.misc2;
		if (curItem != null && curItem.bones)
			items.add(curItem);
		
		while (iterator.hasNext()){
			curItem = iterator.next();
			if (curItem.bones)
				items.add(curItem);
		}
		
		if (items.size() > 0) {
			//pick a random item
			item = Random.element(items);
			
			if (item.stackable){
				item.quantity(Random.NormalIntRange(1, (item.quantity() + 1) / 2));
			}
		} else {
			item = new Dewdrop();
		}
		
		return item;
	}

	public static Item get() {
		if (depth == -1) {

			try {
				Bundle bundle = FileUtils.bundleFromFile(BONES_FILE);

				depth = bundle.getInt( LEVEL );
				item = (Item)bundle.get( ITEM );

				return get();

			} catch (IOException e) {
				return null;
			}

		} else {
			//heroes who are challenged cannot find bones
			if (depth == Dungeon.depth && Dungeon.challenges == 0) {
				FileUtils.deleteFile( BONES_FILE );
				depth = 0;
				
				if (item == null) return null;

				//Enforces artifact uniqueness
				if (item instanceof Artifact){
					if (Generator.removeArtifact(((Artifact)item).getClass())) {
						try {
							//generates a new artifact of the same type, always +0
							Artifact artifact = (Artifact)item.getClass().newInstance();

							artifact.cursed = true;
							artifact.cursedKnown = true;

							return artifact;
						} catch (Exception e) {
							ShatteredPixelDungeon.reportException(e);
							return new Gold(item.price());
						}
					} else {
						return new Gold(item.price());
					}
				}
				
				if (item.isUpgradable() && !(item instanceof MissileWeapon)) {
					item.cursed = true;
					item.cursedKnown = true;
				}
				
				if (item.isUpgradable()) {
					//caps at +2
					if (item.level() > 2) {
						item.degrade( item.level() - 2 );
					}
					//thrown weapons are always IDed, otherwise set unknown
					item.levelKnown = item instanceof MissileWeapon;
				}
				
				item.reset();
				
				return item;
			} else {
				return null;
			}
		}
	}
}
