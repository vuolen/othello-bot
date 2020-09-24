/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra;

import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.WHITE;
import io.github.vuolen.othello.bots.SimpleBot;
import io.github.vuolen.othello.ui.UI;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class TiralabraMain {

    public static void main(String[] args) {
        Bot tiralabra = new Bot(Evaluators::tiralabra);
        
        if (UI.battle(tiralabra, new Bot(Evaluators::greedy), false) != BLACK
                || UI.battle(new Bot(Evaluators::greedy), tiralabra, false) != WHITE) {
            System.out.println("TIRALABRA LOST GREEDY");
        } else {
            System.out.println("TIRALABRA WINS GREEDY");
        }

        if (UI.battle(tiralabra, new Bot(Evaluators::average), false) != BLACK
                || UI.battle(new Bot(Evaluators::average), tiralabra, false) != WHITE) {
            System.out.println("TIRALABRA LOST AVERAGE");
        } else {
            System.out.println("TIRALABRA WINS AVERAGE");
        }
        
        if (UI.battle(tiralabra, new Bot(Evaluators::random), false) != BLACK
                || UI.battle(new Bot(Evaluators::random), tiralabra, false) != WHITE) {
            System.out.println("TIRALABRA LOST RANDOM");
        } else {
            System.out.println("TIRALABRA WINS RANDOM");
        }

        if (UI.battle(tiralabra, new SimpleBot(), false) != BLACK
                || UI.battle(new SimpleBot(), tiralabra, false) != WHITE) {
            System.out.println("TIRALABRA LOST SIMPLEBOT");
        } else {
            System.out.println("TIRALABRA WINS SIMPLEBOT");
        }
    }
}
