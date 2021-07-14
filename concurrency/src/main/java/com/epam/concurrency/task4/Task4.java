package com.epam.concurrency.task4;

public class Task4 {

  public static void main(String[] args) {

    BlockingObjectPool pool = new BlockingObjectPool(10);

    final Runnable producer = () -> {
      while (true) {
        Item item = pool.get();
        process(item);
        try {
          Thread.sleep(getRandomNumber(500, 3000));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        pool.put(item);
      }
    };
    new Thread(producer).start();
    new Thread(producer).start();
    new Thread(producer).start();
  }

  private static void process(Item item) {
    System.out.println("Log. id taken: " + item.getId());
  }

  public static int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }
}
