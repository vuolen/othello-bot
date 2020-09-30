/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra;

import io.github.vuolen.othello.bots.tiralabra.evaluators.Evaluators;
import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.WHITE;
import static io.github.vuolen.othello.bots.tiralabra.TestUtil.stringToBoard;
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
    public void botChoosesWinningMove3() {
        int[][] board = stringToBoard(
                "eeeeeeee" +
                "beebeeee" +
                "beeweeee" +
                "weeeeeee" +
                "weeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        
        bot.startGame(WHITE);
        assertArrayEquals(new int[]{0, 0}, bot.makeMove(board));
    }
    
    @Test
    public void botChoosesDoesntChooseWinningMove3WithReverseGreedyEvaluator() {
        int[][] board = stringToBoard(
                "eeeeeeee" +
                "beebeeee" +
                "beeweeee" +
                "weeeeeee" +
                "weeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        
        Bot reverseGreedyBot = new Bot((int[][] b, int color) -> {
            return -Evaluators.greedy(board, color);
        });
        
        reverseGreedyBot.startGame(WHITE);
        assertArrayEquals(new int[]{3, 0}, reverseGreedyBot.makeMove(board));
    }
    
    @Test
    public void botDoesntChooseXTile() {
        int[][] board = stringToBoard(
                "ebbbbbbe" +
                "eeeebwew" +
                "eeeeewbw" +
                "eeeebeww" +
                "eeeeeeew" +
                "eeeeeeew" +
                "eeeeeeew" +
                "eeeeeeee"
        );
        
        bot.startGame(WHITE);
        int[] move = bot.makeMove(board);
        assertTrue(move[0] != 6 || move[1] != 1);
    }
    
    @Test
    public void botChoosesXTileWithReverseGreedyEvaluator() {
        int[][] board = stringToBoard(
                "ebbbbbbe" +
                "eeeebwew" +
                "eeeeewbw" +
                "eeeebeww" +
                "eeeeeeew" +
                "eeeeeeew" +
                "eeeeeeew" +
                "eeeeeeee"
        );
        
        Bot reverseGreedyBot = new Bot((int[][] b, int color) -> {
            return -Evaluators.greedy(board, color);
        });
        
        reverseGreedyBot.startGame(WHITE);
        int[] move = reverseGreedyBot.makeMove(board);
        assertTrue(move[0] == 6 && move[1] == 1);
    }
    
    @Test
    public void botChoosesCorrectMoveWithCustomEvaluator() {
        int[][] board = stringToBoard(
                "eeeeeeee" +
                "ebeeebee" +
                "eweeewee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        
        Bot customBot = new Bot((int[][] b, int color) -> {
            return b[1][2] == color ? 1 : -1;
        });
        
        customBot.startGame(BLACK);
        assertArrayEquals(new int[]{1, 3}, customBot.makeMove(board));
    }
}
