package ap4b.troyes_dice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sheet {
    private int ID;
    private Player player;
    private Game game;
    private List<Building> buildings;
    private HashMap<Color, Integer> resources;
    private HashMap<Color, Integer> usedResources;
    private HashMap<Color, Integer> citizens;

    public Sheet(Player player, Game game) {
        this.player = player;
        this.game = game;
        this.buildings = new ArrayList<>();
        this.resources = new HashMap<>();
        for (Color color : Color.values()) {
            resources.put(color, 3);
        }
        this.usedResources = new HashMap<>();
        for (Color color : Color.values()) {
            usedResources.put(color, 0);
        }
        this.citizens = new HashMap<>();
        for (Color color : Color.values()) {
            citizens.put(color, 0);
        }
    }

    public boolean hasResource(Color color, int value) {
        return resources.get(color) - usedResources.get(color) >= value;
    }

    public void addResource(Color color, int value) throws TooManyResourceException {
        if (resources.get(color) >= 18) {
            throw new TooManyResourceException(color);
        }
        int resourceCitizen = resources.get(color) / 6;
        resources.put(color, Math.min(resources.get(color) + value, 18));
        if ((int) Math.floor((double) resources.get(color) / 6) > resourceCitizen) {
            addCitizen(color, 1);
        }
    }

    public void useResource(Color color, int value) throws NotEnoughResourceException {
        if (usedResources.get(color) + value > resources.get(color)) {
            throw new NotEnoughResourceException(color);
        }
        usedResources.put(color, usedResources.get(color) + value);
    }

    public void addCitizen(Color color, int value) {
        if (citizens.get(color) >= 20) {
            return;
        }
        citizens.put(color, Math.min(citizens.get(color) + value, 20));

        if (citizens.get(color) - value < 3 && citizens.get(color) >= 3) {
            try {
                addResource(color, 1);
            } catch (TooManyResourceException ignored) {
            }
        }
        // TODO : add free chosen building when 6 or 11 citizens
        // TODO : add free chosen other color building when 15 citizens
        if (citizens.get(color) - value < 20 && citizens.get(color) >= 20) {
            for (Color otherColor : Color.values()) {
                if (otherColor != color) {
                    addCitizen(otherColor, 1);
                }
            }
        }
    }

    public boolean hasBuilding(Color color, int value, BuildingType type) {
        return buildings.contains(new Building(color, value, type));
    }

    public void buildBuilding(Color color, int value, BuildingType type) throws BuildingDestroyedException, AlreadyBuiltException {
        if (hasBuilding(color, value, BuildingType.DESTROYED)) {
            throw new BuildingDestroyedException();
        }
        if (hasBuilding(color, value, type)) {
            throw new AlreadyBuiltException();
        }
        buildings.add(new Building(color, value, type));
    }

    public void destroyBuilding(Color color, int value) {
        if (!hasBuilding(Color.RED, value, BuildingType.PRESTIGE)) {
            if (!hasBuilding(color, value, BuildingType.DESTROYED)) {
                buildings.add(new Building(color, value, BuildingType.DESTROYED));
            }
        }
    }

    public Player getPlayer() {
        return player;
    }
}

