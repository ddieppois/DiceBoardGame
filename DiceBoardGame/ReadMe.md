# DiceBoardGameService

## Overview

`DiceBoardGameService` simulates a dice board game based on configurable properties. It runs multiple simulations, each consisting of rolling several dice, applying specific rules, and calculating scores. The results are logged with their occurrences and percentages.

---

## Configuration Properties

- **dice.board.game.count** (`numberOfSimulations`):  
  Number of game simulations to run. Default: 1,000,000

- **dice.board.game.dice.count** (`numberOfDices`):  
  Number of dice rolled in each game. Default: 5

- **dice.board.game.dice.faces** (`diceFaceCount`):  
  Number of faces on each die. Default: 6 (minimum allowed: 3)

---

## Workflow of `startGame()`

### Configuration Validation

- The method checks that all properties are valid (positive numbers, at least 3 faces per die).
- If not, an exception is thrown.

### Simulation Loop

For each simulation:

- Calls `playSingleGame()` to simulate one game and get its total score.
- Updates a `TreeMap<Integer, Integer>` to count occurrences of each score.

### Game Rules (`playSingleGame`)

- Rolls `numberOfDices` dice.
- While there are dice left:
  - If any die shows a 3, all dice with value 3 are removed (no score added).
  - Otherwise, removes the die with the lowest value and adds its value to the total score.
  - Rolls new dice equal to the number of remaining dice.
- Returns the total score for the game.

### Result Logging

After all simulations:

- Logs the number of occurrences and percentage for each possible score.
- Logs the total simulation time in seconds.

---

## Example Output

```
Number of simulations was 1000000 using 5 dice(s).
Total score: 7 - Occurrence: 12345 - Percentage: 1.23
Total score: 8 - Occurrence: 23456 - Percentage: 2.35
...
Total simulation took 12.34 seconds.
```

---

## Usage

The service is intended to be started at application startup, typically via a `CommandLineRunner` bean.
Simply start the application, and it will run the dice board game simulations based on the configured properties.

---
