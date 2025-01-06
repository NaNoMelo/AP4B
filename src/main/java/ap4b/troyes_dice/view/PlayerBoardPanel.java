package ap4b.troyes_dice.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerBoardPanel extends JPanel {

    private Image boardImage;
    private final List<PlayerBoardDrawAction> actions;

    public PlayerBoardPanel() {
        actions = new ArrayList<>();
        try {
            boardImage = ImageIO.read(new File("/home/disk_/AP4B/src/main/resources/board.jpg"));
        } catch (IOException e) {
            System.err.println("Error loading board image: " + e.getMessage());
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw background image
        if (boardImage != null) {
            g2d.drawImage(boardImage, 0, 0, getWidth(), getHeight(), null);
        } else {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        // Draw actions
        for (PlayerBoardDrawAction action : actions) {
            action.draw(g2d, getWidth(), getHeight());
        }
    }

    protected void addAction(PlayerBoardDrawAction action) {
        actions.add(action);
    }

    protected void clearActions() {
        actions.clear();
    }



}
