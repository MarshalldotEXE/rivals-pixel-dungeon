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

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic;

import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfAffection;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTerror;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.ArcaneCatalyst;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ExoticScroll extends Scroll {
	
	
	public static final HashMap<Class<?extends Scroll>, Class<?extends ExoticScroll>> regToExo = new HashMap<>();
	public static final HashMap<Class<?extends ExoticScroll>, Class<?extends Scroll>> exoToReg = new HashMap<>();
	static{
		regToExo.put(ScrollOfIdentify.class, ScrollOfDivination.class);
		exoToReg.put(ScrollOfDivination.class, ScrollOfIdentify.class);
		
		regToExo.put(ScrollOfUpgrade.class, ScrollOfMagicalInfusion.class);
		exoToReg.put(ScrollOfMagicalInfusion.class, ScrollOfUpgrade.class);
		
		regToExo.put(ScrollOfTerror.class, ScrollOfPetrification.class);
		exoToReg.put(ScrollOfPetrification.class, ScrollOfTerror.class);
		
		regToExo.put(ScrollOfRemoveCurse.class, ScrollOfAntiMagic.class);
		exoToReg.put(ScrollOfAntiMagic.class, ScrollOfRemoveCurse.class);
		//exoToReg.put(ScrollOfCurseInfusion.class, ScrollOfRemoveCurse.class);
		
		regToExo.put(ScrollOfAffection.class, ScrollOfLullaby.class);
		exoToReg.put(ScrollOfLullaby.class, ScrollOfAffection.class);
		
		regToExo.put(ScrollOfRage.class, ScrollOfConfusion.class);
		exoToReg.put(ScrollOfConfusion.class, ScrollOfRage.class);
		//exoToReg.put(ScrollOfTaunt.class, ScrollOfRage.class);
		
		regToExo.put(ScrollOfTerror.class, ScrollOfPetrification.class);
		exoToReg.put(ScrollOfPetrification.class, ScrollOfTerror.class);
		
		regToExo.put(ScrollOfRecharging.class, ScrollOfMysticalEnergy.class);
		exoToReg.put(ScrollOfMysticalEnergy.class, ScrollOfRecharging.class);
		//exoToReg.put(ScrollOfDraining.class, ScrollOfRecharging.class);
		
		regToExo.put(ScrollOfMagicMapping.class, ScrollOfForesight.class);
		exoToReg.put(ScrollOfForesight.class, ScrollOfMagicMapping.class);
		
		regToExo.put(ScrollOfTeleportation.class, ScrollOfPassage.class);
		exoToReg.put(ScrollOfPassage.class, ScrollOfTeleportation.class);
		
		regToExo.put(ScrollOfRetribution.class, ScrollOfPsionicBlast.class);
		exoToReg.put(ScrollOfPsionicBlast.class, ScrollOfRetribution.class);
		//exoToReg.put(ScrollOfDoom.class, ScrollOfRetribution.class);
		
		regToExo.put(ScrollOfMirrorImage.class, ScrollOfPrismaticImage.class);
		exoToReg.put(ScrollOfPrismaticImage.class, ScrollOfMirrorImage.class);
		//exoToReg.put(ScrollOfNecromancy.class, ScrollOfMirrorImage.class);
		
		regToExo.put(ScrollOfTransmutation.class, ScrollOfPolymorph.class);
		exoToReg.put(ScrollOfPolymorph.class, ScrollOfTransmutation.class);
	}
	
	@Override
	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown( exoToReg.get(this.getClass()) ));
	}
	
	@Override
	public void setKnown() {
		if (!isKnown()) {
			handler.know(exoToReg.get(this.getClass()));
			updateQuickslot();
		}
	}
	
	@Override
	public void reset() {
		super.reset();
		if (handler != null && handler.contains(exoToReg.get(this.getClass()))) {
			image = handler.image(exoToReg.get(this.getClass())) + 16;
			rune = handler.label(exoToReg.get(this.getClass()));
		}
	}
	
	@Override
	public void empoweredRead() {
	
	}
	
	@Override
	public int price() {
		return 15 * quantity;
	}
	
	public static class ScrollToExotic extends Recipe {
		
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			boolean catalyst = false;
			Scroll s = null;
			
			for (Item i : ingredients){
				if (i instanceof ArcaneCatalyst){
					catalyst = true;
				} else if (regToExo.containsKey(i.getClass())) {
					s = (Scroll)i;
				}
			}
			
			return s != null && catalyst;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 1;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Item result = null;
			
			for (Item i : ingredients){
				i.quantity(i.quantity()-1);
				if (regToExo.containsKey(i.getClass())) {
					try {
						result = regToExo.get(i.getClass()).newInstance();
					} catch (Exception e) {
						ShatteredPixelDungeon.reportException(e);
					}
				}
			}
			
			Statistics.itemsCrafted++;
			
			return result;
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			for (Item i : ingredients){
				if (regToExo.containsKey(i.getClass())) {
					try {
						return regToExo.get(i.getClass()).newInstance();
					} catch (Exception e) {
						ShatteredPixelDungeon.reportException(e);
					}
				}
			}
			return null;
			
		}
	}
}
