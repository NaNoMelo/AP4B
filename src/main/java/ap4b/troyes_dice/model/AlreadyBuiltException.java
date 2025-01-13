package ap4b.troyes_dice.model;

public class AlreadyBuiltException extends RuntimeException {
    public AlreadyBuiltException() {
        super("Building already built");
    }
}
