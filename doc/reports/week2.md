# Week 2

This week the template is near done, there are still some bugfixes left with the UI. I added the template to my project and started on the implementation for my bot. I also added test coverage reporting, choosing pitest as the library. 

I also implemented a simple minimax algorithm and basic logic for the game (determining if a move is valid, if the game has ended etc.). The code still needs a little bit of refactoring to remove duplication though. There has also been quite a bit of bug hunting and fixing.

This week I learned about mutation testing, which I find very interesting.

Next I'd like to implement alpha-beta refactoring and a better static evaluator (a function that evaluates a game board without taking in consideration the following moves). The minimax algorithm cannot see that many moves forward, so a better evaluator is essential. I'd also like to optimize the algorithm.

I spent approximately 6 hours on the course this week.
