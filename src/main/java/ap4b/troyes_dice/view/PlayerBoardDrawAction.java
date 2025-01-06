package ap4b.troyes_dice.view;

import java.awt.*;

public class PlayerBoardDrawAction {
    private final int cellX;
    private final int cellY;
    private final Color color;
    private final String type;
    private final String text;
    private final int height;
    private final int width;

    public PlayerBoardDrawAction(int cellX, int cellY, Color color, String type) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.color = color;
        this.type = type;
        this.text = "DEFAULT";
        this.height = 0;
        this.width = 0;
    }

    public PlayerBoardDrawAction(int cellX, int cellY, Color color, String type, String text) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.color = color;
        this.type = type;
        this.text = text;
        this.height = 0;
        this.width = 0;
    }

    public PlayerBoardDrawAction(int cellX, int cellY, int width, int height, Color color, String type) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.color = color;
        this.type = type;
        this.text = "DEFAULT";
        this.height = height;
        this.width = width;
    }

    public void draw(Graphics2D g2d, int panelWidth, int panelHeight) {
        int gridWidth = 400;
        int gridHeight = 400;
        int cellWidth = panelWidth / gridWidth;
        int cellHeight = panelHeight / gridHeight;

        int x = cellX * cellWidth;
        int y = cellY * cellHeight;


        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(3));
        switch (type) {
            case "RECT_BUILD":
                g2d.drawRect(x, y, 18 * cellWidth, 16 * cellHeight);
                break;
            case "TEXT_PLAYERNAME":
                g2d.setFont(new Font("Arial", Font.BOLD, 10 * cellHeight));
                g2d.drawString(text, x, y);
                break;
            case "TEXT_COLUMNNUMBER":
                g2d.setFont(new Font("Arial", Font.PLAIN, 16 * cellHeight));
                g2d.drawString(text, x, y);
                break;
            case "TEXT_SCORE":
                g2d.setFont(new Font("Arial", Font.BOLD, 10 * cellHeight));
                g2d.drawString(text, x, y);
                break;
            case "CROSS":
                g2d.drawLine(x, y, x + 18 * cellWidth, y + 16 * cellHeight);
                g2d.drawLine(x + 18 * cellWidth, y, x, y + 16 * cellHeight);
                break;
            case "CHECK":
                g2d.drawLine(x, y + 4 * cellHeight, x + 4 * cellWidth, y + 8 * cellHeight);
                g2d.drawLine(x + 4 * cellWidth, y + 8 * cellHeight, x + 9 * cellWidth, y);
                break;
            case "CASTLE":
                g2d.drawLine(x - 10 * cellWidth, y + 16 * cellHeight, x + 9 * cellWidth, y - 6 * cellHeight);
                g2d.drawLine(x + 9 * cellWidth, y - 6 * cellHeight, x + 28 * cellWidth, y + 16 * cellHeight);
                break;
            case "FILL":
                g2d.fillRoundRect(x, y, width * cellWidth, height * cellHeight, 10, 10);
                break;
            default:
                break;
        }
    }
}
