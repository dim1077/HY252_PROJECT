package model.cards;

import model.paths.PathNames;

public class NumberCard extends Card {
    private final int number;

    public NumberCard(PathNames path, int number) {
        super(path);
        if (number < 0 || number > 10) throw new IllegalArgumentException();
        this.number = number;
    }

    @Override
    public void play() {

    }

    public int getNumber(){
        return number;
    }
}
