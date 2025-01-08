package ap4b.troyes_dice.model;

import java.util.Random;

public enum Color {
    Red,
    Yellow,
    White;

    public static Color getRandom() {
        Random random = new Random();
        Color[] enumValues = Color.values();
        return enumValues[random.nextInt(enumValues.length)];
    }
}
