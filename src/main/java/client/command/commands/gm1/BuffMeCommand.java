package client.command.commands.gm1;

import client.Character;
import client.Client;
import client.Job;
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

        // Get the player's job history (assuming it's stored in a list)
        List<Integer> jobHistory = player.getJobHistory(); // You need to implement this method in Character.java

        // Apply buffs for each job in the player's history
        for (int jobId : jobHistory) {
            applyBuffsForJob(player, jobId);
        }
    }

    private void applyBuffsForJob(Character player, int jobId) {
        // Map of job IDs to their respective buffs
        Map<Integer, List<Integer>> jobBuffs = new HashMap<>();

        // Populate the map with all classes and their corresponding buffs
        jobBuffs.put(Job.BEGINNER.getId(), Arrays.asList(
                1001, // Recovery
                1002  // Nimble Feet
        ));

        jobBuffs.put(Job.WARRIOR.getId(), Arrays.asList(
                1000003 // Iron Body
        ));

        jobBuffs.put(Job.FIGHTER.getId(), Arrays.asList(
                1100006, // Rage
                1101004, // Sword Booster
                1101005, // Axe booster
                1101007  // Power Guard
        ));

        jobBuffs.put(Job.CRUSADER.getId(), Arrays.asList(
                1111002 // Combo
        ));

        jobBuffs.put(Job.HERO.getId(), Arrays.asList(
                1121000, // Maple Warrior
                1120003, // Advanced Combo
                1121011, // Heros will
                1121002  // Stance
        ));

        jobBuffs.put(Job.PAGE.getId(), Arrays.asList(
                1201004, // Sword booster
                1201005, // BW booster
                1201007  // Power Guard
        ));

        jobBuffs.put(Job.WHITEKNIGHT.getId(), Arrays.asList(
                1211009  // Magic Crash
        ));

        jobBuffs.put(Job.PALADIN.getId(), Arrays.asList(
                1221000, // Maple Warrior
                1221002, // stance
                1221012 // heros will
        ));

        jobBuffs.put(Job.SPEARMAN.getId(), Arrays.asList(
                1301006, // Iron Will
                1301004, // Spear booster
                1301005, // polearm booster
                1301007  // Hyper Body
        ));

        jobBuffs.put(Job.DRAGONKNIGHT.getId(), Arrays.asList(
                1311005, // Sacrifice
                1311008  // Dragon Blood
        ));

        jobBuffs.put(Job.DARKKNIGHT.getId(), Arrays.asList(
                1321000, // Maple Warrior
                1320006, // berserk
                1321002, // Stance
                1321010  // heros will
        ));

        jobBuffs.put(Job.MAGICIAN.getId(), Arrays.asList(
                2001002, // Magic Guard
                2001005  // Magic Claw
        ));

        jobBuffs.put(Job.FP_WIZARD.getId(), Arrays.asList(
                2101001 // MEDITATION
        ));

        jobBuffs.put(Job.FP_MAGE.getId(), Arrays.asList(
                2111005 // Spell booster
        ));

        jobBuffs.put(Job.FP_ARCHMAGE.getId(), Arrays.asList(
                2121000, // Maple Warrior
                2121004, // Infinity
                2121008, // heros will
                2121002  // MANA_REFLECTION
        ));

        jobBuffs.put(Job.IL_WIZARD.getId(), Arrays.asList(
                2201001 // MEDITATION
        ));

        jobBuffs.put(Job.IL_MAGE.getId(), Arrays.asList(
                2211005 // SPELL_BOOSTER
        ));

        jobBuffs.put(Job.IL_ARCHMAGE.getId(), Arrays.asList(
                2221000, // Maple Warrior
                2221004, // infinity
                2221008, // heros will
                2221002  // MANA_REFLECTION
        ));

        jobBuffs.put(Job.CLERIC.getId(), Arrays.asList(
                2301003, // INVINCIBLE
                2301004  // Bless
        ));

        jobBuffs.put(Job.PRIEST.getId(), Arrays.asList(
                2311003 // Holy Symbol
        ));

        jobBuffs.put(Job.BISHOP.getId(), Arrays.asList(
                2321000, // Maple Warrior
                2321004, // infinity
                2321009, // Heros will
                2321002  // MANA_REFLECTION
        ));

        jobBuffs.put(Job.BOWMAN.getId(), Arrays.asList(

        ));

        jobBuffs.put(Job.HUNTER.getId(), Arrays.asList(
                3101002, // Bow Booster
                3101004  // SOUL_ARROW
        ));

        jobBuffs.put(Job.RANGER.getId(), Arrays.asList(

        ));

        jobBuffs.put(Job.BOWMASTER.getId(), Arrays.asList(
                3121000, // Maple Warrior
                3121009, // Heros will
                3121002  // SHARP_EYES
        ));

        jobBuffs.put(Job.CROSSBOWMAN.getId(), Arrays.asList(
                3201002, // Crossbow Booster
                3201004  // Soul Arrow
        ));

        jobBuffs.put(Job.SNIPER.getId(), Arrays.asList(

        ));

        jobBuffs.put(Job.MARKSMAN.getId(), Arrays.asList(
                3221000, // Maple Warrior
                3221008  // Heros will
        ));

        jobBuffs.put(Job.THIEF.getId(), Arrays.asList(

        ));

        jobBuffs.put(Job.ASSASSIN.getId(), Arrays.asList(
                4101004, // Haste
                4101003  // CLAW_BOOSTER
        ));

        jobBuffs.put(Job.HERMIT.getId(), Arrays.asList(
                4111002, // Shadow Partner
                4111001  // MESO_UP
        ));

        jobBuffs.put(Job.NIGHTLORD.getId(), Arrays.asList(
                4121000, // Maple Warrior
                4121009  // HEROS_WILL
        ));

        jobBuffs.put(Job.BANDIT.getId(), Arrays.asList(
                4201003 // Haste
        ));

        jobBuffs.put(Job.CHIEFBANDIT.getId(), Arrays.asList(
                4211005 // MESO_GUARD

        ));

        jobBuffs.put(Job.SHADOWER.getId(), Arrays.asList(
                4221000, // Maple Warrior
                4221008  // HEROS_WILL
        ));

        jobBuffs.put(Job.PIRATE.getId(), Arrays.asList(
                5001001  // Flash fist
        ));

        jobBuffs.put(Job.BRAWLER.getId(), Arrays.asList(
              5101006  // Knuckler Booster
        ));

        jobBuffs.put(Job.MARAUDER.getId(), Arrays.asList(

        ));

        jobBuffs.put(Job.BUCCANEER.getId(), Arrays.asList(
                5121000 // Maple Warrior

        ));

        jobBuffs.put(Job.GUNSLINGER.getId(), Arrays.asList(
                5201003 // Gun Booster

        ));

        jobBuffs.put(Job.OUTLAW.getId(), Arrays.asList(

        ));

        jobBuffs.put(Job.CORSAIR.getId(), Arrays.asList(
                5221000, // Maple Warrior
                5220001  // ELEMENTAL_BOOST
        ));

        jobBuffs.put(Job.DAWNWARRIOR1.getId(), Arrays.asList(

        ));

        jobBuffs.put(Job.DAWNWARRIOR2.getId(), Arrays.asList(
                11101002, // Final Attack
                11101001, // Sword booster
                11101003  // Rage
        ));

        jobBuffs.put(Job.DAWNWARRIOR3.getId(), Arrays.asList(
                11111001, // Combo
                11110005  // Advance Combo
        ));

        jobBuffs.put(Job.DAWNWARRIOR4.getId(), Arrays.asList(
                11121000 // Maple Warrior

        ));

        jobBuffs.put(Job.BLAZEWIZARD1.getId(), Arrays.asList(
                12001002, // MAGIC_ARMOR
                12001001  // MAGIC_GUARD
        ));

        jobBuffs.put(Job.BLAZEWIZARD2.getId(), Arrays.asList(
                12101000, // MEDITATION
                12101004  // SPELL_BOOSTER
        ));

        jobBuffs.put(Job.BLAZEWIZARD3.getId(), Arrays.asList(

        ));

        jobBuffs.put(Job.BLAZEWIZARD4.getId(), Arrays.asList(
                12121000 // Maple Warrior

        ));

        jobBuffs.put(Job.WINDARCHER1.getId(), Arrays.asList(
                13001002 // Focus

        ));

        jobBuffs.put(Job.WINDARCHER2.getId(), Arrays.asList(
                13101001, // BOW_BOOSTER
                13101003, // Soul of Arrow
                13101002  // FINAL_ATTACK
        ));

        jobBuffs.put(Job.WINDARCHER3.getId(), Arrays.asList(

        ));

        jobBuffs.put(Job.WINDARCHER4.getId(), Arrays.asList(
                13121000 // Maple Warrior

        ));

        jobBuffs.put(Job.NIGHTWALKER1.getId(), Arrays.asList(

        ));

        jobBuffs.put(Job.NIGHTWALKER2.getId(), Arrays.asList(
                14101002, // Claw Booster
                14101003  // HASTE
        ));

        jobBuffs.put(Job.NIGHTWALKER3.getId(), Arrays.asList(
                14111000 // SHADOW_PARTNER

        ));

        jobBuffs.put(Job.NIGHTWALKER4.getId(), Arrays.asList(
                14121000 // Maple Warrior

        ));

        jobBuffs.put(Job.THUNDERBREAKER1.getId(), Arrays.asList(

        ));

        jobBuffs.put(Job.THUNDERBREAKER2.getId(), Arrays.asList(
                15101002 // KNUCKLER_BOOSTER

        ));

        jobBuffs.put(Job.THUNDERBREAKER3.getId(), Arrays.asList(

        ));

        jobBuffs.put(Job.THUNDERBREAKER4.getId(), Arrays.asList(
                15121000 // Maple Warrior

        ));

        // Apply buffs for the given job
        if (jobBuffs.containsKey(jobId)) {
            for (int skillId : jobBuffs.get(jobId)) {
                SkillFactory.getSkill(skillId).getEffect(SkillFactory.getSkill(skillId).getMaxLevel()).applyTo(player);
            }
        }
    }
}