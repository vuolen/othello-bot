/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra;

import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.WHITE;
import static io.github.vuolen.othello.bots.tiralabra.GameLogic.newBoardFromMove;
import static io.github.vuolen.othello.bots.tiralabra.TestUtil.stringToBoard;
import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class BotTest {
    
    Bot bot;
    
    @Before
    public void setup() {
        this.bot = new Bot();
    }
    
    @Test
    public void botChoosesWinningMove1() {
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
        
        bot.startGame(WHITE);
        assertArrayEquals(new int[]{0, 0}, bot.makeMove(board));
    }
    
    @Test
    public void botChoosesWinningMove2() {
        int[][] board = stringToBoard(
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeebbwee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        
        int[][] board2 = stringToBoard(
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeew" +
                "eeeeeeeb" +
                "eeeeeeeb" +
                "eeeeeeee"
        );
        
        bot.startGame(WHITE);
        assertArrayEquals(new int[]{2, 3}, bot.makeMove(board));
        assertArrayEquals(new int[]{7, 7}, bot.makeMove(board2));
    }
    
    @Test
    public void botDoesntChooseLosingMove() {
        int[][] board = stringToBoard(
                "eeeeeeee" +
                "eeeeeeee" +
                "eewbweee" +
                "eeeweeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        
        bot.startGame(BLACK);
        assertArrayEquals(new int[]{3, 4}, bot.makeMove(board));
    }
}
