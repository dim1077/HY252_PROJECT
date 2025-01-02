package util;

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
