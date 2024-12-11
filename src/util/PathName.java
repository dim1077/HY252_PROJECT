package util;

import model.players.Player;

// numbered enums in java: https://stackoverflow.com/questions/479565/how-do-you-define-a-class-of-constants-in-java
public enum PathName {
    KNOSSOS_PATH(0),
    MALIA_PATH(1),
    PHAISTOS_PATH(2),
    ZAKROS_PATH(3);

    private final int value;

    PathName(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PathName fromValue(int value) {
        for (PathName path : PathName.values()) {
            if (path.value == value) {
                return path;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    public static int playerObjectEncode(Player player){
//        if (player instanceof PlayerRed)
        return -1;
    }
}
