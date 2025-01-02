package model.cardStack;

import model.cards.AriadneCard;
import model.cards.Card;
import model.cards.MinotaurCard;
import model.cards.NumberCard;
import model.paths.Path;
import util.GameConstants;

import java.util.Collections;
import java.util.Stack;


/**
 * This class represents the draw pile of free cards.
 * Before the initiation of the player's cards, it contains
 * 100 cards: 20 number cards for each ancient city (thus 80), 12 Ariadne cards and 8 minotaur cards.
 * */
public class CardStack {

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
        Collections.shuffle(cardStack, new java.util.Random(42));
    }

    public int getStackSize(){
        return cardStack.size();
    }

    private void initCards(Path[] paths) {
        for (Path pathName : paths) {
            for (int number = 1; number <= GameConstants.NUMBER_OF_PATH_NUMBER_CARDS / 2; number++) {
                cardStack.push(new NumberCard(pathName, number));
                cardStack.push(new NumberCard(pathName, number));
            }

            for (int j = 0; j < GameConstants.NUMBER_OF_ARIADNE_CARDS / GameConstants.NUMBER_OF_PATHS; j++ ) {
                cardStack.push(new AriadneCard(pathName));
            }

            for (int j = 0; j < GameConstants.NUMBER_OF_MINOTAUR_CARDS / GameConstants.NUMBER_OF_PATHS; j++ ) {
                cardStack.push(new MinotaurCard(pathName));
            }
        }
    }

    /***
     * @precondition stack is not empty
     * @return draw a new card from the top of the stack
     */
    public Card getCard(){
        if (cardStack.isEmpty()) throw new IllegalArgumentException();
        return cardStack.pop();
    }


    /***
     * @precondition numOfCards is a valid positive integer that is not more than the stack size
     * @return draw n new cards from the top of the stack
     */
    public Card[] getNCards(int numOfCards){
        Card[] cards = new Card[numOfCards];
        if (numOfCards <= 0 || numOfCards > cardStack.size()) throw new IllegalArgumentException();
        for (int i = 0; i < numOfCards; i++) {
            cards[i] = cardStack.pop();
        }
        return cards;
    }
}
