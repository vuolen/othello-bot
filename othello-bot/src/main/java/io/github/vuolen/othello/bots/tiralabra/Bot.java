package io.github.vuolen.othello.bots.tiralabra;

import io.github.vuolen.othello.api.OthelloBot;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class Bot implements OthelloBot {
    
    /**
     * Takes in the current state of the board and returns the coordinates for the next move.
     * @param board
     * @return int[]
     */
    @Override
    public int[] makeMove(int[][] board) {
        return new int[]{0, 0};
    }
    
    @Override
    public void startGame(int color) {
    }

    @Override
    public boolean isHuman() {
        return false;
    }
    
}
