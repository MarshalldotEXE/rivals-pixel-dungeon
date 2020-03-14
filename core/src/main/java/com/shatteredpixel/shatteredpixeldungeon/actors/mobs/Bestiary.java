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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.rivals.Rival;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

public class Bestiary {
	
	public static ArrayList<Class<? extends Mob>> getMobRotation( int depth ){
		ArrayList<Class<? extends Mob>> mobs = standardMobRotation( depth );
		addRareMobs(depth, mobs);
		swapMobAlts(mobs);
		Random.shuffle(mobs);
		return mobs;
	}
	
	//returns a rotation of standard mobs, unshuffled.
	private static ArrayList<Class<? extends Mob>> standardMobRotation( int depth ){
		switch(depth){
			
			// Sewers
			case 1: default:
				//10x rat
				return new ArrayList<Class<? extends Mob>>(Arrays.asList(
						Rat.class, Rat.class, Rat.class, Rat.class, Rat.class,
						Rat.class, Rat.class, Rat.class, Rat.class, Rat.class
						));
						
			case 2:
				//3x rat, 3x gnoll, 1x swarm
				return new ArrayList<>(Arrays.asList(
						Rat.class,
						Rat.class,
						Rat.class,
						Gnoll.class,
						Gnoll.class,
						Gnoll.class,
						Swarm.class
						));
						
			case 3: case 4:
				//1x rat, 1x albino, 2x gnoll, 1x swarm, 2x crab, 2x spawn
				return new ArrayList<>(Arrays.asList(
						Rat.class,
						Albino.class,
						Gnoll.class,
						Gnoll.class,
						Swarm.class,
						Crab.class,
						Crab.class,
						GooSpawn.class,
						GooSpawn.class
						));
				
			// Prison
			case 5:
				//1x swarm, 3x skeleton, 2x thief
				return new ArrayList<>(Arrays.asList(
						Swarm.class,
						Skeleton.class,
						Skeleton.class,
						Skeleton.class,
						Thief.class,
						Thief.class
						));
						
			case 6:
				//2x skeleton, 2x thief, 2x shaman, 1x guard
				return new ArrayList<>(Arrays.asList(
						Skeleton.class,
						Skeleton.class,
						Thief.class,
						Thief.class,
						Shaman.class,
						Shaman.class,
						Guard.class
						));
						
			case 7: case 8:
				//2x skeleton, 1x thief, 1x bandit, 2x shaman, 3x guard
				return new ArrayList<>(Arrays.asList(
						Skeleton.class,
						Skeleton.class,
						Thief.class,
						Bandit.class,
						Shaman.class,
						Shaman.class,
						Guard.class,
						Guard.class,
						Guard.class
						));
				
			// Caves
			case 9:
				//3x bat, 3x brute
				return new ArrayList<>(Arrays.asList(
						Bat.class,
						Bat.class,
						Bat.class,
						Brute.class,
						Brute.class,
						Brute.class
						));
						
			case 10:
				//2x bat, 2x brute, 1x battlemage, 2x spinner
				return new ArrayList<>(Arrays.asList(
						Bat.class,
						Bat.class,
						Brute.class,
						Brute.class,
						Battlemage.class,
						Spinner.class,
						Spinner.class
						));
						
			case 11: case 12:
				//1x bat, 2x brute, 1x shielded, 2x battlemage, 1x spinner, 2x DM-150
				return new ArrayList<>(Arrays.asList(
						Bat.class,
						Brute.class,
						Brute.class,
						Shielded.class,
						Battlemage.class,
						Battlemage.class,
						Spinner.class,
						DM150.class,
						DM150.class
						));
				
			// City
			case 13:
				//3x elemental, 1x warlock, 2x monk
				return new ArrayList<>(Arrays.asList(
						Elemental.class,
						Elemental.class,
						Elemental.class,
						Warlock.class,
						Monk.class,
						Monk.class
						));
						
			case 14:
				//2x elemental, 1x warlock, 2x monk, 2x golem
				return new ArrayList<>(Arrays.asList(
						Elemental.class,
						Elemental.class,
						Warlock.class,
						Monk.class,
						Monk.class,
						Golem.class,
						Golem.class
						));
						
			case 15: case 16:
				//1x elemental, 2x warlock, 2x monk, 1x senior, 3x golem
				return new ArrayList<>(Arrays.asList(
						Elemental.class,
						Warlock.class,
						Warlock.class,
						Monk.class,
						Monk.class,
						Senior.class,
						Golem.class,
						Golem.class,
						Golem.class
						));
				
			// Halls
			case 17: case 18:
				//3x imp, 2x succubus, 1x eye
				return new ArrayList<>(Arrays.asList(
						EvilImp.class,
						EvilImp.class,
						EvilImp.class,
						Succubus.class,
						Succubus.class,
						Eye.class
						));
						
			case 19:
				//2x imp, 1x succubus, 2x eye, 2x scorpio
				return new ArrayList<>(Arrays.asList(
						EvilImp.class,
						EvilImp.class,
						Succubus.class,
						Eye.class,
						Eye.class,
						Scorpio.class,
						Scorpio.class
						));
						
			case 20: case 21:
				//2x imp, 1x succubus, 3x eye, 2x scorpio, 1x acidic
				return new ArrayList<>(Arrays.asList(
						EvilImp.class,
						EvilImp.class,
						Succubus.class,
						Eye.class,
						Eye.class,
						Eye.class,
						Scorpio.class,
						Scorpio.class,
						Acidic.class
						));
						
		}
		
	}
	
	//has a chance to add rarely spawned mobs to the rotation
	public static void addRareMobs( int depth, ArrayList<Class<?extends Mob>> rotation ){
		
		switch (depth){
			default:
				return;
			
			// Sewers
			case 1:
				return;
			case 2:
				if (Random.Float() < 0.3f) rotation.add(GooSpawn.class);
				return;
			case 3:
				if (Random.Float() < 0.03f) rotation.add(Thief.class);
				return;
				
			// Prison
			case 5:
				if (Random.Float() < 0.3f) rotation.add(Shaman.class);
				return;
			case 6:
				return;
			case 7:
				if (Random.Float() < 0.03f) rotation.add(Bat.class);
				return;
				
			// Caves
			case 9:
				if (Random.Float() < 0.3f) rotation.add(Battlemage.class);
				return;
			case 10:
				if (Random.Float() < 0.3f) rotation.add(DM150.class);
				return;
			case 11:
				return;
				
			// City
			case 13:
				if (Random.Float() < 0.3f) rotation.add(Golem.class);
				return;
			case 14:
				return;
			case 15:
				if (Random.Float() < 0.03f) rotation.add(Succubus.class);
				return;
				
			// Halls
			case 18:
				if (Random.Float() < 0.3f) rotation.add(Scorpio.class);
				return;
			case 19:
				return;
			case 20:
				return;
		}
	}
	
	//switches out regular mobs for their alt versions when appropriate
	private static void swapMobAlts(ArrayList<Class<?extends Mob>> rotation){
		for (int i = 0; i < rotation.size(); i++){
			if (Random.Int( 33 ) == 0) {
				Class<? extends Mob> cl = rotation.get(i);
				if (cl == Rat.class) {
					cl = Albino.class;
				} else if (cl == Thief.class) {
					cl = Bandit.class;
				} else if (cl == Tribesman.class) {
					cl = Elite.class;
				} else if (cl == Brute.class) {
					cl = Shielded.class;
				} else if (cl == Monk.class) {
					cl = Senior.class;
				} else if (cl == Scorpio.class) {
					cl = Acidic.class;
				}
				rotation.set(i, cl);
			}
		}
	}
}
