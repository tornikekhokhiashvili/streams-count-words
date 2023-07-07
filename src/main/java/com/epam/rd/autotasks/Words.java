package com.epam.rd.autotasks;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {
//        Map<String, Integer> wordCounts = new HashMap<>();

        // Split lines into words and create a list of words to count
        Map<String, Long> wordCounts = lines.stream()
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .map(word -> word.replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase())
                .filter(word -> word.length() >= 4)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

//        // Count word occurrences
//        wordsToCount.forEach(word -> wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1));

        // Filter out words with frequency less than 10
        Map<String, Long> filteredWordCounts = wordCounts.entrySet().stream()
                .filter(entry -> entry.getValue() >= 10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Sort the word counts by amount and then alphabetically
        List<Map.Entry<String, Long>> sortedWordCounts = new ArrayList<>(filteredWordCounts.entrySet());
        sortedWordCounts.sort(Comparator.comparing(Map.Entry<String, Long>::getValue).reversed().thenComparing(Map.Entry::getKey));

        String statistics = sortedWordCounts.stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .reduce((a, b) -> a + "\n" + b)
                .orElse("");

        return statistics;
    }
}
