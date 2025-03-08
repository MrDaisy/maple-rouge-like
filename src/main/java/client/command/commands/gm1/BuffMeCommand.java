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
        // Beginner
        CLASS_BUFFS.put("Beginner", Arrays.asList(
                // Beginner Skills
                12,      // BLESSING_OF_THE_FAIRY
                8,       // FOLLOW_THE_LEADER
                100,     // MAP_CHAIR
                1001,    // THREE_SNAILS
                1001,    // RECOVERY
                1002,    // NIMBLE_FEET
                1004,    // MONSTER_RIDER
                1005,    // ECHO_OF_HERO
                1009,    // BAMBOO_RAIN
                1010,    // INVINCIBLE_BARRIER
                1011,    // POWER_EXPLOSION
                1013,    // SPACESHIP
                1014,    // SPACE_DASH
                1017,    // YETI_MOUNT1
                1018,    // YETI_MOUNT2
                1019,    // WITCH_BROOMSTICK
                1031     // BALROG_MOUNT
        ));

        // Warrior
        CLASS_BUFFS.put("Warrior", Arrays.asList(
                // 1st Job
                1000000, // IMPROVED_HPREC
                1000001, // IMPROVED_MAXHP
                1000002, // ENDURE
                1000003, // IRON_BODY
                1000004, // POWER_STRIKE
                1000005  // SLASH_BLAST
        ));

        // Fighter
        CLASS_BUFFS.put("Fighter", Arrays.asList(
                // 2nd Job
                1101006, // RAGE
                1101007  // POWER_GUARD
        ));

        // Crusader
        CLASS_BUFFS.put("Crusader", Arrays.asList(
                // 3rd Job
                1111008, // SHOUT
                1111007  // ARMOR_CRASH
        ));

        // Hero
        CLASS_BUFFS.put("Hero", Arrays.asList(
                // 4th Job
                1121000, // MAPLE_WARRIOR
                1121010, // ENRAGE
                1121011  // HEROS_WILL
        ));

        // Page
        CLASS_BUFFS.put("Page", Arrays.asList(
                // 2nd Job
                1201006, // THREATEN
                1201007  // POWER_GUARD
        ));

        // White Knight
        CLASS_BUFFS.put("WhiteKnight", Arrays.asList(
                // 3rd Job
                1211009, // MAGIC_CRASH
                1211008  // SHIELD_MASTERY
        ));

        // Paladin
        CLASS_BUFFS.put("Paladin", Arrays.asList(
                // 4th Job
                1221000, // MAPLE_WARRIOR
                1221012, // HEROS_WILL
                1221009  // BLAST
        ));

        // Spearman
        CLASS_BUFFS.put("Spearman", Arrays.asList(
                // 2nd Job
                1301006, // IRON_WILL
                1301007  // HYPER_BODY
        ));

        // Dragon Knight
        CLASS_BUFFS.put("DragonKnight", Arrays.asList(
                // 3rd Job
                1311008, // DRAGON_BLOOD
                1311006  // DRAGON_ROAR
        ));

        // Dark Knight
        CLASS_BUFFS.put("DarkKnight", Arrays.asList(
                // 4th Job
                1321000, // MAPLE_WARRIOR
                1321002, // STANCE
                1320006  // BERSERK
        ));

        // Magician
        CLASS_BUFFS.put("Magician", Arrays.asList(
                // 1st Job
                2001002, // MAGIC_GUARD
                2001003  // MAGIC_ARMOR
        ));

        // FP Wizard
        CLASS_BUFFS.put("FPWizard", Arrays.asList(
                // 1st Job
                2101001, // MEDITATION
                2101003  // SLOW
        ));

        // FP Mage
        CLASS_BUFFS.put("FPMage", Arrays.asList(
                // 2nd Job
                2111005, // SPELL_BOOSTER
                2111006  // ELEMENT_COMPOSITION
        ));

        // FP Arch Mage
        CLASS_BUFFS.put("FPArchMage", Arrays.asList(
                // 4th Job
                2121000, // MAPLE_WARRIOR
                2121004, // INFINITY
                2121008  // HEROS_WILL
        ));

        // IL Wizard
        CLASS_BUFFS.put("ILWizard", Arrays.asList(
                // 1st Job
                2201001, // MEDITATION
                2201003  // SLOW
        ));

        // IL Mage
        CLASS_BUFFS.put("ILMage", Arrays.asList(
                // 2nd Job
                2211005, // SPELL_BOOSTER
                2211006  // ELEMENT_COMPOSITION
        ));

        // IL Arch Mage
        CLASS_BUFFS.put("ILArchMage", Arrays.asList(
                // 4th Job
                2221000, // MAPLE_WARRIOR
                2221004, // INFINITY
                2221008  // HEROS_WILL
        ));

        // Cleric
        CLASS_BUFFS.put("Cleric", Arrays.asList(
                // 2nd Job
                2301002, // HEAL
                2301004  // BLESS
        ));

        // Priest
        CLASS_BUFFS.put("Priest", Arrays.asList(
                // 3rd Job
                2311003, // HOLY_SYMBOL
                2311006  // SUMMON_DRAGON
        ));

        // Bishop
        CLASS_BUFFS.put("Bishop", Arrays.asList(
                // 4th Job
                2321000, // MAPLE_WARRIOR
                2321001, // BIG_BANG
                2321002, // MANA_REFLECTION
                2321003, // BAHAMUT
                2321004, // INFINITY
                2321005, // HOLY_SHIELD
                2321006, // RESURRECTION
                2321007, // ANGEL_RAY
                2321008, // GENESIS
                2321009  // HEROS_WILL
        ));

        // Bowman
        CLASS_BUFFS.put("Bowman", Arrays.asList(
                // 1st Job
                3000000, // BLESSING_OF_AMAZON
                3000002, // EYE_OF_AMAZON
                3000001, // CRITICAL_SHOT
                3001003, // FOCUS
                3001005, // DOUBLE_SHOT
                3001004  // ARROW_BLOW
        ));

        // Hunter
        CLASS_BUFFS.put("Hunter", Arrays.asList(
                // 2nd Job
                3101002, // BOW_BOOSTER
                3101004  // SOUL_ARROW
        ));

        // Ranger
        CLASS_BUFFS.put("Ranger", Arrays.asList(
                // 3rd Job
                3111006, // STRAFE
                3111005  // SILVER_HAWK
        ));

        // Bowmaster
        CLASS_BUFFS.put("Bowmaster", Arrays.asList(
                // 4th Job
                3121000, // MAPLE_WARRIOR
                3121002, // SHARP_EYES
                3121003, // DRAGONS_BREATH
                3121004, // HURRICANE
                3120005, // BOW_EXPERT
                3121006, // PHOENIX
                3121007, // HAMSTRING
                3121008, // CONCENTRATE
                3121009  // HEROS_WILL
        ));

        // Crossbowman
        CLASS_BUFFS.put("Crossbowman", Arrays.asList(
                // 2nd Job
                3201002, // CROSSBOW_BOOSTER
                3201004  // SOUL_ARROW
        ));

        // Sniper
        CLASS_BUFFS.put("Sniper", Arrays.asList(
                // 3rd Job
                3211006, // STRAFE
                3211005  // GOLDEN_EAGLE
        ));

        // Marksman
        CLASS_BUFFS.put("Marksman", Arrays.asList(
                // 4th Job
                3221000, // MAPLE_WARRIOR
                3221002, // SHARP_EYES
                3221008  // HEROS_WILL
        ));

        // Thief
        CLASS_BUFFS.put("Thief", Arrays.asList(
                // 1st Job
                4001003, // DARK_SIGHT
                4001344  // LUCKY_SEVEN
        ));

        // Assassin
        CLASS_BUFFS.put("Assassin", Arrays.asList(
                // 2nd Job
                4101003, // CLAW_BOOSTER
                4101004, // HASTE
                4101005  // DRAIN
        ));

        // Hermit
        CLASS_BUFFS.put("Hermit", Arrays.asList(
                // 3rd Job
                4111002, // SHADOW_PARTNER
                4111006  // FLASH_JUMP
        ));

        // Night Lord
        CLASS_BUFFS.put("NightLord", Arrays.asList(
                // 4th Job
                4121000, // MAPLE_WARRIOR
                4121009, // HEROS_WILL
                4121006  // SHADOW_STARS
        ));

        // Bandit
        CLASS_BUFFS.put("Bandit", Arrays.asList(
                // 2nd Job
                4201002, // DAGGER_BOOSTER
                4201003, // HASTE
                4201004, // STEAL
                4201005  // SAVAGE_BLOW
        ));

        // Chief Bandit
        CLASS_BUFFS.put("ChiefBandit", Arrays.asList(
                // 3rd Job
                4211005, // MESO_GUARD
                4211006  // MESO_EXPLOSION
        ));

        // Shadower
        CLASS_BUFFS.put("Shadower", Arrays.asList(
                // 4th Job
                4221000, // MAPLE_WARRIOR
                4221008, // HEROS_WILL
                4221006  // SMOKE_SCREEN
        ));

        // Pirate
        CLASS_BUFFS.put("Pirate", Arrays.asList(
                // 1st Job
                5001001, // FLASH_FIST
                5001002, // SOMERSAULT_KICK
                5001003, // DOUBLE_SHOT
                5001005  // DASH
        ));

        // Brawler
        CLASS_BUFFS.put("Brawler", Arrays.asList(
                // 2nd Job
                5101005, // MP_RECOVERY
                5101006, // KNUCKLER_BOOSTER
                5101007  // OAK_BARREL
        ));

        // Marauder
        CLASS_BUFFS.put("Marauder", Arrays.asList(
                // 3rd Job
                5111005, // TRANSFORMATION
                5111006  // SHOCKWAVE
        ));

        // Buccaneer
        CLASS_BUFFS.put("Buccaneer", Arrays.asList(
                // 4th Job
                5121000, // MAPLE_WARRIOR
                5121002, // ENERGY_ORB
                5121003, // SUPER_TRANSFORMATION
                5121004, // DEMOLITION
                5121005, // SNATCH
                5121007, // BARRAGE
                5121008, // PIRATES_RAGE
                5121009, // SPEED_INFUSION
                5121010  // TIME_LEAP
        ));

        // Gunslinger
        CLASS_BUFFS.put("Gunslinger", Arrays.asList(
                // 2nd Job
                5201003, // GUN_BOOSTER
                5201005  // WINGS
        ));

        // Outlaw
        CLASS_BUFFS.put("Outlaw", Arrays.asList(
                // 3rd Job
                5211001, // OCTOPUS
                5211002, // GAVIOTA
                5211004, // FLAME_THROWER
                5211005, // ICE_SPLITTER
                5211006  // HOMING_BEACON
        ));

        // Corsair
        CLASS_BUFFS.put("Corsair", Arrays.asList(
                // 4th Job
                5221000, // MAPLE_WARRIOR
                5221010, // SPEED_INFUSION
                5221009  // HYPNOTIZE
        ));

        // MapleLeaf Brigadier
        CLASS_BUFFS.put("MapleLeafBrigadier", Arrays.asList(
                // Special Skills
                8001001, // MAPLE_LEAF_BRIGADIER_SKILL_1
                8001002  // MAPLE_LEAF_BRIGADIER_SKILL_2
        ));

        // GM
        CLASS_BUFFS.put("GM", Arrays.asList(
                // GM Skills
                9001001, // GM_ROAR1
                9001002, // GM_TELEPORT
                9001004, // HIDE
                9001005, // RESURRECTION
                9001006, // GM_ROAR2
                9001007, // GM_TELEPORT2
                9001008, // HYPER_BODY
                9001009  // ANTI_MACRO
        ));

        // Super GM
        CLASS_BUFFS.put("SuperGM", Arrays.asList(
                // Super GM Skills
                9101000, // HASTE
                9101003, // BLESS
                9101004, // HIDE
                9101005  // RESURRECTION
        ));

        // Noblesse
        CLASS_BUFFS.put("Noblesse", Arrays.asList(
                // Noblesse Skills
                10000012, // BLESSING_OF_THE_FAIRY
                10000100, // MAP_CHAIR
                10001000, // THREE_SNAILS
                10001001, // RECOVERY
                10001002, // NIMBLE_FEET
                10001004, // MONSTER_RIDER
                10001005, // ECHO_OF_HERO
                10001007, // MAKER
                10001009, // BAMBOO_RAIN
                10001010, // INVINCIBLE_BARRIER
                10001011, // POWER_EXPLOSION
                1001014,  // SPACESHIP
                1001015,  // SPACE_DASH
                10001019, // YETI_MOUNT1
                10001022, // YETI_MOUNT2
                10001023, // WITCH_BROOMSTICK
                10001031  // BALROG_MOUNT
        ));

        // Dawn Warrior
        CLASS_BUFFS.put("DawnWarrior", Arrays.asList(
                // 1st Job
                11000000, // MAX_HP_INCREASE
                11001001, // IRON_BODY
                11001002, // POWER_STRIKE
                11001003, // SLASH_BLAST
                11001004, // SOUL
                11001005, // SOUL_RUSH
                11001006  // SOUL_BLADE
        ));

        // Blaze Wizard
        CLASS_BUFFS.put("BlazeWizard", Arrays.asList(
                // 1st Job
                12000000, // INCREASING_MAX_MP
                12001001, // MAGIC_GUARD
                12001002, // MAGIC_ARMOR
                12001003, // MAGIC_CLAW
                12001004  // FLAME
        ));

        // Wind Archer
        CLASS_BUFFS.put("WindArcher", Arrays.asList(
                // 1st Job
                13000000, // CRITICAL_SHOT
                13001001, // EYE_OF_AMAZON
                13001002, // FOCUS
                13001003, // DOUBLE_SHOT
                13001004  // STORM
        ));

        // Night Walker
        CLASS_BUFFS.put("NightWalker", Arrays.asList(
                // 1st Job
                14000000, // NIMBLE_BODY
                14001001, // KEEN_EYES
                14001002, // DISORDER
                14001003, // DARK_SIGHT
                14001004  // LUCKY_SEVEN
        ));

        // Thunder Breaker
        CLASS_BUFFS.put("ThunderBreaker", Arrays.asList(
                // 1st Job
                15000000, // QUICK_MOTION
                15001001, // FIRST_STRIKE
                15001002, // SOMERSAULT_KICK
                15001003, // DASH
                15001004  // LIGHTNING
        ));

        // Legend
        CLASS_BUFFS.put("Legend", Arrays.asList(
                // Legend Skills
                20001000, // THREE_SNAILS
                20001001, // RECOVERY
                20001002, // AGILE_BODY
                20001003, // LEGENDARY_SPIRIT
                20001004, // MONSTER_RIDER
                20001005, // ECHO_OF_HERO
                20001006, // JUMP_DOWN
                20001007, // MAKER
                20001009, // BAMBOO_THRUST
                20001010, // INVINCIBLE_BARRIER
                20001011, // POWER_EXPLOSION
                20001012, // BLESSING_OF_THE_FAIRY
                20001014, // TUTORIAL_SKILL1
                20001015, // TUTORIAL_SKILL2
                20001016, // TUTORIAL_SKILL3
                20001017, // TUTORIAL_SKILL4
                20001018, // TUTORIAL_SKILL5
                20000100, // MAP_CHAIR
                20001019, // YETI_MOUNT1
                20001022, // YETI_MOUNT2
                20001023, // WITCH_BROOMSTICK
                20001031  // BALROG_MOUNT
        ));

        // Evan
        CLASS_BUFFS.put("Evan", Arrays.asList(
                // 1st Job
                22000000, // DRAGON_SOUL
                22001001, // MAGIC_MISSILE
                22001002, // FIRE_CIRCLE
                22001003, // TELEPORT
                22001004  // LIGHTNING_BOLT
        ));

        // Aran
        CLASS_BUFFS.put("Aran", Arrays.asList(
                // 1st Job
                21000000, // COMBO_ABILITY
                21000002, // DOUBLE_SWING
                21001001, // COMBAT_STEP
                21001003  // POLEARM_BOOSTER
        ));
    }

    // Map to store player class history (player ID -> list of classes)
    private static final Map<Integer, List<String>> PLAYER_CLASS_HISTORY = new HashMap<>();

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        int playerId = player.getId();

        // Initialize class history
        initializeClassHistory(player);

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

    // Helper method to initialize class history
    public static void initializeClassHistory(Character player) {
        int playerId = player.getId();
        if (!PLAYER_CLASS_HISTORY.containsKey(playerId)) {
            // Load class history from player data
            List<String> classHistory = loadClassHistoryFromPlayerData(player);
            PLAYER_CLASS_HISTORY.put(playerId, classHistory);
        }
    }

    // Helper method to load class history from player data
    private static List<String> loadClassHistoryFromPlayerData(Character player) {
        List<String> classHistory = new ArrayList<>();
        int jobId = player.getJob().getId(); // Use getId() to get the job ID from the Job object
        String currentJob = getJobName(jobId);

        // Add all previous jobs based on the player's job progression
        if (currentJob.startsWith("DawnWarrior")) {
            classHistory.add("DawnWarrior");
        } else if (currentJob.startsWith("BlazeWizard")) {
            classHistory.add("BlazeWizard");
        } else if (currentJob.startsWith("WindArcher")) {
            classHistory.add("WindArcher");
        } else if (currentJob.startsWith("NightWalker")) {
            classHistory.add("NightWalker");
        } else if (currentJob.startsWith("ThunderBreaker")) {
            classHistory.add("ThunderBreaker");
        } else if (currentJob.startsWith("Aran")) {
            classHistory.add("Aran");
        } else if (currentJob.startsWith("Evan")) {
            classHistory.add("Evan");
        } else {
            classHistory.add(currentJob);
        }

        return classHistory;
    }

    // Helper method to get job name from job ID
    private static String getJobName(int jobId) {
        switch (jobId) {
            // Beginner
            case 0: return "Beginner";

            // Warrior
            case 100: return "Warrior";
            case 110: return "Fighter";
            case 111: return "Crusader";
            case 112: return "Hero";
            case 120: return "Page";
            case 121: return "WhiteKnight";
            case 122: return "Paladin";
            case 130: return "Spearman";
            case 131: return "DragonKnight";
            case 132: return "DarkKnight";

            // Magician
            case 200: return "Magician";
            case 210: return "FPWizard";
            case 211: return "FPMage";
            case 212: return "FPArchMage";
            case 220: return "ILWizard";
            case 221: return "ILMage";
            case 222: return "ILArchMage";
            case 230: return "Cleric";
            case 231: return "Priest";
            case 232: return "Bishop";

            // Bowman
            case 300: return "Bowman";
            case 310: return "Hunter";
            case 311: return "Ranger";
            case 312: return "Bowmaster";
            case 320: return "Crossbowman";
            case 321: return "Sniper";
            case 322: return "Marksman";

            // Thief
            case 400: return "Thief";
            case 410: return "Assassin";
            case 411: return "Hermit";
            case 412: return "NightLord";
            case 420: return "Bandit";
            case 421: return "ChiefBandit";
            case 422: return "Shadower";

            // Pirate
            case 500: return "Pirate";
            case 510: return "Brawler";
            case 511: return "Marauder";
            case 512: return "Buccaneer";
            case 520: return "Gunslinger";
            case 521: return "Outlaw";
            case 522: return "Corsair";

            // MapleLeaf Brigadier
            case 800: return "MapleLeafBrigadier";

            // GM
            case 900: return "GM";
            case 910: return "SuperGM";

            // Noblesse
            case 1000: return "Noblesse";
            case 1100: return "DawnWarrior1";
            case 1110: return "DawnWarrior2";
            case 1111: return "DawnWarrior3";
            case 1112: return "DawnWarrior4";
            case 1200: return "BlazeWizard1";
            case 1210: return "BlazeWizard2";
            case 1211: return "BlazeWizard3";
            case 1212: return "BlazeWizard4";
            case 1300: return "WindArcher1";
            case 1310: return "WindArcher2";
            case 1311: return "WindArcher3";
            case 1312: return "WindArcher4";
            case 1400: return "NightWalker1";
            case 1410: return "NightWalker2";
            case 1411: return "NightWalker3";
            case 1412: return "NightWalker4";
            case 1500: return "ThunderBreaker1";
            case 1510: return "ThunderBreaker2";
            case 1511: return "ThunderBreaker3";
            case 1512: return "ThunderBreaker4";

            // Legend
            case 2000: return "Legend";
            case 2001: return "Evan";

            // Aran
            case 2100: return "Aran1";
            case 2110: return "Aran2";
            case 2111: return "Aran3";
            case 2112: return "Aran4";

            // Evan
            case 2200: return "Evan1";
            case 2210: return "Evan2";
            case 2211: return "Evan3";
            case 2212: return "Evan4";
            case 2213: return "Evan5";
            case 2214: return "Evan6";
            case 2215: return "Evan7";
            case 2216: return "Evan8";
            case 2217: return "Evan9";
            case 2218: return "Evan10";

            // Default
            default: return "Unknown";
        }
    }

    // Helper method to update class history when the player changes classes
    public static void updateClassHistory(Character player, String newClass) {
        int playerId = player.getId();
        List<String> classHistory = PLAYER_CLASS_HISTORY.getOrDefault(playerId, new ArrayList<>());

        // Add the new class to the history if it's not already there
        if (!classHistory.contains(newClass)) {
            classHistory.add(newClass);
            PLAYER_CLASS_HISTORY.put(playerId, classHistory);
            System.out.println("Updated class history for player " + playerId + ": " + classHistory);
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