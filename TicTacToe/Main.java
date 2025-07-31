package TicTacToe;

public class Main {
    public static void main(String[] args) {
        Player player1 = new RandomPlayer();
        Player player2 = new HumanPlayer();
        final Game game = new Game(player1, player2, true);
        System.out.println(new Game(player1, player2, true).play(new TicTacToeBoard()));
    }
}
