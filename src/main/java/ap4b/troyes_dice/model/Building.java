package ap4b.troyes_dice.model;

import java.util.Objects;

public class Building {
    private Sheet sheet;
    private Color color;
    private int value;
    private BuildingType type;

    public Building(Color color, int value, BuildingType type) {
        this.color = color;
        this.value = value;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public BuildingType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return value == building.value && color == building.color && type == building.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, value, type);
    }
}
