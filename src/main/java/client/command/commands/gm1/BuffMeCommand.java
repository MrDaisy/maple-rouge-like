package client.command.commands.gm1;

import client.Character;
import client.Client;
import client.Skill;
import client.SkillFactory;
import client.command.Command;

import java.util.*;

public class BuffMeCommand extends Command {
    {
        setDescription("Activate buffs based on the player's job history.");
    }


    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        player.loadJobHistory(); // Load job history from database

        List<Integer> jobHistory = player.getJobHistory();

        if (jobHistory.isEmpty()) {
            player.sendMessage("You still have not attained any buffs.");
            return;
        }

        // Ensure current job is included
        if (!jobHistory.contains(player.getJob().getId())) {
            jobHistory.add(player.getJob().getId());
        }

        // Collect all jobs in the player's advancement path
        Set<Integer> allJobs = new HashSet<>();
        for (int jobId : jobHistory) {
            allJobs.addAll(getJobPath(jobId));
        }

        // Apply buffs from all collected jobs
        for (int jobId : allJobs) {
            applyBuffsForJob(player, jobId);
        }

        player.saveJobHistory(); // Save job history to database
    }

    private void applyBuffsForJob(Character player, int jobId) {
        Map<Integer, List<Integer>> jobBuffs = new HashMap<>();

        // BEGINNER
        jobBuffs.put(0, Arrays.asList(
        ));

         /* Warrior Branch
                WARRIOR(100),
                FIGHTER(110), CRUSADER(111), HERO(112),
                PAGE(120), WHITEKNIGHT(121), PALADIN(122),
                SPEARMAN(130), DRAGONKNIGHT(131), DARKKNIGHT(132),*/

        // Warrior
        jobBuffs.put(100, Arrays.asList(
                1000003 // Iron Body
        ));
        //Fighter
        jobBuffs.put(110, Arrays.asList(
                1100006, // Rage
                1101004, // Sword Booster
                1101005, // Axe Booster
                1101007  // Power Guard
        ));
        //CRUSADER
        jobBuffs.put(111, Arrays.asList(
                1111002 // Combo Attack
        ));
        //HERO
        jobBuffs.put(112, Arrays.asList(
                1121000, // Maple Warrior
                1120003, // Advanced Combo
                1121011, // Hero's Will
                1121002  // Stance
        ));
        //PAGE
        jobBuffs.put(120, Arrays.asList(
                1201004, // Sword booster
                1201005, // BW booster
                1201007  // Power Guard
        ));
        //WHITEKNIGHT
        jobBuffs.put(121, Arrays.asList(
                1211009  // Magic Crash
        ));
        //PALADIN
        jobBuffs.put(122, Arrays.asList(
                1221000, // Maple Warrior
                1221002, // stance
                1221012 // heros will
        ));
        //SPEARMAN
        jobBuffs.put(130, Arrays.asList(
                1301006, // Iron Will
                1301004, // Spear booster
                1301005, // polearm booster
                1301007  // Hyper Body
        ));
        //DRAGONKNIGHT
        jobBuffs.put(131, Arrays.asList(
                1311005, // Sacrifice
                1311008  // Dragon Blood
        ));
        //DARKKNIGHT
        jobBuffs.put(132, Arrays.asList(
                1321000, // Maple Warrior
                1320006, // berserk
                1321002, // Stance
                1321010  // heros will
        ));

     /*   Magician Branch
                MAGICIAN(200),
                FP_WIZARD(210), FP_MAGE(211), FP_ARCHMAGE(212),
                IL_WIZARD(220), IL_MAGE(221), IL_ARCHMAGE(222),
                CLERIC(230), PRIEST(231), BISHOP(232),*/

        // Magician
        jobBuffs.put(200, Arrays.asList(
                2001002 // Magic Guard
        ));
        //FP_WIZARD
        jobBuffs.put(210, Arrays.asList(
                2101001 // Meditation
        ));
        //FP_MAGE
        jobBuffs.put(211, Arrays.asList(
                2111005 // Spell Booster
        ));
        //FP_ARCHMAGE
        jobBuffs.put(212, Arrays.asList(
                2121000, // Maple Warrior
                2121004, // Infinity
                2121008, // Hero's Will
                2121002  // Mana Reflection
        ));
        //IL_WIZARD
        jobBuffs.put(220, Arrays.asList(
                2201001 // MEDITATION
        ));
        //IL_MAGE
        jobBuffs.put(221, Arrays.asList(
                2211005 // SPELL_BOOSTER
        ));
        //IL_ARCHMAGE
        jobBuffs.put(222, Arrays.asList(
                2221000, // Maple Warrior
                2221004, // infinity
                2221008, // heros will
                2221002  // MANA_REFLECTION
        ));
        //CLERIC
        jobBuffs.put(230, Arrays.asList(
                2301003, // INVINCIBLE
                2301004  // Bless
        ));
        //PRIEST
        jobBuffs.put(231, Arrays.asList(
                2311003 // Holy Symbol
        ));
        //BISHOP
        jobBuffs.put(232, Arrays.asList(
                2321000, // Maple Warrior
                2321004, // infinity
                2321009, // Heros will
                2321002  // MANA_REFLECTION
        ));


       /*  Bowman Branch
       BOWMAN(300),
        HUNTER(310), RANGER(311), BOWMASTER(312),
        CROSSBOWMAN(320), SNIPER(321), MARKSMAN(322),*/

        //Bowman
        jobBuffs.put(300, Arrays.asList(
        ));
        //HUNTER
        jobBuffs.put(310, Arrays.asList(
                3101002, // Bow Booster
                3101004  // SOUL_ARROW
        ));
        //RANGER
        jobBuffs.put(311, Arrays.asList(

        ));
        //BOWMASTER
        jobBuffs.put(312, Arrays.asList(
                3121000, // Maple Warrior
                3121009, // Heros will
                3121002  // SHARP_EYES
        ));
        //CROSSBOWMAN
        jobBuffs.put(320, Arrays.asList(
                3201002, // Crossbow Booster
                3201004  // Soul Arrow
        ));
        //SNIPER
        jobBuffs.put(321, Arrays.asList(

        ));
        //MARKSMAN
        jobBuffs.put(322, Arrays.asList(
                3221000, // Maple Warrior
                3221008  // Heros will
        ));

        // Thief Branch
/*  THIEF(400),
    ASSASSIN(410), HERMIT(411), NIGHTLORD(412),
    BANDIT(420), CHIEFBANDIT(421), SHADOWER(422),*/

        // Thief
        jobBuffs.put(400, Arrays.asList(
        ));
        // ASSASSIN
        jobBuffs.put(410, Arrays.asList(
                4101004, // Haste
                4101003  // Claw Booster
        ));
        // HERMIT
        jobBuffs.put(411, Arrays.asList(
                4111002, // Shadow Partner
                4111001  // Meso Up
        ));
        // NIGHTLORD
        jobBuffs.put(412, Arrays.asList(
                4121000, // Maple Warrior
                4121009  // Hero's Will
        ));
        // BANDIT
        jobBuffs.put(420, Arrays.asList(
                4201003  // Haste
        ));
        // CHIEFBANDIT
        jobBuffs.put(421, Arrays.asList(
                4211005 // MESO_GUARD
        ));
        // SHADOWER
        jobBuffs.put(422, Arrays.asList(
                4221000, // Maple Warrior
                4221008  // HEROS_WILL
        ));

        /*// Pirate Branch
         PIRATE(500),
        BRAWLER(510), MARAUDER(511), BUCCANEER(512),
        GUNSLINGER(520), OUTLAW(521), CORSAIR(522),
        */

        // Pirate
        jobBuffs.put(500, Arrays.asList(
                5001001 // Flash Fist
        ));
        // BRAWLER
        jobBuffs.put(510, Arrays.asList(
                5101006 // Knuckle Booster
        ));
        // MARAUDER
        jobBuffs.put(511, Arrays.asList(

        ));
        // BUCCANEER
        jobBuffs.put(512, Arrays.asList(
                5121000 // Maple Warrior
        ));
        // GUNSLINGER
        jobBuffs.put(520, Arrays.asList(
                5201003 // Gun Booster
        ));
        // OUTLAW
        jobBuffs.put(521, Arrays.asList(

        ));
        // CORSAIR
        jobBuffs.put(522, Arrays.asList(
                5221000, // Maple Warrior
                5220001  // ELEMENTAL_BOOST
        ));

        /*
        * NOBLESSE(1000),
    DAWNWARRIOR1(1100), DAWNWARRIOR2(1110), DAWNWARRIOR3(1111), DAWNWARRIOR4(1112),
    BLAZEWIZARD1(1200), BLAZEWIZARD2(1210), BLAZEWIZARD3(1211), BLAZEWIZARD4(1212),
    WINDARCHER1(1300), WINDARCHER2(1310), WINDARCHER3(1311), WINDARCHER4(1312),
    NIGHTWALKER1(1400), NIGHTWALKER2(1410), NIGHTWALKER3(1411), NIGHTWALKER4(1412),
    THUNDERBREAKER1(1500), THUNDERBREAKER2(1510), THUNDERBREAKER3(1511), THUNDERBREAKER4(1512),
        */
        // NOBLESSE
        jobBuffs.put(1000, Arrays.asList(

        ));
        //DAWNWARRIOR1
        jobBuffs.put(1100, Arrays.asList(

        ));
        //DAWNWARRIOR2
        jobBuffs.put(1110, Arrays.asList(
                11101002, // Final Attack
                11101001, // Sword booster
                11101003  // Rage
        ));
        //DAWNWARRIOR3
        jobBuffs.put(1111, Arrays.asList(
                11111001, // Combo
                11110005  // Advance Combo
        ));
        //DAWNWARRIOR4
        jobBuffs.put(1112, Arrays.asList(
                11121000 // Maple Warrior
        ));
        //BLAZEWIZARD1
        jobBuffs.put(1200, Arrays.asList(
                12001002, // MAGIC_ARMOR
                12001001  // MAGIC_GUARD
        ));
        //BLAZEWIZARD2
        jobBuffs.put(1210, Arrays.asList(
                12101000, // MEDITATION
                12101004  // SPELL_BOOSTER
        ));
        //BLAZEWIZARD3
        jobBuffs.put(1211, Arrays.asList(

        ));
        //BLAZEWIZARD4
        jobBuffs.put(1212, Arrays.asList(
                12121000 // Maple Warrior
        ));
        //WINDARCHER1
        jobBuffs.put(1300, Arrays.asList(
                13001002 // Focus

        ));
        //WINDARCHER2
        jobBuffs.put(1310, Arrays.asList(
                13101001, // BOW_BOOSTER
                13101003, // Soul of Arrow
                13101002  // FINAL_ATTACK
        ));
        //WINDARCHER3
        jobBuffs.put(1311, Arrays.asList(

        ));
        //WINDARCHER4
        jobBuffs.put(1312, Arrays.asList(
                13121000 // Maple Warrior

        ));
        //NIGHTWALKER1
        jobBuffs.put(1400, Arrays.asList(

        ));
        //NIGHTWALKER2
        jobBuffs.put(1410, Arrays.asList(
                14101002, // Claw Booster
                14101003  // HASTE
        ));
        //NIGHTWALKER3
        jobBuffs.put(1411, Arrays.asList(
                14111000 // SHADOW_PARTNER

        ));
        //NIGHTWALKER4
        jobBuffs.put(1412, Arrays.asList(
                14121000 // Maple Warrior

        ));
        //THUNDERBREAKER1
        jobBuffs.put(1500, Arrays.asList(

        ));
        //THUNDERBREAKER2
        jobBuffs.put(1510, Arrays.asList(
                15101002 // KNUCKLER_BOOSTER

        ));
        //THUNDERBREAKER3
        jobBuffs.put(1511, Arrays.asList(

        ));
        //THUNDERBREAKER4
        jobBuffs.put(1512, Arrays.asList(
                15121000 // Maple Warrior

        ));

        // Apply buffs
        if (jobBuffs.containsKey(jobId)) {
            for (int skillId : jobBuffs.get(jobId)) {
                Skill skill = SkillFactory.getSkill(skillId);
                if (skill != null) {
                    skill.getEffect(skill.getMaxLevel()).applyTo(player);
                }
            }
        } else {
            player.sendMessage("No buffs found for job ID: " + jobId);
        }
    }
    private List<Integer> getJobPath(int jobId) {
        Map<Integer, List<Integer>> jobPaths = new HashMap<>();

        // Define full job paths
        jobPaths.put(112, Arrays.asList(111, 110, 100)); // Hero
        jobPaths.put(111, Arrays.asList(110, 100));
        jobPaths.put(110, Arrays.asList(100));

        jobPaths.put(122, Arrays.asList(121, 120, 100)); // Paladin
        jobPaths.put(121, Arrays.asList(120, 100));
        jobPaths.put(120, Arrays.asList(100));

        jobPaths.put(132, Arrays.asList(131, 130, 100)); // Dark Knight
        jobPaths.put(131, Arrays.asList(130, 100));
        jobPaths.put(130, Arrays.asList(100));

        jobPaths.put(212, Arrays.asList(211, 210, 200)); // Fire/Poison Mage
        jobPaths.put(211, Arrays.asList(210, 200));
        jobPaths.put(210, Arrays.asList(200));

        jobPaths.put(222, Arrays.asList(221, 220, 200)); // Ice/Lightning Mage
        jobPaths.put(221, Arrays.asList(220, 200));
        jobPaths.put(220, Arrays.asList(200));

        jobPaths.put(232, Arrays.asList(231, 230, 200)); // Bishop
        jobPaths.put(231, Arrays.asList(230, 200));
        jobPaths.put(230, Arrays.asList(200));

        jobPaths.put(312, Arrays.asList(311, 310, 300)); // Bowmaster
        jobPaths.put(311, Arrays.asList(310, 300));
        jobPaths.put(310, Arrays.asList(300));

        jobPaths.put(322, Arrays.asList(321, 320, 300)); // Marksman
        jobPaths.put(321, Arrays.asList(320, 300));
        jobPaths.put(320, Arrays.asList(300));

        jobPaths.put(412, Arrays.asList(411, 410, 400)); // Night Lord
        jobPaths.put(411, Arrays.asList(410, 400));
        jobPaths.put(410, Arrays.asList(400));

        jobPaths.put(422, Arrays.asList(421, 420, 400)); // Shadower
        jobPaths.put(421, Arrays.asList(420, 400));
        jobPaths.put(420, Arrays.asList(400));

        jobPaths.put(512, Arrays.asList(511, 510, 500)); // Buccaneer
        jobPaths.put(511, Arrays.asList(510, 500));
        jobPaths.put(510, Arrays.asList(500));

        jobPaths.put(522, Arrays.asList(521, 520, 500)); // Corsair
        jobPaths.put(521, Arrays.asList(520, 500));
        jobPaths.put(520, Arrays.asList(500));

        // Cygnus Knights
        jobPaths.put(1512, Arrays.asList(1511, 1510, 1500, 1000)); // Thunder Breaker
        jobPaths.put(1511, Arrays.asList(1510, 1500, 1000));
        jobPaths.put(1510, Arrays.asList(1500, 1000));
        jobPaths.put(1500, Arrays.asList(1000));

        jobPaths.put(1412, Arrays.asList(1411, 1410, 1400, 1000)); // Night Walker
        jobPaths.put(1411, Arrays.asList(1410, 1400, 1000));
        jobPaths.put(1410, Arrays.asList(1400, 1000));
        jobPaths.put(1400, Arrays.asList(1000));

        jobPaths.put(1312, Arrays.asList(1311, 1310, 1300, 1000)); // Wind Archer
        jobPaths.put(1311, Arrays.asList(1310, 1300, 1000));
        jobPaths.put(1310, Arrays.asList(1300, 1000));
        jobPaths.put(1300, Arrays.asList(1000));

        jobPaths.put(1212, Arrays.asList(1211, 1210, 1200, 1000)); // Blaze Wizard
        jobPaths.put(1211, Arrays.asList(1210, 1200, 1000));
        jobPaths.put(1210, Arrays.asList(1200, 1000));
        jobPaths.put(1200, Arrays.asList(1000));

        jobPaths.put(1112, Arrays.asList(1111, 1110, 1100, 1000)); // Dawn Warrior
        jobPaths.put(1111, Arrays.asList(1110, 1100, 1000));
        jobPaths.put(1110, Arrays.asList(1100, 1000));
        jobPaths.put(1100, Arrays.asList(1000));

        // Dynamically construct full path
        List<Integer> fullPath = new ArrayList<>();
        int currentJob = jobId;

        // Traverse backwards through the job path until we reach the base job
        while (jobPaths.containsKey(currentJob)) {
            fullPath.add(currentJob);
            List<Integer> previousJobs = jobPaths.get(currentJob);
            if (previousJobs.isEmpty()) break;
            currentJob = previousJobs.get(0); // Always take the first (previous) job
        }

        // Add the final base job
        fullPath.add(currentJob);

        return fullPath;
    }
}
/*
* How This Works
🔹 If a player gets 112 (Hero) → They automatically get [112, 111, 110, 100].
🔹 If a player gets 111 (3rd job) → They automatically get [111, 110, 100].
🔹 If a player gets 110 (2nd job) → They automatically get [110, 100].
🔹 If a player gets 100 (1st job) → They only get [100].
🔹 The same logic applies to all other classes.

Why This Is Better
✅ Dynamic Traversal → Instead of hardcoding long lists, it dynamically finds all previous jobs.
✅ Works for Any Job Advancement → Handles cases where players are at any stage in their job path.
✅ Supports Future Expansions → If new jobs are added, you just add them to jobPaths, and the traversal logic still works.
*
* */