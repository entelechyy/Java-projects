package TicTacToe;

public interface Board {
    Position getPosition();

    Result makeMove(Move move);
}

//чем меньше зависимостей, тем больше шансов, что код пригодится где-нибудь ещё
