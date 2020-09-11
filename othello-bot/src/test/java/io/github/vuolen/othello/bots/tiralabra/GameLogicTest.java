/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra;

import static io.github.vuolen.othello.api.Tile.*;
import static io.github.vuolen.othello.bots.tiralabra.GameLogic.isGameOver;
import static io.github.vuolen.othello.bots.tiralabra.GameLogic.isMoveValid;
import static io.github.vuolen.othello.bots.tiralabra.GameLogic.newBoardFromMove;
import static io.github.vuolen.othello.bots.tiralabra.TestUtil.stringToBoard;
import java.util.Arrays;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class GameLogicTest {
    
    private int[][] emptyBoard = new int[8][8];
    
    @Before
    public void setup() {
        for (int y = 0; y < 8; y++) {
            Arrays.fill(emptyBoard[y], WHITE);
        }
    }
    
    @Test
    public void moveOutOfBoundsIsInvalid() {
        assertFalse(isMoveValid(emptyBoard, -1, -1, BLACK));
    }
    
    @Test
    public void noNeighbourMoveIsInvalid() {
        assertFalse(isMoveValid(emptyBoard, 0, 0, BLACK));
    }
    
    @Test
    public void WbwHorizontalIsValid() {
        int[][] board = stringToBoard(
                "ebweeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        assertTrue(isMoveValid(board, 0, 0, WHITE));
    }
    
    @Test
    public void WbwVerticalIsValid() {
        int[][] board = stringToBoard(
                "eeeeeeee" +
                "beeeeeee" +
                "weeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        assertTrue(isMoveValid(board, 0, 0, WHITE));
    }
    
    @Test
    public void startingPositionIsNotGameOver() {
        int[][] board = stringToBoard(
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeewbeee" +
                "eeebweee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        assertFalse(isGameOver(board));
    }
    
    @Test
    public void boardWithOnlyOneColorIsGameOver() {
        int[][] board = stringToBoard(
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeewweee" +
                "eeewweee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        assertTrue(isGameOver(board));
    }
    
    @Test
    public void newBoardFromMoveWBW() {
        int[][] board = stringToBoard(
                "ebweeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        
        board = newBoardFromMove(board, 0, 0, WHITE);
        assertTrue(board[1][0] == WHITE);
    }
}
