package ap4b.troyes_dice.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CorePanel extends JPanel {
    private final List<JButton> buttons;
    private final List<JLabel> labels;

    public CorePanel() {
        buttons = new ArrayList<>();
        labels = new ArrayList<>();
        setBackground(new Color(236, 225, 195));
        setLayout(new GridLayout(8, 1, 10, 10));

        for (int i = 0; i < 4; i++) {
            JButton button = new JButton("Dice " + (i + 1));
            button.setBackground(new Color(234, 31, 50));
            button.setPreferredSize(new Dimension(100, 100));
            buttons.add(button);
            add(button);

            JLabel label = new JLabel("Price " + (i + 1), SwingConstants.CENTER);
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
}
