package ap4b.troyes_dice.model;

public class TurnAlreadyPlayedException extends RuntimeException {
    public TurnAlreadyPlayedException() {
        super("Already played this turn");
    }
}
