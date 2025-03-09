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

    private static final Map<Integer, List<Integer>> jobBuffs = new HashMap<>();

    static {
        // Populate jobBuffs map with all classes and corresponding buffs
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
                1101005, // Axe Booster
                1101007  // Power Guard
        ));

        jobBuffs.put(Job.CRUSADER.getId(), Arrays.asList(
                1111002 // Combo
        ));

        jobBuffs.put(Job.HERO.getId(), Arrays.asList(
                1121000, // Maple Warrior
                1120003, // Advanced Combo
                1121011, // Hero's Will
                1121002  // Stance
        ));

        jobBuffs.put(Job.PAGE.getId(), Arrays.asList(
                1201004, // Sword Booster
                1201005, // BW Booster
                1201007  // Power Guard
        ));

        jobBuffs.put(Job.WHITEKNIGHT.getId(), Arrays.asList(
                1211009  // Magic Crash
        ));

        jobBuffs.put(Job.PALADIN.getId(), Arrays.asList(
                1221000, // Maple Warrior
                1221002, // Stance
                1221012  // Hero's Will
        ));

        jobBuffs.put(Job.SPEARMAN.getId(), Arrays.asList(
                1301006, // Iron Will
                1301004, // Spear Booster
                1301005, // Polearm Booster
                1301007  // Hyper Body
        ));

        jobBuffs.put(Job.DRAGONKNIGHT.getId(), Arrays.asList(
                1311005, // Sacrifice
                1311008  // Dragon Blood
        ));

        jobBuffs.put(Job.DARKKNIGHT.getId(), Arrays.asList(
                1321000, // Maple Warrior
                1320006, // Berserk
                1321002, // Stance
                1321010  // Hero's Will
        ));

        jobBuffs.put(Job.MAGICIAN.getId(), Arrays.asList(
                2001002, // Magic Guard
                2001005  // Magic Claw
        ));

        jobBuffs.put(Job.FP_WIZARD.getId(), Arrays.asList(
                2101001 // Meditation
        ));

        jobBuffs.put(Job.FP_MAGE.getId(), Arrays.asList(
                2111005 // Spell Booster
        ));

        jobBuffs.put(Job.FP_ARCHMAGE.getId(), Arrays.asList(
                2121000, // Maple Warrior
                2121004, // Infinity
                2121008, // Hero's Will
                2121002  // Mana Reflection
        ));

        jobBuffs.put(Job.IL_WIZARD.getId(), Arrays.asList(
                2201001 // Meditation
        ));

        jobBuffs.put(Job.IL_MAGE.getId(), Arrays.asList(
                2211005 // Spell Booster
        ));

        jobBuffs.put(Job.IL_ARCHMAGE.getId(), Arrays.asList(
                2221000, // Maple Warrior
                2221004, // Infinity
                2221008, // Hero's Will
                2221002  // Mana Reflection
        ));

        jobBuffs.put(Job.CLERIC.getId(), Arrays.asList(
                2301003, // Invincible
                2301004  // Bless
        ));

        jobBuffs.put(Job.PRIEST.getId(), Arrays.asList(
                2311003 // Holy Symbol
        ));

        jobBuffs.put(Job.BISHOP.getId(), Arrays.asList(
                2321000, // Maple Warrior
                2321004, // Infinity
                2321009, // Hero's Will
                2321002  // Mana Reflection
        ));
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();

        // Get the player's job history (assuming it's stored in a list)
        List<Integer> jobHistory = player.getJobHistory(); // Ensure this method exists in Character.java

        // Apply buffs for each job in the player's history
        for (int jobId : jobHistory) {
            applyBuffsForJob(player, jobId);
        }
    }

    private void applyBuffsForJob(Character player, int jobId) {
        if (jobBuffs.containsKey(jobId)) {
            for (int skillId : jobBuffs.get(jobId)) {
                if (SkillFactory.getSkill(skillId) != null) {
                    SkillFactory.getSkill(skillId).getEffect(SkillFactory.getSkill(skillId).getMaxLevel()).applyTo(player);
                }
            }
        }
    }
}
