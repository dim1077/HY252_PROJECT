package model.cards;


import model.paths.PathNames;

/**
 * This class represents the cards (Ariadne, minotaur, or number card) on some path
 * The game consists of 100 cards: 80 number , 12 ariadne  and 8 minotaur cards.
 * Cards are the way a player can move his pawns on the path
 * */
public abstract class Card {
    // TODO: perhaps the path should be an enum?
    private final PathNames path;

    public Card(PathNames path) {
        this.path = path;
    }

    // may or may not be needed
    public abstract void play();

    public PathNames getPath() {
        return path;
    }
}
