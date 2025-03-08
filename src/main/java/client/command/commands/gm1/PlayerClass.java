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
            3001003, // FOCUS
    }),
    ASSASSIN(new int[]{
            4101003, // CLAW_BOOSTER
            4101004, // HASTE
    }),
    BANDIT(new int[]{
            4201002, // DAGGER_BOOSTER
            4201003, // HASTE
    }),
    BEGINNER(new int[]{
            1005, // ECHO_OF_HERO
    }),
    Cleric(new int[]{
            2301003,// INVINCIBLE
            2301004,// BLESS
    }),
    BISHOP(new int[]{
            2321000, // MAPLE_WARRIOR
            2321002, // MANA_REFLECTION
            2321004, // INFINITY
            2321005, // HOLY_SHIELD
            2321009  // HEROS_WILL
    }),
    BLAZE_WIZARD(new int[]{
            12001001, // MAGIC_GUARD
            12001002, // MAGIC_ARMOR
            12101000, // MEDITATION
       }),
    BOWMASTER(new int[]{
            3121000, // MAPLE_WARRIOR
            3121002, // SHARP_EYES
            3121009  // HEROS_WILL
    }),
    BRAWLER(new int[]{
            5101006, // KNUCKLER_BOOSTER
    }),
    BUCCANEER(new int[]{
            5121000, // MAPLE_WARRIOR
    }),
    CHIEF_BANDIT(new int[]{
            4211003, // PICKPOCKET
            4211005, // MESO_GUARD
           });

    private final int[] skills;

    PlayerClass(int[] skills) {
        this.skills = skills;
    }

    public int[] getSkills() {
        return skills;
    }
}
