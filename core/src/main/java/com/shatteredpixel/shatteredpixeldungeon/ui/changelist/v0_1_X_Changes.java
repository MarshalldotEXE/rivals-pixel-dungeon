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

public class v0_1_X_Changes {
	
	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		add_v0_1_3_Changes(changeInfos);
		add_v0_1_2_Changes(changeInfos);
		add_v0_1_1_Changes(changeInfos);
	}

	public static void add_v0_1_3_Changes( ArrayList<ChangeInfo> changeInfos ){
		ChangeInfo changes = new ChangeInfo("v0.1.3", true, "");
		changes.hardlight( Window.TITLE_COLOR );
		changeInfos.add(changes);
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight( Window.TITLE_COLOR );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(Icons.get(Icons.SHPX), "Developer Commentary",
				"_-_ Released December 25th, 2019\n" +
				"_-_ 21 days after Rivals v0.1.2\n\n" +
				"Dev commentary will (probably) be added here in the future."));
		
		changes.addButton( new ChangeButton( new Image(Assets.SHADOWBANDIT, 0, 0, 12, 13), "Enemies",
				"Some more new enemies have been temporarily added for testing balance.\n\n" +
				"_-_ _Goo spawns_ are small slimes with high health and armor. They can quickly pump up to deal more damage, especially when threatened. They appear in sewers.\n" +
				"_-_ _DM-150s_ are small robots who deal high, consistent damage. They spread toxic gas and even build up a shield when stepping on traps. They appear in caves.\n" +
				"_-_ _Gold thieves_ are nimble thieves who are able to steal tons of gold from the hero, rather than their items. They appear in metropolis.\n" +
				"_-_ _Shadow bandits_ are demonic murderers who build a combo that increases their chance to bleed and blind the hero. They also appear in metropolis."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.EXOTIC_THURISAZ, null), "Alternate Scrolls",
				"Some alternate scrolls can now be crafted in alchemy instead of into exotic versions. These recipes will require non-catalyst materials to make.\n\n" +
				"_-_ Re-added the _scroll of enchantment,_ which randomly picks three options for the player to choose to enchant their selected item.\n" +
				"_-_ Added the _scroll of necromancy,_ which summons two corrupted wraiths that each fight by your side.\n" +
				"_-_ Added the _scroll of doom,_ which dooms all enemies in sight of the hero."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.SCROLL_NAUDIZ, null), "Transmutation",
				"_-_ Armor can now be transmuted into another armor of its tier.\n\n" +
				"_-_ Materials can now be transmuted into another material.\n\n" +
				"_-_ A _new ring_ can be obtained only using transmutation.\n\n" +
				"_-_ A _new wand_ can be obtained only using transmutation."));
		
		changes.addButton( new ChangeButton( new Image(Assets.TERRAIN_FEATURES, 16, 32, 16, 16), "Traps",
				"_-_ Slightly reworked the composition of traps in normal levels and trap rooms.\n\n" +
				"_-_ Traps besides worn darts can now spawn on floor 1.\n\n" +
				"_-_ _Ooze traps_ now affect everything in a 3x3 range.\n\n" +
				"_-_ Added the _melting trap,_ which gives burning, ooze, and levitation all at once.\n\n" +
				"_-_ Added the _inferno trap,_ which summons an inferno that burns enemies in the cloud.\n\n" +
				"_-_ Added the _blizzard trap,_ which summons a blizzard that freezes enemies in the cloud.\n\n" +
				"_-_ Added the _paralytic trap,_ which summons paralytic gas that paralyzes enemies in the cloud."));
		
		changes.addButton( new ChangeButton( new Image(Assets.BADGES, 96, 0, 16, 16), "Badges/Challenges",
				"Badges/challenges have been reworked:\n\n" +
				"_-_ Removed most badges that served as little achievement value.\n" +
				"_-_ Removed many of the lesser tiered badges, such as 13 strength attained, item of level 3 acquired, etc.\n" +
				"_-_ Most challenges now take the form of badges and can be obtained regardless of selecting them.\n" +
				"_-_ Selecting challenges no longer require the player to win the game once."));
		
		changes.addButton( new ChangeButton(new Image(Assets.ICONS, 16, 96, 16, 16), "Particle Effects",
				"_-_ Updated/created new particle effects for items and mobs."));
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight( CharSprite.WARNING );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton( Icons.get(Icons.DEPTH), "Generation",
				"_-_ The dungeon has been reduced to 21 depths, down from 26.\n\n" +
				"_-_ Slightly reduced the overall number of rooms per floor.\n\n" +
				"_-_ Slightly raised the overall number of special rooms per floor.\n\n" +
				"_-_ Dungeon depth-dependent durational damage has been changed accordingly.\n\n" +
				"_-_ Enemy spawnrates have been massively changed throughout the dungeon.\n\n" +
				"_-_ Token requirement for the ambitious imp's quest has been halved due to the overall less spawned enemies.\n\n" +
				"_-_ Each of the six materials now spawn once per game, instead of just limited to shops or alchemy.\n\n" +
				"_-_ Goo and DM-300 no longer drop their respective materials."));
		
		changes.addButton( new ChangeButton( new Image(Assets.KEEPER, 32, 0, 14, 14), "Shops",
				"_-_ The scroll holder or potion bandolier will now always spawn in the prison and caves shops.\n\n" +
				"_-_ The magical holster will now always spawn in the metropolis shop."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.MEAT_PIE, null), "Food",
				"_-_ Floors now have rare chances to spawn meat pies or overpriced rations instead of only rations and pasties."));
		
		changes.addButton( new ChangeButton(new Image(Assets.SPINNER, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				"Fixed:\n" +
				"_-_ Spectral fire detaching itself when in water.\n" +
				"_-_ Secret maze rooms unintentionally allowing to get natural +3 items.\n" +
				"_-_ Blue wraiths resisting Grim and Terror debuffs instead of being immune.\n" +
				"_-_ Blue wraiths and purple wraiths not being resistant/immune to Lloyd's beacon.\n" +
				"_-_ Lloyd's beacon incorrectly applying buffs to enemies as battlemage.\n" +
				"_-_ Scrolls of magical infusion causing crash when exiting inventory.\n" +
				"_-_ Scrolls of passage warping to incorrect depths.\n" +
				"_-_ Guaranteed scroll of transmutation in dungeon sometimes not spawning."));
		
		changes.addButton( new ChangeButton( new Image(Assets.WISP, 0, 0, 12, 14), "Wisp",
				"_-_ Reverted the duration of the spectral fire debuff back from v0.1.2.\n\n" +
				"_-_ Reduced base damage and chance to be debuffed at range."));
				
		changes.addButton( new ChangeButton(new Image(Assets.ICONS, 32, 96, 16, 16), "Rankings",
				"_-_ Erased runs now contribute to the total number of runs in rankings."));
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight( CharSprite.POSITIVE );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.RING_GARNET, null), "Rings",
				"_-_ All rings are now automatically identified of their type upon equipping."));
		
		changes.addButton( new ChangeButton( new Image(Assets.TRIBESMAN, 0, 16, 13, 16), "Enemies",
				"_-_ _Elite tribesmen_ and _golems_ now have 1.5x and 2x accuracy respectively to compensate for their low attack speed."));
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight( CharSprite.NEGATIVE );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.GREATSHIELD, null), "Shield Weapons",
				"_-_ Reduced the blocked damage scaling by the small shield, round shield, and greatshield by half. Base blocking unchanged."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.WAND_TRANSFUSION, null), "Wands and Rings",
				"_-_ Transfusion now gives the caster 1 less shielding.\n\n" +
				"_-_ Ring of haste now has a 15% base speed bonus, down from 20%."));
		
		changes.addButton( new ChangeButton( new Image(Assets.MONK, 0, 0, 15, 14), "Dwarf Monk",
				"_-_ Can no longer disarm chainsaws, as they are attached onto the hand.\n\n" +
				"_-_ Ration drop nerfed to overpriced rations, which can't be used in alchemy. Drop rate doubled to compensate."));
		
	}
	
	
	
	public static void add_v0_1_2_Changes( ArrayList<ChangeInfo> changeInfos ){
		ChangeInfo changes = new ChangeInfo("v0.1.2", true, "");
		changes.hardlight( Window.TITLE_COLOR );
		changeInfos.add(changes);
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight( Window.TITLE_COLOR );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(Icons.get(Icons.SHPX), "Developer Commentary",
				"_-_ Released December 4th, 2019\n" +
				"_-_ 31 days after Rivals v0.1.1\n\n" +
				"Dev commentary will (probably) be added here in the future."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.ARMOR_DISC, null), "Armor",
				"Armor was reworked!\n\n" +
				"_-_ Armor has now been split into _4 tiers._ Some armor pieces were changed to be different in tier than their usual counterparts.\n\n" +
				"_-_ _Cloth armor,_ still tier-1, now has a slightly higher dodge chance in exchange for lower base blocking. Currently limited to Faith is my armor.\n" +
				"_-_ _Mail armor,_ now tier-2, has a slightly higher dodge chance in exchange for lower base blocking.\n" +
				"_-_ _Plate armor,_ now tier-4, has a slightly lower dodge chance in exchange for higher base blocking.\n\n" +
				"_-_ Added _disc armor,_ a tier-2 armor that is slightly more efficient at blocking damage. (Sprite Credit to ConsideredHamster)\n" +
				"_-_ Added _ring armor,_ a tier-3 armor that is slightly more efficient at dodging enemies. (Sprite Credit to ConsideredHamster)\n" +
				"_-_ Added _half plate armor,_ a tier-4 armor that is slightly more efficient at dodging enemies."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.ARMOR_ROGUE, null), "Armor Cont.",
				"_-_ Added a starter _mage robe,_ a tier-1 armor that greatly resists magical damage and effects, but has lower base blocking.\n" +
				"_-_ Added a starter _rogue garb,_ a tier-1 armor that reduces your chance of being seen by enemies, but has lower base blocking.\n" +
				"_-_ Added a starter _huntress cloak,_ a tier-1 armor that significantly increases dodge chance, but has lower base blocking.\n\n" +
				"_-_ Removed obfuscation and anti-magic glyphs.\n" +
				"_-_ Modified glyph roll chances."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.HAMMER, null), "Weapons",
				"Two new transmute-only weapons added!\n\n" +
				"_-_ Added the _hammer,_ a tier-1 weapon which is rather accurate.\n" +
				"_-_ Added the _small shield,_ a tier-1 shield weapon whose blocking scales with upgrades."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.PIPE_GOLDEN, null), "Bombs",
				"Bombs have been reworked!\n\n" +
				"_-_ All bombs have a unique recipe that consists of a bomb, a certain material item, and a certain potion, scroll, or exotic version of that potion or scroll.\n" +
				"_-_ Added 7 new bombs, many of which spread gases that were removed as a consequence of removing brews.\n" +
				"_-_ Tweaked and rebalanced 5 old bombs."));
				
		changes.addButton( new ChangeButton( new Image(Assets.MIMIC, 0, 16, 16, 16), "Enemies",
				"Some more new enemies have been temporarily added for testing balance.\n\n" +
				"_-_ _Gnoll tribesmen_ and _elites_ have received a thinner sprite to cut the size of the large health bar visual.\n" +
				"_-_ _Gold mimics_ are strong mimics that have surrounded themselves with a gilded skeleton, allowing them to be resistant to the same effects as inorganic enemies.\n" +
				"_-_ _Purple wraiths_ replace both the blue and red wraiths which appear in the demon halls. They are bulky, evasive, and immune to most magic all at once."));
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight( CharSprite.WARNING );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton( new Image(Assets.KEEPER, 32, 0, 14, 14), "Shops",
				"Shop contents have been tweaked.\n\n" +
				"_-_ The prices of items have been massively rebalanced. Items are overall cheaper and sell for less.\n" +
				"_-_ Shops will now spawn a potion of shielding.\n" +
				"_-_ All shops will now feature a torch.\n" +
				"_-_ Any equipment item in the shop now has a chance to be upgraded and/or enchanted.\n" +
				"_-_ The imp's shop no longer features more upgraded equipment. Instead, he houses even more equipment for sale."));
		
		changes.addButton( new ChangeButton( Icons.get(Icons.DEPTH), "Generation",
				"_-_ A larger variety of enemies can now rarely spawn on earlier depths.\n\n" +
				"_-_ Secret runestone rooms now spawn random materials."));
		
		changes.addButton( new ChangeButton( new Image(Assets.BADGES, 112, 0, 16, 16), "Badges",
				"Badges have been slightly changed due to progression changes:\n\n" +
				"_-_ Removed the unlocked heroes badges. They are now permanently unlocked at the start of the game.\n" +
				"_-_ Reduced the requirements for level, upgrade, and strength badges.\n" +
				"_-_ All items identified badges are no longer unlocked at the start. Any new items from Rivals or its updates will have to be discovered."));
		
		changes.addButton( new ChangeButton(new Image(Assets.SPINNER, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				"Fixed:\n" +
				"_-_ Mage's staff having an additional charge when reloading save.\n" +
				"_-_ Double dart sprite was changed to a single dart sprite.\n" +
				"_-_ Furor buff having missing text.\n" +
				"_-_ Blue wraiths having a row missing in their sprite.\n" +
				"_-_ Enemies unintentionally giving EXP beyond the intended cap.\n" +
				"_-_ Pool rooms/trap rooms/crypts containing only a maximum of +1 items\n" +
				"_-_ Potions of might not updating health when reloading save.\n" +
				"_-_ Ring of elements not resisting spectral fire.\n" +
				"_-_ Hero's remains unintentionally allowing to get natural +3 items.\n" +
				"_-_ Lloyd's beacon teleporting immovable enemies."));
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight( CharSprite.POSITIVE );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.RING_OPAL, null), "Rings",
				"Rings have been mostly buffed across the board:\n\n" +
				"_-_ Buffed the _ring of tenacity,_ now has base 20% reduction, up from 15%.\n\n" +
				"_-_ Buffed the _ring of evasion,_ now gives base 20% bonus evasion, up from 15%.\n\n" +
				"_-_ Buffed the _ring of energy,_ now gives base 30% bonus wand charge speed, up from 25%.\n\n" +
				"_-_ Buffed the _ring of furor,_ now gives base 15% bonus attack speed, up from 10.5%."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.LLOYDS_BEACON, null), "Lloyd's Beacon",
				"Rebalanced Lloyd's beacon:\n\n" +
				"_-_ No longer gives many movement-hindering debuffs on non-boss floors.\n" +
				"_-_ Instead only gives vertigo, which scales with level better.\n" +
				"_-_ The beacon can also be used on the hero to teleport them to a random location."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.ARTIFACT_CHALICE3, null), "Chalice of Blood",
				"_-_ The chalice deals 33% less base damage when pricked to balance with the lower max health."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.JAVELIN, null), "Throwing Weapons",
				"_-_ All throwing weapons now have infinite durability when upgraded."));
		
		changes.addButton( new ChangeButton( new Image(Assets.THIEF, 0, 0, 12, 13), "Enemies",
				"_-_ Fast attackers like thieves and monks have had their damage output increased."));
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight( CharSprite.NEGATIVE );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.RING_DIAMOND, null), "Rings",
				"However, a few rings have been nerfed:\n\n" +
				"_-_ Changed the _ring of might,_ now only increases maximum health, being 20% at base, up from 3.5%.\n\n" +
				"_-_ Removed the _ring of force_ entirely.\n\n" +
				"_-_ Nerfed the _ring of sharpshooting,_ no longer gives any durability bonus to throwing weapons."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.MAGES_STAFF, null), "Mage's Staff",
				"_-_ No longer preserves an upgrade when imbuing wands."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.WAND_DISINTEGRATION, null), "Wand of Disintegration",
				"_-_ Reverted the change from v0.1.1. Distance is now back at 4, down from 6."));
		
		changes.addButton( new ChangeButton( new Image(Assets.GHOST, 0, 0, 14, 15), "Sad Ghost",
				"_-_ Quest no longer guarantees an upgraded item."));
				
		changes.addButton( new ChangeButton( new Image(Assets.WISP, 0, 0, 12, 14), "Wisp",
				"_-_ Cut the duration of the spectral fire debuff by half."));
		
	}
	
	
	
	public static void add_v0_1_1_Changes( ArrayList<ChangeInfo> changeInfos ){
		ChangeInfo changes = new ChangeInfo("v0.1.1", true, "");
		changes.hardlight( Window.TITLE_COLOR );
		changeInfos.add(changes);
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight( Window.TITLE_COLOR );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(Icons.get(Icons.SHPX), "Developer Commentary",
				"_-_ Released November 3rd, 2019\n" +
				"_-_ 108 days after Shattered v0.7.4\n\n" +
				"Rivals is, at last, in development!"));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.CHAIN_WHIP, null), "Melee Weapons",
				"Melee weapons were reworked!\n\n" +
				"_-_ Melee weapons are now split into _4 tiers._ Some weapons were changed to be different in tier than their usual counterparts.\n\n" +
				"_-_ The _spear_ and _glaive_ now have slightly higher minimum damage scaling.\n" +
				"_-_ The _pickaxe, battle axe,_ and _greataxe_ are now very slow tier-3 weapon with doubled base damage and scaling.\n\n" +
				"_-_ Added the _qatars,_ a very fast tier-2 weapon that is more effective when surprise attacking.\n" +
				"_-_ Added the _bludgeon,_ a rather accurate tier-3 weapon.\n" +
				"_-_ Added the _chain whip,_ a tier-4 weapon with tremendous reach.\n" +
				"_-_ Added the _chainsaw,_ an extremely fast tier-4 weapon and a nod to Sprouted."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.GLAIVE_STAR, null), "Throwing Weapons",
				"Throwing weapons were reworked!\n\n" +
				"_-_ Like melee weapons, throwing weapons were also split into _4 tiers._ Some were also changed to be different in tier than their usual counterparts.\n\n" +
				"_-_ The warrior now starts with _throwing clubs._ This is primarily a visual change.\n\n" +
				"_-_ Added _glaive stars,_ which have the properties of shurikens with damage escalated to tier-4."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.WAND_VENOM, null), "Wands",
				"Wands were slightly reworked!\n\n" +
				"_-_ Many damage wands have had reduced base damage and scaling to be more balanced with the melee weapons.\n" +
				"_-_ The _wand of venom_ makes a return! This wand spawns venom gas at a location that deals increasing damage with time. The base damage is stronger than corrosion, but it has no effect against inorganic enemies.\n" +
				"_-_ _Lloyd's beacon_ also makes a return! This wand randomly teleports targets, damaging and inflicting them with movement debuffs in the process. However, the teleportation and most of the debuffs are cancelled on boss floors."));
		
		changes.addButton( new ChangeButton( new Image(Assets.BATTLEMAGE, 0, 0, 13, 16), "Enemies",
				"New enemies have been temporarily added for testing balance.\n\n" +
				"_-_ _Swarms of fireflies_ replace the swarms of flies that normally spawn in the prison, scaled up to respective strength.\n" +
				"_-_ _Gnoll tribesmen_ attack with their spears from two tiles away in prison. Their rare form wields a much stronger, slower glaive.\n" +
				"_-_ _Blue wraiths_ are bulky, though not very evasive, magic-resisting undead that appear in prison and the demon halls.\n" +
				"_-_ _Gnoll battlemages_ replace the shamans that normally spawn in caves, scaled up to respective strength.\n" +
				"_-_ _Red wraiths_ are highly damaging undead that are immune to most sources of magic, appearing in metropolis and the demon halls.\n" +
				"_-_ _Wisps_ are ranged, although short, attackers that inflict enemies with a less dynamic form of the fire debuff, called spectral fire. They appear in the demon halls."));
		
		changes.addButton( new ChangeButton( new Image(Assets.ICONS, 0, 96, 16, 16), "Alchemy",
				"Alchemy has been entirely reworked!\n\n" +
				"_-_ A majority of the alchemy items are now crafted using one of six _materials,_ which can be either made themselves or bought from shops.\n" +
				"_-_ Runestones, brews, elixirs, and spells have been entirely removed. Their effects will be reincorporated into some new items.\n" +
				"_-_ Replaced potions of paralytic gas with potions of _shielding,_ made with earthroot seeds.\n" +
				"_-_ Reworked some exotic potions and scrolls."));
		
		changes.addButton( new ChangeButton( new Image(Assets.TERRAIN_FEATURES, 80, 32, 16, 16), "Traps",
				"_-_ Trap rooms have a much larger variety of traps that can spawn in them.\n\n" +
				"_-_ Added the _venom trap,_ which summons venom gas that doesn't hurt inorganic enemies."));
				
		changes.addButton( new ChangeButton( new Image(Assets.TERRAIN_FEATURES, 80, 112, 16, 16), "Plants",
				"_-_ Added 2 new plants, being _shinestem_ and _blighttrap,_ each with respective plant counterparts.\n\n" +
				"_-_ Along with the new plants, added the new potions of _caustic ooze_ and _electricity._"));
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight( CharSprite.WARNING );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.POTION_CRIMSON, null), "Progression",
				"Looks like the dungeon has much less room for progression...\n\n" +
				"_-_ The hero can only reach a max experience level of 20.\n" +
				"_-_ Gaining EXP from enemies at higher levels is now much more limited.\n" +
				"_-_ Only 5 potions of strength now spawn in the dungeon.\n" +
				"_-_ Only 8 scrolls of upgrade now spawn in the dungeon.\n" +
				"_-_ Items which cannot be upgraded by scrolls have a blue tint to their upgrade level and name."));
		
		changes.addButton( new ChangeButton( new Image(Assets.KEEPER, 32, 0, 14, 14), "Shops",
				"Shop contents have been heavily changed.\n\n" +
				"_-_ Two random materials will now spawn in every shop.\n" +
				"_-_ An arcane stylus will now spawn in every shop.\n" +
				"_-_ Shops will now feature a random exotic potion and scroll.\n" +
				"_-_ Shops no longer guarantee a scroll of magic mapping.\n" +
				"_-_ Every shop will now spawn either a random wand, ring, or artifact.\n" +
				"_-_ The imp's shop features some upgraded equipment.\n" +
				"_-_ Shops no longer spawn runestones of augmentation."));
		
		changes.addButton( new ChangeButton( new Image(Assets.BRUTE, 0, 0, 12, 16), "Enemies",
				"_-_ All enemies have been rebalanced to suit the hero's new level cap of 20.\n\n" +
				"_-_ Bosses (and soon, the Rival) now give double experience.\n\n" +
				"_-_ Golems now have 0.5x attack speed, down from 0.67x. Damage increased to compensate.\n\n" +
				"_-_ Mutants are now guaranteed to spawn on lower depths of an area."));
		
		changes.addButton( new ChangeButton( new Image(Assets.MAKER, 0, 0, 12, 14), "Quests",
				"_-_ All item-giving quest NPCs are now guaranteed to give a +1 or better item of their reward, except the imp, which always gives a +2 ring.\n" +
				"_-_ The troll blacksmith quest has been disabled temporarily."));
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight( CharSprite.POSITIVE );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.WAND_REGROWTH5, null), "Item Buffs",
				"_-_ Fishing spears no longer halve the health of piranhas.\n\n" +
				"_-_ The wand of disintegration now has a distance of 6 at base, up from 4.\n\n" +
				"_-_ The wand of frost now deals 3-10 damage at base, up from 2-8.\n\n" +
				"_-_ The wand of transfusion now deals 1-6 damage at base and scales at +1/+2 against the undead.\n\n" +
				"_-_ The wand of transfusion's charm also scales with level slightly.\n\n" +
				"_-_ The wand of regrowth now casts infinitely at +9, down from +12.\n\n" +
				"_-_ Most wands now have 3 max charges at base. The mage's staff no longer gives an extra charge to compensate."));
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.EXOTIC_TIWAZ, null), "Generation Buffs",
				"_-_ Runestone rooms are now exotic rooms which spawn 1-2 exotic scrolls.\n\n" +
				"_-_ Runestones in alchemy rooms instead spawn exotic potions.\n\n" +
				"_-_ Instead of a stone of enchantment, a scroll of transmutation now spawns somewhere on chapter 2-4.\n\n" +
				"_-_ Instead of a stone of intuition, a scroll of identify now spawns somewhere on chapter 1."));
		
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight( CharSprite.NEGATIVE );
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(new ItemSprite(ItemSpriteSheet.SWORD, null), "Upgrade Nerfs",
				"_-_ Items can now only be found naturally at +2 maximum in any scenario. This means items can only be at a maximum of +10 at any time."));
		
	}
}
