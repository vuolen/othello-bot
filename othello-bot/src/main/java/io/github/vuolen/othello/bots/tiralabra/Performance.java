/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.bots.tiralabra;

import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.EMPTY;
import static io.github.vuolen.othello.api.Tile.WHITE;
import io.github.vuolen.othello.bots.tiralabra.evaluators.TiralabraEvaluator;
import io.github.vuolen.othello.domain.Board;
import java.util.Random;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class Performance {
    public static void main(String[] args) {
        makeMoveStartingState(1000);
        makeMoveRandomState(100);
        evaluateStartingState(1000000);
    }
    
    public static void makeMoveStartingState(int n) {
        Bot bot = new Bot();
        int[][] board = new Board().getAsArray();
        
        bot.startGame(WHITE);
        
        long tAcc = 0;
        for (int i = 0; i < n; i++) {
            long t = System.nanoTime();
            bot.makeMove(board);
            tAcc += System.nanoTime() - t;
        }
        
        double seconds = ((double) tAcc / n) / 1000000000;
        System.out.println("makeMove on starting board state: average " + seconds + " seconds " + "(n = " + n + ")");
    }
    
    public static void evaluateStartingState(int n) {
        int[][] board = new Board().getAsArray();
        TiralabraEvaluator e = new TiralabraEvaluator();
        
        long tAcc = 0;
        for (int i = 0; i < n; i++) {
            long t = System.nanoTime();
            e.evaluateBoard(board, WHITE);
            tAcc += System.nanoTime() - t;
        }
        
        double seconds = ((double) tAcc / n) / 1000000000;
        System.out.println("evaluateBoard on starting board state: average " + seconds + " seconds " + "(n = " + n + ")");
    }
    
    public static void makeMoveRandomState(int n) {
        Bot bot = new Bot();
        int[][] board = new int[8][8];
        int[] TILES = new int[]{EMPTY, WHITE, BLACK};
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = TILES[new Random().nextInt(TILES.length)];
            }
        }
        
        bot.startGame(WHITE);
        
        long tAcc = 0;
        for (int i = 0; i < n; i++) {
            long t = System.nanoTime();
            bot.makeMove(board);
            tAcc += System.nanoTime() - t;
        }
        double seconds = ((double) tAcc / n) / 1000000000;
        System.out.println("makeMove on random board state: average " + seconds + " s " + "(n = " + n + ")");
    }
}
