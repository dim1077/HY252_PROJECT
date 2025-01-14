package controller;

import model.cards.AriadneCard;
import model.cards.Card;
import model.cards.MinotaurCard;
import model.cards.NumberCard;
import model.paths.Path;
import util.CardName;
import util.FindingName;
import util.GameConstants;
import view.components.playerMenu.CardView;

import java.util.ArrayList;
import java.util.List;



/**
 * Utility class for converting between cards, card views, and handling game-related utilities.
 */
public class ControllerUtil {

    /**
     * Converts an array of Card objects to an array of CardView objects.
     * Each CardView is constructed based on the Card's name, path, and number
     * in the case of NumberCard. Null cards are converted to null CardView objects.
     *
     * @param cards the array of Card objects to be converted
     * @return an array of CardView objects representing the given cards
     */
    public static CardView[] convertCardsToViewCards(Card[] cards) {
        CardView[] cardViews = new CardView[cards.length];
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) cardViews[i] = null;
            else if (cards[i].getName() != CardName.NUMBER_CARD) cardViews[i] = new CardView(cards[i].getName(), cards[i].getPath().getPathName());
            else cardViews[i] = new CardView(cards[i].getName(), cards[i].getPath().getPathName(), String.valueOf(((NumberCard)cards[i]).getNumber()));
        }
        return cardViews;
    }

    /**
     * Converts an array of CardView objects to an array of Card objects.
     * Each Card is constructed based on the CardView's name, path, and number
     * in the case of NumberCard. This method assumes that the Path array provides
     * valid paths for conversion.
     *
     * This method is deprecated as it may introduce errors due to unchecked
     * conversions and is not recommended for use.
     *
     * @param cardViews the array of CardView objects to be converted
     * @param paths the array of Path objects used for mapping paths in the conversion
     * @return an array of Card objects representing the given card views
     * @throws IllegalArgumentException if an invalid card type is encountered
     * @deprecated This method may cause issues with unchecked conversions and is not recommended for use
     */
    @Deprecated
    public static Card[] convertCardViewsToCards(CardView[] cardViews, Path[] paths) {
        Card[] cards = new Card[cardViews.length];
        for (int i = 0; i < cardViews.length; i++) {
            int pathIdx = cardViews[i].getPathName().getValue();
            Path currentPath = paths[pathIdx];
            if (cards[i].getName() == CardName.MINOTAUR_CARD) new MinotaurCard(currentPath);
            else if (cards[i].getName() == CardName.ARIADNE_CARD) new AriadneCard(currentPath);
            else if (cards[i].getName() == CardName.NUMBER_CARD) new NumberCard(currentPath, Integer.parseInt(cardViews[i].getNumber()));
            else throw new IllegalArgumentException();
        }
        return cards;
    }

    /**
     * Counts the number of Snake Goddess statues in a list of finding names.
     *
     * @param findingNames the list of finding names to analyze
     * @return the number of Snake Goddess statues found in the list
     */
    public static int countSnakeGoddess(List<FindingName> findingNames) {
        int statuesNumber = 0;
        if (findingNames.isEmpty()) return 0;
        for (FindingName name : findingNames) {
            if (name.equals(FindingName.SNAKE_GODDESS)) statuesNumber++;
        }
        return statuesNumber;
    }

    /**
     * Extracts a list of fresco names from a list of finding names.
     * Fresco names are determined based on the predefined set in GameConstants.
     *
     * @param findingNames the list of finding names to analyze
     * @return a list of finding names corresponding to frescoes
     */
    public static List<FindingName> playerFrescoes(List<FindingName> findingNames) {
        List<FindingName> frescoes = new ArrayList<>();
        if (findingNames.isEmpty()) return frescoes;
        for (FindingName name : findingNames) {
            if (GameConstants.FRESCOES_NAMES.contains(name)) frescoes.add(name);
        }
        return frescoes;
    }
}
