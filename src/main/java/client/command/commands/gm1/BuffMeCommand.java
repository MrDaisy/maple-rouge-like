package client.command.commands.gm1;

import client.Character;
import client.Client;
import client.SkillFactory;
import client.command.Command;
import java.util.HashSet;
import java.util.Set;

public class BuffMeCommand extends Command {
    {
        setDescription("Activate GM buffs on self.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        Set<Integer> buffSkills = new HashSet<>();

        // Collect all buff skills from attained classes
        for (PlayerClass playerClass : player.getAttainedClasses())
            for (int skillId : playerClass.getSkills()) {
                buffSkills.add(skillId);
            }

        // Apply all unique buff skills to the player
        for (int skillId : buffSkills) {
            SkillFactory.getSkill(skillId).getEffect(SkillFactory.getSkill(skillId).getMaxLevel()).applyTo(player);
        }
    }
}
