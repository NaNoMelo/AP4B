package ap4b.troyes_dice.view;

import javax.swing.*;
import java.awt.*;

public class PlayerTabContainer extends JPanel {
    protected final PlayerDataComponent playerDataComponent;
    protected final PlayerBoardPanel playerBoardPanel;
    protected final JPanel playerBoardContainer;

    public PlayerTabContainer(String playerName) {
        playerDataComponent = new PlayerDataComponent(playerName);

        playerBoardPanel = new PlayerBoardPanel();
        playerBoardPanel.setPreferredSize(new Dimension(800, 800));
        playerBoardContainer = squareContainer(playerBoardPanel);

        setLayout(new BorderLayout());

        add(playerDataComponent, BorderLayout.NORTH);
        add(playerBoardContainer, BorderLayout.SOUTH);
    }

    private JPanel squareContainer(JPanel squareComponent) {
        JPanel gui = new JPanel(new GridBagLayout());
        gui.add(squareComponent, new GridBagConstraints());
        gui.setBackground(new Color(236, 225, 195));
        return gui;
    }

    public PlayerBoardPanel getPlayerBoardPanel() {
        return playerBoardPanel;
    }

}
