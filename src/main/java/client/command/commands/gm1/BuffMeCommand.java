package client.command.commands.gm1;

import client.Character;
import client.Client;
import client.Job;
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
        List<Integer> jobHistory = player.getJobHistory(); // Ensure this method exists in Character.java

        if (jobHistory.isEmpty()) {
            player.sendMessage("No job history found.");
            return;
        }

        for (int jobId : jobHistory) {
            applyBuffsForJob(player, jobId);
        }
    }

    private void applyBuffsForJob(Character player, int jobId) {
        Map<Integer, List<Integer>> jobBuffs = new HashMap<>();

        // Beginner
        jobBuffs.put(0, Arrays.asList(
                1001, // Recovery
                1002  // Nimble Feet
        ));

        // Warrior Branch
        jobBuffs.put(100, Arrays.asList(
                1000003 // Iron Body
        ));
        jobBuffs.put(110, Arrays.asList(
                1100006, // Rage
                1101004, // Sword Booster
                1101005, // Axe Booster
                1101007  // Power Guard
        ));
        jobBuffs.put(111, Arrays.asList(
                1111002 // Combo Attack
        ));
        jobBuffs.put(112, Arrays.asList(
                1121000, // Maple Warrior
                1120003, // Advanced Combo
                1121011, // Hero's Will
                1121002  // Stance
        ));

        // Magician Branch
        jobBuffs.put(200, Arrays.asList(
                2001002, // Magic Guard
                2001005  // Magic Claw
        ));
        jobBuffs.put(210, Arrays.asList(
                2101001 // Meditation
        ));
        jobBuffs.put(211, Arrays.asList(
                2111005 // Spell Booster
        ));
        jobBuffs.put(212, Arrays.asList(
                2121000, // Maple Warrior
                2121004, // Infinity
                2121008, // Hero's Will
                2121002  // Mana Reflection
        ));

        // Bowman Branch
        jobBuffs.put(300, Arrays.asList(
        ));
        jobBuffs.put(310, Arrays.asList(
                3101002, // Bow Booster
                3101004  // Soul Arrow
        ));
        jobBuffs.put(312, Arrays.asList(
                3121000, // Maple Warrior
                3121009, // Hero's Will
                3121002  // Sharp Eyes
        ));

        // Thief Branch
        jobBuffs.put(400, Arrays.asList(
        ));
        jobBuffs.put(410, Arrays.asList(
                4101004, // Haste
                4101003  // Claw Booster
        ));
        jobBuffs.put(411, Arrays.asList(
                4111002, // Shadow Partner
                4111001  // Meso Up
        ));
        jobBuffs.put(412, Arrays.asList(
                4121000, // Maple Warrior
                4121009  // Hero's Will
        ));

        // Pirate Branch
        jobBuffs.put(500, Arrays.asList(
                5001001 // Flash Fist
        ));
        jobBuffs.put(510, Arrays.asList(
                5101006 // Knuckle Booster
        ));
        jobBuffs.put(512, Arrays.asList(
                5121000 // Maple Warrior
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
}
