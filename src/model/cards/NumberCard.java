package model.cards;

import model.paths.Path;
import model.pawns.Pawn;
import model.players.Player;
import model.positions.Position;
import util.CardName;

/**
 * Represents a numbered card (0–10) that moves a player's pawn
 * to the next cell (1 step) if the number is >= the previously played card's number.
 * <p>
 * The first time a pawn is placed using a NumberCard, it starts at cell 0.
 */
public class NumberCard extends Card {

    private final int number; // Could use a smaller type (byte) if desired

    public NumberCard(Path path, int number) {
        super(path);
        if (number < 0 || number > 10) {
            throw new IllegalArgumentException("NumberCard must be between 0 and 10.");
        }
        this.number = number;
    }

    @Override
    public void play(Player player) {
        Pawn playerPawn = path.getPlayerPawn(player);

        // If the pawn doesn't exist or has already finished, do nothing.
        if (playerPawn == null || playerPawn.hasFinished()) return;


        // If the number is lower than the max card played so far for this path, ignore this play (no movement).
        int maxSoFar = path.getMaxCardPlayed(player);
        if (number < maxSoFar) return;

        Position[] positions = path.getPositions();
        Position currentPosition = playerPawn.getPosition();
        final int lastIndex = positions.length - 1;

        // CASE 1: Pawn has not been placed yet (first move for this path).
        if (currentPosition == null) {
            positions[0].setHasPlayer(player, true);
            playerPawn.setCurrentPosition(positions[0]);
            path.setMaxCardPlayed(number, player);
            return;
        }

        // CASE 2: Pawn is already on the last cell -> remove it and mark as finished.
        int pawnIdx = currentPosition.getCellIdx();
        if (pawnIdx == lastIndex) {
            positions[pawnIdx].setHasPlayer(player, false);
            playerPawn.setHasFinished(true);
            path.setMaxCardPlayed(number, player);
            return;
        }

        // CASE 3: Normal move: move the pawn 1 step forward
        positions[pawnIdx].setHasPlayer(player, false);
        positions[pawnIdx + 1].setHasPlayer(player, true);
        playerPawn.setCurrentPosition(positions[pawnIdx + 1]);

        // Update the max card played with this number
        path.setMaxCardPlayed(number, player);
    }

    @Override
    void setCardName() {
        cardName = CardName.NUMBER_CARD;
    }

    /**
     * @return The numeric value of this card (0–10).
     */
    public int getNumber() {
        return number;
    }
}
