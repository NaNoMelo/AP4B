package ap4b.troyes_dice.controller;

import ap4b.troyes_dice.model.Action;
import ap4b.troyes_dice.view.View;
import ap4b.troyes_dice.model.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.*;

public class Main {
    private static final View view = new View();
    private static final Game game = new Game();
    private static int nbPlayers = 0;

    //Create and set up the window.
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Troyes Dice");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view.getMainContainer());
        frame.pack();
        frame.setVisible(true);
    }

    public static void updateDices() {
        for (int i = 0; i < 4; i++) {
            Dice dice = game.getCurrentDay().getDice(i);
            java.awt.Color[] colors = {
                    new java.awt.Color(128, 27, 13),
                    new java.awt.Color(228, 209, 71),
                    new java.awt.Color(205, 204, 199)
            };
            view.setCorePanelButtonColor(i, colors[dice.getColor().ordinal()]);
            view.setCorePanelButtonTitle(i, (dice.isBlack() ? "Black Dice : " : "Dice : ") + dice.getValue());
        }
        Dice dice = game.getCurrentDay().getDice(view.getSelectedDice());
        view.selectDice(dice.getColor().ordinal(), dice.getValue());
        view.revalidateFrame();
    }

    public static void main(String[] args) {
        while (nbPlayers == 0) {
            try {
                nbPlayers = Integer.parseInt(JOptionPane.showInputDialog(null, "how many players?"));
            } catch (NumberFormatException ignored) {

            }
        }
        for (int i = 0; i < nbPlayers; i++) {
            Player player = new Player(JOptionPane.showInputDialog(null, "Player " + (i + 1) + "'s name"));
            game.addPlayer(player);
        }

        view.setActionCallbacks(buildActionListener(Action.GET_RESOURCE), buildActionListener(Action.BUILD_WORKPLACE), buildActionListener(Action.BUILD_PRESTIGE), buildActionListener(Action.SKIP));

        view.addOnDiceSelectAction(e -> {
            Dice dice = game.getCurrentDay().getDice(view.getSelectedDice());
            view.selectDice(dice.getColor().ordinal(), dice.getValue());
        });

        //updatePlayerBoards();
        game.setNewTurnCallback(() -> {
            updateDices();
            updatePlayerBoards();
        });


        Thread gameThread = game.startGame();


        // Schedule a job for the event-dispatching thread: creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> {
            createAndShowGUI();

            view.clearPlayerTabs();

            for (int i = 0; i < game.getNbPlayers(); i++) {
                Player player = game.getPlayers().get(i);
                view.addPlayerTab(i, player.getName());
                view.drawPlayerName(i, player.getName());
            }

            for (int i = 0; i < 4; i++) {
                System.out.println("Dice " + i + ": Color : " + game.getCurrentDay().getDice(i).getColor().name() + " Value : " + game.getCurrentDay().getDice(i).getValue() + " Black : " + game.getCurrentDay().getDice(i).isBlack());
            }
            updateDices();
            updatePlayerBoards();

            view.repaintPlayerBoards();
            view.revalidateFrame();

        });
    }

    private static ActionListener buildActionListener(Action action) {
        return e -> {
            int playerID = view.getSelectedPlayerID();
            int dice = view.getSelectedDice();
            int color = view.getSelectedColor();
            int value = view.getSelectedValue();
            Player player = game.getPlayers().get(playerID);
            try {
                game.getCurrentDay().canPlayOrError(player);
                Turn turn = new Turn(
                        player.getSheet(),
                        new PlayerDice(game.getCurrentDay().getDice(dice), value, Color.values()[color]),
                        action,
                        game.getCurrentDay());
                turn.execute();
                game.getCurrentDay().addTurn(turn);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            updatePlayerBoards();
        };
    }


    private static void updatePlayerBoards() {
        Map<Integer, Integer> scores = new HashMap<>();
        Map<Integer, List<Integer>> resources = new HashMap<>();
        for (int i = 0; i < game.getNbPlayers(); i++) {
            scores.put(i, game.getPlayers().get(i).getSheet().getScore());
            resources.put(i, game.getPlayers().get(i).getSheet().getResources());
        }
        view.setPlayersScores(scores, resources);

        for (int i = 0; i < game.getNbPlayers(); i++) {
            List<Building> buildings = game.getPlayers().get(i).getSheet().getBuildings();
            System.out.println("Drawing buildings for player " + i);
            for (int value = 0; value <= 6; value++) {
                if (buildings.contains(new Building(Color.RED, value, BuildingType.PRESTIGE))) {
                    for (int j = 1; j <= 3; j++) {
                        view.drawCastle(i, value, j);
                    }
                }
                for (Color color : Color.values()) {
                    for (BuildingType type : new BuildingType[]{BuildingType.PRESTIGE, BuildingType.WORKPLACE}) {
                        if (buildings.contains(new Building(color, value, type))) {
                            view.drawBuilding(i, value, getBuildingRow(color, type));
                            System.out.println("Drawing building " + color + " " + value + " " + type);
                        } else if (buildings.contains(new Building(color, value, BuildingType.DESTROYED))) {
                            view.drawCross(i, value, getBuildingRow(color, type));
                        }
                    }
                }
            }
        }
        System.out.println();
    }

    private static int getBuildingRow(Building building) {
        return building.getColor().ordinal() * 2 + 1 + ((building.getType() == BuildingType.WORKPLACE) ? 1 : 0);
    }

    private static int getBuildingRow(Color color, BuildingType type) {
        return color.ordinal() * 2 + 1 + ((type == BuildingType.WORKPLACE) ? 1 : 0);
    }

}
