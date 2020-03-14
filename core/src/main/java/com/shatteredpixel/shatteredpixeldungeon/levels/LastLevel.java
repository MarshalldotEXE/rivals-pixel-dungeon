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

package com.shatteredpixel.shatteredpixeldungeon.levels;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.rivals.Rival;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Amulet;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.HeavyBoomerang;
import com.shatteredpixel.shatteredpixeldungeon.levels.arenas.LastLevelArenas;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.standard.EmptyRoom;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.BurningTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.ChillingTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.ConfusionTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.ExplosiveTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.OozeTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.PoisonDartTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.ShockingTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.ToxicTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.BArray;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Group;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.Arrays;
import java.util.ArrayList;

public class LastLevel extends Level {

	{
		color1 = 0x801500;
		color2 = 0xa68521;
	}
	
	public enum State {
		BRIDGE,
		PHASE_1,
		PHASE_2,
		PHASE_3,
		PHASE_4,
		PHASE_5,
		WON
	}
	
	private static final int JUMP_TIME = 1;
	
	private State state;
	private Rival rival;
	
	public State state(){
		return state;
	}
	
	//keep track of items to dump back after fight
	private ArrayList<Item> storedItems = new ArrayList<>();
	
	private int heroJumpPoint;
	private int rivalJumpPoint;
	
	private int pedestal;

	@Override
	public String tilesTex() {
		return Assets.TILES_HALLS;
	}

	@Override
	public String waterTex() {
		return Assets.WATER_HALLS;
	}

	@Override
	protected boolean build() {
		
		setSize(32, 64);
		Arrays.fill( map, Terrain.CHASM );

		int mid = (width-1)/2;

		Painter.fill( this, 0, height-1, width, 1, Terrain.WALL );
		Painter.fill( this, mid - 1, 13, 3, (height-14), Terrain.EMPTY);
		Painter.fill( this, mid - 2, height - 3, 5, 1, Terrain.EMPTY);
		Painter.fill( this, mid - 3, height - 2, 7, 1, Terrain.EMPTY);

		Painter.fill( this, mid - 2, 12, 5, 7, Terrain.EMPTY);
		Painter.fill( this, mid - 3, 12, 7, 6, Terrain.EMPTY);

		entrance = (height-2) * width() + mid;
		map[entrance] = Terrain.ENTRANCE;

		pedestal = 15*(width()) + mid;
		map[pedestal] = Terrain.PEDESTAL;
		map[pedestal-1-width()] = map[pedestal+1-width()] = map[pedestal-1+width()] = map[pedestal+1+width()] = Terrain.STATUE_SP;

		exit = pedestal;
		
		int pos;
		
		//place grass and bookshelves
		pos = pedestal;
		map[pos-3] = map[pos+3] = Terrain.HIGH_GRASS;
		pos -= width();
		map[pos-2] = map[pos+2] = map[pos-3] = map[pos+3] = Terrain.HIGH_GRASS;
		pos -= width();
		map[pos] = map[pos-1] = map[pos+1] = map[pos-2] = map[pos+2] = Terrain.HIGH_GRASS;
		map[pos-3] = map[pos+3] = Terrain.BOOKSHELF;
		pos -= width();
		map[pos] = map[pos-1] = map[pos+1] = Terrain.HIGH_GRASS;
		map[pos-2] = map[pos+2] = map[pos-3] = map[pos+3] = Terrain.BOOKSHELF;
		
		//place water
		pos = pedestal;
		map[pos-width()] = map[pos-1] = map[pos+1] = map[pos-2] = map[pos+2] = Terrain.WATER;
		pos += width();
		map[pos-2] = map[pos+2] = map[pos-3] = map[pos+3] = Terrain.WATER;
		pos += width();
		map[pos-2] = map[pos+2] = map[pos-3] = map[pos+3] = Terrain.WATER;
		pos += width();
		map[pos-2] = map[pos+2] = Terrain.WATER;
		
		for (int i=0; i < length(); i++) {
			if (map[i] == Terrain.EMPTY && Random.Int( 10 ) == 0) {
				map[i] = Terrain.EMPTY_DECO;
			}
		}
		
		state = State.BRIDGE;
		
		feeling = Feeling.NONE;

		return true;
	}
	
	@Override
	public Mob createMob() {
		return null;
	}
	
	@Override
	protected void createMobs() {
		rival = new Rival();
		rival.pos = pedestal;
		mobs.add( rival );
	}

	public Actor respawner() {
		return null;
	}
	
	@Override
	protected void createItems() {
		//do nothing
	}
	
