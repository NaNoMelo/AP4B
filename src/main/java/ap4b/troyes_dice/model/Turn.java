package ap4b.troyes_dice.model;

public class Turn {
    Sheet sheet;
    PlayerDice playerDice;
    Action action;
    Day day;

    public Turn(Sheet sheet, PlayerDice playerDice, Action action, Day day) {
        this.sheet = sheet;
        this.playerDice = playerDice;
        this.action = action;
        this.day = day;
    }

    public void execute() throws NotEnoughResourceException, TooManyResourceException, AlreadyBuiltException, BuildingDestroyedException, TurnAlreadyPlayedException {
        // verify for having all needed ressources to execute the turn
        if(action == Action.SKIP){
            try {
                for (Color color : Color.values()) {
                    sheet.addResource(color, 1);
                }
            } catch (TooManyResourceException ignored) {
            }
            return;
        }
        if (!(sheet.hasResource(Color.RED, playerDice.getRedCost()) && // dice cost for changing value
                sheet.hasResource(Color.WHITE, playerDice.getWhiteCost()) && // dice cost for changing color
                sheet.hasResource(Color.YELLOW, playerDice.getCost()) // cost to buy the dice
        )) {
            throw new NotEnoughResourceException();
        }

        switch (action) {
            case GET_RESOURCE:
                sheet.addResource(playerDice.getNewColor(), playerDice.getNewValue());
                break;
            case BUILD_WORKPLACE:
                sheet.buildBuilding(playerDice.getNewColor(), playerDice.getNewValue(), BuildingType.WORKPLACE);
                break;
            case BUILD_PRESTIGE:
                sheet.buildBuilding(playerDice.getNewColor(), playerDice.getNewValue(), BuildingType.PRESTIGE);
                break;

            case SKIP:

        }

        // once the action is validated and executed, we consume the resources
        sheet.useResource(Color.RED, playerDice.getRedCost());
        sheet.useResource(Color.WHITE, playerDice.getWhiteCost());
        sheet.useResource(Color.YELLOW, playerDice.getCost());
    }

    public Player getPlayer() {
        return sheet.getPlayer();
    }
}
