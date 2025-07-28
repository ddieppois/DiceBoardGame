package com.ddieppois.diceboard.diceboardgame.services;

import com.ddieppois.diceboard.diceboardgame.exceptions.DiceBoardGamePropertiesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class DiceBoardGameService {
    private final Random random = new Random();

    @Value("${dice.board.game.count:1000000}")
    private int numberOfSimulations;

    @Value("${dice.board.game.dice.count:5}")
    private int numberOfDices;

    @Value("${dice.board.game.dice.faces:6}")
    private int diceFaceCount;

    /**
     * Starts the game simulation.
     * It rolls the dice for the specified number of simulations/dices and calculates the scores.
     * The results are logged with their occurrences and percentages.
     */
    public void startGame() {
        validateConfig();
        log.info("Number of simulations was {} using {} dice(s).", numberOfSimulations, numberOfDices);
        Map<Integer, Integer> finalResult = new TreeMap<>();

        long start = System.currentTimeMillis();

        for (int i = 0; i < numberOfSimulations; i++) {
            int score = playSingleGame();
            finalResult.merge(score, 1, Integer::sum);
        }

        long end = System.currentTimeMillis();

        finalResult.forEach((score, count) -> {
            double percentage = (count * 100.0 / numberOfSimulations);
            log.info("Total score: {} - Occurrence: {} - Percentage: {}%", score, count, String.format("%.4f", percentage));
        });

        log.info("Total simulation took {} seconds.", (end - start) / 1000.0);
    }

    /**
     * Plays a single game of the dice board game.
     * It rolls the dice, removes the lowest die or all threes, and accumulates the score until no dice are left.
     * @return the total score of the game
     */
    private int playSingleGame() {
        List<Integer> dice = rollDice(numberOfDices);
        int totalScore = 0;

        while (!dice.isEmpty()) {
            if (dice.contains(3)) {
                dice.removeIf(die -> die == 3);
            } else {
                int min = Collections.min(dice);
                dice.remove(Integer.valueOf(min));
                totalScore += min;
            }
            dice = rollDice(dice.size());
        }

        return totalScore;
    }

    /**
     * Rolls a specified number of dice and returns the results.
     * @param x the number of dice to roll
     *          The dice are rolled with faces numbered from 1 to diceFaceCount.
     * @return a list of integers representing the results of the dice rolls
     */
    public List<Integer> rollDice(int x) {
        List<Integer> results = new ArrayList<>(x);
        for (int i = 0; i < x; i++) {
            results.add(random.nextInt(diceFaceCount) + 1);
        }
        return results;
    }

    /**
     * Validates the configuration properties for the game.
     * Throws an exception if any of the properties are invalid.
     */
    private void validateConfig() {
        if (numberOfSimulations <= 0) {
            throw new DiceBoardGamePropertiesException("Invalid property config: game.count must be greater than 0");
        }
        if (numberOfDices <= 0) {
            throw new DiceBoardGamePropertiesException("Invalid property config: game.dices.count must be greater than 0");
        }
        if (diceFaceCount < 3) {
            throw new DiceBoardGamePropertiesException("Invalid property config: game.dice.faces must be at least 3 to allow value 3");
        }
    }

}
