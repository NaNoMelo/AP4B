package ap4b.troyes_dice.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DiceActionsPanel extends JPanel {
    private final List<JButton> diceValuesButtons;
    private final JButton changeColorButtonRed;
    private final JButton changeColorButtonYellow;
    private final JButton changeColorButtonWhite;

    public DiceActionsPanel() {
        this.diceValuesButtons = new ArrayList<>();
        setBackground(new Color(236, 225, 195));
        setLayout(new GridLayout(9, 1, 10, 10));

        for (int i = 0; i < 6; i++) {
            JButton button = new JButton("Change to " + (i + 1));
            diceValuesButtons.add(button);
            add(button);
        }

        this.changeColorButtonRed = new JButton("Change to Red");
        this.changeColorButtonYellow = new JButton("Change to Yellow");
        this.changeColorButtonWhite = new JButton("Change to Blue");

        add(changeColorButtonRed);
        add(changeColorButtonYellow);
        add(changeColorButtonWhite);
    }


}
