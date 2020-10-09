# othello-bot

An algorithm that plays Othello. Created for a university course.

This project is built upon a [project template](https://github.com/vuolen/othello-core). You can find my bot implementation [here](othello-bot/src/main/java/io/github/vuolen/othello/bots/tiralabra).

## Documentation

[Project specification](doc/project_specification.md)

[Implementation document](doc/implementation.md)

### Usage

#### How to play

To play against my bot, use your terminal to navigate to `othello-bot/`, then run `mvn package` and `java -jar target/othello-bot-0.jar tiralabra.Bot`

Use the terminal to specify the coordinates of your next move (eg. d3)

You can also run my bot against itself with `java -jar target/othello-bot-0.jar tiralabra.Bot tiralabra.Bot`

### How to test

See the [testing document](doc/testing.md)

### How to generate a checkstyle report

Navigate to `othello-bot/` on your terminal, run `mvn jxr:jxr checkstyle:checkstyle` and open `target/site/checkstyle.html` on your browser.

## Weekly reports

[Week 1](doc/reports/week1.md)

[Week 2](doc/reports/week2.md)

[Week 3](doc/reports/week3.md)

[Week 4](doc/reports/week4.md)

[Week 5](doc/reports/week5.md)

[Week 6](doc/reports/week5.md)
