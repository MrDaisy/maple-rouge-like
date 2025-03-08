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
                // 1st Job
                21000000, // COMBO_ABILITY
                21000002, // DOUBLE_SWING
                21001001, // COMBAT_STEP
                21001003, // POLEARM_BOOSTER

                // 2nd Job
                21100000, // POLEARM_MASTERY
                21100001, // TRIPLE_SWING
                21100002, // FINAL_CHARGE
                21100005, // COMBO_DRAIN
                21100004, // COMBO_SMASH
                21101003, // BODY_PRESSURE

                // 3rd Job
                21110002, // FULL_SWING
                21110000, // COMBO_CRITICAL
                21110003, // FINAL_TOSS
                21110004, // COMBO_FENRIR
                21111005, // SNOW_CHARGE
                21111001, // SMART_KNOCKBACK
                21110006, // ROLLING_SPIN
                21110007, // HIDDEN_FULL_DOUBLE
                21110008, // HIDDEN_FULL_TRIPLE

                // 4th Job
                21121000, // MAPLE_WARRIOR
                21120001, // HIGH_MASTERY
                21120002, // OVER_SWING
                21120004, // HIGH_DEFENSE
                21120005, // FINAL_BLOW
                21120006, // COMBO_TEMPEST
                21120007, // COMBO_BARRIER
                21121003, // FREEZE_STANDING
                21121008, // HEROS_WILL
                21120009, // HIDDEN_OVER_DOUBLE
                21120010  // HIDDEN_OVER_TRIPLE
        ));

        // Archer
        CLASS_BUFFS.put("Archer", Arrays.asList(
                // 1st Job
                3000000, // BLESSING_OF_AMAZON
                3000002, // EYE_OF_AMAZON
                3000001, // CRITICAL_SHOT
                3001003, // FOCUS
                3001005, // DOUBLE_SHOT
                3001004  // ARROW_BLOW
        ));

        // Assassin
        CLASS_BUFFS.put("Assassin", Arrays.asList(
                // 1st Job
                4100000, // CLAW_MASTERY
                4100001, // CRITICAL_THROW
                4100002, // ENDURE

                // 2nd Job
                4101003, // CLAW_BOOSTER
                4101004, // HASTE
                4101005  // DRAIN
        ));

        // Bandit
        CLASS_BUFFS.put("Bandit", Arrays.asList(
                // 1st Job
                4200000, // DAGGER_MASTERY
                4200001, // ENDURE

                // 2nd Job
                4201002, // DAGGER_BOOSTER
                4201003, // HASTE
                4201004, // STEAL
                4201005  // SAVAGE_BLOW
        ));

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

        // Blaze Wizard
        CLASS_BUFFS.put("BlazeWizard", Arrays.asList(
                // 1st Job
                12000000, // INCREASING_MAX_MP
                12001001, // MAGIC_GUARD
                12001002, // MAGIC_ARMOR
                12001003, // MAGIC_CLAW
                12001004, // FLAME,

                // 2nd Job
                12101000, // MEDITATION
                12101001, // SLOW
                12101002, // FIRE_ARROW
                12101003, // TELEPORT
                12101004, // SPELL_BOOSTER
                12101005, // ELEMENTAL_RESET
                12101006, // FIRE_PILLAR,

                // 3rd Job
                12110000, // ELEMENTAL_RESISTANCE
                12110001, // ELEMENT_AMPLIFICATION
                12111002, // SEAL
                12111003, // METEOR_SHOWER
                12111004, // IFRIT
                12111005, // FLAME_GEAR
                12111006  // FIRE_STRIKE
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

        // Brawler
        CLASS_BUFFS.put("Brawler", Arrays.asList(
                // 1st Job
                5100000, // IMPROVE_MAX_HP
                5100001, // KNUCKLER_MASTERY
                5101002, // BACK_SPIN_BLOW
                5101003, // DOUBLE_UPPERCUT
                5101004, // CORKSCREW_BLOW,

                // 2nd Job
                5101005, // MP_RECOVERY
                5101006, // KNUCKLER_BOOSTER
                5101007  // OAK_BARREL
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

        // Chief Bandit
        CLASS_BUFFS.put("ChiefBandit", Arrays.asList(
                // 4th Job
                4211005, // MESO_GUARD
                4211006  // MESO_EXPLOSION
        ));

        // Cleric
        CLASS_BUFFS.put("Cleric", Arrays.asList(
                // 2nd Job
                2301002, // HEAL
                2301004  // BLESS
        ));

        // Corsair
        CLASS_BUFFS.put("Corsair", Arrays.asList(
                // 4th Job
                5221000, // MAPLE_WARRIOR
                5221010, // SPEED_INFUSION
                5221009  // HYPNOTIZE
        ));

        // Crossbowman
        CLASS_BUFFS.put("Crossbowman", Arrays.asList(
                // 2nd Job
                3201002, // CROSSBOW_BOOSTER
                3201004  // SOUL_ARROW
        ));

        // Crusader
        CLASS_BUFFS.put("Crusader", Arrays.asList(
                // 3rd Job
                1111008, // SHOUT
                1111007  // ARMOR_CRASH
        ));

        // Dark Knight
        CLASS_BUFFS.put("DarkKnight", Arrays.asList(
                // 4th Job
                1321000, // MAPLE_WARRIOR
                1321002, // STANCE
                1320006  // BERSERK
        ));

        // Dawn Warrior
        CLASS_BUFFS.put("DawnWarrior", Arrays.asList(
                // 3rd Job
                11101003, // RAGE
                11101004  // SOUL_BLADE
        ));

        // Dragon Knight
        CLASS_BUFFS.put("DragonKnight", Arrays.asList(
                // 3rd Job
                1311008, // DRAGON_BLOOD
                1311006  // DRAGON_ROAR
        ));

        // Evan
        CLASS_BUFFS.put("Evan", Arrays.asList(
                // 4th Job
                22171000, // MAPLE_WARRIOR
                22171004, // HEROS_WILL
                22161003  // RECOVERY_AURA
        ));

        // FP Arch Mage
        CLASS_BUFFS.put("FPArchMage", Arrays.asList(
                // 4th Job
                2121000, // MAPLE_WARRIOR
                2121004, // INFINITY
                2121008  // HEROS_WILL
        ));

        // FP Mage
        CLASS_BUFFS.put("FPMage", Arrays.asList(
                // 2nd Job
                2111005, // SPELL_BOOSTER
                2111006  // ELEMENT_COMPOSITION
        ));

        // FP Wizard
        CLASS_BUFFS.put("FPWizard", Arrays.asList(
                // 1st Job
                2101001, // MEDITATION
                2101003  // SLOW
        ));

        // Fighter
        CLASS_BUFFS.put("Fighter", Arrays.asList(
                // 1st Job
                1101006, // RAGE
                1101007  // POWER_GUARD
        ));

        // Gunslinger
        CLASS_BUFFS.put("Gunslinger", Arrays.asList(
                // 2nd Job
                5201003, // GUN_BOOSTER
                5201005  // WINGS
        ));

        // Hermit
        CLASS_BUFFS.put("Hermit", Arrays.asList(
                // 3rd Job
                4111002, // SHADOW_PARTNER
                4111006  // FLASH_JUMP
        ));

        // Hero
        CLASS_BUFFS.put("Hero", Arrays.asList(
                // 4th Job
                1121000, // MAPLE_WARRIOR
                1121010, // ENRAGE
                1121011  // HEROS_WILL
        ));

        // Hunter
        CLASS_BUFFS.put("Hunter", Arrays.asList(
                // 2nd Job
                3101002, // BOW_BOOSTER
                3101004  // SOUL_ARROW
        ));

        // IL Arch Mage
        CLASS_BUFFS.put("ILArchMage", Arrays.asList(
                // 4th Job
                2221000, // MAPLE_WARRIOR
                2221004, // INFINITY
                2221008  // HEROS_WILL
        ));

        // IL Mage
        CLASS_BUFFS.put("ILMage", Arrays.asList(
                // 2nd Job
                2211005, // SPELL_BOOSTER
                2211006  // ELEMENT_COMPOSITION
        ));

        // IL Wizard
        CLASS_BUFFS.put("ILWizard", Arrays.asList(
                // 1st Job
                2201001, // MEDITATION
                2201003  // SLOW
        ));

        // Magician
        CLASS_BUFFS.put("Magician", Arrays.asList(
                // 1st Job
                2001002, // MAGIC_GUARD
                2001003  // MAGIC_ARMOR
        ));

        // Marauder
        CLASS_BUFFS.put("Marauder", Arrays.asList(
                // 3rd Job
                5111005, // TRANSFORMATION
                5111006  // SHOCKWAVE
        ));

        // Marksman
        CLASS_BUFFS.put("Marksman", Arrays.asList(
                // 4th Job
                3221000, // MAPLE_WARRIOR
                3221002, // SHARP_EYES
                3221008  // HEROS_WILL
        ));

        // Night Lord
        CLASS_BUFFS.put("NightLord", Arrays.asList(
                // 4th Job
                4121000, // MAPLE_WARRIOR
                4121009, // HEROS_WILL
                4121006  // SHADOW_STARS
        ));

        // Night Walker
        CLASS_BUFFS.put("NightWalker", Arrays.asList(
                // 3rd Job
                14101003, // HASTE
                14101004  // FLASH_JUMP
        ));

        // Paladin
        CLASS_BUFFS.put("Paladin", Arrays.asList(
                // 4th Job
                1221000, // MAPLE_WARRIOR
                1221012, // HEROS_WILL
                1221009  // BLAST
        ));

        // Priest
        CLASS_BUFFS.put("Priest", Arrays.asList(
                // 3rd Job
                2311003, // HOLY_SYMBOL
                2311006  // SUMMON_DRAGON
        ));

        // Ranger
        CLASS_BUFFS.put("Ranger", Arrays.asList(
                // 3rd Job
                3111006, // STRAFE
                3111005  // SILVER_HAWK
        ));

        // Rogue
        CLASS_BUFFS.put("Rogue", Arrays.asList(
                // 1st Job
                4001003, // DARK_SIGHT
                4001344  // LUCKY_SEVEN
        ));

        // Shadower
        CLASS_BUFFS.put("Shadower", Arrays.asList(
                // 4th Job
                4221000, // MAPLE_WARRIOR
                4221008, // HEROS_WILL
                4221006  // SMOKE_SCREEN
        ));

        // Sniper
        CLASS_BUFFS.put("Sniper", Arrays.asList(
                // 4th Job
                3211006, // STRAFE
                3211005  // GOLDEN_EAGLE
        ));

        // Spearman
        CLASS_BUFFS.put("Spearman", Arrays.asList(
                // 2nd Job
                1301006, // IRON_WILL
                1301007  // HYPER_BODY
        ));

        // Thunder Breaker
        CLASS_BUFFS.put("ThunderBreaker", Arrays.asList(
                // 3rd Job
                15111005, // SPEED_INFUSION
                15111003  // SHOCKWAVE
        ));

        // Wind Archer
        CLASS_BUFFS.put("WindArcher", Arrays.asList(
                // 3rd Job
                13101006, // WIND_WALK
                13111002  // HURRICANE
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
            // Initialize with an empty list
            PLAYER_CLASS_HISTORY.put(playerId, new ArrayList<>());
        }
    }

    // Helper method to update class history
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