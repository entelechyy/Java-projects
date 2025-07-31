package TicTacToe;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board, Position {
    private final Cell[][] field = new Cell[3][3];
    private Cell turn;
    private Map<Cell, Character> CELL_TO_STRING = Map.of(
            Cell.X, 'X',
            Cell.O, '0',
            Cell.E, '.'
    );

    public TicTacToeBoard() {
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        field[move.getRow()][move.getColumn()] = move.getCell();
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == turn && field[i][1] == turn && field[i][2] == turn) {
                return Result.WIN;
            }
            if (field[0][i] == turn && field[1][i] == turn && field[2][i] == turn) {
                return Result.WIN;
            }
        }
        if (
            field[0][0] == turn && field[1][1] == turn && field[2][2] == turn ||
            field[0][2] == turn && field[1][1] == turn && field[2][0] == turn
        ) {
            return Result.WIN;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;

        int empty = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (field[r][c] == Cell.E) {
                    empty++;
                }
            }
        }
            return empty > 0 ? Result.UNKNOWN : Result.DRAW;
    }

    public boolean isValid(Move move) {
        return
                0 <= move.getRow() && move.getRow() < 3 &&
                0 <= move.getColumn() && move.getRow() < 3 &&
                field[move.getRow()][move.getColumn()] == Cell.E &&
                move.getCell() == turn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("  123\n +---\n");
        for (int r = 0; r < 3; r++) {
            sb.append(r + 1).append("|");
            for (int c = 0; c < 3; c++) {
                sb.append(CELL_TO_STRING.get(field[r][c]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public Cell getTurn() {
        return turn;
    }
}
