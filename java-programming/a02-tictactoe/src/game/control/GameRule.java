package game.control;

public class GameRule {

    private static final int[][] WINNING_COMBINATIONS = {
        {0, 1, 2}, // row 1
        {3, 4, 5}, // row 2
        {6, 7, 8}, // row 3
        {0, 3, 6}, // column 1
        {1, 4, 7}, // column 2
        {2, 5, 8}, // column 3
        {0, 4, 8}, // diagonal 1
        {2, 4, 6}, // diagonal 2
    };

    public static boolean isWin(Shape[] board, Shape shape) {
        for (int[] combination : WINNING_COMBINATIONS) {
            if (board[combination[0]] == shape &&
                board[combination[1]] == shape &&
                board[combination[2]] == shape) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDraw(Shape[] board) {
        for (int i = 0; i < 9; i++) {
            if (board[i] == Shape.EMPTY) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGameOver(Shape[] board, Shape shape) {
        return isWin(board, shape) || isDraw(board);
    }

    public static int getComputerMove(Shape[] board) {
        for (int i = 0; i < 9; i++) {
            if (board[i] == Shape.EMPTY) {
                return i;
            }
        }
        return -1;
    }

}
