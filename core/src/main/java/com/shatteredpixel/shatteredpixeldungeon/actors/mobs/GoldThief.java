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

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GoldThiefSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class GoldThief extends Mob {
	
	private static final String TXT_VALUE = "-%d";
	
	public int stolenGold = 0;
	
	{
		spriteClass = GoldThiefSprite.class;
		
		HP = HT = 51;
		defenseSkill = 22;
		
		EXP = 11;
		maxLvl = 16;
		
		loot = Random.oneOf(Generator.Category.RING, Generator.Category.ARTIFACT);
		lootChance = 0.01f;
		
		WANDERING = new Wandering();
		FLEEING = new Fleeing();
	}

	private static final String STOLENGOLD = "stolenGold";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( STOLENGOLD, stolenGold );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		stolenGold = bundle.getInt( STOLENGOLD );
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 6, 16 );
	}

	@Override
	protected float attackDelay() {
		return super.attackDelay()*0.5f;
	}
	
	@Override
	public void rollToDropLoot() {
		if (stolenGold > 0) {
			Dungeon.level.drop( new Gold( stolenGold ), pos ).sprite.drop();
		}
		super.rollToDropLoot();
	}

	@Override
	public int attackSkill( Char target ) {
		return 21;
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 4);
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		
		if (alignment == Alignment.ENEMY && stolenGold == 0
				&& enemy instanceof Hero && steal( (Hero)enemy )) {
			state = FLEEING;
		}

		return damage;
	}

	@Override
	public int defenseProc(Char enemy, int damage) {
		if (state == FLEEING) {
			Dungeon.level.drop( new Gold(), pos ).sprite.drop();
		}

		return super.defenseProc(enemy, damage);
	}

	protected boolean steal( Hero hero ) {

		if (Dungeon.gold >= 100) {

			stolenGold = Random.NormalIntRange( 100, Dungeon.gold );
			
			GLog.w( Messages.get(GoldThief.class, "stole", stolenGold ) );

			Dungeon.gold -= stolenGold;
			Statistics.goldCollected -= stolenGold;
			
			hero.sprite.showStatus( CharSprite.WARNING, TXT_VALUE, stolenGold );

			return true;
		} else {
			return false;
		}
	}

	@Override
	public String description() {
		String desc = super.description();

		if (stolenGold > 0) {
			desc += Messages.get(this, "carries", stolenGold );
		}

		return desc;
	}
	
	private class Wandering extends Mob.Wandering {
		
		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			super.act(enemyInFOV, justAlerted);
			
			//if an enemy is just noticed and the thief posses an item, run, don't fight.
			if (state == HUNTING && stolenGold > 0){
				state = FLEEING;
			}
			
			return true;
		}
	}

	private class Fleeing extends Mob.Fleeing {
		@Override
		protected void nowhereToRun() {
			if (buff( Terror.class ) == null && buff( Corruption.class ) == null) {
				if (enemySeen) {
					sprite.showStatus(CharSprite.NEGATIVE, Messages.get(Mob.class, "rage"));
					state = HUNTING;
				} else if (stolenGold > 0
						&& !Dungeon.level.heroFOV[pos]
						&& Dungeon.level.distance(Dungeon.hero.pos, pos) < 6) {

					int count = 32;
					int newPos;
					do {
						newPos = Dungeon.level.randomRespawnCell();
						if (count-- <= 0) {
							break;
						}
					} while (newPos == -1 || Dungeon.level.heroFOV[newPos] || Dungeon.level.distance(newPos, pos) < (count/3));

					if (newPos != -1) {

						if (Dungeon.level.heroFOV[pos]) CellEmitter.get(pos).burst(Speck.factory(Speck.WOOL), 6);
						pos = newPos;
						sprite.place( pos );
						sprite.visible = Dungeon.level.heroFOV[pos];
						if (Dungeon.level.heroFOV[pos]) CellEmitter.get(pos).burst(Speck.factory(Speck.WOOL), 6);

					}

					if (stolenGold > 0) GLog.n( Messages.get(GoldThief.class, "escapes", stolenGold ));
					stolenGold = 0;
					state = WANDERING;
				} else {
					state = WANDERING;
				}
			} else {
				super.nowhereToRun();
			}
		}
	}
}
