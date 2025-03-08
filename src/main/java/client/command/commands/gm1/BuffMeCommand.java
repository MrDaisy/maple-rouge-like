package client.command.commands.gm1;

import client.Character;
import client.Client;
import client.SkillFactory;
import client.command.Command;

import java.util.*;

public class BuffMeCommand extends Command {
    {
        setDescription("Activate buffs from all classes attained.");
    }

    // Map to store class names and their corresponding buff skills
    private static final Map<String, List<Integer>> CLASS_BUFFS = new HashMap<>();

    static {
        // Aran
        CLASS_BUFFS.put("Aran", Arrays.asList(
                4101004, // Maple Hero
                2311003, // Holy Symbol
                1301007, // Hyper Body
                2301004, // Bless
                1005     // Echo of Hero
        ));

        // Archer
        CLASS_BUFFS.put("Archer", Arrays.asList(
                3000000, // Blessing of Amazon
                3000002, // Eye of Amazon
                3001003  // Focus
        ));

        // Assassin
        CLASS_BUFFS.put("Assassin", Arrays.asList(
                4100002, // Endure
                4101004, // Haste
                4101005  // Drain
        ));

        // Bandit
        CLASS_BUFFS.put("Bandit", Arrays.asList(
                4200001, // Endure
                4201003, // Haste
                4201004  // Steal
        ));

        // Beginner
        CLASS_BUFFS.put("Beginner", Arrays.asList(
                1005,   // Echo of Hero
                1001,   // Recovery
                1002    // Nimble Feet
        ));

        // Bishop
        CLASS_BUFFS.put("Bishop", Arrays.asList(
                2321000, // Maple Warrior
                2321003, // Bahamut
                2321005, // Holy Shield
                2321006  // Resurrection
        ));

        // Blaze Wizard
        CLASS_BUFFS.put("BlazeWizard", Arrays.asList(
                12101000, // Meditation
                12101004, // Spell Booster
                12101005  // Elemental Reset
        ));

        // Bowmaster
        CLASS_BUFFS.put("Bowmaster", Arrays.asList(
                3121000, // Maple Warrior
                3121002, // Sharp Eyes
                3121008  // Concentrate
        ));

        // Brawler
        CLASS_BUFFS.put("Brawler", Arrays.asList(
                5101005, // MP Recovery
                5101006  // Knuckler Booster
        ));

        // Buccaneer
        CLASS_BUFFS.put("Buccaneer", Arrays.asList(
                5121000, // Maple Warrior
                5121009, // Speed Infusion
                5121008  // Pirate's Rage
        ));

        // Chief Bandit
        CLASS_BUFFS.put("ChiefBandit", Arrays.asList(
                4211005, // Meso Guard
                4211006  // Meso Explosion
        ));

        // Cleric
        CLASS_BUFFS.put("Cleric", Arrays.asList(
                2301002, // Heal
                2301004  // Bless
        ));

        // Corsair
        CLASS_BUFFS.put("Corsair", Arrays.asList(
                5221000, // Maple Warrior
                5221010, // Speed Infusion
                5221009  // Hypnotize
        ));

        // Crossbowman
        CLASS_BUFFS.put("Crossbowman", Arrays.asList(
                3201002, // Crossbow Booster
                3201004  // Soul Arrow
        ));

        // Crusader
        CLASS_BUFFS.put("Crusader", Arrays.asList(
                1111008, // Shout
                1111007  // Armor Crash
        ));

        // Dark Knight
        CLASS_BUFFS.put("DarkKnight", Arrays.asList(
                1321000, // Maple Warrior
                1321002, // Stance
                1320006  // Berserk
        ));

        // Dawn Warrior
        CLASS_BUFFS.put("DawnWarrior", Arrays.asList(
                11101003, // Rage
                11101004  // Soul Blade
        ));

        // Dragon Knight
        CLASS_BUFFS.put("DragonKnight", Arrays.asList(
                1311008, // Dragon Blood
                1311006  // Dragon Roar
        ));

        // Evan
        CLASS_BUFFS.put("Evan", Arrays.asList(
                22171000, // Maple Warrior
                22171004, // Hero's Will
                22161003  // Recovery Aura
        ));

        // FP Arch Mage
        CLASS_BUFFS.put("FPArchMage", Arrays.asList(
                2121000, // Maple Warrior
                2121004, // Infinity
                2121008  // Hero's Will
        ));

        // FP Mage
        CLASS_BUFFS.put("FPMage", Arrays.asList(
                2111005, // Spell Booster
                2111006  // Element Composition
        ));

        // FP Wizard
        CLASS_BUFFS.put("FPWizard", Arrays.asList(
                2101001, // Meditation
                2101003  // Slow
        ));

        // Fighter
        CLASS_BUFFS.put("Fighter", Arrays.asList(
                1101006, // Rage
                1101007  // Power Guard
        ));

        // Gunslinger
        CLASS_BUFFS.put("Gunslinger", Arrays.asList(
                5201003, // Gun Booster
                5201005  // Wings
        ));

        // Hermit
        CLASS_BUFFS.put("Hermit", Arrays.asList(
                4111002, // Shadow Partner
                4111006  // Flash Jump
        ));

        // Hero
        CLASS_BUFFS.put("Hero", Arrays.asList(
                1121000, // Maple Warrior
                1121010, // Enrage
                1121011  // Hero's Will
        ));

        // Hunter
        CLASS_BUFFS.put("Hunter", Arrays.asList(
                3101002, // Bow Booster
                3101004  // Soul Arrow
        ));

        // IL Arch Mage
        CLASS_BUFFS.put("ILArchMage", Arrays.asList(
                2221000, // Maple Warrior
                2221004, // Infinity
                2221008  // Hero's Will
        ));

        // IL Mage
        CLASS_BUFFS.put("ILMage", Arrays.asList(
                2211005, // Spell Booster
                2211006  // Element Composition
        ));

        // IL Wizard
        CLASS_BUFFS.put("ILWizard", Arrays.asList(
                2201001, // Meditation
                2201003  // Slow
        ));

        // Magician
        CLASS_BUFFS.put("Magician", Arrays.asList(
                2001002, // Magic Guard
                2001003  // Magic Armor
        ));

        // Marauder
        CLASS_BUFFS.put("Marauder", Arrays.asList(
                5111005, // Transformation
                5111006  // Shockwave
        ));

        // Marksman
        CLASS_BUFFS.put("Marksman", Arrays.asList(
                3221000, // Maple Warrior
                3221002, // Sharp Eyes
                3221008  // Hero's Will
        ));

        // Night Lord
        CLASS_BUFFS.put("NightLord", Arrays.asList(
                4121000, // Maple Warrior
                4121009, // Hero's Will
                4121006  // Shadow Stars
        ));

        // Night Walker
        CLASS_BUFFS.put("NightWalker", Arrays.asList(
                14101003, // Haste
                14101004  // Flash Jump
        ));

        // Paladin
        CLASS_BUFFS.put("Paladin", Arrays.asList(
                1221000, // Maple Warrior
                1221012, // Hero's Will
                1221009  // Blast
        ));

        // Priest
        CLASS_BUFFS.put("Priest", Arrays.asList(
                2311003, // Holy Symbol
                2311006  // Summon Dragon
        ));

        // Ranger
        CLASS_BUFFS.put("Ranger", Arrays.asList(
                3111006, // Strafe
                3111005  // Silver Hawk
        ));

        // Rogue
        CLASS_BUFFS.put("Rogue", Arrays.asList(
                4001003, // Dark Sight
                4001344  // Lucky Seven
        ));

        // Shadower
        CLASS_BUFFS.put("Shadower", Arrays.asList(
                4221000, // Maple Warrior
                4221008, // Hero's Will
                4221006  // Smoke Screen
        ));

        // Sniper
        CLASS_BUFFS.put("Sniper", Arrays.asList(
                3211006, // Strafe
                3211005  // Golden Eagle
        ));

        // Spearman
        CLASS_BUFFS.put("Spearman", Arrays.asList(
                1301006, // Iron Will
                1301007  // Hyper Body
        ));

        // Thunder Breaker
        CLASS_BUFFS.put("ThunderBreaker", Arrays.asList(
                15111005, // Speed Infusion
                15111003  // Shockwave
        ));

        // Wind Archer
        CLASS_BUFFS.put("WindArcher", Arrays.asList(
                13101006, // Wind Walk
                13111002  // Hurricane
        ));
    }

