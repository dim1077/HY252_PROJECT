package model.cards;

import model.paths.Path;
import model.pawns.Pawn;
import model.pawns.Theseus;
import model.players.Player;
import model.positions.Position;
import util.CardName;
import util.PawnName;
import util.PlayerName;


/**
 * Represents a Minotaur card in the game.
 * <p>
 * This card is used to move an opponent's pawn backward by 2 positions, revealing its identity if necessary.
 * Special behavior applies when the opponent's pawn is Theseus.
 * </p>
 */
public class MinotaurCard extends Card {
    public MinotaurCard(Path pathName) {
        super(pathName);
    }

    // TODO: needs refactoring

    /**
     * Plays the Minotaur card, applying its effect on the opponent's pawn.
     *
     * If the opponent's pawn is Theseus, he is revealed. Otherwise,
     * the opponent's pawn is moved backward by 2 positions and revealed.
     *
     *
     * @param player The player who is playing the card.
     */
    @Override
    public void play(Player player) {
        PlayerName currentPlayerName = player.getName();
        PlayerName opponentPlayerName = (currentPlayerName == PlayerName.PLAYER_GREEN) ? PlayerName.PLAYER_RED : PlayerName.PLAYER_GREEN;
        Pawn opponentPawn = path.getPlayerPawn(opponentPlayerName);
        if (opponentPawn == null) return; // TODO


        if (opponentPawn.getPawnName() == PawnName.THESEUS){
            opponentPawn.setImmobilized(true);
        }else{
            int opponentPosition = opponentPawn.getPosition().getCellIdx();
            int newOpponentPosition = opponentPosition - 2;
            newOpponentPosition = Math.max(newOpponentPosition, 0);

            Position oldPosition = path.getPosition(opponentPosition);
            Position newPosition = path.getPosition(newOpponentPosition);
            oldPosition.setHasPlayer(opponentPlayerName,false);
            newPosition.setHasPlayer(opponentPlayerName, true);
            opponentPawn.setCurrentPosition(newPosition);
        }
        opponentPawn.setRevealed(true);
    }

    @Override
    void setCardName() {
        cardName = CardName.MINOTAUR_CARD;
    }


}
