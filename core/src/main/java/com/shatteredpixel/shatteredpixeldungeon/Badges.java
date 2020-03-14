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

package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.EtherealChains;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.MasterThievesArmband;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.SandalsOfNature;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TalismanOfForesight;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.UnstableSpellbook;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.VelvetPouch;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfAccuracy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEvasion;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFuror;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfMight;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfPower;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfSharpshooting;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfTenacity;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfWealth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorrosion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorruption;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfDisintegration;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFireblast;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFirebolt;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLightning;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfPrismaticLight;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfRegrowth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfTransfusion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfWarding;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.LloydsBeacon;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Badges {
	
	public enum Badge {
		//single-run
		MONSTERS_SLAIN( 0 ),
		
		GOLD_COLLECTED( 1 ),
		
		LEVEL_REACHED( 2 ),
		
		STRENGTH_ATTAINED( 3 ),
		STRENGTH_ATTAINED_SUPER( 4 ),
		
		ITEM_LEVEL( 5 ),
		ITEM_LEVEL_SUPER( 6 ),
		
		//multi-run
		DEATH_FROM_FIRE,
		DEATH_FROM_POISON,
		DEATH_FROM_GAS,
		DEATH_FROM_HUNGER,
		DEATH_FROM_GLYPH,
		DEATH_FROM_FALLING,
		YASD( 8, true ),
		
		ALL_WEAPONS_IDENTIFIED,
		ALL_ARMOR_IDENTIFIED,
		ALL_WANDS_IDENTIFIED,
		ALL_RINGS_IDENTIFIED,
		ALL_ARTIFACTS_IDENTIFIED,
		ALL_POTIONS_IDENTIFIED,
		ALL_SCROLLS_IDENTIFIED,
		CATALOG_COMPLETE( 9, true ),
		
		ALL_WANDS_OBTAINED( 10, true ),
		ALL_RINGS_OBTAINED( 11, true ),
		ALL_ARTIFACTS_OBTAINED( 12, true ),
		
		HAPPY_END( 13 ),
		
		GAMES_PLAYED( 15 ),
		
		//victory
		VICTORY( 16, true ),
		
		VICTORY_WARRIOR,
		VICTORY_MAGE,
		VICTORY_ROGUE,
		VICTORY_HUNTRESS,
		VICTORY_SUPER( 17, true ),
		
		VICTORY_GLADIATOR,
		VICTORY_BERSERKER,
		VICTORY_WARLOCK,
		VICTORY_BATTLEMAGE,
		VICTORY_FREERUNNER,
		VICTORY_ASSASSIN,
		VICTORY_SNIPER,
		VICTORY_WARDEN,
		VICTORY_SUPER_SUPER( 18, true ),
		
		CHAMPION_A( 19, true ),
		CHAMPION_B( 20, true ),
		CHAMPION_C( 21, true ),
		
		//challenges
		PHARMACOPHOBIA( 32 ),
		FORBIDDEN_RUNES( 33 ),
		ON_DIET( 34 ),
		FAITH_ARMOR( 35 ),
		MERCHANT_WARS( 36 ),
		CLASSICIST( 37 ),
		SPEEDRUN( 38 ),
		PACIFIST( 39 ),
		
		BARREN_LAND( 48 ),
		SWARM_INTELLIGENCE( 49 ),
		INTO_DARKNESS( 50 );

		public boolean meta;

		public int image;
		
		Badge( int image ) {
			this( image, false );
		}
		
		Badge( int image, boolean meta ) {
			this.image = image;
			this.meta = meta;
		}

		public String desc(){
			return Messages.get(this, name());
		}
		
		Badge() {
			this( -1 );
		}
	}
	
	private static HashSet<Badge> global;
	private static HashSet<Badge> local = new HashSet<Badges.Badge>();
	
	private static boolean saveNeeded = false;

	public static void reset() {
		local.clear();
		loadGlobal();
	}
	
	public static final String BADGES_FILE	= "badges.dat";
	private static final String BADGES		= "badges";
	
	private static final HashSet<String> removedBadges = new HashSet<>();
	static{
		//removed in 0.6.5
		removedBadges.addAll(Arrays.asList("RARE_ALBINO", "RARE_BANDIT", "RARE_SHIELDED",
				"RARE_SENIOR", "RARE_ACIDIC", "RARE", "TUTORIAL_WARRIOR", "TUTORIAL_MAGE"));
	}

	private static final HashMap<String, String> renamedBadges = new HashMap<>();
	static{
		//0.6.5
		renamedBadges.put("CHAMPION", "CHAMPION_1");
	}

	public static HashSet<Badge> restore( Bundle bundle ) {
		HashSet<Badge> badges = new HashSet<>();
		if (bundle == null) return badges;
		
		String[] names = bundle.getStringArray( BADGES );
		for (int i=0; i < names.length; i++) {
			try {
				if (renamedBadges.containsKey(names[i])){
					names[i] = renamedBadges.get(names[i]);
				}
				if (!removedBadges.contains(names[i])){
					badges.add( Badge.valueOf( names[i] ) );
				}
			} catch (Exception e) {
				ShatteredPixelDungeon.reportException(e);
			}
		}
	
		return badges;
	}
	
	public static void store( Bundle bundle, HashSet<Badge> badges ) {
		int count = 0;
		String names[] = new String[badges.size()];
		
		for (Badge badge:badges) {
			names[count++] = badge.toString();
		}
		bundle.put( BADGES, names );
	}
	
	public static void loadLocal( Bundle bundle ) {
		local = restore( bundle );
	}
	
	public static void saveLocal( Bundle bundle ) {
		store( bundle, local );
	}
	
	public static void loadGlobal() {
		if (global == null) {
			try {
				Bundle bundle = FileUtils.bundleFromFile( BADGES_FILE );
				global = restore( bundle );
				
			} catch (IOException e) {
				global = new HashSet<Badge>();
			}
		}
	}

	public static void saveGlobal() {
		if (saveNeeded) {
			
			Bundle bundle = new Bundle();
			store( bundle, global );
			
			try {
				FileUtils.bundleToFile(BADGES_FILE, bundle);
				saveNeeded = false;
			} catch (IOException e) {
				ShatteredPixelDungeon.reportException(e);
			}
		}
	}

	public static void validateMonstersSlain() {
		Badge badge = null;
		
		if (!local.contains( Badge.MONSTERS_SLAIN ) && Statistics.enemiesSlain >= 150) {
			badge = Badge.MONSTERS_SLAIN;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateGoldCollected() {
		Badge badge = null;
		
		if (!local.contains( Badge.GOLD_COLLECTED ) && Statistics.goldCollected >= 7500) {
			badge = Badge.GOLD_COLLECTED;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateLevelReached() {
		Badge badge = null;
		
		if (!local.contains( Badge.LEVEL_REACHED ) && Dungeon.hero.lvl >= 20) {
			badge = Badge.LEVEL_REACHED;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateStrengthAttained() {
		Badge badge = null;
		
		if (!local.contains( Badge.STRENGTH_ATTAINED ) && Dungeon.hero.STR >= 15) {
			badge = Badge.STRENGTH_ATTAINED;
			local.add( badge );
		}
		if (!local.contains( Badge.STRENGTH_ATTAINED_SUPER ) && Dungeon.hero.STR >= 16) {
			badge = Badge.STRENGTH_ATTAINED_SUPER;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateItemLevelAquired( Item item ) {
		
		// This method should be called:
		// 1) When an item is obtained (Item.collect)
		// 2) When an item is upgraded (ScrollOfUpgrade, ScrollOfWeaponUpgrade, ShortSword, WandOfMagicMissile)
		// 3) When an item is identified

		// Note that artifacts should never trigger this badge as they are alternatively upgraded
		if (!item.levelKnown || item instanceof Artifact) {
			return;
		}
		
		Badge badge = null;
		if (!local.contains( Badge.ITEM_LEVEL ) && item.level() >= 8) {
			badge = Badge.ITEM_LEVEL;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEM_LEVEL_SUPER ) && item.level() >= 10) {
			badge = Badge.ITEM_LEVEL_SUPER;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateCatalogComplete() {
		
		for (Catalog cat : Catalog.values()){
			if (cat.allSeen()){
				Badge b = Catalog.catalogBadges.get(cat);
				local.add( b );
			}
		}
		
		if (!global.contains( Badge.CATALOG_COMPLETE ) &&
			global.contains( Badge.ALL_WEAPONS_IDENTIFIED ) &&
			global.contains( Badge.ALL_ARMOR_IDENTIFIED ) &&
			global.contains( Badge.ALL_WANDS_IDENTIFIED ) &&
			global.contains( Badge.ALL_RINGS_IDENTIFIED ) &&
			global.contains( Badge.ALL_ARTIFACTS_IDENTIFIED ) &&
			global.contains( Badge.ALL_POTIONS_IDENTIFIED ) &&
			global.contains( Badge.ALL_SCROLLS_IDENTIFIED )) {
			
			Badge badge = Badge.CATALOG_COMPLETE;
			local.add( badge );
			displayBadge( badge );
		}
	}
	
	public static void validateAllWandsObtained() {
		Badge badge = null;
		
		if (!local.contains( Badge.ALL_WANDS_OBTAINED )
			&& Dungeon.hero.belongings.getItem( LloydsBeacon.class )		!= null
			&& Dungeon.hero.belongings.getItem( WandOfBlastWave.class )		!= null
			&& Dungeon.hero.belongings.getItem( WandOfCorrosion.class )		!= null
			&& Dungeon.hero.belongings.getItem( WandOfCorruption.class )	!= null
			&& Dungeon.hero.belongings.getItem( WandOfDisintegration.class )!= null
			&& Dungeon.hero.belongings.getItem( WandOfFireblast.class )		!= null
			&& Dungeon.hero.belongings.getItem( WandOfFirebolt.class )		!= null
			&& Dungeon.hero.belongings.getItem( WandOfFrost.class )			!= null
			&& Dungeon.hero.belongings.getItem( WandOfLightning.class )		!= null
			&& Dungeon.hero.belongings.getItem( WandOfLivingEarth.class )	!= null
			&& Dungeon.hero.belongings.getItem( WandOfPrismaticLight.class )!= null
			&& Dungeon.hero.belongings.getItem( WandOfRegrowth.class )		!= null
			&& Dungeon.hero.belongings.getItem( WandOfTransfusion.class )	!= null
			&& Dungeon.hero.belongings.getItem( WandOfWarding.class )		!= null) {
			badge = Badge.ALL_WANDS_OBTAINED;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateAllRingsObtained() {
		Badge badge = null;
		
		if (!local.contains( Badge.ALL_RINGS_OBTAINED )
			&& Dungeon.hero.belongings.getItem( RingOfAccuracy.class )		!= null
			&& Dungeon.hero.belongings.getItem( RingOfElements.class )		!= null
			&& Dungeon.hero.belongings.getItem( RingOfEnergy.class )		!= null
			&& Dungeon.hero.belongings.getItem( RingOfEvasion.class )		!= null
			&& Dungeon.hero.belongings.getItem( RingOfFuror.class )			!= null
			&& Dungeon.hero.belongings.getItem( RingOfHaste.class )			!= null
			&& Dungeon.hero.belongings.getItem( RingOfMight.class )			!= null
			&& Dungeon.hero.belongings.getItem( RingOfPower.class )			!= null
			&& Dungeon.hero.belongings.getItem( RingOfSharpshooting.class )	!= null
			&& Dungeon.hero.belongings.getItem( RingOfTenacity.class )		!= null
			&& Dungeon.hero.belongings.getItem( RingOfWealth.class )		!= null) {
			badge = Badge.ALL_RINGS_OBTAINED;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateAllArtifactsObtained() {
		Badge badge = null;
		
		if (!local.contains( Badge.ALL_ARTIFACTS_OBTAINED )
			&& Dungeon.hero.belongings.getItem( AlchemistsToolkit.class )	!= null
			&& Dungeon.hero.belongings.getItem( ChaliceOfBlood.class )		!= null
			&& Dungeon.hero.belongings.getItem( EtherealChains.class )		!= null
			&& Dungeon.hero.belongings.getItem( HornOfPlenty.class )		!= null
			&& Dungeon.hero.belongings.getItem( MasterThievesArmband.class )!= null
			&& Dungeon.hero.belongings.getItem( SandalsOfNature.class )		!= null
			&& Dungeon.hero.belongings.getItem( TalismanOfForesight.class )	!= null
			&& Dungeon.hero.belongings.getItem( UnstableSpellbook.class )	!= null) {
			badge = Badge.ALL_ARTIFACTS_OBTAINED;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateDeathFromFire() {
		Badge badge = Badge.DEATH_FROM_FIRE;
		local.add( badge );
		
		validateYASD();
	}
	
	public static void validateDeathFromPoison() {
		Badge badge = Badge.DEATH_FROM_POISON;
		local.add( badge );
		
		validateYASD();
	}
	
	public static void validateDeathFromGas() {
		Badge badge = Badge.DEATH_FROM_GAS;
		local.add( badge );
		
		validateYASD();
	}
	
	public static void validateDeathFromHunger() {
		Badge badge = Badge.DEATH_FROM_HUNGER;
		local.add( badge );
		
		validateYASD();
	}
	
	public static void validateDeathFromGlyph() {
		Badge badge = Badge.DEATH_FROM_GLYPH;
		local.add( badge );

		validateYASD();
	}
	
	public static void validateDeathFromFalling() {
		Badge badge = Badge.DEATH_FROM_FALLING;
		local.add( badge );

		validateYASD();
	}
	
	private static void validateYASD() {
		if (global.contains( Badge.DEATH_FROM_FIRE ) &&
			global.contains( Badge.DEATH_FROM_POISON ) &&
			global.contains( Badge.DEATH_FROM_GAS ) &&
			global.contains( Badge.DEATH_FROM_HUNGER) &&
			global.contains( Badge.DEATH_FROM_GLYPH) &&
			global.contains( Badge.DEATH_FROM_FALLING)) {
			
			Badge badge = Badge.YASD;
			local.add( badge );
			displayBadge( badge );
		}
	}
	
	public static void validateVictory() {

		Badge badge = Badge.VICTORY;
		local.add( badge );
		if (!global.contains( badge )) {
			global.add( badge );
			saveNeeded = true;
		}
		
		//all classes
		switch (Dungeon.hero.heroClass) {
		case WARRIOR:
			badge = Badge.VICTORY_WARRIOR;
			break;
		case MAGE:
			badge = Badge.VICTORY_MAGE;
			break;
		case ROGUE:
			badge = Badge.VICTORY_ROGUE;
			break;
		case HUNTRESS:
			badge = Badge.VICTORY_HUNTRESS;
			break;
		}
		local.add( badge );
		if (!global.contains( badge )) {
			global.add( badge );
			saveNeeded = true;
		}
		
		if (global.contains( Badge.VICTORY_WARRIOR ) &&
			global.contains( Badge.VICTORY_MAGE ) &&
			global.contains( Badge.VICTORY_ROGUE ) &&
			global.contains( Badge.VICTORY_HUNTRESS )) {
			
			badge = Badge.VICTORY_SUPER;
			displayBadge( badge );
		}
		
		//all subclasses
		switch (Dungeon.hero.subClass) {
		case GLADIATOR:
			badge = Badge.VICTORY_GLADIATOR;
			break;
		case BERSERKER:
			badge = Badge.VICTORY_BERSERKER;
			break;
		case BATTLEMAGE:
			badge = Badge.VICTORY_BATTLEMAGE;
			break;
		case WARLOCK:
			badge = Badge.VICTORY_WARLOCK;
			break;
		case FREERUNNER:
			badge = Badge.VICTORY_FREERUNNER;
			break;
		case ASSASSIN:
			badge = Badge.VICTORY_ASSASSIN;
			break;
		case SNIPER:
			badge = Badge.VICTORY_SNIPER;
			break;
		case WARDEN:
			badge = Badge.VICTORY_WARDEN;
			break;
		default:
			return;
		}
		local.add( badge );
		if (!global.contains( badge )) {
			global.add( badge );
			saveNeeded = true;
		}
		
		if (global.contains( Badge.VICTORY_GLADIATOR ) &&
			global.contains( Badge.VICTORY_BERSERKER ) &&
			global.contains( Badge.VICTORY_BATTLEMAGE ) &&
			global.contains( Badge.VICTORY_WARLOCK ) &&
			global.contains( Badge.VICTORY_FREERUNNER ) &&
			global.contains( Badge.VICTORY_ASSASSIN ) &&
			global.contains( Badge.VICTORY_SNIPER ) &&
			global.contains( Badge.VICTORY_WARDEN )) {
			
			badge = Badge.VICTORY_SUPER_SUPER;
			displayBadge( badge );
		}
	}
	
	public static void validateGamesPlayed() {
		Badge badge = null;
		if (Rankings.INSTANCE.trueTotal >= 100) {
			badge = Badge.GAMES_PLAYED;
		}
		
		displayBadge( badge );
	}
	
	//necessary in order to display the happy end badge in the surface scene
	public static void silentValidateHappyEnd() {
		if (!local.contains( Badge.HAPPY_END )){
			local.add( Badge.HAPPY_END );
		}
	}
	
	public static void validateHappyEnd() {
		displayBadge( Badge.HAPPY_END );
	}
	
	public static void validatePharmacophobia() {
		Badge badge = null;
		
		if (!Statistics.healingUsed) {
			badge = Badge.PHARMACOPHOBIA;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateForbiddenRunes() {
		Badge badge = null;
		
		if (!Statistics.upgradeUsed) {
			badge = Badge.FORBIDDEN_RUNES;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateOnDiet() {
		Badge badge = null;
		
		if (Statistics.foodEaten <= 0) {
			badge = Badge.ON_DIET;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateFaithArmor() {
		Badge badge = null;
		
		if (!Statistics.armorEquipped) {
			badge = Badge.FAITH_ARMOR;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateMerchantWars() {
		Badge badge = null;
		
		if (Statistics.itemsBought <= 0) {
			badge = Badge.MERCHANT_WARS;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateClassicist() {
		Badge badge = null;
		
		if (Statistics.itemsCrafted <= 0) {
			badge = Badge.CLASSICIST;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateSpeedrun() {
		Badge badge = null;
		
		if (Statistics.duration < 4000) {
			badge = Badge.SPEEDRUN;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validatePacifist() {
		Badge badge = null;
		
		if (Statistics.enemiesSlain <= 7 || Dungeon.hero.lvl <= 1) {
			badge = Badge.PACIFIST;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateBarrenLand() {
		Badge badge = null;
		
		if (Dungeon.isChallenged(Challenges.BARREN_LAND)) {
			badge = Badge.BARREN_LAND;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateSwarmIntelligence() {
		Badge badge = null;
		
		if (Dungeon.isChallenged(Challenges.SWARM_INTELLIGENCE)) {
			badge = Badge.SWARM_INTELLIGENCE;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	public static void validateIntoDarkness() {
		Badge badge = null;
		
		if (Dungeon.isChallenged(Challenges.INTO_DARKNESS)) {
			badge = Badge.INTO_DARKNESS;
			local.add( badge );
		}
		
		displayBadge( badge );
	}
	
	
	
	private static void displayBadge( Badge badge ) {
		
		if (badge == null) {
			return;
		}
		
		if (global.contains( badge )) {
			
			if (!badge.meta) {
				GLog.h( Messages.get(Badges.class, "endorsed", badge.desc()) );
			}
			
		} else {
			
			global.add( badge );
			saveNeeded = true;
			
			if (badge.meta) {
				GLog.h( Messages.get(Badges.class, "new_super", badge.desc()) );
			} else {
				GLog.h( Messages.get(Badges.class, "new", badge.desc()) );
			}
			PixelScene.showBadge( badge );
		}
	}
	
	public static boolean isUnlocked( Badge badge ) {
		return global.contains( badge );
	}
	
	public static HashSet<Badge> allUnlocked(){
		loadGlobal();
		return new HashSet<>(global);
	}
	
	public static void disown( Badge badge ) {
		loadGlobal();
		global.remove( badge );
		saveNeeded = true;
	}
	
	public static void addGlobal( Badge badge ){
		if (!global.contains(badge)){
			global.add( badge );
			saveNeeded = true;
		}
	}
	
	public static List<Badge> filtered( boolean global ) {
		
		HashSet<Badge> filtered = new HashSet<Badge>( global ? Badges.global : Badges.local );

		Iterator<Badge> iterator = filtered.iterator();
		while (iterator.hasNext()) {
			Badge badge = iterator.next();
			if ((!global && badge.meta) || badge.image == -1) {
				iterator.remove();
			}
		}
		
		leaveBest( filtered, Badge.STRENGTH_ATTAINED, Badge.STRENGTH_ATTAINED_SUPER );
		leaveBest( filtered, Badge.ITEM_LEVEL, Badge.ITEM_LEVEL_SUPER );
		leaveBest( filtered, Badge.VICTORY, Badge.VICTORY_SUPER, Badge.VICTORY_SUPER_SUPER );
		
		ArrayList<Badge> list = new ArrayList<Badge>( filtered );
		Collections.sort( list );
		
		return list;
	}
	
	private static void leaveBest( HashSet<Badge> list, Badge...badges ) {
		for (int i=badges.length-1; i > 0; i--) {
			if (list.contains( badges[i])) {
				for (int j=0; j < i; j++) {
					list.remove( badges[j] );
				}
				break;
			}
		}
	}
}
