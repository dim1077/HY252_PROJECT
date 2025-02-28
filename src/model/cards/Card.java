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

    /**
     * The path associated with this card, determining where it can be used.
     */
    protected final Path path;

    /**
     * The name of the card, specifying its type or identity.
     */
    protected CardName cardName;

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
     * <p>
     * The specific behavior of this method depends on the card type:
     * <ul>
     *   <li>**Ariadne** - May provide a strategic advantage for movement.</li>
     *   <li>**Minotaur** - May block paths or create obstacles.</li>
     *   <li>**Number** - Determines the number of steps a player can move.</li>
     * </ul>
     * </p>
     *
     * @param player The player who is playing the card.
     */
    public abstract void play(Player player);

    /**
     * Retrieves the path associated with this card.
     *
     * @return The {@code Path} linked to this card.
     */
    public Path getPath() {
        return path;
    }

    /**
     * Sets the name of the card.
     * <p>
     * This method is abstract, ensuring that each specific card type defines its own name.
     * </p>
     */
    abstract void setCardName();

    /**
     * Retrieves the name of the card.
     *
     * @return The name of the card as a {@code CardName}.
     */
    public CardName getName() {
        return cardName;
    }
}
