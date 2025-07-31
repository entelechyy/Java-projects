package TicTacToe;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;
    private final PrintStream out;

    public HumanPlayer(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public HumanPlayer() {
        this(new Scanner(System.in), System.out);
    }

    @Override
    public Move move(Position position) {
        System.out.println("Position: ");
        System.out.println(position);
        System.out.println("Enter row and column");
        int row = in.nextInt() - 1;
        int col = in.nextInt() - 1;
        return new Move(row, col, position.getTurn());
    }
}
