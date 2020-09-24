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
import io.github.vuolen.othello.bots.tiralabra.evaluators.IEvaluator;
import io.github.vuolen.othello.bots.tiralabra.evaluators.TiralabraEvaluator;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class EvaluatorTest {
    
    private static final IEvaluator[] EVALUATORS = new IEvaluator[]{
        Evaluators::greedy,
        (board, color) -> {
            return (new TiralabraEvaluator()).evaluateBoard(board, color);
        },
        Evaluators::average,
        Evaluators::random
    };
    
    @Test
    public void EvaluatorsReturnOneForWin() {
        int[][] board = stringToBoard(
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeebbeee" +
                "eeebbeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        
        int[][] board1 = stringToBoard(
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeewweee" +
                "eeewweee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        
        for (IEvaluator e : EVALUATORS) {
            assertEquals(1, e.evaluateBoard(board, BLACK), 0.001);
            assertEquals(1, e.evaluateBoard(board1, WHITE), 0.001);
        }
    }
    
    @Test
    public void EvaluatorsReturnMinusOneForLoss() {
        int[][] board = stringToBoard(
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeebbeee" +
                "eeebbeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        
        int[][] board1 = stringToBoard(
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeewweee" +
                "eeewweee" +
                "eeeeeeee" +
                "eeeeeeee" +
                "eeeeeeee"
        );
        
        for (IEvaluator e : EVALUATORS) {
            assertEquals(-1, e.evaluateBoard(board, WHITE), 0.001);
            assertEquals(-1, e.evaluateBoard(board1, BLACK), 0.001);
        }
    }
}
