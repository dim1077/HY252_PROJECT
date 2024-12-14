package model.cards;

import javafx.geometry.Pos;
import model.paths.Path;
import model.pawns.Pawn;
import model.players.Player;
import model.positions.Position;
import util.CardName;

public class NumberCard extends Card {
    private final int number; // TODO: maybe byte

    public NumberCard(Path path, int number) {
        super(path);
        if (number < 0 || number > 10) throw new IllegalArgumentException();
        this.number = number;
    }

    @Override
    public void play(Player player) {
        Position[] position = path.getPositions();
        Pawn playerPawn = path.getPlayerPawn(player);
        Position playerPos = playerPawn.getPosition();

        // In case the pawn has finished, the player can play the card, but it won't do anything
        if (playerPawn.getHasFinished()) return;


        // Player plays for the first time: every number card is valid
        if (playerPos == null){
            position[0].setHasPlayer(player, true);
            playerPawn.setCurrentPosition(position[0]);
            path.setMaxCardPlayed(number, player);
            return;
        }

        int pawnPosition = playerPawn.getPosition().getCellIdx();

//        if () throw new IllegalArgumentException();
        if (number < path.getMaxCardPlayed(player)) return;
        System.out.println(path.getMaxCardPlayed(player));


        // The Player is in the last position: he is now removed from the map, and the pawn doesn't exist anymore
         if (pawnPosition == position.length - 1){
            position[pawnPosition].setHasPlayer(player,false);
             playerPawn.setHasFinished(true);
         }

        // move the pawn into the next position
        else{
            position[pawnPosition].setHasPlayer(player,false);
            position[pawnPosition + 1].setHasPlayer(player,true);
             playerPawn.setCurrentPosition(position[pawnPosition + 1]);
        }
        path.setMaxCardPlayed(number, player);
    }

    @Override
    void setCardName() {
        cardName = CardName.NUMBER_CARD;
    }

    /**
     * @return returns the number associated with this card. This number
     * has to be higher than the previously drawn number from that path
     * */
    public int getNumber(){
        return number;
    }
}
