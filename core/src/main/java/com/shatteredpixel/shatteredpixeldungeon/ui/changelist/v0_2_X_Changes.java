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

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.effects.BadgeBanner;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.ChangesScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Image;

import java.util.ArrayList;

public class v0_2_X_Changes {
	
	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		add_v0_2_0_Changes(changeInfos);
	}

	public static void add_v0_2_0_Changes( ArrayList<ChangeInfo> changeInfos ){
		ChangeInfo changes = new ChangeInfo("v0.2.0", true, "");
		changes.hardlight( Window.TITLE_COLOR );
		changeInfos.add(changes);
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight( Window.TITLE_COLOR );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(Icons.get(Icons.SHPX), "Developer Commentary",
				"_-_ Released MMMMMMMM DDth, 2020\n" +
				"_-_ DDD days after Rivals v0.1.3\n\n" +
				"Dev commentary will (probably) be added here in the future."));
		
		changes.addButton( new ChangeButton(new Image(Assets.ROGUE, 0, 135, 12, 15), "The Rival",
				"Added _the Rival,_ the first and final boss all in one!\n\n" +
				"_-_ Removed _the Goo, Tengu, DM-300, the King of Dwarves, and Yog-Dzewa._\n" +
				"_-_ Added the Rival to depths 8, 12, 16, and 21.\n" +
				"_-_ Filled Tengu's, DM-300's, and the King of Dwarves' floors with water, vegetation, and decoration.\n" +
				"_-_ Replaced Yog-Dzewa's floor with a normal demon halls floor.\n" +
				"_-_ Added a complete redux to depth 21, where the final Rival fight takes place..."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.ARMOR_PLATE, new ItemSprite.Glowing( 0x33CCFF )), "New Glyphs",
				"_-_ Added the _glyph of weightlessness!_ This glyph provides the wearer with levitation, as long as they aren't over a chasm.\n\n" +
				"_-_ Added the _glyph of nova!_ This glyph builds up destructive energy when being attacked. After being completely charged, it can explode at will, destroying the armor, but dealing insanely high damage to anything nearby."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.ARCANE_KUNAI, null), "Arcane Kunai",
				"_-_ Added the _arcane kunai!_ This _tier-3_ missile weapon deals some hefty damage, working quite well when surprise attacking.\n\n" +
				"_-_ Additionally, it also has the ability to reroll glyphs on armor..."));
		
		changes.addButton( new ChangeButton( new Image(Assets.IMP, 0, 14, 12, 14), "New Enemies",
				"Added some new, permanent enemies that will stay in-game.\n" +
				"In order of appearance:\n\n" +
				"_-_ Added frail _malevolent imps,_ which alert nearby enemies, inflict debuffs, and flee when threatened. They appear in demon halls.\n" +
				"_-_ Added rare _gold mimics,_ which often have better loot, but have greater damage output and health.\n" +
				"_-_ Added rare _crystal mimics,_ which often have better loot, but have greater evasion and blink magic.\n" +
				"_-_ Added rare _monster boxes,_ which can spawn many enemies at a time when defeated.\n" +
				"_-_ Added the _newborn wisp..._"));
		
		changes.addButton( new ChangeButton( new Image(Assets.TROLL, 0, 0, 13, 16), "Troll Blacksmith",
				"_-_ The troll blacksmith has returned with a new quest reward!\n\n" +
				"_-_ Instead of sacrificing an item to upgrade it, he will take any one item and upgrade it to +2.\n\n" +
				"_-_ Additionally, he'll even let you keep the pickaxe!"));
		
		changes.addButton( new ChangeButton( new Image(Assets.BADGES, 64, 16, 16, 16), "Badges",
				"Added three new multi-run badges:\n\n" +
				"_-_ Obtain one of every wand (excluding magic missile) in a single run.\n" +
				"_-_ Obtain one of every ring in a single run.\n" +
				"_-_ Obtain every artifact (excluding the cloak of shadows) in a single run."));
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight( CharSprite.WARNING );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.WAR_HAMMER, null), "Equipment Items",
				"_-_ Items now have a certain chance to be upgraded, cursed, and/or enchanted depending on the dungeon region.\n\n" +
				"_-_ Typically, the deeper the item spawns, the higher chance for the item to be upgraded, cursed, and/or enchanted.\n\n" +
				"_-_ _Tier-2_ equipment no longer appears in metropolis.\n\n" +
				"_-_ _Tier-3_ equipment no longer appears in demon halls.\n\n" +
				"_-_ Items are now automatically identified on equip.\n\n" +
				"_-_ Arcane styli have been removed and replaced with scrolls of enchantment."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.LONGSWORD, new ItemSprite.Glowing( 0x0099FF )), "Enchantments",
				"Many enchantment procs now scale with level and damage as well as have a secondary effect that can trigger.\n\n" +
				"_-_ _Blazing's_ bonus damage scaling with level.\n" +
				"_-_ _Chilling's_ chill buff no longer has a cap, now has slightly reduced duration per proc on average.\n" +
				"_-_ _Shocking's_ arc damage now scales with level.\n" +
				"_-_ _Kinetic's_ conserved damage now increases slightly with level.\n" +
				"_-_ _Blooming_ will now always try to spawn two grass, with a chance of spawning one extra, at the user's location.\n" +
				"_-_ _Elastic_ now has a chance to knock back three tiles instead of two.\n" +
				"_-_ _Vampiric's_ heal amount now scales with lost health, instead of heal chance scaling with lost health.\n\n" +
				"_-_ _Corrupting_ mostly unchanged.\n" +
				"_-_ _Grim_ mostly unchanged.\n\n" +
				"_-_ Removed _blocking_ enchantment.\n" +
				"_-_ Removed _projecting_ enchantment.\n" +
				"_-_ Removed _lucky_ enchantment."));
				
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.ARMOR_SCALE, new ItemSprite.Glowing( 0xFF3300 )), "Enchantments",
				"Many glyph procs and effects have also been overhauled.\n\n" +
				"_-_ _Potential's_ charge amount now scales with level.\n" +
				"_-_ _Swiftness_ has been slightly nerfed in scaling with upgrades.\n" +
				"_-_ _Repulsion_ has an additional chance to knock back three tiles instead of two.\n" +
				"_-_ _Flow_ has been slightly nerfed in base speed, but it now scales with upgrades.\n" +
				"_-_ _Brimstone_ now creates magma armor, which lowers evasion but raises minimum armor.\n" +
				"_-_ _Viscosity_ now sometimes converts all damage to deferred damage, which fades quicker.\n" +
				"_-_ _Thorns_ has an additional chance to cause even more severe bleeding.\n\n" +
				"_-_ _Camouflage_ mostly unchanged.\n\n" +
				"_-_ Removed glyph of _affection._\n" +
				"_-_ Removed glyph of _stone._\n" +
				"_-_ Removed glyph of _entanglement._"));
		
		changes.addButton( new ChangeButton(new Image(Assets.SPINNER, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				"Fixed:\n" +
				"_-_ Throwing clubs appearing in remains.\n" +
				"_-_ Being able to descend on floor 21.\n" +
				"_-_ Transmuted armor losing its broken seal.\n" +
				"_-_ Shield weapons incorrectly displaying higher defense stats.\n" +
				"_-_ Armor displaying incorrectly in the start game menu after restarting game."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.ARTIFACT_HORN1, null), "Artifacts",
				"Reduxed artifact mechanics and methods to upgrade!\n\n" +
				"_-_ The _Alchemist's Toolkit_ scales less due to alchemy nerfs. Now has a cap at 50 energy.\n" +
				"_-_ The _Chalice of Blood_ deals slightly more damage when pricking. Typically only relevent early-game.\n" +
				"_-_ The _Cloak of Shadows_ recharges slightly faster. Recharge bonus at higher levels decreased.\n" +
				"_-_ The _Ethereal Chains_ recharge only by experience. Now has a cap of 50 charges.\n" +
				"_-_ The _Horn of Plenty_ recharges slightly more at lower levels and significantly less at higher levels.\n" +
				"_-_ The _Master Thieves' Armband_ is rebalanced due to globally lower item prices.\n" +
				"_-_ The _Footwear of Nature_ no longer charges herbal armor. Now uses slightly more seeds to upgrade.\n" +
				"_-_ The _Talisman of Foresight_ recharges significantly faster. Now upgrades itself while discovering deeper floors.\n" +
				"_-_ The _Unstable Spellbook_ is unchanged.\n\n" +
				"_-_ Removed the _Dried Rose._\n" +
				"_-_ Removed the _Timekeeper's Hourglass._"));
		
		changes.addButton( new ChangeButton( new Image(Assets.KEEPER, 32, 0, 14, 14), "Shops",
				"_-_ Shop items are now never cursed.\n\n" +
				"_-_ Shops will now include each a wand, ring, and artifact.\n\n" +
				"_-_ Shops will now include both an alchemical and arcane catalyst.\n\n" +
				"_-_ Shop bombs are now always in quantities of two.\n\n" +
				"_-_ Shops will now spawn one random food item.\n\n" +
				"_-_ Shops no longer sell exotic items.\n\n" +
				"_-_ The ambitious imp's shop items are now always at least +1."));
		
		changes.addButton( new ChangeButton( new Image(Assets.GNOLL, 0, 0, 12, 15), "Enemies",
				"Most of the new enemies for testing have been removed. Most will be re-added in a later update, but some will still stay in-game.\n" +
				"In order of appearance:\n\n" +
				"_-_ Kept _goo spawns._\n" +
				"_-_ Removed _swarms of fireflies,_ replaced by buffed swarms of flies.\n" +
				"_-_ Removed _gnoll tribesmen._\n" +
				"_-_ Removed _blue wraiths._\n" +
				"_-_ Kept _gnoll battlemages._\n" +
				"_-_ Kept _DM-150s._\n" +
				"_-_ Removed _gold thieves._\n" +
				"_-_ Removed _shadow bandits._\n" +
				"_-_ Removed _red wraiths._\n" +
				"_-_ Removed _purple wraiths._\n" +
				"_-_ Removed _wisps._\n\n" +
				"Mimics are no longer considered demonic.\n\n" +
				"Overall increased the quantity of enemies per depth and decreased the frequency of enemy respawns.\n\n" +
				"Increased the chance for mutant enemies to spawn, especially on the lowest standard floor of a region."));
		
		changes.addButton( new ChangeButton( new Image(Assets.TERRAIN_FEATURES, 128, 32, 16, 16), "Visuals",
				"_-_ Updated many various visuals and effects."));
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight( CharSprite.POSITIVE );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.CHEST, null), "Item Generation",
				"_-_ Slightly increased the average number of items that can spawn per level.\n\n" +
				"_-_ Potions of strength now spawn on either the first or second depth of a region.\n\n" +
				"_-_ Bone piles now always spawn wraiths if they first contained a cursed item.\n\n" +
				"_-_ The rogue no longer received an increased spawn rate of secret rooms."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.SEAL, null), "Broken Seal",
				"_-_ The broken seal's shielding now regenerates significantly faster to give the warrior a better overall game."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.WAND_MAGIC_MISSILE, null), "Wand of Magic Missile",
				"_-_ Magic missile now also scales with hero level to give the mage a better early-game."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.KUNAI, null), "Missile Weapons",
				"_-_ Overall increased the base damage of missile weapons.\n\n" +
				"_-_ Base durability is now more consistent and increases with tier.\n\n" +
				"_-_ Slightly increased the chance of getting more missile weapons per stack.\n\n" +
				"_-_ Missile weapons can now rarely spawn upgraded."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.PICKAXE, null), "Pickaxe",
				"_-_ Base damage increased to 8-25, from 8-20.\n\n" +
				"_-_ Damage scaling increased to +1/+3, from +1/+2.\n\n" +
				"_-_ Strength requirement increased to 11, from 10."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.WEAPON_HOLDER, null), "Unarmed",
				"_-_ Increased the base damage of unarmed attacks.\n\n" +
				"_-_ Unarmed attacks now have double base attack speed."));
		
		changes.addButton( new ChangeButton( new Image(Assets.GHOST, 0, 0, 14, 15), "Quests",
				"_-_ The sad ghost now provides the names of the items they are rewarding.\n\n" +
				"_-_ The sad ghost can now provide tier-1 items, which are always at least +1.\n\n" +
				"_-_ The young wandmaker now has a slightly higher chance to reward +2 wands.\n\n" +
				"_-_ The sad ghost and young wandmaker will now show the upgrade level of their items if they are upgraded."));
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight( CharSprite.NEGATIVE );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.GLOVES, null), "Fast Weapons",
				"_-_ Reduced minimum damage scaling for all fast weapons.\n\n" +
				"_-_ Studded gloves base damage reduced to 1-5, from 1-6."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.WAND_VENOM, null), "Wand of Venom",
				"_-_ The wand of venom has been removed from spawning. It will be reduxed in a later update."));
		
		changes.addButton( new ChangeButton( new Image(Assets.ICONS, 0, 112, 16, 16), "Alchemy",
				"_-_ Overall reduced the amount of alchemical energy available late-game."));
		
	}
	
	
	
}
