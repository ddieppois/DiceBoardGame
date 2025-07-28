# 💬 GitHub Copilot Discussion – Dice Board Game Project

This document contains a set of prompts (P:) and Copilot responses (R:) related to simulating a dice game and implementing it in Java using Spring Boot.

---

> **Prompt**:  
> Give me a function that simulates the rolling of a dice `x` times (parameter) with 6 faces (1 to 6). We need to return a list of integers of size `x` showing the result of each roll.

**Response**:  
Here's a method that simulates rolling a 6-faced dice `x` times and returns a list of results. It uses `java.util.Random` to generate numbers between 1 and 6.

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public List<Integer> rollDice(int x) {
    Random random = new Random();
    List<Integer> results = new ArrayList<>(x);
    for (int i = 0; i < x; i++) {
        results.add(random.nextInt(6) + 1);
    }
    return results;
}
```

This function returns a `List<Integer>` of size `x`, where each element represents the result of a dice roll.

---

### 🧩 P: Best Map Collection for Ordered Integer Key/Value Pairs

> **Prompt**:  
> What's the best `Map` collection to store ordered key/value (integers) combos? I'll insert combos a huge number of times (multiple thousands). Mostly for insertion.

**Response**:  
For storing ordered key/value integer pairs with efficient insertions and maintaining order:

- ✅ Use `java.util.TreeMap` if you want the **keys sorted** (natural order or custom comparator). It provides O(log n) insertion and lookup.
- ✅ Use `java.util.LinkedHashMap` if you only want to maintain **insertion order**. It provides constant-time insertion and preserves the order items were added.

**Summary**:
- For **sorted order by key**: `TreeMap<Integer, Integer>`
- For **insertion order**: `LinkedHashMap<Integer, Integer>`

---

> **Prompt**:  
> Give me a `CommandLineRunner` function that will start the `DiceBoardGameService` at startup, please.

**Response**:  
To start `DiceBoardGameService` when the application launches, define a `CommandLineRunner` bean like so:

```java
@Bean
public CommandLineRunner run(DiceBoardGameService gameService) {
    return args -> gameService.startGame();
}
```

Add this bean to your main application class. It will trigger `startGame()` automatically when your Spring Boot app starts.

---

> **Prompt**:  
> Could you read the `DiceBoardGameService` class and give me a README documentation explaining the workflow of `startGame()` based on the properties? This is the main and only class.

**Response**:
> `a: readme`

_(Note: The full README content was handled elsewhere, possibly manually generated after this response)_

---

> **Prompt**:  
> How would I show the complete percentage from the log instead of this:  
> `Total score: 27 - Occurrence: 1 - Percentage: 1.0E-4%`

**Response**:  
The percentage is shown in scientific notation (`1.0E-4%`), which means `0.0001%`.  
To display the percentage in decimal format, use `String.format("%.4f", percentage)` to enforce a fixed number of decimal places.

✅ **Example Code Fix**:
```java
double percentage = (occurrence * 100.0) / numberOfSimulations;
logger.info("Total score: {} - Occurrence: {} - Percentage: {}%",
            score,
            occurrence,
            String.format("%.4f", percentage));
```

This will log the percentage as `0.0001%` instead of using scientific notation.  
Adjust the precision (`%.4f`) as needed.

---
