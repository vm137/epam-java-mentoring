package com.epam.concurrency.task4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingObjectPool {

  private BlockingQueue<Object> pool;
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
  }

  /**
   * Gets object from pool or blocks if pool is empty
   * @return object from pool
   */
  public Object get() {
    if (pool.size() == 0) {
      try {
        notEmpty.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    Object object = pool.remove();
    notFull.notifyAll();
    return object;
  }

  /**
   * Puts object to pool or blocks if pool is full
   * @param object to be taken back to pool
   */
  public synchronized void put(Object object) {
    while (pool.size() == max) {
      try {
        notFull.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    pool.add(object);
    notEmpty.notifyAll();
  }
}
