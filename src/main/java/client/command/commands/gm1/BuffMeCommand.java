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

        // Add all other classes here (as shown in the previous code)...
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
            // Load class history from player data (if available)
            List<String> classHistory = loadClassHistoryFromPlayerData(player);
            PLAYER_CLASS_HISTORY.put(playerId, classHistory);
        }
    }

    // Helper method to load class history from player data
    private static List<String> loadClassHistoryFromPlayerData(Character player) {
        // Replace this with actual logic to load class history from player data
        // For now, return a list of classes based on the player's current job
        List<String> classHistory = new ArrayList<>();
        String currentJob = getJobName(player.getJobId());

        // Add all previous jobs based on the player's level and job progression
        if (currentJob.equals("Aran")) {
            classHistory.add("Aran");
        } else if (currentJob.equals("Archer")) {
            classHistory.add("Archer");
        } else if (currentJob.equals("Assassin")) {
            classHistory.add("Assassin");
        }
        // Add more conditions for other classes...

        return classHistory;
    }

    // Helper method to get job name from job ID
    private static String getJobName(int jobId) {
        // Replace this with actual logic to map job IDs to job names
        switch (jobId) {
            case 2000:
                return "Aran";
            case 300:
                return "Archer";
            case 400:
                return "Assassin";
            // Add more cases for other jobs...
            default:
                return "Unknown";
        }
    }

    // Helper method to update class history when the player changes classes
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