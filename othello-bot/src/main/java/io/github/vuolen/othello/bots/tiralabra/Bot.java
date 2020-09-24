package io.github.vuolen.othello.bots.tiralabra;

import io.github.vuolen.othello.api.OthelloBot;
import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.WHITE;
import static io.github.vuolen.othello.bots.tiralabra.GameLogic.isGameOver;
import static io.github.vuolen.othello.bots.tiralabra.GameLogic.isMoveValid;
import static io.github.vuolen.othello.bots.tiralabra.GameLogic.newBoardFromMove;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class Bot implements OthelloBot {

    public static final int BOARD_SIZE = 8;

    /**
     * The color of the bot's disks. BLACK or WHITE
     */
    private int color;
    /**
     * The color of the opponent's disks. BLACK or WHITE
     */
    private int opponent;
    
    /**
     * The bot's static evaluator
     */
    private IEvaluator evaluator;
    
    public Bot() {
        this(Evaluators::average);
    }
    
    public Bot(IEvaluator evaluator) {
        this.evaluator = evaluator;
    }
    
    /**
     * The minimax algorithm. Evaluates a board by looking at following possible
     * board states. Returns a float in the range -1...1
     *
     * @param board
     * @param depth
     * @param min
     * @param max
     * @param isMyTurn
     * @return score of the board state
     */
    private float minimax(int[][] board, int depth, float min, float max, boolean isMyTurn) {
        if (isGameOver(board) || depth == 0) {
            return this.evaluator.evaluateBoard(board, color);
        }
        if (isMyTurn) {
            float bestScore = min;
            boolean hasValidMoves = false;
            for (int x = 0; x < BOARD_SIZE; x++) {
                for (int y = 0; y < BOARD_SIZE; y++) {
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
            if (!hasValidMoves) {
                return minimax(board, depth - 1, min, max, !isMyTurn);
            }
            return bestScore;
        } else {
            float worstScore = max;
            boolean hasValidMoves = false;
            for (int x = 0; x < BOARD_SIZE; x++) {
                for (int y = 0; y < BOARD_SIZE; y++) {
                    if (isMoveValid(board, x, y, opponent)) {
                        hasValidMoves = true;
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
            if (!hasValidMoves) {
                return minimax(board, depth - 1, min, max, !isMyTurn);
            }
            return worstScore;
        }
    }

    /**
     * Takes in the current state of the board and returns the coordinates for
     * the next move.
     *
     * @param board
     * @return two-element array containing the x and y coordinates
     */
    @Override
    public int[] makeMove(int[][] board) {
        int[] bestMove = new int[2];
        float bestScore = -1f;
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (isMoveValid(board, x, y, color)) {
                    int[][] newBoard = newBoardFromMove(board, x, y, color);
                    float newBoardScore = minimax(newBoard, 4, -1f, 1f, false);
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
