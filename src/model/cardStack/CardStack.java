package model.cardStack;

import model.cards.AriadneCard;
import model.cards.Card;
import model.cards.MinotaurCard;
import model.cards.NumberCard;
import model.paths.Path;

import java.util.Collections;
import java.util.Stack;


/**
 * This class represents the draw pile of free cards.
 * Before the initiation of the player's cards, it contains
 * 100 cards: 20 number cards for each ancient city (thus 80), 12 Ariadne cards and 8 minotaur cards.
 * */
public class CardStack {


    // TODO: perhaps you should take those variables in the constructor....
    final static int CARDS = 100;
    final static int PATH_NUMBER_CARDS = 20;
    final static int ARIADNE_CARDS = 12;
    final static int MINOTAUR_CARDS = 8;



    private Stack<Card> cardStack;



    /**
     * This constructor initializes 100 cards:
     * 80 number cards for each ancient city (20 for each path), 12 Ariadne cards (3 for each path) and 8 minotaur cards (2 for each path)
     * */
    public CardStack(Path[] paths) {
        cardStack = new Stack<>();
        initCards(paths);
        shuffleCards();
    }

    private void shuffleCards() {
        Collections.shuffle(cardStack);
    }

    public int getStackSize(){
        return cardStack.size();
    }

    private void initCards(Path[] paths) {
        for (Path pathName : paths) {
            for (int number = 1; number <= PATH_NUMBER_CARDS / 2; number++) {
                cardStack.push(new NumberCard(pathName, number));
                cardStack.push(new NumberCard(pathName, number));
            }

            for (int j = 0; j <= ARIADNE_CARDS ; j++ ) {
                cardStack.push(new AriadneCard(pathName));
            }

            for (int j = 0; j <= MINOTAUR_CARDS; j++ ) {
                cardStack.push(new MinotaurCard(pathName));
            }
        }
    }

    public Card getCard(){
        return cardStack.pop();
    }
}
