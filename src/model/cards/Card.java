package model.cards;


import model.paths.Path;
import model.players.Player;
import util.CardName;

/**
 * This class represents the cards (Ariadne, minotaur, or number card) on some path
 * The game consists of 100 cards: 80 number, 12 ariadne and 8 minotaur cards.
 * Cards are the way a player can move his pawns on the path
 * */
public abstract class Card {

    protected final Path path;
    CardName cardName;

    /**
     * Constructs a card associated with a specific path.
     *
     * @param path The path on which this card can be used.
     */
    public Card(Path path) {
        this.path = path;
        setCardName();
    }


    /**
     * Plays the card, triggering its effect for the specified player.
     * The exact behavior of this method is determined by the specific card type
     * (Ariadne, Minotaur, or Number).
     *
     * @param player The player who is playing the card.
     */
    public abstract void play(Player player);

    /**
     * @return returns the path associated with this card.
     */
    public Path getPath() {
        return path;
    }

    abstract void setCardName();

    public CardName getName(){
        return cardName;
    }
}
