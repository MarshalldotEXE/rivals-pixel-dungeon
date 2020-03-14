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

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Roots;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.EarthParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Earthroot;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.Collections;

public class SandalsOfNature extends Artifact {

	{
		image = ItemSpriteSheet.ARTIFACT_SANDALS;
		//passive, no default action
		
		//no exp
		levelCap = 3;

		//no charge cap
		//no partial charge cap
		//no charge cap
	}

	public static final String AC_FEED = "FEED";

	protected WndBag.Mode mode = WndBag.Mode.SEED;

	public ArrayList<Class> seeds = new ArrayList<>();

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if (isEquipped( hero ) && level() < 3 && !cursed)
			actions.add(AC_FEED);
		return actions;
	}

	@Override
	public void execute( Hero hero, String action ) {
		super.execute(hero, action);

		if (action.equals(AC_FEED)){

			GameScene.selectItem(itemSelector, mode, Messages.get(this, "prompt"));

		}
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new Naturalism();
	}
	
	@Override
	public void charge(Hero target) {
		//see HighGrass
	}

	@Override
	public String desc() {
		String desc = Messages.get(this, "desc_" + (level()+1));

		if ( isEquipped ( Dungeon.hero ) ){
			desc += "\n\n";

			if (!cursed)
				desc += Messages.get(this, "desc_hint");
			else
				desc += Messages.get(this, "desc_cursed");
		}

		if (!seeds.isEmpty()){
			desc += "\n\n" + Messages.get(this, "desc_seeds", seeds.size());
		}

		return desc;
	}

	@Override
	public Item upgrade() {
		if (level() < 0)        image = ItemSpriteSheet.ARTIFACT_SANDALS;
		else if (level() == 0)  image = ItemSpriteSheet.ARTIFACT_SHOES;
		else if (level() == 1)  image = ItemSpriteSheet.ARTIFACT_BOOTS;
		else if (level() >= 2)  image = ItemSpriteSheet.ARTIFACT_GREAVES;
		name = Messages.get(this, "name_" + (level()+1));
		return super.upgrade();
	}

	private static final String SEEDS = "seeds";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put(SEEDS, seeds.toArray(new Class[seeds.size()]));
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		if (level() > 0) name = Messages.get(this, "name_" + level());
		if (bundle.contains(SEEDS))
			Collections.addAll(seeds , bundle.getClassArray(SEEDS));
		if (level() == 1)  image = ItemSpriteSheet.ARTIFACT_SHOES;
		else if (level() == 2)  image = ItemSpriteSheet.ARTIFACT_BOOTS;
		else if (level() >= 3)  image = ItemSpriteSheet.ARTIFACT_GREAVES;
	}

	public class Naturalism extends ArtifactBuff{
		
	}

	protected WndBag.Listener itemSelector = new WndBag.Listener() {
		@Override
		public void onSelect( Item item ) {
			if (item != null && item instanceof Plant.Seed) {
				if (seeds.contains(item.getClass())){
					GLog.w( Messages.get(SandalsOfNature.class, "already_fed") );
				} else {
					seeds.add(item.getClass());

					Hero hero = Dungeon.hero;
					hero.sprite.operate( hero.pos );
					Sample.INSTANCE.play( Assets.SND_PLANT );
					hero.busy();
					hero.spend( 2f );
					int requirement = 3 + Math.round(3.5f * level());
					if (seeds.size() >= requirement){
						seeds.clear();
						upgrade();
						if (level() >= 1 && level() <= 3) {
							GLog.p( Messages.get(SandalsOfNature.class, "levelup") );
						}

					} else {
						GLog.i( Messages.get(SandalsOfNature.class, "absorb_seed") );
					}
					item.detach(hero.belongings.backpack);
				}
			}
		}
	};

}
