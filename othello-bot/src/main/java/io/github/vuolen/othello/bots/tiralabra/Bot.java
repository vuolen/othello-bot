package io.github.vuolen.othello.bots.tiralabra;

import io.github.vuolen.othello.api.OthelloBot;
import static io.github.vuolen.othello.api.Tile.*;
import static io.github.vuolen.othello.bots.tiralabra.GameLogic.*;
import java.util.Arrays;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class Bot implements OthelloBot {
    
    private int color;
    private int opponent;
    
    private float minimax(int[][] board, int depth, float min, float max, boolean isMyTurn) {
        if (isGameOver(board) || depth == 0) {
            return evaluateBoard(board);
        }
        if (isMyTurn) {
            float bestScore = min;
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (isMoveValid(board, x, y, color)) {
                        int[][] newBoard = newBoardFromMove(board, x, y, color);
                        float newBoardScore = minimax(newBoard, depth - 1, bestScore, max, !isMyTurn);

                        if (newBoardScore > bestScore) {
                            bestScore = newBoardScore;
                        }
                        
                        if (bestScore > max) {
                            return max;
                        }
                    }
                }
            }
            return bestScore;
        } else {
            float worstScore = max;
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (isMoveValid(board, x, y, opponent)) {
                       int[][] newBoard = newBoardFromMove(board, x, y, opponent);
                       float newBoardScore = minimax(newBoard, depth - 1, min, worstScore, !isMyTurn);
                       
                       if (newBoardScore < worstScore) {
                           worstScore = newBoardScore;
                       }
                       
                       if (worstScore < min) {
                           return min;
                       }
                    }
                }
            }
            return worstScore;
        }
    }
    
    /**
     * Takes in a board state and returns a float in the range -1...1, indicating which player
     * is in a better position to win.
     * @param board
     * @return a float in the range of -1...1, closer to 1 is an advantage to this bot,
     * closer to -1 is an advantage to the opponent.
     */
    private float evaluateBoard(int[][] board) {
                
        // This is just a simple evaluator right now, definitely not optimal.
        float botScore = 0, opponentScore = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y] == this.color) {
                    botScore++;
                } else if (board[x][y] == this.opponent) {
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
    
    /**
     * Takes in the current state of the board and returns the coordinates for the next move.
     * @param board
     * @return two-element array containing the x and y coordinates
     */
    @Override
    public int[] makeMove(int[][] board) {
        int[] bestMove = new int[2];
        float bestScore = -1f;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (isMoveValid(board, x, y, color)) {
                    int[][] newBoard = newBoardFromMove(board, x, y, color);
                    float newBoardScore = minimax(newBoard, 5, -1f, 1f, false);
                                        
                    if (newBoardScore >= bestScore) {
                         bestScore = newBoardScore;
                         bestMove[0] = x;
                         bestMove[1] = y;
                    }
                }
            }
        }
        return bestMove;
    }
    
    @Override
    public void startGame(int color) {
        this.color = color;
        this.opponent = this.color == BLACK ? WHITE : BLACK;
    }

    @Override
    public boolean isHuman() {
        return false;
    }
    
}
