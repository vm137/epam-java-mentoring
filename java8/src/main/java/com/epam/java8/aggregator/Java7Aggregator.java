package com.epam.java8.aggregator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.util.Pair;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        int sum = 0;
        for (Integer n : numbers) {
            sum += n;
        }
        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Map<String, Long> freqMap = new HashMap<>();
        for (String word : words) {
            if (freqMap.containsKey(word)) {
                long curNum = freqMap.get(word);
                freqMap.put(word, ++curNum);
            } else {
                freqMap.put(word, 1L);
            }
        }

        List<Pair<String, Long>> resultList = new ArrayList<>();
        Iterator<Entry<String, Long>> it = freqMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Long> entry = it.next();
            Pair<String, Long> pair = new Pair<>(entry.getKey(), entry.getValue());
            resultList.add(pair);
            it.remove();
        }

        Comparator<Pair<String, Long>> comp = new Comparator<Pair<String, Long>>() {
            @Override
            public int compare(Pair<String, Long> a, Pair<String, Long> b) {
                if (a.getValue() > b.getValue()) {
                    return -1;
                } else if (a.getValue() < b.getValue()) {
                    return 1;
                } else {
                    return Integer.compare(a.getKey().length(), b.getKey().length());
                }
            }
        };
        resultList.sort(comp);

        return resultList;
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }
}
