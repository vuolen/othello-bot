/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra.evaluators;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public interface IEvaluator {
    public float evaluateBoard(int[][] board, int color);
}
