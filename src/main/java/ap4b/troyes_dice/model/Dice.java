package ap4b.troyes_dice.model;

import java.util.Random;

public class Dice implements Comparable<Dice> {
    private Color color;
    private int value;
    private boolean black;

    public Dice(){
        this (false);
    }

    public Dice(boolean black) {
        this(black, Color.getRandom());
    }

    public Dice(boolean black, Color color) {
        value = new Random().nextInt(1, 7);
        this.black = black;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public boolean isBlack() {
        return black;
    }

    public Color getColor() {
        return color;
    }

    public void save() {

    }

    @Override
    public int compareTo(Dice o) {
        // if the values are different, just return the comparison beetween them.
        if (this.value != o.value) {
            return Integer.compare(this.value, o.value);
        }

        // if the values are the same, make the black dice have a lower value
        if (this.black && !o.black) {
            return -1;
        } else if (!this.black && o.black) {
            return 1;
        }

        // if both dices are same value and both are either black or transparent, rank them equal.
        return 0;
    }
}
