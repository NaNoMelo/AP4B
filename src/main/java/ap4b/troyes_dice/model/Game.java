package ap4b.troyes_dice.model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Day> days = new ArrayList<>();
    private int ID;
    private int currentDay = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private int nbPlayers = 0;

    public Game() {
    }

    public void addPlayer(Player player) {
        players.add(player);
        nbPlayers++;
    }

    public void startGame() {
        for (Player player : players) {
            player.setSheet(new Sheet(player, this));
        }
        days.add(new Day(currentDay));
    }

    public void genNextDay() {
        currentDay++;
        days.add(new Day(currentDay));
    }

    public Day getCurrentDay() {
        return days.get(currentDay);
    }

    public int getNbPlayers() {
        return nbPlayers;
    }
}
