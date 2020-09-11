package io.github.vuolen.othello.bots.tiralabra;

import io.github.vuolen.othello.api.OthelloBot;
import static io.github.vuolen.othello.api.Tile.EMPTY;
import static io.github.vuolen.othello.bots.tiralabra.GameLogic.isGameOver;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class Bot implements OthelloBot {
    
    private int color;
    
    private float minimax(int[][] board, int depth, boolean isMyTurn) {
        if (isGameOver(board) || depth == 0) {
            return evaluateBoard(board);
        }
        return 0;
    }
    
    /**
     * Takes in a board state and returns a float in the range -1...1, indicating which player
     * is in a better position to win.
     * @param board
     * @return a float in the range of -1...1, closer to 1 is an advantage to this bot,
     * closer to -1 is an advantage to the opponent.
     */
    private float evaluateBoard(int[][] board) {
        // This is just a simple evaluator right now, definitely not optimal.
        float botScore = 0, opponentScore = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y] == this.color) {
                    botScore++;
                } else if (board[x][y] != EMPTY) {
                    opponentScore++;
                }
            }
        }
        
        return ((botScore + opponentScore) / botScore) * 2 - 1;
    }
    
    /**
     * Takes in the current state of the board and returns the coordinates for the next move.
     * @param board
     * @return two-element array containing the x and y coordinates
     */
    @Override
    public int[] makeMove(int[][] board) {
        return new int[]{0, 0};
    }
    
    @Override
    public void startGame(int color) {
        this.color = color;
    }

    @Override
    public boolean isHuman() {
        return false;
    }
    
}
