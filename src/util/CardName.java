package util;



// The reason for this is to have some way for the view and the controller to be able to identify the card type.
// Made more sense to me to have it separated with an enum rather than to put the constant names in GameConstants
/**
 * The enum contains the card names, which are global to all the program
 * */
public enum CardName {
    ARIADNE_CARD,
    MINOTAUR_CARD,
    NUMBER_CARD
}
