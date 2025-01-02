package util;

import model.paths.Path;
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

    public static int pathObjectEncode(Path path){
//        if (player instanceof PlayerRed)
        return -1;
    }

    @Override
    public String toString() {
        if (this == KNOSSOS_PATH) return "Knossos path";
        else if (this == MALIA_PATH) return "Malia path";
        else if (this == PHAISTOS_PATH) return "Phaistos path";
        else if (this == ZAKROS_PATH) return "Zakros path";
        else throw new IllegalArgumentException("Unknown value: " + value);
    }
}
