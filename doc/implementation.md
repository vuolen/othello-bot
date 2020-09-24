# Implementation document

## Project structure

The bot's implementation is in the package `io.github.vuolen.othello.tiralabra` and its subpackages. Other packages implement the framework which runs the bot. The `Bot` class is the central class of the implementation. It implements the `OthelloBot` interface, which makes it conform to the framework. In the `Bot` class there is a minimax algorithm with alpha-beta pruning. Leaf nodes in the board state tree get statically evaluated (that is, given a score based on the state of the board) by the `TiralabraEvaluator`.

The `TiralabraEvaluator` evaluates a board state, without investigating the possible following moves. This makes it less accurate than the minimax algorithm, but significantly faster. The weights of the evaluator were found by testing random combinations and finding the weights that did the best against other bots. Though this weight search is not exhaustive, it's certainly better than me trying to reason about them.

## Time and space complexity

The implemented minimax algorithm is O(b*d), where b is the branching factor and d is the depth. According to Peter Norvig's research, the average branching factor of Othello is 10, so given the depth is large enough, the algorithm will take a lot of time to complete. For my implementation I found that a depth of 4 is quite reliable throughout the game.

## Improvements

Even though the minimax algorithm's worst case is still in constant time, in reality this isn't true. This means that throughout the game the algorithms depth could change, allowing for a more effective bot.

## Sources
https://www.sciencedirect.com/topics/computer-science/branching-factor
