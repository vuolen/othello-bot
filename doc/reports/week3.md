# Week 3

This week the template is finished, at least I hope so. I updated the template in my repo and haven't noticed any bugs yet. I implemented alpha-beta pruning to my minimax algorithm, but I feel like it didn't have that large of an effect on the performance of the bot. I profiled my code and it seems like the bot spends the most time on the method `isMoveValid`. I'm quite confident the actual move validation cannot be optimized significantly, but I might be able to reduce the amount of calls to the method overall. I fixed the static evaluator a bit, but didn't spend too much time on it, since it will most likely change drastically next week. I added a few tests to increase coverage, but didn't spend too much time on it.

I spent approximately 4 hours on the course this week.
