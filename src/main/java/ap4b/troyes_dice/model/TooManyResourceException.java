package ap4b.troyes_dice.model;

public class TooManyResourceException extends RuntimeException {
    Color color;
    public TooManyResourceException(Color color) {
        super("Can't get more " + color.name());
        this.color = color;
    }
}
