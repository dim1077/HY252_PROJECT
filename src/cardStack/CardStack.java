package cardStack;

import model.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

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
