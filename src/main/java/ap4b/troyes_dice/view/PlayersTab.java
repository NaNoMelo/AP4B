package ap4b.troyes_dice.view;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PlayersTab extends JTabbedPane {
    private final Map<Integer, PlayerTabContainer> playerBoards;

    public PlayersTab() {
        setMinimumSize(new Dimension(800, 1000));
        playerBoards = new Hashtable<>();
        setBackground(new Color(236, 225, 195));
    }

    public void refactor() {
        removeAll();
        for (Map.Entry<Integer, PlayerTabContainer> entry : playerBoards.entrySet()) {
            addTab(entry.getValue().playerDataComponent.getPlayerName(), entry.getValue());
        }
    }

    public void addPlayerBoard(int playerID, String playerName) {
        PlayerTabContainer playerBoard = new PlayerTabContainer(playerName);
        playerBoards.put(playerID, playerBoard);
        refactor();
    }

    public void setPlayerScore(int playerID, int score, List<Integer> resources) {
        playerBoards.get(playerID).playerDataComponent.setPlayerScore(score, resources.get(0), resources.get(1), resources.get(2));
        refactor();
    }

    public void setPlayerName(int playerID, String playerName) {
        playerBoards.get(playerID).playerDataComponent.setPlayerName(playerName);
        refactor();
    }

    public void removePlayerBoard(int playerID) {
        playerBoards.remove(playerID);
        refactor();
    }

    public PlayerBoardPanel getCurrentPlayerBoard() {
        PlayerTabContainer playerTab = (PlayerTabContainer) getSelectedComponent();
        return playerTab.getPlayerBoardPanel();
    }

    public PlayerBoardPanel getPlayerBoard(int playerID) {
        return playerBoards.get(playerID).getPlayerBoardPanel();
    }

    public Map<Integer, PlayerTabContainer> getPlayerBoards() {
        return playerBoards;
    }

    public void clearPlayers() {
        playerBoards.clear();
        refactor();
    }
}
