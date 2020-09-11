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
    public static boolean isMoveValid(int[][] board, int x, int y, int color) {
        
        if (!isMoveInBounds(x, y)) {
            return false;
        }
        
        int[][] directions = new int[][] {
            {1, 0}, {-1, 0}, // east and west
            {0, 1}, {0, -1}, // south and north
            {1, 1}, {-1, 1}, // southeast, southwest
            {1, -1}, {-1, -1} // northeast, northwest
        };
        
        for (int[] direction : directions) {
            if (isMoveValidInDirection(board, x, y, color, direction)) {
                return true;
            }
        }
    
        return false;
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
    
    public static boolean isMoveInBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
