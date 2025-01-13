package ap4b.troyes_dice.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DiceActionsPanel extends JPanel {
    private final JSpinner diceValueSpinner;
    private final JButton changeColorButtonRed;
    private final JButton changeColorButtonYellow;
    private final JButton changeColorButtonWhite;
    private final JButton dice;
    private final JButton getRessourceButton;
    private final JButton buildWorkplaceButton;
    private final JButton buildPrestigeButton;
    private int color = 0;

    private final java.awt.Color[] colors = {
            new java.awt.Color(128, 27, 13),
            new java.awt.Color(228, 209, 71),
            new java.awt.Color(205, 204, 199)
    };

    public DiceActionsPanel() {
        setBackground(new Color(236, 225, 195));
        setLayout(new GridLayout(9, 1, 10, 10));


        diceValueSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 6, 1));
        diceValueSpinner.addChangeListener(e -> updateDice());
        add(diceValueSpinner);

        changeColorButtonRed = new JButton("Change dice to Red");
        changeColorButtonRed.addActionListener(e -> {
            color = 0;
            updateDice();
        });
        changeColorButtonYellow = new JButton("Change dice to Yellow");
        changeColorButtonYellow.addActionListener(e -> {
            color = 1;
            updateDice();
        });
        changeColorButtonWhite = new JButton("Change dice to White");
        changeColorButtonWhite.addActionListener(e -> {
            color = 2;
            updateDice();
        });


        dice = new JButton(diceValueSpinner.getValue().toString());
        dice.setFont(new Font("Arial", Font.PLAIN, 40));

        getRessourceButton = new JButton("Get Ressource");
        buildWorkplaceButton = new JButton("Build Workplace");
        buildPrestigeButton = new JButton("Build Prestige");



        add(changeColorButtonRed);
        add(changeColorButtonYellow);
        add(changeColorButtonWhite);
        add(dice);
        add(getRessourceButton);
        add(buildWorkplaceButton);
        add(buildPrestigeButton);
    }

    private void updateDice() {
        dice.setText(diceValueSpinner.getValue().toString());
        dice.setBackground(colors[color]);
    }

    public int getColor() {
        return color;
    }

    public int getValue() {
        return (int) diceValueSpinner.getValue();
    }

    public void selectDice(int color, int value) {
        this.color = color;
        this.diceValueSpinner.setValue(value);
        updateDice();
    }

    public void setActionCallbacks(ActionListener getRessource, ActionListener buildWorkplace, ActionListener buildPrestige) {
        getRessourceButton.addActionListener(getRessource);
        buildWorkplaceButton.addActionListener(buildPrestige);
        buildPrestigeButton.addActionListener(buildPrestige);
    }

}
