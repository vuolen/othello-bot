/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra;

import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.EMPTY;
import static io.github.vuolen.othello.api.Tile.WHITE;
import static io.github.vuolen.othello.bots.tiralabra.Bot.BOARD_SIZE;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class Evaluators {
    
    
    
    /**
     * Goes for the largest amount of disks.
     */
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
    
    public static float random(int[][] board, int color) {
        if (GameLogic.isGameOver(board)) {
            int botDisks = 0, opponentDisks = 0;
            for (int x = 0; x < BOARD_SIZE; x++) {
                for (int y = 0; y < BOARD_SIZE; y++) {
                    if (board[x][y] == color) {
                        botDisks++;
                    } else if (board[x][y] != EMPTY) {
                        opponentDisks++;
                    }
                }
            }
            return botDisks > opponentDisks ? 1 : -1;
        }
        return (float) (Math.random() * 2 - 1);
    }
    
    public static float average(int[][] board, int color) {
        
        final float DISKAMOUNTFACTOR = 0.5f, MOVEAMOUNTFACTOR = 1f;
        
        float totalDisks = 0, totalMoves = 0;
        float diskAmountScore = 0, moveAmountScore = 0;
        int opponent = color == WHITE ? BLACK : WHITE;
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (board[x][y] == color) {
                    diskAmountScore++;
                    totalDisks++;
                } else if (board[x][y] == opponent) {
                    diskAmountScore--;
                    totalDisks++;
                }
                if (GameLogic.isMoveValid(board, x, y, color)) {
                    moveAmountScore++;
                    totalMoves++;
                }
                if (GameLogic.isMoveValid(board, x, y, opponent)) {
                    moveAmountScore--;
                    totalMoves++;
                }
            }
        }
        
        if (totalMoves == 0) {
            if (diskAmountScore > 0) {
                return 1;
            } else {
                return -1;
            }
        }
        
        float diskAmountScoreNormalized = diskAmountScore / totalDisks;
        float moveAmountScoreNormalized = 0;
        if (totalMoves != 0) {
            moveAmountScoreNormalized = moveAmountScore / totalMoves;
        }
        
        
        return (DISKAMOUNTFACTOR * diskAmountScoreNormalized + MOVEAMOUNTFACTOR * moveAmountScoreNormalized) / 2;
    }
    
}
