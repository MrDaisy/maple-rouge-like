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
                1221002,  // stance
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
                3101005  // Arrow Bomb
        ));

        jobBuffs.put(Job.RANGER.getId(), Arrays.asList(
                3111004, // Arrow Rain
                3111005  // Silver Hawk
        ));

        jobBuffs.put(Job.BOWMASTER.getId(), Arrays.asList(
                3121000, // Maple Warrior
                3121004  // Hurricane
        ));

        jobBuffs.put(Job.CROSSBOWMAN.getId(), Arrays.asList(
                3201002, // Crossbow Booster
                3201004  // Soul Arrow
        ));

        jobBuffs.put(Job.SNIPER.getId(), Arrays.asList(
                3211004, // Arrow Eruption
                3211005  // Golden Eagle
        ));

        jobBuffs.put(Job.MARKSMAN.getId(), Arrays.asList(
                3221000, // Maple Warrior
                3221007  // Snipe
        ));

        jobBuffs.put(Job.THIEF.getId(), Arrays.asList(
                4001003, // Dark Sight
                4001334  // Double Stab
        ));

        jobBuffs.put(Job.ASSASSIN.getId(), Arrays.asList(
                4101004, // Haste
                4101005  // Drain
        ));

        jobBuffs.put(Job.HERMIT.getId(), Arrays.asList(
                4111002, // Shadow Partner
                4111006  // Flash Jump
        ));

        jobBuffs.put(Job.NIGHTLORD.getId(), Arrays.asList(
                4121000, // Maple Warrior
                4121008  // Ninja Storm
        ));

        jobBuffs.put(Job.BANDIT.getId(), Arrays.asList(
                4201003, // Haste
                4201005  // Savage Blow
        ));

        jobBuffs.put(Job.CHIEFBANDIT.getId(), Arrays.asList(
                4211004, // Band of Thieves
                4211006  // Meso Explosion
        ));

        jobBuffs.put(Job.SHADOWER.getId(), Arrays.asList(
                4221000, // Maple Warrior
                4221007  // Boomerang Step
        ));

        jobBuffs.put(Job.PIRATE.getId(), Arrays.asList(
                5001001, // Flash Fist
                5001002  // Somersault Kick
        ));

        jobBuffs.put(Job.BRAWLER.getId(), Arrays.asList(
                5101004, // Corkscrew Blow
                5101006  // Knuckler Booster
        ));

        jobBuffs.put(Job.MARAUDER.getId(), Arrays.asList(
                5111002, // Energy Blast
                5111006  // Shockwave
        ));

        jobBuffs.put(Job.BUCCANEER.getId(), Arrays.asList(
                5121000, // Maple Warrior
                5121007  // Barrage
        ));

        jobBuffs.put(Job.GUNSLINGER.getId(), Arrays.asList(
                5201003, // Gun Booster
                5201006  // Recoil Shot
        ));

        jobBuffs.put(Job.OUTLAW.getId(), Arrays.asList(
                5211002, // Gaviota
                5211005  // Ice Splitter
        ));

        jobBuffs.put(Job.CORSAIR.getId(), Arrays.asList(
                5221000, // Maple Warrior
                5221008  // Battleship Torpedo
        ));

        jobBuffs.put(Job.DAWNWARRIOR1.getId(), Arrays.asList(
                11001002, // Power Strike
                11001003  // Slash Blast
        ));

        jobBuffs.put(Job.DAWNWARRIOR2.getId(), Arrays.asList(
                11101002, // Final Attack
                11101003  // Rage
        ));

        jobBuffs.put(Job.DAWNWARRIOR3.getId(), Arrays.asList(
                11111004, // Brandish
                11111006  // Soul Driver
        ));

        jobBuffs.put(Job.DAWNWARRIOR4.getId(), Arrays.asList(
                11121000, // Maple Warrior
                11121003  // Freeze Standing
        ));

        jobBuffs.put(Job.BLAZEWIZARD1.getId(), Arrays.asList(
                12001003, // Magic Claw
                12001004  // Flame
        ));

        jobBuffs.put(Job.BLAZEWIZARD2.getId(), Arrays.asList(
                12101002, // Fire Arrow
                12101006  // Fire Pillar
        ));

        jobBuffs.put(Job.BLAZEWIZARD3.getId(), Arrays.asList(
                12111003, // Meteor Shower
                12111006  // Fire Strike
        ));

        jobBuffs.put(Job.BLAZEWIZARD4.getId(), Arrays.asList(
                12121000, // Maple Warrior
                12121003  // Ifrit
        ));

        jobBuffs.put(Job.WINDARCHER1.getId(), Arrays.asList(
                13001003, // Double Shot
                13001004  // Storm
        ));

        jobBuffs.put(Job.WINDARCHER2.getId(), Arrays.asList(
                13101002, // Final Attack
                13101005  // Storm Break
        ));

        jobBuffs.put(Job.WINDARCHER3.getId(), Arrays.asList(
                13111002, // Hurricane
                13111006  // Wind Piercing
        ));

        jobBuffs.put(Job.WINDARCHER4.getId(), Arrays.asList(
                13121000, // Maple Warrior
                13121005  // Eagle Eye
        ));

        jobBuffs.put(Job.NIGHTWALKER1.getId(), Arrays.asList(
                14001003, // Dark Sight
                14001004  // Lucky Seven
        ));

        jobBuffs.put(Job.NIGHTWALKER2.getId(), Arrays.asList(
                14101002, // Claw Booster
                14101004  // Flash Jump
        ));

        jobBuffs.put(Job.NIGHTWALKER3.getId(), Arrays.asList(
                14111002, // Avenger
                14111006  // Poison Bomb
        ));

        jobBuffs.put(Job.NIGHTWALKER4.getId(), Arrays.asList(
                14121000, // Maple Warrior
                14121003  // Shadow Stars
        ));

        jobBuffs.put(Job.THUNDERBREAKER1.getId(), Arrays.asList(
                15001002, // Somersault Kick
                15001003  // Dash
        ));

        jobBuffs.put(Job.THUNDERBREAKER2.getId(), Arrays.asList(
                15101003, // Corkscrew Blow
                15101006  // Lightning Charge
        ));

        jobBuffs.put(Job.THUNDERBREAKER3.getId(), Arrays.asList(
                15111004, // Barrage
                15111007  // Shark Wave
        ));

        jobBuffs.put(Job.THUNDERBREAKER4.getId(), Arrays.asList(
                15121000, // Maple Warrior
                15121003  // Speed Infusion
        ));

        // Apply buffs for the given job
        if (jobBuffs.containsKey(jobId)) {
            for (int skillId : jobBuffs.get(jobId)) {
                SkillFactory.getSkill(skillId).getEffect(SkillFactory.getSkill(skillId).getMaxLevel()).applyTo(player);
            }
        }
    }
}