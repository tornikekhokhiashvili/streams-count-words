package com.epam.rd.autotasks;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Words {
    public String countWords(List<String> lines) {
        Map<String, Integer> wordCounts = lines.stream()
                .flatMap(line -> Pattern.compile("[ .,‘’(“—/:?!”;*)'\"-]|\\s+")
                        .splitAsStream(line))
                .map(String::toLowerCase)
                .filter(word -> word.length() >= 4)
                .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));

        wordCounts.entrySet().removeIf(stringIntegerEntry -> stringIntegerEntry.getValue() < 10);

        List<Map.Entry<String, Integer>> sortedEntries = wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toList());


        return sortedEntries.stream()
                .map(entry -> {
                    String entryString = entry.getKey() + " - " + entry.getValue();
                    wordCounts.put(entry.getKey(), entry.getValue());
                    return entryString;
                })
                .collect(Collectors.joining("\n"));
    }
}
