package com.epam.java8.aggregator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.util.Pair;

public class Java8Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return numbers.stream()
            .reduce(0, Integer::sum);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {

        Set<Entry<String, Integer>> entries = words.stream()
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(w -> w, Collectors.summingInt(w -> 1)))
            .entrySet();

        return entries
            .stream()
            .map(entry -> new Pair<>(entry.getKey(), Long.valueOf(entry.getValue())))
            .collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {

        List<String> wordsLower = words.stream()
             .map(String::toLowerCase)
             .collect(Collectors.toList());

        return wordsLower.stream()
            .filter(i -> Collections.frequency(wordsLower, i) > 1)
            .distinct()
            .map(String::toUpperCase)
            .sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
            .limit(limit)
            .collect(Collectors.toList());
    }
}
