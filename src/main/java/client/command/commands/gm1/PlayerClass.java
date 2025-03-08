package client.command.commands.gm1;

// Enum to represent player classes and their buffs
public enum PlayerClass {
    ARAN(new int[]{
            21000000, // COMBO_ABILITY
            21000002, // DOUBLE_SWING
            21001001, // COMBAT_STEP
            21001003, // POLEARM_BOOSTER
            21100000, // POLEARM_MASTERY
            21100001, // TRIPLE_SWING
            21100002, // FINAL_CHARGE
            21100005, // COMBO_DRAIN
            21100004, // COMBO_SMASH
            21101003, // BODY_PRESSURE
            21110002, // FULL_SWING
            21110000, // COMBO_CRITICAL
            21110003, // FINAL_TOSS
            21110004, // COMBO_FENRIR
            21111005, // SNOW_CHARGE
            21111001, // SMART_KNOCKBACK
            21110006, // ROLLING_SPIN
            21110007, // HIDDEN_FULL_DOUBLE
            21110008, // HIDDEN_FULL_TRIPLE
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
    }),
    ARCHER(new int[]{
            3000000, // BLESSING_OF_AMAZON
            3000002, // EYE_OF_AMAZON
            3000001, // CRITICAL_SHOT
            3001003, // FOCUS
            3001005, // DOUBLE_SHOT
            3001004  // ARROW_BLOW
    }),
    ASSASSIN(new int[]{
            4100000, // CLAW_MASTERY
            4100001, // CRITICAL_THROW
            4100002, // ENDURE
            4101003, // CLAW_BOOSTER
            4101004, // HASTE
            4101005  // DRAIN
    }),
    BANDIT(new int[]{
            4200000, // DAGGER_MASTERY
            4200001, // ENDURE
            4201002, // DAGGER_BOOSTER
            4201003, // HASTE
            4201004, // STEAL
            4201005  // SAVAGE_BLOW
    }),
    BEGINNER(new int[]{
            12, // BLESSING_OF_THE_FAIRY
            8,  // FOLLOW_THE_LEADER
            100, // MAP_CHAIR
            1001, // THREE_SNAILS
            1001, // RECOVERY
            1002, // NIMBLE_FEET
            1004, // MONSTER_RIDER
            1005, // ECHO_OF_HERO
            1009, // BAMBOO_RAIN
            1010, // INVINCIBLE_BARRIER
            1011, // POWER_EXPLOSION
            1013, // SPACESHIP
            1014, // SPACE_DASH
            1017, // YETI_MOUNT1
            1018, // YETI_MOUNT2
            1019, // WITCH_BROOMSTICK
            1031, // BALROG_MOUNT
    }),
    BISHOP(new int[]{
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
    }),
    BLAZE_WIZARD(new int[]{
            12000000, // INCREASING_MAX_MP
            12001001, // MAGIC_GUARD
            12001002, // MAGIC_ARMOR
            12001003, // MAGIC_CLAW
            12001004, // FLAME
            12101000, // MEDITATION
            12101001, // SLOW
            12101002, // FIRE_ARROW
            12101003, // TELEPORT
            12101004, // SPELL_BOOSTER
            12101005, // ELEMENTAL_RESET
            12101006, // FIRE_PILLAR
            12110000, // ELEMENTAL_RESISTANCE
            12110001, // ELEMENT_AMPLIFICATION
            12111002, // SEAL
            12111003, // METEOR_SHOWER
            12111004, // IFRIT
            12111005, // FLAME_GEAR
            12111006  // FIRE_STRIKE
    }),
    BOWMASTER(new int[]{
            3121000, // MAPLE_WARRIOR
            3121002, // SHARP_EYES
            3121003, // DRAGONS_BREATH
            3121004, // HURRICANE
            3120005, // BOW_EXPERT
            3121006, // PHOENIX
            3121007, // HAMSTRING
            3121008, // CONCENTRATE
            3121009  // HEROS_WILL
    }),
    BRAWLER(new int[]{
            5100000, // IMPROVE_MAX_HP
            5100001, // KNUCKLER_MASTERY
            5101002, // BACK_SPIN_BLOW
            5101003, // DOUBLE_UPPERCUT
            5101004, // CORKSCREW_BLOW
            5101005, // MP_RECOVERY
            5101006, // KNUCKLER_BOOSTER
            5101007  // OAK_BARREL
    }),
    BUCCANEER(new int[]{
            5121000, // MAPLE_WARRIOR
            5121002, // ENERGY_ORB
            5121003, // SUPER_TRANSFORMATION
            5121004, // DEMOLITION
            5121005, // SNATCH
            5121007, // BARRAGE
            5121008, // PIRATES_RAGE
            5121009, // SPEED_INFUSION
            5121010, // TIME_LEAP
            5121001  // DRAGON_STRIKE
    }),
    CHIEF_BANDIT(new int[]{
            4210000, // SHIELD_MASTERY
            4211001, // CHAKRA
            4211002, // ASSAULTER
            4211003, // PICKPOCKET
            4211004, // BAND_OF_THIEVES
            4211005, // MESO_GUARD
            4211006  // MESO_EXPLOSION
    });

    private final int[] skills;

    PlayerClass(int[] skills) {
        this.skills = skills;
    }

    public int[] getSkills() {
        return skills;
    }
}
