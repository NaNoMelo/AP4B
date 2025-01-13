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

        view.setActionCallbacks(buildActionListener(Action.GET_RESOURCE), buildActionListener(Action.BUILD_WORKPLACE), buildActionListener(Action.BUILD_PRESTIGE));

        view.addOnDiceSelectAction(e -> {
            Dice dice = game.getCurrentDay().getDice(view.getSelectedDice());
            view.selectDice(dice.getColor().ordinal(), dice.getValue());
        });

        //updatePlayerBoards();
        game.setNewTurnCallback(Main::updateDices);


        Thread gameThread = game.startGame();


        // Schedule a job for the event-dispatching thread: creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
            view.setCorePanelButtonColor(0, java.awt.Color.GREEN);
            view.revalidateFrame();

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
            scores.put(i, 0); //game.getPlayers().get(i).getSheet().getScore());
            resources.put(i, game.getPlayers().get(i).getSheet().getResources());
        }
        view.setPlayersScores(scores, resources);

        for (int i = 0; i < game.getNbPlayers(); i++) {
            List<Building> buildings = game.getPlayers().get(i).getSheet().getBuildings();
            for (Building building : buildings) {
                if (building.getType() == BuildingType.DESTROYED) continue;
                view.drawBuilding(i, building.getValue(), building.getColor().ordinal() * 2 + (building.getType().ordinal() - 1));
            }
        }
    }

}
