package ap4b.troyes_dice.model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Day> days = new ArrayList<>();
    private int ID;
    private int currentDay = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private int nbPlayers = 0;
    boolean host;
    Runnable newTurnCallback;


    public Game() {
    }

    public void addPlayer(Player player) {
        players.add(player);
        nbPlayers++;
    }

    public Thread startGame() {
        for (Player player : players) {
            player.setSheet(new Sheet(player, this));
        }
        days.add(new Day(currentDay));
        Thread gameThread = new Thread(() -> {
            System.out.println("Game started");
            while (currentDay < 16) {
                try {
                    if (days.get(currentDay).playedTurns() >= nbPlayers) {
                        genNextDay();
                        System.out.println("Day " + currentDay);
                        newTurnCallback.run();
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
        return gameThread;
    }

    public void genNextDay() {
        currentDay++;
        days.add(new Day(currentDay));
        if (currentDay > 6) {
            //destroy the building targeted by the black dice on each player's sheet
            for (int i = 0; i < 4; i++) {
                Dice dice = days.get(currentDay).getDice(i);
                if (dice.isBlack()) {
                    for (Player player : players) {
                        player.getSheet().destroyBuilding(dice.getColor(), dice.getValue());
                    }
                    break;
                }
            }
        }
    }

    public Day getCurrentDay() {
        return days.get(currentDay);
    }

    public int getNbPlayers() {
        return nbPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setNewTurnCallback(Runnable newTurnCallback) {
        this.newTurnCallback = newTurnCallback;
    }
}
