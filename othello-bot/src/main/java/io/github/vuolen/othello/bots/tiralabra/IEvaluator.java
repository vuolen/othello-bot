/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public interface IEvaluator {
    /**
     * Takes in a board state and the color of the player and returns a float in the range -1...1,
     * indicating which player is in a better position to win.
     *
     * @param board
     * @param color
     * @return a float in the range of -1...1, closer to 1 is an advantage to
     * this bot, closer to -1 is an advantage to the opponent.
     */
    public float evaluateBoard(int[][] board, int color);
}
