# Project specification

## Problem

The algorithm aims to play a game of Othello with a maximal win rate. The rules of Othello can be found [here](https://www.worldothello.org/about/about-othello/othello-rules/official-rules/english)

## Program input

The program will take a two dimensional array representing the Othello board's state. The board state will be used to determine possible moves and choosing the one that will yield the best chance at victory.

## Data structures and algorithms

The program will be implemented as a minimax algorithm with alpha-beta pruning. Due to the size of the tree of possible game states, the minimax algorithm will only search as deep as feasible given the time constraints (one second per turn), thus requiring a static evaluator

The static evaluator will most likely take in consideration a couple of factors:
- The number of discs compared to the opponent's.
- The number of possible moves compared to the opponent's (mobility).
- The number of unflippable discs compared to the opponent's (stability).
- The remaining number of moves to the end of the game (parity).

The correct weighings of these factors is difficult to determine and will require experimentation.

## Expected time and space complexities

The worst case scenario means that alpha-beta pruning does not improve the time complexity of the minimax algorithm. The complexity of the minimax algorithm is O(b*d), where b is the amount of legal moves each turn and d is the depth of the tree. Given that the amount of legal moves is difficult to determine, I will assume the worst case of a constant amount of possible empty squares at the start. The depth of the tree will most likely also be constant, or very close to one, to ensure the program does not exceed the time constraint. Thus the whole algorithm should work in constant time.

The average time complexity is far beyond the scope of this project, since it also includes the optimization provided by the alpha-beta pruning.

Since the worst case tree of possible moves is a constant, the worst case space complexity is constant too.

## Information for the course administration

Degree: Bachelor's in Computer Science

Documentation language: English

## Sources

[CS312 Recitation 21 Minimax search and Alpha-Beta Pruning](https://www.cs.cornell.edu/courses/cs312/2002sp/lectures/rec21.htm)

[Alpha-beta pruning](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning)

[Othello strategy guide](http://radagast.se/othello/Help/strategy.html)
