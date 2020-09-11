# othello-bot

An algorithm that plays Othello. Created for a university course.

This project is built upon a [project template](https://github.com/vuolen/othello-core). You can find my bot implementation [here](othello-bot/src/main/java/io/github/vuolen/othello/bots/tiralabra).

## Documentation

[Project specification](doc/project_specification.md)

### How to run

To play against my bot, use your terminal to navigate to `othello-bot/`, then run `mvn package` and `java -jar target/othello-bot-0.jar tiralabra.Bot`

### How to generate test coverage report

Navigate to `othello-bot/` on your terminal, run `mvn test && mvn org.pitest:pitest-maven:mutationCoverage` and open `target/pit-reports/index.html` on your browser.

## Weekly reports

[Week 1](doc/reports/week1.md)
[Week 2](doc/reports/week2.md)
