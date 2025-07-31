package TicTacToe;

public class Move {
    private final int row;
    private final int column;
    private final Cell cell;

    public Move(int row, int column, Cell cell) {
        this.row = row;
        this.column = column;
        this.cell = cell;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Cell getCell() {
        return cell;
    }

//    public void play(final Board board) {
//        Move move = player1.move(board.getPosition());
//        Result result = board.makeMove(move);
//    }
}
