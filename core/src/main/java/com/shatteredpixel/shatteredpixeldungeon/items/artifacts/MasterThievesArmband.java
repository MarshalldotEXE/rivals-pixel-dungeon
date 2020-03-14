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

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class MasterThievesArmband extends Artifact {

	{
		image = ItemSpriteSheet.ARTIFACT_ARMBAND;
		//passive, no default action
		
		exp = 0;
		levelCap = 10;

		charge = 0;
		partialCharge = 0;
		//no charge cap
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new Thievery();
	}
	
	@Override
	public void charge(Hero target) {
		charge += 10;
		updateQuickslot();
	}

	@Override
	public String desc() {
		String desc = super.desc();

		if ( isEquipped (Dungeon.hero) ){
			if (cursed){
				desc += "\n\n" + Messages.get(this, "desc_cursed");
			} else {
				desc += "\n\n" + Messages.get(this, "desc_worn");
			}
		}
		

		return desc;
	}

	public class Thievery extends ArtifactBuff{
		
		public void collect(int gold) {
			if (!cursed) {
				partialCharge += gold / 2f;
				
				while (partialCharge >= 1) {
					charge++;
					partialCharge--;
				}
			}
		}
		
		@Override
		public boolean act() {
			if (cursed) {
				
				if (Dungeon.gold > 0 && Random.Int(5) == 0){
					Dungeon.gold--;
				}
				
				spend( TICK );
				
				return true;
				
			} else {
				return super.act();
			}
		}
		
		public boolean steal(int value) {
			
			//charge is 100% or greater compared to the item's cost
			if (charge >= value) {
				charge -= value;
				exp += value;
			}
			
			//charge is 99% or less compared to the item's cost
			else {
				float chance = stealChance(value);
				//random chance fails
				if (Random.Float() > chance) {
					return false;
				}
				//random chance succeeds
				else {
					//chance is less than or equal to 100%
					if (chance <= 1) {
						charge = 0;
					}
					
					//chance is more than 100%
					else {
						charge -= charge/chance;
					}
					
					exp += value;
				}
				
			}
			
			while (exp >= 50 + 50 * level() && level() < levelCap) {
				upgrade();
				exp -= 50 + 50 * level();
			}	
			
			return true;
		}

		public float stealChance(int value) {
			//item's cost is decreased by 3.3% per armband level
			value *= 1 - (.033f * level());
			return (float)charge / value;
		}
	}
}
