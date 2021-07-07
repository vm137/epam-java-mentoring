package com.epam.concurrency.task4;

public class Task4 {
  public static void main(String[] args) {

    BlockingObjectPool pool = new BlockingObjectPool(10);

    final Runnable producer = () -> {
      while (true) {
        Item item = pool.get();
        process(item);
        pool.put(item);
      }
    };
    new Thread(producer).start();
    new Thread(producer).start();
    new Thread(producer).start();
  }

  private static void process(Item item) {
    System.out.println("Log: " + item.getId());
  }
}
