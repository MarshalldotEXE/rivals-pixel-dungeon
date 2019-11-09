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
						Rat.class, Rat.class, Rat.class, Rat.class, Rat.class));
			case 2:
				//3x rat, 3x gnoll
				return new ArrayList<>(Arrays.asList(
						Rat.class, Rat.class, Rat.class,
						Gnoll.class, Gnoll.class, Gnoll.class));
			case 3:
				//1x rat, 2x albino, 4x gnoll, 1x crab, 1x swarm
				return new ArrayList<>(Arrays.asList(
						Rat.class,
						Albino.class, Albino.class,
						Gnoll.class, Gnoll.class, Gnoll.class, Gnoll.class,
						Crab.class, Swarm.class));
			case 4: case 5:
				//1x albino, 2x gnoll, 3x crab, 1x swarm
				return new ArrayList<>(Arrays.asList(
						Albino.class,
						Gnoll.class, Gnoll.class,
						Crab.class, Crab.class, Crab.class,
						Swarm.class));
				
			// Prison
			case 6:
				//2x skeleton, 1x thief, 1x tribesman, 1x swarm
				return new ArrayList<>(Arrays.asList(
						Skeleton.class, Skeleton.class,
						Tribesman.class,
						Thief.class,
						Fireflies.class));
			case 7:
				//2x skeleton, 1x thief, 1x tribesman, 1x shaman, 1x guard
				return new ArrayList<>(Arrays.asList(
						Skeleton.class, Skeleton.class,
						Tribesman.class,
						Thief.class,
						Shaman.class,
						Guard.class));
			case 8:
				//2x skeleton, 1x bandit, 1x elite, 2x shaman, 1x guard, 1x blue wraith
				return new ArrayList<>(Arrays.asList(
						Skeleton.class, Skeleton.class,
						Elite.class,
						Bandit.class,
						Shaman.class, Shaman.class,
						Guard.class,
						BlueWraith.class));
			case 9: case 10:
				//2x skeleton, 1x bandit, 1x elite, 2x shaman, 2x guard, 1x blue wraith
				return new ArrayList<>(Arrays.asList(
						Skeleton.class, Skeleton.class,
						Elite.class,
						Bandit.class,
						Shaman.class, Shaman.class,
						Guard.class, Guard.class,
						BlueWraith.class));
				
			// Caves
			case 11:
				//5x bat, 1x brute
				return new ArrayList<>(Arrays.asList(
						Bat.class, Bat.class, Bat.class, Bat.class, Bat.class,
						Brute.class));
			case 12:
				//5x bat, 5x brute, 1x spinner
				return new ArrayList<>(Arrays.asList(
						Bat.class, Bat.class, Bat.class, Bat.class, Bat.class,
						Brute.class, Brute.class, Brute.class, Brute.class, Brute.class,
						Spinner.class));
			case 13:
				//1x bat, 3x shielded, 1x battlemage, 1x spinner
				return new ArrayList<>(Arrays.asList(
						Bat.class,
						Shielded.class, Shielded.class, Shielded.class,
						Battlemage.class,
						Spinner.class));
			case 14: case 15:
				//1x bat, 3x shielded, 1x battlemage, 4x spinner
				return new ArrayList<>(Arrays.asList(
						Bat.class,
						Shielded.class, Shielded.class, Shielded.class,
						Battlemage.class,
						Spinner.class, Spinner.class, Spinner.class, Spinner.class));
				
			// City
			case 16:
				//5x elemental, 5x warlock, 1x monk
				return new ArrayList<>(Arrays.asList(
						Elemental.class, Elemental.class, Elemental.class, Elemental.class, Elemental.class,
						Warlock.class, Warlock.class, Warlock.class, Warlock.class, Warlock.class,
						Monk.class));
			case 17:
				//2x elemental, 2x warlock, 2x monk
				return new ArrayList<>(Arrays.asList(
						Elemental.class, Elemental.class,
						Warlock.class, Warlock.class,
						Monk.class, Monk.class));
			case 18:
				//1x elemental, 1x warlock, 1x monk, 1x senior, 1x golem
				return new ArrayList<>(Arrays.asList(
						Elemental.class,
						Warlock.class,
						Monk.class,
						Senior.class,
						Golem.class));
			case 19: case 20:
				//1x elemental, 1x warlock, 1x senior, 2x golem, 1x red wraith, 1x gold mimic
				return new ArrayList<>(Arrays.asList(
						Elemental.class,
						Warlock.class,
						Senior.class, Senior.class,
						Golem.class, Golem.class,
						RedWraith.class,
						GoldMimic.class));
				
			// Halls
			case 21: case 22:
				//2x succubus, 2x purple wraith, 1x wisp, 2x evil eye,
				return new ArrayList<>(Arrays.asList(
						Succubus.class, Succubus.class,
						PurpleWraith.class, PurpleWraith.class,
						Wisp.class,
						Eye.class, Eye.class));
			case 23:
				//2x succubus, 1x purple wraith, 1x wisp, 2x evil eye, 2x scorpio
				return new ArrayList<>(Arrays.asList(
						Succubus.class, Succubus.class,
						PurpleWraith.class,
						Wisp.class,
						Eye.class, Eye.class,
						Scorpio.class, Scorpio.class));
			case 24: case 25: case 26:
				//1x succubus, 1x purple wraith, 1x wisp, 1x evil eye, 1x scorpio, 1x acidic, 1x monster box
				return new ArrayList<>(Arrays.asList(
						Succubus.class,
						PurpleWraith.class,
						Wisp.class,
						Eye.class,
						Scorpio.class,
						Acidic.class,
						MonsterBox.class));
		}
		
	}
	
	//has a chance to add a rarely spawned mobs to the rotation
	public static void addRareMobs( int depth, ArrayList<Class<?extends Mob>> rotation ){
		
		switch (depth){
			
			// Sewers
			default:
				return;
			case 4:
				if (Random.Float() < 0.02f) rotation.add(Fireflies.class);
				if (Random.Float() < 0.01f) rotation.add(Thief.class);
				if (Random.Float() < 0.01f) rotation.add(Skeleton.class);
				return;
				
			// Prison
			case 6:
				if (Random.Float() < 0.2f)  rotation.add(Shaman.class);
				return;
			case 8:
				if (Random.Float() < 0.02f) rotation.add(Bat.class);
				return;
			case 9:
				if (Random.Float() < 0.02f) rotation.add(Bat.class);
				if (Random.Float() < 0.01f) rotation.add(Brute.class);
				if (Random.Float() < 0.01f) rotation.add(Battlemage.class);
				return;
				
			// Caves
			case 12:
				if (Random.Float() < 0.2f)  rotation.add(Battlemage.class);
				return;
			case 13:
				if (Random.Float() < 0.02f) rotation.add(Elemental.class);
				return;
			case 14:
				if (Random.Float() < 0.02f) rotation.add(Elemental.class);
				if (Random.Float() < 0.01f) rotation.add(Monk.class);
				return;
				
			// City
			case 17:
				if (Random.Float() < 0.2f)  rotation.add(Golem.class);
				return;
			case 19:
				if (Random.Float() < 0.02f) rotation.add(Succubus.class);
				return;
		}
	}
	
	//switches out regular mobs for their alt versions when appropriate
	private static void swapMobAlts(ArrayList<Class<?extends Mob>> rotation){
		for (int i = 0; i < rotation.size(); i++){
			if (Random.Int( 50 ) == 0) {
				Class<? extends Mob> cl = rotation.get(i);
				if (cl == Rat.class) {
					cl = Albino.class;
				} else if (cl == Thief.class) {
					cl = Bandit.class;
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
