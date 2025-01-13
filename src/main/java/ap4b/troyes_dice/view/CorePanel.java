package ap4b.troyes_dice.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CorePanel extends JPanel {
    private final List<JButton> buttons;
    private final List<JLabel> labels;
    private int selectedDice = 0;

    public CorePanel() {
        buttons = new ArrayList<>();
        labels = new ArrayList<>();
        setBackground(new Color(236, 225, 195));
        setLayout(new GridLayout(8, 1, 10, 10));

        for (int i = 0; i < 4; i++) {
            JButton button = new JButton("Dice " + (i + 1));
            button.setBackground(new Color(234, 31, 50));
            button.setPreferredSize(new Dimension(100, 100));
            int finalI = i;
            button.addActionListener(e -> {
                selectedDice = finalI;
                for (JButton b : buttons) {
                    b.setForeground(new Color(0, 0, 0));
                }
                button.setForeground(new Color(255, 0, 0));
            });
            buttons.add(button);
            add(button);

            int[] prices = {0, 1, 1, 2};
            JLabel label = new JLabel("Price : " + prices[i] + " Deniers", SwingConstants.CENTER);
            labels.add(label);
            add(label);
        }
    }

    public void setButtonTitle(int index, String title) {
        if (index >= 0 && index < buttons.size()) {
            buttons.get(index).setText(title);
        }
    }

    public void setButtonColor(int index, Color color) {
        if (index >= 0 && index < buttons.size()) {
            buttons.get(index).setBackground(color);
        }
    }

    public int getSelectedDice() {
        return selectedDice;
    }

    private ActionListener buildDiceSelection(int id, JButton button) {
        return e -> {
            selectedDice = id;
            for (JButton b : buttons) {
                b.setForeground(new Color(0, 0, 0));
            }
            button.setForeground(new Color(255, 0, 0));
        };
    }

    public void addOnSelectAction(ActionListener action) {
        for (JButton b : buttons) {
            for (ActionListener a : b.getActionListeners()) {
                b.removeActionListener(a);
            }
            b.addActionListener(action);
            b.addActionListener(buildDiceSelection(buttons.indexOf(b), b));

        }
    }
}
