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

package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Regrowth;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.StormCloud;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Lightning;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.BlastParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.FlameParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SmokeParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfPower;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Unstable;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.ColorMath;
import com.watabou.utils.PathFinder;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class WandOfChaos extends Wand {

	{
		image = ItemSpriteSheet.WAND_CHAOS;
	}
	
	private int randomCast = Random.Int(5);
	
	@Override
	public void onZap( Ballistica bolt ) {
		this.chaosZap( bolt,
			randomCast,
			level()+
			RingOfPower.levelDamageBonus(Dungeon.hero) );
		randomCast = Random.Int(5);
	}
	
	private void chaosZap( Ballistica bolt, int castType, int lvl ) {
		int damage;
		Char ch;
		switch(castType) {
			default:
			case 0: //magic missile
				ch = Actor.findChar(bolt.collisionPos);
				if (ch != null) {
					damage = Random.IntRange(
							2+lvl+Dungeon.hero.lvl/4,
							8+2*lvl+Dungeon.hero.lvl/2);
					processSoulMark(ch, chargesPerCast());
					ch.damage(damage, this);
					ch.sprite.burst(0xFFFFFFFF, lvl / 2 + 2);
				}
				break;
			case 1: //thunderbolt
				ch = Actor.findChar(bolt.collisionPos);
				if (ch != null) {
					damage = Random.IntRange(
							3+lvl,
							10+4*lvl);
					processSoulMark(ch, chargesPerCast());
					ch.damage(damage, this);
				}
				if (Dungeon.level.heroFOV[bolt.collisionPos]) {
					CellEmitter.center(bolt.collisionPos).burst(BlastParticle.FACTORY, 30);
					Sample.INSTANCE.play( Assets.SND_BLAST );
				}
				for (int i : PathFinder.NEIGHBOURS8) {
					int AOE = bolt.collisionPos + i;
					if (AOE >= 0 && AOE < Dungeon.level.length()) {
						ch = Actor.findChar(AOE);
						if (ch != null) {
							damage = Random.IntRange(
									1+lvl,
									6+2*lvl);
							processSoulMark(ch, chargesPerCast());
							ch.damage(damage, this);
						}
						if (Dungeon.level.heroFOV[AOE]) {
							CellEmitter.get(AOE).burst(SmokeParticle.FACTORY, 4);
						}
					}
				}
				break;
			case 2: //frostfire
				ch = Actor.findChar(bolt.collisionPos);
				if (ch != null) {
					damage = Random.IntRange(
							4+2*lvl,
							16+6*lvl);
					processSoulMark(ch, chargesPerCast());
					ch.damage(damage, this); //second hit
					if (ch.isAlive() && ch.buff(Frost.class) == null) {
						if (Dungeon.level.water[ch.pos])
							Buff.prolong(ch, Chill.class, 4+lvl);
						else
							Buff.prolong(ch, Chill.class, 2+lvl);
					}
				}
				CellEmitter.get(bolt.collisionPos).burst(FlameParticle.FACTORY, 6);
				break;
			case 3: //flaming regrowth
				ch = Actor.findChar(bolt.collisionPos);
				if (ch != null) {
					damage = Random.IntRange(
							1+lvl,
							6+2*lvl);
					processSoulMark(ch, chargesPerCast());
					ch.damage(damage, this);
				}
				GameScene.add(Blob.seed(bolt.collisionPos, 30, Regrowth.class));
				GameScene.add(Blob.seed(bolt.collisionPos, 2, Fire.class));
				break;
			case 4: //electric storm cloud
				ch = Actor.findChar(bolt.collisionPos);
				if (ch != null) {
					damage = Random.IntRange(
							3+lvl,
							10+4*lvl);
					processSoulMark(ch, chargesPerCast());
					ch.damage(damage, this);
				}
				GameScene.add(Blob.seed(bolt.collisionPos, 50, StormCloud.class));
				GameScene.add(Blob.seed(bolt.collisionPos, 10, Electricity.class));
				break;
			case 5:
				
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			case 11:
				break;
			case 12:
				break;
			case 13:
				break;
			case 14:
				break;
			case 15:
				break;
		}
	}
	
	public void fx(Ballistica bolt, Char caster, Callback callback) {
		switch(randomCast) {
			default:
			case 0: //magic missile
				MagicMissile.boltFromChar( caster.sprite.parent,
					MagicMissile.MAGIC_MISSILE,
					caster.sprite,
					bolt.collisionPos,
					callback);
				Sample.INSTANCE.play( Assets.SND_ZAP );
				break;
			case 1: //thunderbolt
				caster.sprite.parent.addToFront( new Lightning( caster.pos, bolt.collisionPos, null ) );
				callback.call();
				break;
			case 2: //frostfire
				MagicMissile.boltFromChar(caster.sprite.parent,
					MagicMissile.FIRE,
					caster.sprite,
					bolt.collisionPos,
					null);
				MagicMissile.boltFromChar(caster.sprite.parent,
					MagicMissile.FROST,
					caster.sprite,
					bolt.collisionPos,
					callback);
				Sample.INSTANCE.play(Assets.SND_ZAP);
				break;
			case 3: //flaming regrowth
				MagicMissile.boltFromChar(caster.sprite.parent,
					MagicMissile.FIRE,
					caster.sprite,
					bolt.collisionPos,
					null);
				MagicMissile.boltFromChar(caster.sprite.parent,
					MagicMissile.FOLIAGE,
					caster.sprite,
					bolt.collisionPos,
					callback);
				Sample.INSTANCE.play(Assets.SND_ZAP);
				break;
			case 4: //electric storm cloud
				MagicMissile.boltFromChar(caster.sprite.parent,
					MagicMissile.WATER,
					caster.sprite,
					bolt.collisionPos,
					null);
				MagicMissile.boltFromChar(caster.sprite.parent,
					MagicMissile.SPARK,
					caster.sprite,
					bolt.collisionPos,
					callback);
				Sample.INSTANCE.play(Assets.SND_ZAP);
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			case 11:
				break;
			case 12:
				break;
			case 13:
				break;
			case 14:
				break;
			case 15:
				break;
		}
	}
	
	@Override
	public void onHit(MagesStaff staff, Char attacker, Char defender, int damage) {
		new Unstable().proc( staff, attacker, defender, damage);
	}
	
	@Override
	public void staffFx(MagesStaff.StaffParticle particle) {
		particle.color(ColorMath.random(0xFFD400, 0x994000));
		particle.am = 0.6f;
		particle.setLifespan(0.6f);
		particle.acc.set(0, +10);
		particle.speed.polar(-Random.Float(3.1415926f), 6f);
		particle.setSize(0f, 1.5f);
		particle.sizeJitter = 1f;
		particle.shuffleXY(1f);
		float dst = Random.Float(1f);
		particle.x -= dst;
		particle.y += dst;
	}
	
	protected int initialCharges() {
		return 2;
	}

}
