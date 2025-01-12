package ap4b.troyes_dice.model;

public class NotEnoughResourceException extends RuntimeException {
    Color color;
    public NotEnoughResourceException(Color color) {
        super("Not enough " + color.name());
        this.color = color;
    }

    public NotEnoughResourceException(){
        super("Not enough resources");
    }
}
