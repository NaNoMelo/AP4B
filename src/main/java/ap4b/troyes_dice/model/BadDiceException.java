package ap4b.troyes_dice.model;

public class BadDiceException extends RuntimeException {
    public BadDiceException() {
        super("Illegal dice selected");
    }
}
