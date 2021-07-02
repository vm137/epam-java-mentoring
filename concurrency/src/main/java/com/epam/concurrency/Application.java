package com.epam.concurrency;

import com.epam.concurrency.task1.Task1;

public class Application {

  public static void main(String[] args) throws InterruptedException {

    Task1 task1 = new Task1();
    task1.sumRace();
  }
}
