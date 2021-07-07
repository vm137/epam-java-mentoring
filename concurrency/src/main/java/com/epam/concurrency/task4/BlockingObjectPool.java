package com.epam.concurrency.task4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Task 4
 *
 * Create simple object pool with support for multithreading environment. No extra inheritance,
 * polymorphism or generics needed here, just implementation of simple class.
 *
 * Please look for details in task/descr.md
 */

public class BlockingObjectPool {

  private final BlockingQueue<Item> pool;
  private final int max;
  private final Object notEmpty = new Object();
  private final Object notFull = new Object();

  /**
   * Creates filled pool of passed size
   * @param size of pool
   */
  public BlockingObjectPool(int size) {
    this.max = size;
    pool = new ArrayBlockingQueue<>(max);
    for (int i = 0; i < max; i++) {
      pool.add(new Item(i));
    }
  }

  /**
   * Gets item from pool or blocks if pool is empty
   * @return item from pool
   */
  public synchronized Item get() {
    if (pool.size() == 0) {
      try {
        notEmpty.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    Item item = pool.remove();
    notFull.notifyAll();
    return item;
  }

  /**
   * Puts item to pool or blocks if pool is full
   * @param item to put back to pool
   */
  public synchronized void put(Item item) {
    while (pool.size() == max) {
      try {
        notFull.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    pool.add(item);
    notEmpty.notifyAll();
  }
}