	@Override
	public int randomRespawnCell() {
		int cell;
		do {
			cell = entrance + PathFinder.NEIGHBOURS8[Random.Int(8)];
		} while (!passable[cell] || Actor.findChar(cell) != null);
		return cell;
	}

	@Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(HallsLevel.class, "water_name");
			case Terrain.GRASS:
				return Messages.get(HallsLevel.class, "grass_name");
			case Terrain.HIGH_GRASS:
				return Messages.get(HallsLevel.class, "high_grass_name");
			case Terrain.STATUE:
			case Terrain.STATUE_SP:
				return Messages.get(HallsLevel.class, "statue_name");
			default:
				return super.tileName( tile );
		}
	}

	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(HallsLevel.class, "water_desc");
			case Terrain.STATUE:
			case Terrain.STATUE_SP:
				return Messages.get(HallsLevel.class, "statue_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(HallsLevel.class, "bookshelf_desc");
			default:
				return super.tileDesc( tile );
		}
	}

	@Override
	public Group addVisuals() {
		super.addVisuals();
		HallsLevel.addHallsVisuals(this, visuals);
		return visuals;
	}
	
	private static final String STATE	        = "state";
	private static final String RIVAL	        = "rival";
	private static final String STORED_ITEMS    = "storeditems";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put( STATE, state );
		bundle.put( RIVAL, rival );
		bundle.put( STORED_ITEMS, storedItems);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		state = bundle.getEnum( STATE, State.class );
		rival = (Rival)bundle.get( RIVAL );
		for (Bundlable item : bundle.getCollection(STORED_ITEMS)){
			storedItems.add( (Item)item );
		}
	}
	
	@Override
	public void press( int cell, Char ch ) {
		super.press(cell, ch);

		if (ch == Dungeon.hero){
			//hero reaches amulet
			if (state == State.BRIDGE
					&& (new EmptyRoom().set(7, 7, 23, 23)).inside(cellToPoint(cell))){
				progress();
			}
			if (state == State.BRIDGE
					&& (new EmptyRoom().set(6, 6, 24, 24)).inside(cellToPoint(cell))){
				rival.notice();
			}
		}
	}
	
	public void progress() {
		switch(state) {
			case BRIDGE:
				changeMap(LastLevelArenas.randomPhase1Map());
				heroJumpPoint = 16 + 15 * width();
				rivalJumpPoint = 28 + 15 * width();
				
				doProgress(true);
				
				state = State.PHASE_1;
				break;
			case PHASE_1:
				changeMap(LastLevelArenas.randomPhase2Map());
				heroJumpPoint = 14 + 16 * width();
				rivalJumpPoint = 2 + 28 * width();
				
				doProgress(true);
				
				state = State.PHASE_2;
				break;
			case PHASE_2:
				changeMap(LastLevelArenas.randomPhase3Map());
				heroJumpPoint = 15 + 14 * width();
				rivalJumpPoint = 15 + 2 * width();
				
				doProgress(true);
				
				state = State.PHASE_3;
				break;
			case PHASE_3:
				changeMap(LastLevelArenas.randomPhase4Map());
				heroJumpPoint = 16 + 16 * width();
				rivalJumpPoint = 28 + 28 * width();
				
				doProgress(true);
				
				traps.clear();
				
				state = State.PHASE_4;
				break;
			case PHASE_4:
				changeMap(LastLevelArenas.randomPhase5Map());
				heroJumpPoint = 14 + 15 * width();
				rivalJumpPoint = 2 + 15 * width();
				
				doProgress(true);
				
				state = State.PHASE_5;
				break;
			case PHASE_5:
				changeMap(LastLevelArenas.randomWonMap());
				heroJumpPoint = 15 + 26 * width();
				rivalJumpPoint = 15 + 16 * width();
				int newEntrance = 15 + 15 * width();
				
				doProgress(false);
				
				map[newEntrance] = Terrain.ENTRANCE;
				entrance = newEntrance;
				GameScene.resetMap();
				
				drop( new Amulet(), rivalJumpPoint );
				
				for (Item item : storedItems)
					drop( item, randomWonCell() );
				
				state = State.WON;
				break;
			case WON:
			default:
		}
	}
	
	private void doProgress(boolean alive) {
		GameScene.resetMap();
		
		boolean[] visited = Dungeon.level.visited;
		boolean[] discoverable = Dungeon.level.discoverable;
		for (int i = 0; i < Dungeon.level.length(); i++) {
			int terr = Dungeon.level.map[i];
			if (discoverable[i]) {
				visited[i] = true;
				if ((Terrain.flags[terr] & Terrain.SECRET) != 0) {
					Dungeon.level.discover( i );
					if (Dungeon.level.heroFOV[i]) {
						GameScene.discoverTile( i, terr );
						discover( i );
					}
				}
			}
		}
		
		Sample.INSTANCE.play(Assets.SND_ROCKS);
		GameScene.flash(0x66665D);
		Camera.main.shake( 4, 1f );
		for (int i = 0; i < Dungeon.level.length(); i++) {
			int r = Random.Int(6);
			if (Dungeon.level.heroFOV[i] && r == 0) {
				CellEmitter.get(i).start(Speck.factory(Speck.ROCK), 0.07f, 10);
			} else if (Dungeon.level.heroFOV[i] && r == 1) {
				CellEmitter.get(i).burst(Speck.factory(Speck.WOOL), 6 );
			}
		}
		
		Dungeon.hero.spendAndNext(JUMP_TIME);
		
		Dungeon.hero.sprite.jump( Dungeon.hero.pos, heroJumpPoint, new Callback() {
			@Override
			public void call() {
				Dungeon.hero.move( heroJumpPoint );
				Dungeon.level.press( heroJumpPoint, Dungeon.hero, true );
				Dungeon.observe();
				GameScene.updateFog();
				
				CellEmitter.center( heroJumpPoint ).burst(Speck.factory(Speck.DUST), 10);
				Camera.main.shake(2, 0.5f);
			}
		});
		
		if (alive) {
			rival.sprite.jump( rival.pos, rivalJumpPoint, new Callback() {
				@Override
				public void call() {
					rival.move( rivalJumpPoint );
					Dungeon.level.press( rivalJumpPoint, rival, true );
					if (Dungeon.level.heroFOV[rivalJumpPoint]) {
						CellEmitter.center( rivalJumpPoint ).burst(Speck.factory(Speck.DUST), 10);
					}
				}
			});
		}
	}
	
	private void changeMap(int[] map) {
		this.map = map.clone();
		buildFlagMaps();
		cleanWalls();
		
		//decoration
		for (int i=0; i < length(); i++) {
			if (map[i] == Terrain.EMPTY || map[i] == Terrain.WALL) {
				if (Random.Int( 6 ) == 0) {
					if (map[i] == Terrain.EMPTY) {
						map[i] = Terrain.EMPTY_DECO;
					} else if (map[i] == Terrain.WALL) {
						map[i] = Terrain.WALL_DECO;
					}
				}
			}
		}
		
		exit = entrance = 0;
		
		BArray.setFalse(visited);
		BArray.setFalse(mapped);
		
		for (Blob blob: blobs.values()){
			blob.fullyClear();
		}
		addVisuals(); //this also resets existing visuals
		
		//place traps for phase 3
		traps.clear();
		for (int i = 0; i < length(); i++) {
			if (map[i] == Terrain.INACTIVE_TRAP) {
				Trap trap;
				int randomTrap = Random.chances(new float[]{2, 2, 2, 2, 1, 1, 1, 1});
				switch (randomTrap) {
					default:
					case 0:
						trap = new BurningTrap();
						break;
					case 1:
						trap = new ChillingTrap();
						break;
					case 2:
						trap = new ShockingTrap();
						break;
					case 3:
						trap = new OozeTrap();
						break;
					case 4:
						trap = new ToxicTrap();
						break;
					case 5:
						trap = new ConfusionTrap();
						break;
					case 6:
						trap = new PoisonDartTrap();
						break;
					case 7:
						trap = new ExplosiveTrap();
						break;
				}
				
				trap.reveal();
				trap.active = true;
				setTrap( trap, i );
				map[i] = Terrain.TRAP;
			}
		}
		
		//store excess items
		for (Heap heap : heaps.values()){
			storedItems.addAll(heap.items);
			heap.destroy();
		}
		
		for (HeavyBoomerang.CircleBack b : Dungeon.hero.buffs(HeavyBoomerang.CircleBack.class)){
			storedItems.add(b.cancel());
		}
		
		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])){
			if (mob != rival){
				mob.destroy();
				if (mob.sprite != null)
					mob.sprite.killAndErase();
			}
		}
		
		for (Plant plant : plants.values()){
			plants.remove(plant.pos);
		}
		
		GameScene.resetMap();
		Dungeon.observe();
	}
	
	private int randomWonCell() {
		int pos;
		do {
			pos = 12 + 12*32; //initial position at top-left
			
			//randomly assign a cell
			pos += Random.Int(6); //random width
			pos += Random.Int(13)*32; //random height
			
		} while (solid[pos]
				|| map[pos] == Terrain.CHASM
				|| map[pos] == Terrain.ENTRANCE);
		return pos;
	}
	
}
