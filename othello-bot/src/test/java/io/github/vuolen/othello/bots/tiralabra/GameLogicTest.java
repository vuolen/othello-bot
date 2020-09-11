/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra;

import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.EMPTY;
import static io.github.vuolen.othello.api.Tile.WHITE;
import io.github.vuolen.othello.bots.tiralabra.GameLogic;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class GameLogicTest {
    
    private int[][] emptyBoard;
    
    @Before
    public void setup() {
        emptyBoard = new int[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                emptyBoard[x][y] = EMPTY;
            }
        }
    }
    
    @Test
    public void WbwIsValid() {
        emptyBoard[1][0] = BLACK;
        emptyBoard[2][0] = WHITE;
        assertTrue(GameLogic.isMoveValid(emptyBoard, 0, 0, WHITE));
    }
}
