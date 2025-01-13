package ap4b.troyes_dice.model;

public class BuildingDestroyedException extends RuntimeException {
    public BuildingDestroyedException() {
        super("Building is destroyed and can't be built");
    }
}
