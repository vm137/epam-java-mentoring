package com.epam.tickets.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogClass {

  private static final Logger logger = LogManager.getLogger(LogClass.class);
  
  public static void main(String[] args) {
    logger.trace("Trace Message!");
    logger.debug("Debug Message!");
    logger.info("Info Message!");
    logger.warn("Warn Message!");
    logger.error("Error Message!");
    logger.fatal("Fatal Message!");
  }
}
