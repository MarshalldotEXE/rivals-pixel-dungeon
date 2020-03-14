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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.rivals;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Doom;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PinCushion;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Amulet;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.KindofMisc;
import com.shatteredpixel.shatteredpixeldungeon.items.TomeOfMastery;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor.Glyph;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.SkeletonKey;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon.Enchantment;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Grim;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.levels.LastLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GrimTrap;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RivalSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BossHealthBar;
import com.shatteredpixel.shatteredpixeldungeon.ui.TargetHealthIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class Rival extends Mob implements Callback {
	
	private static final float TIME_TO_ZAP	= 1f;
	private static final float TIME_TO_JUMP	= 1f;
	
	{
		spriteClass = RivalSprite.class;
	}
	
	public MeleeWeapon weapon;
	public Armor armor;
	public KindofMisc misc1;
	public KindofMisc misc2;
	public Wand wand;
	public MissileWeapon missile;
	
	public Rival() {
		super();
		
		int lvl = Math.min( 20, Dungeon.depth );
		
		//melee
		do {
			weapon = (MeleeWeapon)Generator.random(Generator.Category.WEAPON);
		} while (weapon.cursed);
		if (lvl >= 16) weapon.enchant(Enchantment.random());
		weapon.identify();
		
		//armor
		do {
			armor = (Armor)Generator.random(Generator.Category.ARMOR);
		} while (armor.cursed);
		if (lvl >= 8) armor.inscribe(Glyph.random());
		armor.identify();
		
		//misc1
		do {
			misc1 = (KindofMisc)Generator.random(Generator.Category.RING);
		} while (misc1.cursed);
		misc1.identify();
		
		//misc2
		do {
			misc2 = (KindofMisc)Generator.random(Generator.Category.RING);
		} while (misc2.cursed);
		misc2.identify();
		
		//wand
		do {
			wand = (Wand)Generator.random(Generator.Category.WAND);
		} while (wand.cursed);
		wand.updateLevel();
		wand.curCharges = Math.min( 10, wand.maxCharges );
		wand.identify();
		
		//missile
		do {
			missile = (MissileWeapon)Generator.random(Generator.Category.MISSILE);
		} while (missile.cursed);
		
		HP = HT = 15 + lvl * 5;
		defenseSkill = (int)(armor.evasionFactor( this, 7 + lvl ));
		
		EXP = lvl * 2;
		if (lvl >= 20) EXP = 100;
	}
	
	private static final String WEAPON	= "weapon";
	private static final String ARMOR	= "armor";
	private static final String MISC1	= "misc1";
	private static final String MISC2	= "misc2";
	private static final String WAND	= "wand";
	private static final String MISSILE	= "missile";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( WEAPON, weapon );
		bundle.put( ARMOR, armor );
		bundle.put( MISC1, misc1 );
		bundle.put( MISC2, misc2 );
		bundle.put( WAND, wand );
		bundle.put( MISSILE, missile );
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		weapon		= (MeleeWeapon)		bundle.get( WEAPON );
		armor		= (Armor)			bundle.get( ARMOR );
		misc1		= (KindofMisc)		bundle.get( MISC1 );
		misc2		= (KindofMisc)		bundle.get( MISC2 );
		wand		= (Wand)			bundle.get( WAND );
		missile		= (MissileWeapon)	bundle.get( MISSILE );
		BossHealthBar.assignBoss(this);
	}
	
	@Override
	public int damageRoll() {
		int dmg = 0;
		dmg += weapon.damageRoll( this );
		if (dmg < 0) dmg = 0;
		return dmg;
	}
	
	@Override
	public int drRoll() {
		int dr = 0;
		dr += Random.NormalIntRange( armor.DRMin(), armor.DRMax() );
		dr += Random.NormalIntRange( 0, weapon.defenseFactor( this ) );
		if (dr < 0) dr = 0;
		return dr;
	}
	
	@Override
	public int attackSkill( Char target ) {
		return (int)((12 + Dungeon.depth) * weapon.accuracyFactor( this ));
	}
	
	@Override
	protected float attackDelay() {
		return super.attackDelay() * weapon.speedFactor( this );
	}
	
	@Override
	public float speed() {
		return armor.speedFactor( this, super.speed() );
	}

	@Override
	protected boolean canAttack( Char enemy ) {
			return super.canAttack(enemy)
		 || weapon.canReach(this, enemy.pos)
		 || (new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos);
	}
	
	protected boolean doAttack( Char enemy ) {
		
		if (Dungeon.level.adjacent( pos, enemy.pos ) || weapon.canReach(this, enemy.pos)) {
			
			return super.doAttack( enemy );
			
		} else {
			
			boolean visible = fieldOfView[pos] || fieldOfView[enemy.pos];
			if (wand.curCharges > 0) {
				if (visible) {
					sprite.zap( enemy.pos );
				} else {
					zap();
				}
				wand.curCharges--;
			} else if (missile.quantity() > 0) {
				if (visible) {
					sprite.toss( enemy.pos );
				} else {
					toss();
				}
			}
			
			return !visible;
		}
	}
	
	private void zap() {
		spend( TIME_TO_ZAP );
		
		final Ballistica shot = new Ballistica( pos, enemy.pos, wand.collisionProperties);
		
		wand.rivalOnZap( shot, this );
	}
	
	private void toss() {
		spend( TIME_TO_ZAP );
		
		if (hit( this, enemy, true )) {
			enemy.damage( this.missile.damageRoll(this), this.missile.getClass() );
		} else {
			enemy.sprite.showStatus( CharSprite.NEUTRAL, enemy.defenseVerb() );
		}
	}
	
	public void onZapComplete() {
		zap();
		next();
	}
	
	public void onTossComplete() {
		toss();
		next();
	}
	
	@Override
	public void call() {
		next();
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		return weapon.proc( this, enemy, damage );
	}
	
	@Override
	public int defenseProc( Char enemy, int damage ) {
		damage = super.defenseProc( enemy, damage );
		return armor.proc( enemy, this, damage );
	}
	
	@Override
	public void damage( int dmg, Object src ) {
		super.damage( dmg, src );
		if (HP <= 0) {
			spend( TICK );
		}
	}
	
	@Override
	public void die( Object cause ) {
		if (Dungeon.depth == 21 || Dungeon.depth == 1) {
			
			//cures doom and drops missile weapons
			for (Buff buff : buffs()) {
				if (buff instanceof Doom || buff instanceof PinCushion) {
					buff.detach();
				}
			}
			
			LastLevel.State state = ((LastLevel)Dungeon.level).state();
			switch(state) {
				case BRIDGE:
					HP = 1;
					
					PotionOfHealing.cure(this);
					Buff.detach(this, Paralysis.class);
					
					((LastLevel)Dungeon.level).progress();
					
					yell( Messages.get(this, "interrobang") );
					return;
				case PHASE_1:
				case PHASE_2:
				case PHASE_3:
				case PHASE_4:
					HP = HT;
					
					PotionOfHealing.cure(this);
					Buff.detach(this, Paralysis.class);
					
					if (Dungeon.level.heroFOV[pos] && Dungeon.hero.isAlive()) {
						new Flare(8, 32).color(0xFFFF66, true).show(sprite, 2f);
						CellEmitter.get(this.pos).start(Speck.factory(Speck.LIGHT), 0.2f, 3);
						Sample.INSTANCE.play( Assets.SND_TELEPORT );
						GLog.w( Messages.get(this, "revive") );
					}
					
					((LastLevel)Dungeon.level).progress();
					
					yell( Messages.get(this, "exclamation") );
					return;
				case PHASE_5:
					((LastLevel)Dungeon.level).progress();
					super.die( cause );
					
					GameScene.bossSlain();
					
					yell( Messages.get(this, "ellipsis") );
					return;
				case WON:
				default:
			}
		} else if (Dungeon.depth == 8 || Dungeon.depth == 12 || Dungeon.depth == 16) {
			if (Dungeon.level.heroFOV[pos]) {
				sprite.emitter().start( Speck.factory(Speck.LIGHT), 0.2f, 3 );
				Sample.INSTANCE.play( Assets.SND_TELEPORT );
			}
			
			//manually drop keys on depths 12 and 16
			if (Dungeon.depth == 12 || Dungeon.depth == 16) {
				Dungeon.level.drop( new SkeletonKey( Dungeon.depth ), pos ).sprite.drop();
			}
			
			//rival isn't dead yet, so manually reward exp and drops
			Dungeon.hero.sprite.showStatus( CharSprite.POSITIVE, Messages.get(this, "exp", EXP) );
			Dungeon.hero.earnExp( EXP, enemy.getClass() );
			
			for (Buff buff : buffs()) {
				if (buff instanceof PinCushion) {
					buff.detach();
				}
			}
			
			yell( Messages.get(this, "exclamation") );
			
			Actor.remove(this);
			Dungeon.level.mobs.remove(this);
			TargetHealthIndicator.instance.target(null);
			sprite.kill();
		} else {
			super.die( cause );
			
			GameScene.bossSlain();
			
			yell( Messages.get(this, "ellipsis") );
		}
	}
	
	@Override
	public void notice() {
		super.notice();
		if (!BossHealthBar.isAssigned()) {
			BossHealthBar.assignBoss(this);
			yell(Messages.get(this, "question"));
		}
	}
	
	@Override
	public String description() {
		String desc = super.description();
		
		desc += Messages.get(this, "weapon", weapon.toString() );
		desc += Messages.get(this, "armor", armor.toString() );
		desc += Messages.get(this, "ring", misc1.toString() );
		desc += Messages.get(this, "ring", misc2.toString() );
		desc += Messages.get(this, "wand", wand.toString() );
		desc += Messages.get(this, "missile", missile.toString() );
		
		return desc;
	}
	
	{
		resistances.add( Grim.class );
		resistances.add( GrimTrap.class );
		resistances.add( ScrollOfRetribution.class );
		resistances.add( ScrollOfPsionicBlast.class );
		immunities.add( Amok.class );
		immunities.add( Corruption.class );
		immunities.add( Terror.class );
	}
}
