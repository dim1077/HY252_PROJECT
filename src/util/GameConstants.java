package util;

import java.util.*;
import java.awt.Color;



// TODO: there are too many constants, break the class into smaller parts
/**
 * This class defines various constants used throughout the game.
 * These constants represent fixed values such as dimensions, numbers of game elements,
 * and quantities of different cards and findings.
 */
public class GameConstants {

    /**
     * Screen-related constants such as width and height.
     */
    public static class Screen {
        /** The width of the game screen in pixels. */
        public static final int WIDTH = 1920;
        /** The height of the game screen in pixels. */
        public static final int HEIGHT = 1080;
    }

    /**
     * Player-related constants including number of players and pawns.
     */
    public static class Players {
        /** The total number of players in the game. */
        public static final int NUMBER_OF_PLAYERS = 2;
        /** The total number of pawns per player. */
        public static final int NUMBER_OF_PAWNS = 4;
    }

    /**
     * Path-related constants such as number of paths, cells, and their configurations.
     */
    public static class Paths {
        /** The total number of paths in the game. */
        public static final int NUMBER_OF_PATHS = 4;
        /** The total number of cells in each path. */
        public static final int NUMBER_OF_PATH_CELLS = 9;
        /** The number of finding positions on each path. */
        public static final int NUMBER_OF_FINDING_POSITIONS_IN_PATH = 6;
        /** Represents the positions in the path that have findings. */
        public static final Set<Integer> POSITIONS_WITH_FINDINGS =
                new HashSet<>(Arrays.asList(2, 4, 6, 8, 9));
        /** The index of the checkpoint in the path (0-indexed). */
        public static final int CHECKPOINT_INDEX = 6;
        /** A mapping of colors associated with each path for visual distinction. */
        public static final Map<PathName, Color> COLOR_FOR_EACH_PATH = createPathColors();

        private static Map<PathName, Color> createPathColors() {
            Map<PathName, Color> map = new HashMap<>();
            map.put(PathName.KNOSSOS_PATH, Color.RED);
            map.put(PathName.MALIA_PATH, Color.YELLOW);
            map.put(PathName.PHAISTOS_PATH, Color.WHITE);
            map.put(PathName.ZAKROS_PATH, Color.BLUE);
            return map;
        }
    }

    /**
     * Card-related constants such as the number of cards and their configurations.
     */
    public static class Cards {
        /** The total number of number cards per path. */
        public static final int NUMBER_OF_PATH_NUMBER_CARDS = 20;
        /** The total number of cards in the game. */
        public static final int NUMBER_OF_CARDS = 100;
        /** The total number of number cards in the game. */
        public static final int NUMBER_OF_NUMBER_CARDS = 80;
        /** The total number of Minotaur cards in the game. */
        public static final int NUMBER_OF_MINOTAUR_CARDS = 8;
        /** The total number of Ariadne cards in the game. */
        public static final int NUMBER_OF_ARIADNE_CARDS = 12;
        /** The number of cards in a player's deck. */
        public static final int NUMBER_OF_DECK_CARDS = 8;
        /** The number of finding positions on cards. */
        public static final int NUMBER_OF_FINDING_POSITIONS_IN_CARD = 4;
        /** The number of last cards played section displayed on the player's menu. */
        public static final int NUMBER_OF_LAST_CARD_PLAYED_DECK = 4;
    }

    /**
     * Finding-related constants such as rare findings and their configurations.
     */
    public static class Findings {
        /** The total number of rare findings in the game. */
        public static final int NUMBER_OF_RARE_FINDINGS = 4;
        /** The total number of Snake Goddess statues in the game. */
        public static final int NUMBER_OF_SNAKE_GODDESS_STATUES = 10;
        /** The total number of Fresco findings in the game. */
        public static final int NUMBER_OF_FRESCO = 6;
        /** The total number of relics available in the game. */
        public static final int NUMBER_OF_RELICS = 20;
        /** The maximum number of findings Theseus can destroy. */
        public static final int MAX_DESTRUCTION_COUNT = 3;
        /** A set of rare findings in the game, identified by their names. */
        public static final Set<FindingName> RARE_FINDING_NAMES = createRareFindingNames();
        /** A set of fresco findings in the game, identified by their names. */
        public static final Set<FindingName> FRESCOES_NAMES = createFrescoesNames();

        private static Set<FindingName> createRareFindingNames() {
            return new HashSet<>(Arrays.asList(
                    FindingName.MALIA_JEWELRY,
                    FindingName.MINOS_RING,
                    FindingName.RHYTHON_OF_ZAKROS,
                    FindingName.PHAISTOS_DISC
            ));
        }

        private static Set<FindingName> createFrescoesNames() {
            return new HashSet<>(Arrays.asList(
                    FindingName.FRESCO_1,
                    FindingName.FRESCO_2,
                    FindingName.FRESCO_3,
                    FindingName.FRESCO_4,
                    FindingName.FRESCO_5,
                    FindingName.FRESCO_6
            ));
        }
    }

    /**
     * Reward-related constants for various game achievements.
     */
    public static class Rewards {
        /** Rewards assigned for reaching specific cells in a path. */
        public static final int[] REWARD_PATH_FOR_ITH_CELL = {-20, -15, -10, 5, 10, 15, 30, 35, 50};
        /** Rewards assigned based on the number of Snake Goddess statues collected. */
        public static final int[] REWARD_PATH_FOR_NUM_OF_STATUES = {0, -20, -15, 10, 15, 30, 50};
    }

    /**
     * Token-related constants such as the number of Theseus tokens.
     */
    public static class Tokens {
        /** The number of Theseus tokens available in the game. */
        public static final int NUMBER_OF_THESEUS = 1;
        /** The total number of archaeologist tokens in the game. */
        public static final int NUMBER_OF_ARCHEOLOGIST = 3;
    }

    /**
     * Turn timing constants, such as maximum allowed time per turn.
     */
    public static class Turn {
        /** The maximum amount of time the user has to play a card. */
        public static final int MAX_TIME_FOR_TURN = 30;
    }

    /**
     * Visual constants related to players.
     */
    public static class PlayerVisuals {
        /** A mapping of colors associated with each player for visual distinction. */
        public static final Map<PlayerName, Color> COLOR_FOR_EACH_PLAYER = createPlayerColors();

        private static Map<PlayerName, Color> createPlayerColors() {
            Map<PlayerName, Color> map = new HashMap<>();
            map.put(PlayerName.PLAYER_GREEN, Color.GREEN);
            map.put(PlayerName.PLAYER_RED, Color.RED);
            return map;
        }
    }
}
