package ap4b.troyes_dice.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

public class View {
    private JPanel mainContainer;
    private CorePanel corePanel;
    private PlayersTab playersTab;
    private DiceActionsPanel diceActionsPanel;
    private JPanel playerBoardContainer;

    public View() {
        mainContainer = new JPanel(new BorderLayout());
        mainContainer.setMinimumSize(new Dimension(1000, 1000));

        corePanel = new CorePanel();
        corePanel.setPreferredSize(new Dimension(200, 800));

        playersTab = new PlayersTab();
        dummyPlayerTab();
        playersTab.setMinimumSize(new Dimension(800, 1000));

        diceActionsPanel = new DiceActionsPanel();
        diceActionsPanel.setPreferredSize(new Dimension(200, 800));

        gameSetup();
    }

    public void gameSetup(){
        mainContainer.add(corePanel, BorderLayout.WEST);
        mainContainer.add(playersTab, BorderLayout.CENTER);
        mainContainer.add(diceActionsPanel, BorderLayout.EAST);

        mainContainer.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaintPlayerBoards();
            }
        });
    }

    public JPanel getMainContainer() {
        return mainContainer;
    }

    public void dummyPlayerTab(){
        addPlayerTab(0, "Dummy");
    }

    public void testPlayerBoard(int playerID, String playerName){
        addPlayerTab(playerID, playerName);
        drawPlayerName(playerID, playerName);
        drawColumnNumbers(playerID);
        drawBuilding(playerID,1,1);
        drawBuilding(playerID,2, 2);
        drawBuilding(playerID,3, 2);
        drawBuilding(playerID,6, 5);
        drawAllBuildings(playerID);
        drawCross(playerID,3,1);
        drawCross(playerID,4,1);
        drawCross(playerID,4,2);
        drawCheck(playerID,1,1);
        drawCheck(playerID,1,2);
        drawCheck(playerID,1,4);
        drawCastle(playerID,3,6);
        drawAllCastles(playerID);
        drawTotalScore(playerID,37);

        repaintPlayerBoard(playerID);
    }

    private PlayerBoardPanel getPlayerBoardPanelFromID(int playerID){
        return playersTab.getPlayerBoard(playerID);
    }

    public void repaintPlayerBoard(int playerID){
        PlayerBoardPanel boardPanel = getPlayerBoardPanelFromID(playerID);
        boardPanel.repaint();
    }

    public void repaintPlayerBoards(){
        playersTab.refactor();
        for (int playerID : playersTab.getPlayerBoards().keySet()){
            repaintPlayerBoard(playerID);
        }
    }

    public void clearPlayerBoard(int playerID){
        PlayerBoardPanel boardPanel = getPlayerBoardPanelFromID(playerID);
        boardPanel.clearActions();
        defaultFills(playerID);
        repaintPlayerBoard(playerID);
    }

    public void drawPlayerName(int playerID, String playerName) {
        PlayerBoardPanel boardPanel = getPlayerBoardPanelFromID(playerID);
        boardPanel.addAction(new PlayerBoardDrawAction(338, 32, Color.BLACK, "TEXT_PLAYERNAME", playerName));
    }

    public void drawColumnNumbers(int playerID) {
        PlayerBoardPanel boardPanel = getPlayerBoardPanelFromID(playerID);
        for (int i = 0; i < 6; i++) {
            for (int row = 0; row < 3; row++) {
                boardPanel.addAction(new PlayerBoardDrawAction(86+43*i, 46+row*103, Color.BLACK, "TEXT_COLUMNNUMBER", String.format("%d", i+1)));
            }
        }
    }

    public void drawAllBuildings(int playerID) {
        PlayerBoardPanel boardPanel = getPlayerBoardPanelFromID(playerID);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                for (int column = 0; column < 6; column++) {
                    int offsetX = 0;
                    if (i == 2 && j == 0){
                        offsetX = -5;
                    };
                    boardPanel.addAction(new PlayerBoardDrawAction(80+43*column+offsetX, 55+25*j+103*i, Color.BLACK, "RECT_BUILD"));
                }
            }
        }
    }

    public void drawBuilding(int playerID, int column, int row) {
        PlayerBoardPanel boardPanel = getPlayerBoardPanelFromID(playerID);
        int i = (row - 1) / 2;
        int j = (row - 1) % 2;
        int offsetX = 0;
        if (i == 2 && j == 0){
            offsetX = -5;
        };
        boardPanel.addAction(new PlayerBoardDrawAction(80+43*(column - 1)+offsetX, 55+25*j+103*i, Color.BLACK, "RECT_BUILD"));
    }

    public void drawCross(int playerID, int column, int row) {
        PlayerBoardPanel boardPanel = getPlayerBoardPanelFromID(playerID);
        int i = (row - 1) / 2;
        int j = (row - 1) % 2;
        boardPanel.addAction(new PlayerBoardDrawAction(80+43*(column - 1), 55+25*j+103*i, Color.BLACK, "CROSS"));
    }

    public void drawCheck(int playerID, int column, int row) {
        PlayerBoardPanel boardPanel = getPlayerBoardPanelFromID(playerID);
        int i = (row - 1) / 2;
        int j = (row - 1) % 2;
        boardPanel.addAction(new PlayerBoardDrawAction(107+43*(column - 1), 58+25*j+103*i, Color.BLACK, "CHECK"));
    }

    public void drawAllChecks(int playerID) {
        PlayerBoardPanel boardPanel = getPlayerBoardPanelFromID(playerID);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                for (int column = 0; column < 6; column++) {
                    boardPanel.addAction(new PlayerBoardDrawAction(107+43*column, 58+25*j+103*i, Color.BLACK, "CHECK"));
                }
            }
        }
    }

    public void drawCastle(int playerID, int column, int row) {
        PlayerBoardPanel boardPanel = getPlayerBoardPanelFromID(playerID);
        boardPanel.addAction(new PlayerBoardDrawAction(81+43*(column - 1), 30+103*(row - 1), Color.BLACK, "CASTLE"));
    }

    public void drawAllCastles(int playerID) {
        PlayerBoardPanel boardPanel = getPlayerBoardPanelFromID(playerID);
        for (int color = 0; color < 3; color++){
            for (int column = 0; column < 6; column++) {
                boardPanel.addAction(new PlayerBoardDrawAction(81+43*column, 30+103*color, Color.BLACK, "CASTLE"));
            }
        }
    }

    public void drawTotalScore(int playerID, int score) {
        PlayerBoardPanel boardPanel = getPlayerBoardPanelFromID(playerID);
        boardPanel.addAction(new PlayerBoardDrawAction(344, 377, Color.BLACK, "TEXT_SCORE", String.format("%d", score)));
    }

    public void fillRectangle(int playerID, int cellX, int cellY, int width, int height, Color color) {
        PlayerBoardPanel boardPanel = getPlayerBoardPanelFromID(playerID);
        boardPanel.addAction(new PlayerBoardDrawAction(cellX, cellY, width, height, color, "FILL"));
    }

    public void defaultFills(int playerID){
        fillRectangle(playerID, 71, 331, 254, 50, new Color(57,91,137));

        fillRectangle(playerID,20, 100, 306, 15, new Color(128,27,13));
        fillRectangle(playerID,20, 203, 306, 15, new Color(228, 209,71));
        fillRectangle(playerID,20, 306, 306, 15, new Color(205, 204, 199));

    }


    public void setCorePanelButtonTitle(int index, String title) {
        corePanel.setButtonTitle(index, title);
    }

    public void setCorePanelButtonColor(int index, Color color) {
        corePanel.setButtonColor(index, color);
    }


    public void revalidateFrame() {
        mainContainer.revalidate();
    }

    public void addPlayerTab(int playerID, String playerName){
        playersTab.addPlayerBoard(playerID, playerName);
        clearPlayerBoard(playerID);
    }

    public void removePlayerTab(int playerID){
        playersTab.removePlayerBoard(playerID);
    }

    public void setPlayersScores(Map<Integer, Integer> playerScores, Map<Integer, List<Integer>> playerResources){
        for (int playerID : playerScores.keySet()){
            playersTab.setPlayerScore(playerID, playerScores.get(playerID), playerResources.get(playerID));
        }
    }

    public void clearPlayerTabs(){
        playersTab.clearPlayers();
    }

    public void selectDice(int color, int value) {
        diceActionsPanel.selectDice(color, value);
    }

    public int getSelectedColor() {
        return diceActionsPanel.getColor();
    }

    public int getSelectedValue() {
        return diceActionsPanel.getValue();
    }

    public int getSelectedDice() {
        return corePanel.getSelectedDice();
    }

    public int getSelectedPlayerID() {
        return playersTab.getSelectedPlayerID();
    }

    public void setActionCallbacks(ActionListener getRessource, ActionListener buildWorkplace, ActionListener buildPrestige) {
        diceActionsPanel.setActionCallbacks(getRessource, buildWorkplace, buildPrestige);
    }

    public void addOnDiceSelectAction(ActionListener action){
        corePanel.addOnSelectAction(action);
    }
}

