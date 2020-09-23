/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra;

import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.WHITE;
import static io.github.vuolen.othello.bots.tiralabra.Bot.BOARD_SIZE;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class Evaluators {
    
    public static float greedy(int[][] board, int color) {
        float botScore = 0, opponentScore = 0;
        int opponent = color == WHITE ? BLACK : WHITE;
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (board[x][y] == color) {
                    botScore++;
                } else if (board[x][y] == opponent) {
                    opponentScore++;
                }
            }
        }

        if (botScore > opponentScore) {
            return (botScore - opponentScore) / (botScore + opponentScore);
        } else {
            return -(opponentScore - botScore) / (botScore + opponentScore);
        }
    }
    
}
