package model.cards;


import model.paths.Path;
import model.paths.PathNames;
import model.pawns.Pawn;
import model.players.Player;
import model.positions.Position;

/**
 * This class represents the cards (Ariadne, minotaur, or number card) on some path
 * The game consists of 100 cards: 80 number, 12 ariadne and 8 minotaur cards.
 * Cards are the way a player can move his pawns on the path
 * */
public abstract class Card {

    protected final Path path;

    public Card(Path path) {
        this.path = path;
    }

    public abstract void play(Player player);


    public Path getPath() {
        return path;
    }
}
