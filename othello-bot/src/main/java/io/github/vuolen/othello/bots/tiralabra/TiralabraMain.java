/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra;

import io.github.vuolen.othello.bots.tiralabra.evaluators.Evaluators;
import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.WHITE;
import io.github.vuolen.othello.bots.SimpleBot;
import io.github.vuolen.othello.bots.tiralabra.evaluators.TiralabraEvaluator;
import io.github.vuolen.othello.ui.UI;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class TiralabraMain {

    public static void main(String[] args) {
        TiralabraEvaluator[] evaluators = new TiralabraEvaluator[4];
        int[] scores = new int[10];
        for (int i = 0; i < evaluators.length; i++) {
            evaluators[i] = new TiralabraEvaluator((float) Math.random(), (float) Math.random(), 
                                                    (float) Math.random(), (float) Math.random());
        }
        
        evaluators[0] = new TiralabraEvaluator(1f, 0.5f, 0.5f, 1f);
        
        for (int i = 0; i < evaluators.length; i++) {
            for (int j = 0; j < evaluators.length; j++) {
                System.out.println(i + " " + j);
                int winner = UI.battle(new Bot(evaluators[j]), new Bot(evaluators[i]), false);
                if (winner == BLACK) {
                    scores[j]++;
                } else if (winner == WHITE) {
                    scores[i]++;
                }

                winner = UI.battle(new Bot(evaluators[i]), new Bot(evaluators[j]), false);
                if (winner == BLACK) {
                    scores[i]++;
                } else if (winner == WHITE) {
                    scores[j]++;
                }
            }
        }
        
        for (int i = 0; i < evaluators.length; i++) {
            System.out.println(evaluators[i]);
            System.out.println("\t" + scores[i]);
            System.out.println("-------------");
        }
    }
}
