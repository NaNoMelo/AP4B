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
        if ((o == null) || (getClass() != o.getClass())) return false;
        Building building = (Building) o;
        return value == building.value && color == building.color && type == building.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, value, type);
    }

    @Override
    public String toString() {
        return "Building{" + "color=" + color + ", value=" + value + ", type=" + type + '}';
    }

    private void onBuild() {
        switch (type) {
            case PRESTIGE:
                onBuildPrestige();
                break;
            case WORKPLACE:
                sheet.addCitizen(color, 2);
                onBuildWorkplace();
                break;
        }
    }

    private void onBuildPrestige() {
        switch (color) {
            case RED:
                onBuildPrestigeRed();
                break;
            case YELLOW:
                onBuildPrestigeYellow();
                break;
        }
    }

    private void onBuildWorkplace() {
        switch (color) {
            case RED:
                onBuildWorkplaceRed();
                break;
            case YELLOW:
                onBuildWorkplaceYellow();
                break;
            case WHITE:
                onBuildWorkplaceWhite();
                break;
        }
    }

    private void onBuildPrestigeRed() {
        boolean hasMatchingBuilding =
                (value == 1 && sheet.hasBuilding(Color.RED, 2, BuildingType.PRESTIGE)) ||
                        (value == 2 && sheet.hasBuilding(Color.RED, 1, BuildingType.PRESTIGE)) ||
                        (value == 3 && sheet.hasBuilding(Color.RED, 4, BuildingType.PRESTIGE)) ||
                        (value == 4 && sheet.hasBuilding(Color.RED, 3, BuildingType.PRESTIGE)) ||
                        (value == 5 && sheet.hasBuilding(Color.RED, 6, BuildingType.PRESTIGE)) ||
                        (value == 6 && sheet.hasBuilding(Color.RED, 5, BuildingType.PRESTIGE));
        sheet.addCitizen(value <= 2 ? Color.RED : value <= 4 ? Color.YELLOW : Color.WHITE, hasMatchingBuilding ? 2 : 1);
    }

private void onBuildPrestigeYellow() {
    Color color = value <= 2 ? Color.RED : value <= 4 ? Color.YELLOW : Color.WHITE;
    int diceCount = sheet.getGame().getCurrentDay().getDiceNumber(color);
    if (value % 2 == 1) {
        sheet.addResource(color, diceCount * 3);
    } else {
        sheet.addCitizen(color, diceCount * 2);
    }
}

    private void onBuildWorkplaceRed() {
        if ((value == 1 && sheet.hasBuilding(Color.RED, 2, BuildingType.WORKPLACE)) ||
                (value == 2 && sheet.hasBuilding(Color.RED, 1, BuildingType.WORKPLACE))) {
            sheet.addCitizen(Color.RED, 2);
        }
        if ((value == 5 && sheet.hasBuilding(Color.RED, 6, BuildingType.WORKPLACE)) ||
                (value == 6 && sheet.hasBuilding(Color.RED, 5, BuildingType.WORKPLACE))) {
            try {
                sheet.addResource(Color.RED, 3);
            } catch (TooManyResourceException ignored) {
            }
        }
    }

    private void onBuildWorkplaceYellow() {
        if ((value == 3 && sheet.hasBuilding(Color.RED, 4, BuildingType.WORKPLACE)) ||
                (value == 4 && sheet.hasBuilding(Color.RED, 3, BuildingType.WORKPLACE))) {
            sheet.addCitizen(Color.YELLOW, 2);
        }
        if ((value == 1 && sheet.hasBuilding(Color.RED, 2, BuildingType.WORKPLACE)) ||
                (value == 2 && sheet.hasBuilding(Color.RED, 1, BuildingType.WORKPLACE))) {
            try {
                sheet.addResource(Color.YELLOW, 3);
            } catch (TooManyResourceException ignored) {
            }
        }
    }

    private void onBuildWorkplaceWhite() {
        if ((value == 5 && sheet.hasBuilding(Color.RED, 6, BuildingType.WORKPLACE)) ||
                (value == 6 && sheet.hasBuilding(Color.RED, 5, BuildingType.WORKPLACE))) {
            sheet.addCitizen(Color.WHITE, 2);
        }
        if ((value == 3 && sheet.hasBuilding(Color.RED, 4, BuildingType.WORKPLACE)) ||
                (value == 4 && sheet.hasBuilding(Color.RED, 3, BuildingType.WORKPLACE))) {
            try {
                sheet.addResource(Color.WHITE, 3);
            } catch (TooManyResourceException ignored) {
            }
        }
    }
}
