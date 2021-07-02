package com.epam.concurrency.task1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

  // Map<Integer, Integer> map = new ConcurrentHashMap<>();
  Map<Integer, Integer> map = new HashMap<>();

  static AtomicInteger counter = new AtomicInteger(1);

  public void sumRace() throws InterruptedException {

    Runnable first = () -> {
      while (true) {
        map.put(counter.incrementAndGet(), counter.get());
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(counter.get());
      }
    };

    Runnable second = () -> {
      while (true) {
        int sum = 0;
        Iterator<Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
          Entry<Integer, Integer> entry = it.next();
          int key = entry.getKey();
          int value = entry.getValue();
          sum += value;
          if (value > 10) {
            map.remove(key);
          }
        }
        System.out.println("sum: " + sum);
      }
    };

    ExecutorService service = Executors.newFixedThreadPool(2);
    service.submit(first);
    service.submit(second);

    service.shutdown();
    service.awaitTermination(10, TimeUnit.SECONDS);

    System.exit(0);
  }

  public static void main(String[] args) throws InterruptedException {
    Task1 task = new Task1();
    task.sumRace();
  }
}
