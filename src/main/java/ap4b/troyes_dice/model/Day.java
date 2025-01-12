package ap4b.troyes_dice.model;

import java.util.ArrayList;

public class Day {
    private ArrayList<Dice> dices = new ArrayList<>();
    private ArrayList<Turn> turns = new ArrayList<>();
    private Time time;
    private int index;
    private Game game;

    public Day(int index) {
        this.index = index;
        time = Time.values()[index%2];
        for (int i = 0; i < 4; i++) {
            dices.add(new Dice(i == 0));
        }
        dices.sort(null);
        dices.get(0).setCost(0);
        dices.get(1).setCost(1);
        dices.get(2).setCost(1);
        dices.get(3).setCost(2);
    }

    public void addTurn(Turn turn) throws TurnAlreadyPlayedException {
        for (Turn t : turns) {
            if (t.getPlayer().equals(turn.getPlayer())) {
                throw new TurnAlreadyPlayedException();
            }
        }
        turns.add(turn);
        if (turns.size() == game.getNbPlayers()) {
            game.genNextDay();
        }
    }
}
