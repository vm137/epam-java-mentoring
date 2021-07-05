package com.epam.concurrency.task3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {

  private final Queue<E> queue;
  private final ReentrantLock lock = new ReentrantLock(true);
  private final Condition notEmpty = lock.newCondition();
  private final Condition notFull = lock.newCondition();
  private int max = 16;

  public MyBlockingQueue(int size) {
    queue = new LinkedList<>();
    this.max = size;
  }

  public void put(E e) {
    lock.lock();
    try {
      if (queue.size() >= max) {
        try {
          notFull.wait();
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
      }
      queue.add(e);
      notEmpty.signalAll();
    } finally {
      lock.unlock();
    }
  }

  public E take() {
    lock.lock();
    try {
      while (queue.size() == 0) {
        try {
          notEmpty.wait();
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
      }
      E item = queue.remove();
      notFull.signalAll();
      return item;
    } finally {
      lock.unlock();
    }
  }
}
