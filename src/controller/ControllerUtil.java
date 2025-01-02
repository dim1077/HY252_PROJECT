package controller;

import model.cards.AriadneCard;
import model.cards.Card;
import model.cards.MinotaurCard;
import model.cards.NumberCard;
import model.paths.Path;
import util.CardName;
import util.FindingName;
import util.GameConstants;
import view.components.menus.CardView;

import java.util.ArrayList;
import java.util.List;

public class ControllerUtil {
    public static CardView[] convertCardsToViewCards(Card[] cards) {
        CardView[] cardViews = new CardView[cards.length];
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) cardViews[i] = null;
            else if (cards[i].getName() != CardName.NUMBER_CARD) cardViews[i] = new CardView(cards[i].getName(), cards[i].getPath().getPathName());
            else cardViews[i] = new CardView(cards[i].getName(), cards[i].getPath().getPathName(), String.valueOf(((NumberCard)cards[i]).getNumber()));
        }
        return cardViews;
    }

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

    public static int countSnakeGoddess(List<FindingName> findingNames) {
        int statuesNumber = 0;
        if (findingNames.isEmpty()) return 0;
        for (FindingName name : findingNames) {
            if (name.equals(FindingName.SNAKE_GODDESS)) statuesNumber++;
        }
        return statuesNumber;
    }

    public static List<FindingName> playerFrescoes(List<FindingName> findingNames) {
        List<FindingName> frescoes = new ArrayList<>();
        if (findingNames.isEmpty()) return frescoes;
        for (FindingName name : findingNames) {
            if (GameConstants.FRESCOES_NAMES.contains(name)) frescoes.add(name);
        }
        return frescoes;
    }
}