    // Map to store player class history (player ID -> list of classes)
    private static final Map<Integer, List<String>> PLAYER_CLASS_HISTORY = new HashMap<>();

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        int playerId = player.getId();

        // Get the player's class history
        List<String> classHistory = PLAYER_CLASS_HISTORY.getOrDefault(playerId, new ArrayList<>());

        if (classHistory.isEmpty()) {
            player.dropMessage(5, "You have not attained any classes yet.");
            return;
        }

        // Get all buffs from classes attained
        Set<Integer> buffsToApply = new HashSet<>();
        for (String attainedClass : classHistory) {
            if (CLASS_BUFFS.containsKey(attainedClass)) {
                buffsToApply.addAll(CLASS_BUFFS.get(attainedClass));
            }
        }

        // Apply buffs
        for (int skillId : buffsToApply) {
            if (SkillFactory.getSkill(skillId) != null) {
                SkillFactory.getSkill(skillId).getEffect(SkillFactory.getSkill(skillId).getMaxLevel()).applyTo(player);
                player.dropMessage(5, "Applied buff: " + getSkillName(skillId));
            }
        }

        player.dropMessage(5, "All buffs applied.");
    }

    // Helper method to update class history when the player changes classes
    public static void updateClassHistory(Character player, String newClass) {
        int playerId = player.getId();
        List<String> classHistory = PLAYER_CLASS_HISTORY.getOrDefault(playerId, new ArrayList<>());

        // Add the new class to the history if it's not already there
        if (!classHistory.contains(newClass)) {
            classHistory.add(newClass);
            PLAYER_CLASS_HISTORY.put(playerId, classHistory);
        }
    }

    // Helper method to get skill name (for feedback)
    private String getSkillName(int skillId) {
        // Example: Add skill name mappings here
        switch (skillId) {
            case 4101004:
                return "Maple Hero";
            case 2311003:
                return "Holy Symbol";
            case 1301007:
                return "Hyper Body";
            case 2301004:
                return "Bless";
            case 1005:
                return "Echo of Hero";
            // Add other skill name mappings here...
            default:
                return "Unknown Skill";
        }
    }
}