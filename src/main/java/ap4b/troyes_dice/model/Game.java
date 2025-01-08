package ap4b.troyes_dice.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Day> days;
    private int ID;
    private int currentDay = 0;

    public Game(){
        days = new ArrayList<>();
        days.add(new Day(currentDay));
    }

    public void genNextDay(){
        currentDay++;
        days.add(new Day(currentDay));
    }

    public Day getCurrentDay() {
        return days.get(currentDay);
    }
}
