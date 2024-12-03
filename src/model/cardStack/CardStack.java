package model.cardStack;

import model.cards.AriadneCard;
import model.cards.Card;
import model.cards.MinotaurCard;
import model.cards.NumberCard;
import model.paths.PathNames;

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
    public CardStack() {
        cardStack = new Stack<>();
        initCards();
        shuffleCards();
    }

    private void shuffleCards() {
        Collections.shuffle(cardStack);
    }

    public void getCardSize(){

    }

    private void initCards(){
        for (PathNames pathName : PathNames.values()) {
            for (int j = 1; j <= PATH_NUMBER_CARDS / 2; j++) {
                cardStack.push(new NumberCard(pathName, j));
                cardStack.push(new NumberCard(pathName, j));
            }

            for (int j = 0; j <= ARIADNE_CARDS ; j++ ) {
                cardStack.push(new AriadneCard(pathName));
            }

            for (int j = 0; j <= MINOTAUR_CARDS; j++ ) {
                cardStack.push(new MinotaurCard(pathName));
            }
        }
    }

    public Card CardStackPop(){
        return null;
    }
}
