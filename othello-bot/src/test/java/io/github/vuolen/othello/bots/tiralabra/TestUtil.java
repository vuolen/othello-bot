/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra;

import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.EMPTY;
import static io.github.vuolen.othello.api.Tile.WHITE;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class TestUtil {
    
    public static int[][] stringToBoard(String s) {
        assertTrue(s.length() == 64);
        int i = 0;
        int[][] board = new int[8][8];
        for (char c : s.toCharArray()) {
            int x = i % 8, y = (int) ((i - x) / 8);
            if (c == 'w') {
                board[x][y] = WHITE;
            } else if (c == 'b') {
                board[x][y] = BLACK;
            } else {
                board[x][y] = EMPTY;
            }
            i++;
        }
        return board;
    }
    
}
