package com.epam.concurrency.task4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Task 4
 *
 * Create simple object pool with support for multithreading environment.
 * No extra inheritance, polymorphism or generics needed here, just implementation of simple class.
 *
 * More details in /docs/task-description.md
 */

public class BlockingObjectPool {

  private final BlockingQueue<Item> pool;

  /**
   * Creates filled pool of passed size
   * @param max size of the pool
   */
  public BlockingObjectPool(int max) {
    pool = new ArrayBlockingQueue<>(max);
    for (int i = 0; i < max; i++) {
      pool.add(new Item(i));
    }
  }

  /**
   * Gets item from pool or blocks thread if pool is empty
   * @return item from the pool
   */
  public synchronized Item get() {
    if (pool.size() == 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
        throw new RuntimeException("Error. Can't block the thread while pool is empty.");
      }
    }
    Item item = pool.remove();
    notifyAll();
    return item;
  }

  /**
   * Puts item to pool
   * @param item to put back
   */
  public synchronized void put(Item item) {
    pool.add(item);
    notifyAll();
  }
}
