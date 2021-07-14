package com.epam.concurrency.task3;

/**
 * Task 3 - Where’s Your Bus, Dude?
 *
 * Implement message bus using Producer-Consumer pattern.
 *
 * Implement asynchronous message bus. Do not use queue implementations from java.util.concurrent.
 * Implement producer, which will generate and post randomly messages to the queue.
 * Implement consumer, which will consume messages on specific topic and log to the console message payload.
 * (Optional) Application should create several consumers and producers that run in parallel.
 */

public class Task3 {

  public static void main(String[] args) {

    MyBlockingQueue<Item> queue = new MyBlockingQueue<>(10);

    // Producer
    final Runnable producer = () -> {
      while (true) {
        queue.put(createItem());
      }
    };
    new Thread(producer).start();
    new Thread(producer).start();

    // Consumer
    final Runnable consumer = () -> {
      while (true) {
        Item item = queue.take();
        process(item);
      }
    };
    new Thread(consumer).start();
    new Thread(consumer).start();
  }

  private static Item createItem() {
    return new Item(getRandomNumber(1, 100));
  }

  private static void process(Item item) {
    System.out.println("Log. Item from Queue: " + item.getId());
  }

  public static int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }
}
