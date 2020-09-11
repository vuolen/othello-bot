/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra;

import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.EMPTY;
import static io.github.vuolen.othello.api.Tile.WHITE;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class GameLogic {
    
    private static final int[][] directions = new int[][] {
        {1, 0}, {-1, 0}, // east and west
        {0, 1}, {0, -1}, // south and north
        {1, 1}, {-1, 1}, // southeast, southwest
        {1, -1}, {-1, -1} // northeast, northwest
    };
    
    /**
     * 
     * @param board
     * @param x
     * @param y
     * @param color
     * @return true if the given move is valid, false otherwise.
     */
    public static boolean isMoveValid(int[][] board, int x, int y, int color) {
        
        if (!isMoveInBounds(x, y)) {
            return false;
        }
        
        for (int[] direction : directions) {
            if (isMoveValidInDirection(board, x, y, color, direction)) {
                return true;
            }
        }
    
        return false;
    }
    
    /**
     * 
     * @param board
     * @return true if game is over, false otherwise
     */
    public static boolean isGameOver(int[][] board) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (isMoveValid(board, x, y, BLACK)
                        || isMoveValid(board, x, y, WHITE)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Takes in a board and a move to make, returns the new board.
     * @param board
     * @param x
     * @param y
     * @param color
     * @return New board with the move applied.
     */
    public static int[][] newBoardFromMove(int[][] board, int x, int y, int color) {
        int[][] newBoard = new int[8][8];
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        
        int opponent = color == BLACK ? WHITE : BLACK;
        for (int[] direction : directions) {
            if (isMoveValidInDirection(newBoard, x, y, color, direction)) {
                int nextx = x + direction[0], nexty = y + direction[1];
                while (newBoard[nextx][nexty] == opponent) {
                    newBoard[nextx][nexty] = color;
                    nextx += direction[0];
                    nexty += direction[1];
                }
            }
        }
        
        newBoard[x][y] = color;
        
        return newBoard;
    }
    
    private static boolean isMoveValidInDirection(int[][] board, int x, int y, int color, int[] direction) {
        int opponent = color == BLACK ? WHITE : BLACK;
        int nextx = x + direction[0], nexty = y + direction[1];
        
        if (!isMoveInBounds(x, y)
                || board[x][y] != EMPTY
                || !isMoveInBounds(nextx, nexty)
                || board[nextx][nexty] != opponent) {
            return false;
        }

        while (isMoveInBounds(nextx, nexty) && board[nextx][nexty] == opponent) {
            nextx += direction[0];
            nexty += direction[1];
        }
        
        if (isMoveInBounds(nextx, nexty) && board[nextx][nexty] == color) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return true if coordinates are in the board
     */
    public static boolean isMoveInBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
