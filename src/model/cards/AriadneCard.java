package model.cards;

import model.paths.Path;
import model.pawns.Pawn;
import model.players.Player;
import model.positions.Position;
import util.CardName;



/**
 * Represents the Ariadne card, which moves a player's pawn 2 cells forward,
 * potentially skipping over a FindingPosition. If the pawn is near the end,
 * it may finish the path.
 */
public class AriadneCard extends Card {
    public AriadneCard(Path name) {
        super(name);
    }


    // TODO: this code needs refactoring
    @Override
    public void play(Player player) {
        Pawn playerPawn = path.getPlayerPawn(player);
        Position[] position = path.getPositions();

        // In case the pawn has finished, the player can play the card, but it won't do anything
        if (playerPawn.hasFinished()) return;

        // Invalid, can't use Ariadne card in the first round.
        if (playerPawn.getPosition() == null){
            throw new IllegalArgumentException("Cannot play ariadne card in the first round"); // this shouldn't happen, as we are not going to let the program reach card.play() in that case
        }
        int pawnPosition = playerPawn.getPosition().getCellIdx();

        position[pawnPosition].setHasPlayer(player,false);
        if (pawnPosition == position.length - 1){
            position[pawnPosition].setHasPlayer(player,false);
            playerPawn.setHasFinished(true);
        }
        else if (pawnPosition == position.length - 2){
            position[pawnPosition + 1].setHasPlayer(player,false);
            playerPawn.setHasFinished(true);
        }else{
            int newPawnPosition = pawnPosition + 2;
            if (position[newPawnPosition - 1].getFinding() != null){

            }
            position[newPawnPosition].setHasPlayer(player,true);
            playerPawn.setCurrentPosition(position[newPawnPosition]);
        }
    }

    @Override
    void setCardName() {
        cardName = CardName.ARIADNE_CARD;
    }
}
