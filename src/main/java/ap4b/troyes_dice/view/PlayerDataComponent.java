package ap4b.troyes_dice.view;

import javax.swing.*;
import java.awt.*;

public class PlayerDataComponent extends JPanel {
    private final JLabel playerNameLabel;
    private final JLabel playerScoreLabel;

    public PlayerDataComponent(String playerName) {
        setMinimumSize(new Dimension(800, 200));
        playerNameLabel = new JLabel(playerName);
        playerScoreLabel = new JLabel("Score: 0");

        add(playerNameLabel);
        add(playerScoreLabel);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(236, 225, 195));
    }

    public void setPlayerScore(int score, int influence, int coins, int connaissance) {
        playerScoreLabel.setText("Score: " + score + " " + "Influence " + influence + " " + "Coins " + coins + " " + "Connaissance " + connaissance);
    }

    public void setPlayerName(String playerName) {
        playerNameLabel.setText(playerName);
    }

    public String getPlayerName() {
        return playerNameLabel.getText();
    }
}
