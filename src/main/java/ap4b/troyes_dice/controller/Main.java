package ap4b.troyes_dice.controller;

import ap4b.troyes_dice.view.View;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class Main {
    private static final View view = new View();

    //Create and set up the window.
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Troyes Dice");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view.getMainContainer());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread: creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                createAndShowGUI();
                view.setCorePanelButtonColor(0, java.awt.Color.GREEN);
                view.revalidateFrame();

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        view.clearPlayerTabs();
                        view.addPlayerTab(1, "Player 1");
                        view.addPlayerTab(2, "Player 2");
                        view.addPlayerTab(3, "Player 3");

                        view.drawPlayerName(1, "Player 1");
                        view.drawPlayerName(2, "Player 2");
                        view.drawPlayerName(3, "Player 3");

                        view.repaintPlayerBoards();
                        view.revalidateFrame();
                    }
                }, 2*1000);

            }
        });


    }
}
