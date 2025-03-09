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
        List<Integer> jobHistory = player.getJobHistory(); // Ensure this method is implemented in Character.java

        System.out.println("Job history for " + player.getName() + ": " + jobHistory);

        for (int jobId : jobHistory) {
            System.out.println("Applying buffs for job ID: " + jobId);
            applyBuffsForJob(player, jobId);
        }
    }

    private void applyBuffsForJob(Character player, int jobId) {
        Map<Integer, List<Integer>> jobBuffs = new HashMap<>();

        // Beginner
        jobBuffs.put(Job.BEGINNER.getId(), Arrays.asList(
                1001, // Recovery
                1002  // Nimble Feet
        ));

        // Warrior
        jobBuffs.put(Job.WARRIOR.getId(), Arrays.asList(
                1000003 // Iron Body
        ));

        // Fighter
        jobBuffs.put(Job.FIGHTER.getId(), Arrays.asList(
                1100006, // Rage
                1101004, // Sword Booster
                1101005, // Axe Booster
                1101007  // Power Guard
        ));

        // Crusader
        jobBuffs.put(Job.CRUSADER.getId(), Arrays.asList(
                1111002 // Combo
        ));

        // Hero
        jobBuffs.put(Job.HERO.getId(), Arrays.asList(
                1121000, // Maple Warrior
                1120003, // Advanced Combo
                1121011, // Hero's Will
                1121002  // Stance
        ));

        // Page
        jobBuffs.put(Job.PAGE.getId(), Arrays.asList(
                1201004, // Sword Booster
                1201005, // BW Booster
                1201007  // Power Guard
        ));

        // White Knight
        jobBuffs.put(Job.WHITEKNIGHT.getId(), Arrays.asList(
                1211009  // Magic Crash
        ));

        // Paladin
        jobBuffs.put(Job.PALADIN.getId(), Arrays.asList(
                1221000, // Maple Warrior
                1221002, // Stance
                1221012  // Hero's Will
        ));

        // Magician
        jobBuffs.put(Job.MAGICIAN.getId(), Arrays.asList(
                2001002, // Magic Guard
                2001005  // Magic Claw
        ));

        // FP Wizard
        jobBuffs.put(Job.FP_WIZARD.getId(), Arrays.asList(
                2101001 // Meditation
        ));

        // Bishop
        jobBuffs.put(Job.BISHOP.getId(), Arrays.asList(
                2321000, // Maple Warrior
                2321004, // Infinity
                2321009, // Hero's Will
                2321002  // Mana Reflection
        ));

        // Thief
        jobBuffs.put(Job.THIEF.getId(), Arrays.asList(
                4101004, // Haste
                4101003  // Claw Booster
        ));

        // Hermit
        jobBuffs.put(Job.HERMIT.getId(), Arrays.asList(
                4111002, // Shadow Partner
                4111001  // Meso Up
        ));

        // Night Lord
        jobBuffs.put(Job.NIGHTLORD.getId(), Arrays.asList(
                4121000, // Maple Warrior
                4121009  // Hero's Will
        ));

        // Pirate
        jobBuffs.put(Job.PIRATE.getId(), Arrays.asList(
                5001001  // Flash Fist
        ));

        // Buccaneer
        jobBuffs.put(Job.BUCCANEER.getId(), Arrays.asList(
                5121000 // Maple Warrior
        ));

        // Thunder Breaker
        jobBuffs.put(Job.THUNDERBREAKER2.getId(), Arrays.asList(
                15101002 // Knuckler Booster
        ));

        jobBuffs.put(Job.THUNDERBREAKER4.getId(), Arrays.asList(
                15121000 // Maple Warrior
        ));

        // Apply buffs for the given job
        if (!jobBuffs.containsKey(jobId)) {
            System.out.println("No buffs found for job ID: " + jobId);
            return;
        }

        for (int skillId : jobBuffs.get(jobId)) {
            if (SkillFactory.getSkill(skillId) == null) {
                System.out.println("Skill ID " + skillId + " not found in SkillFactory.");
                continue;
            }

            System.out.println("Applying skill ID: " + skillId + " to " + player.getName());
            SkillFactory.getSkill(skillId).getEffect(SkillFactory.getSkill(skillId).getMaxLevel()).applyTo(player);
        }
    }
}
