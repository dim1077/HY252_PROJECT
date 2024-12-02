package model.cardStack;

import model.cards.Card;

import java.util.Collections;
import java.util.Stack;


/**
 * This class represents the draw pile of free cards.
 * Before the initiation of the player's cards, it contains
 * 100 cards: 20 number cards for each ancient city (thus 80), 12 Ariadne cards and 8 minotaur cards.
 * */
public class CardStack {

    private Stack<Card> cardStack;

    public CardStack() {
        cardStack = new Stack<>();
        initCards();
        shuffleCards();
    }

    private void shuffleCards() {
        Collections.shuffle(cardStack);
    }

    public void getCardSize(){
        ;
    }

    private void initCards(){
    }

    public Card CardStackPop(){
        return null;
    }
}
