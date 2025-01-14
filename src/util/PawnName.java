package util;

/**
 * The enum contains the pawn names, which are global to all the program
 * */
public enum PawnName {
    ARCHEOLOGIST(0),
    THESEUS(1);

    private final int value;

    PawnName(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
