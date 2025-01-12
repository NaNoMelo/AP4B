package ap4b.troyes_dice.model;

public class PlayerDice extends Dice {
    private int newValue;
    private Color newColor;

    public PlayerDice(Dice dice, int value, Color color) throws BadDiceException {
        super(dice.getValue(), dice.getColor(), dice.getCost());
        if (dice.isBlack()){
            throw new BadDiceException();
        }
        this.newValue = value;
        this.newColor = color;
    }

    public int getNewValue() {
        return newValue;
    }

    public Color getNewColor() {
        return newColor;
    }

    public int getRedCost() {
        return Math.abs(super.getValue() - newValue);
    }

    public int getWhiteCost() {
        return (super.getColor() != newColor) ? 2 : 0;
    }
}
