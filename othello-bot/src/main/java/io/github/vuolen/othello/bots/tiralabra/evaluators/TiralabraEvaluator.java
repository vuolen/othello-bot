/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra.evaluators;

import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.WHITE;
import static io.github.vuolen.othello.bots.tiralabra.Bot.BOARD_SIZE;
import io.github.vuolen.othello.bots.tiralabra.GameLogic;

/**
 * The default evaluator. Used in the final release
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class TiralabraEvaluator implements IEvaluator {

    private float MOBILITYWEIGHT;
    private float CORNERWEIGHT;
    private float XTILEWEIGHT;
    private float FRONTIERWEIGHT;

    public TiralabraEvaluator() {
        this(1f, 0.5f, 0.5f, 1f);
    }
    
    public TiralabraEvaluator(float MOBILITYWEIGHT, float CORNERWEIGHT, float XTILEWEIGHT, float FRONTIERWEIGHT) {
        this.MOBILITYWEIGHT = MOBILITYWEIGHT;
        this.CORNERWEIGHT = CORNERWEIGHT;
        this.XTILEWEIGHT = XTILEWEIGHT;
        this.FRONTIERWEIGHT = FRONTIERWEIGHT;
    }
    
    @Override
    public float evaluateBoard(int[][] board, int color) {        
        int opponent = color == WHITE ? BLACK : WHITE;
        float totalMoves = 0, totalDisks = 0, totalFrontierDisks = 0;
        float mobilityScore = 0, diskAmountScore = 0, frontierDiskAmountScore = 0;
        float cornerScore = 0;
        float xtileScore = 0;
        
        int[][] CORNERS = new int[][] {
            {0, 0}, {0, 7}, {7, 0}, {7, 7}
        };
        
        for (int[] corner : CORNERS) {
            int cornerColor = board[corner[0]][corner[1]];
            if (cornerColor == color) {
                cornerScore += 0.25;
            } else if (cornerColor == opponent) {
                cornerScore -= 0.25;
            }
        }
        
        int[][] XTILES = new int[][] {
            {1, 1}, {1, 6}, {6, 1}, {6, 6}
        };
        
        for (int[] xtile : XTILES) {
            int xtileColor = board[xtile[0]][xtile[1]];
            if (xtileColor == color) {
                xtileScore -= 0.25;
            } else if (xtileColor == opponent) {
                xtileScore += 0.25;
            }
        }
        
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (board[x][y] == color) {
                    diskAmountScore++;
                    totalDisks++;
                    
                    if (GameLogic.isFrontierDisk(board, x, y)) {
                        frontierDiskAmountScore--;
                        totalFrontierDisks++;
                    }
                    
                } else if (board[x][y] == opponent) {
                    diskAmountScore--;
                    totalDisks++;
                    
                    if (GameLogic.isFrontierDisk(board, x, y)) {
                        frontierDiskAmountScore++;
                        totalFrontierDisks++;
                    }
                }
                if (GameLogic.isMoveValid(board, x, y, color)) {
                    mobilityScore++;
                    totalMoves++;
                }
                if (GameLogic.isMoveValid(board, x, y, opponent)) {
                    mobilityScore--;
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
        
        float TOTALWEIGHT = (MOBILITYWEIGHT + CORNERWEIGHT + XTILEWEIGHT + FRONTIERWEIGHT);
        
        return (MOBILITYWEIGHT * (mobilityScore / totalMoves) 
                + CORNERWEIGHT * cornerScore 
                + XTILEWEIGHT * xtileScore
                + FRONTIERWEIGHT * (frontierDiskAmountScore / totalFrontierDisks)) / TOTALWEIGHT;
    }

    @Override
    public String toString() {
        return MOBILITYWEIGHT + "f, " + CORNERWEIGHT + "f, " + XTILEWEIGHT + "f, " + FRONTIERWEIGHT + "f";
    }
    
    
    
}
