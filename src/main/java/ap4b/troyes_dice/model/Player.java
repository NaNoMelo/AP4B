package ap4b.troyes_dice.model;

public class Player {
    private int ID;
    private String name;
    private Game currentGame;
    private Sheet sheet;
    private int highScore;

    public Player(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public int getID() {
        return ID;
    }
}
