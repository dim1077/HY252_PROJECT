package util;

import model.players.Player;
import model.players.PlayerGreen;
import model.players.PlayerRed;

public enum PlayerName {
    PLAYER_RED(0),
    PLAYER_GREEN(1);

    private final int value;

    PlayerName(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PlayerName fromValue(int value) {
        for (PlayerName player : PlayerName.values()) {
            if (player.value == value) {
                return player;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    public static PlayerName playerObjectEncode(Player player){
        if (player instanceof PlayerRed) return PLAYER_RED;
        if (player instanceof PlayerGreen) return PLAYER_GREEN;
        else throw new IllegalArgumentException("Unknown player type " + player);
    }

    @Override
    public String toString() {
        if (this == PLAYER_RED) return "Player red";
        else if (this == PLAYER_GREEN) return "Player green";
        else throw new IllegalArgumentException("Unknown player type " + this);
    }
}
