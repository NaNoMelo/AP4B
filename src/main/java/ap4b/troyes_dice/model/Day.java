package ap4b.troyes_dice.model;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private List<Dice> dices;
    private Time time;
    private int index;
    private int GameID;

    public Day(int index) {
        this.index = index;
        time = Time.values()[index%2];
        dices = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            dices.add(new Dice(i == 0));
        }
        dices.sort(null);
    }

    public void save(){

    }
}
