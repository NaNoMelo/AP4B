package ap4b.troyes_dice.model;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Game game = new Game();
        Player player = new Player("Player 1");
        game.addPlayer(player);
        Thread gameThread = game.startGame();
        while (gameThread.isAlive()) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 4; i++) {
                System.out.println("Dice " + i + ": Color : " + game.getCurrentDay().getDice(i).getColor().name() + " Value : " + game.getCurrentDay().getDice(i).getValue()+ " Black : " + game.getCurrentDay().getDice(i).isBlack());
            }
            Turn turn = new Turn(player.getSheet(), new PlayerDice(game.getCurrentDay().getDice(0), 3, Color.RED), Action.BUILD_WORKPLACE, game.getCurrentDay());
        }
    }
}
