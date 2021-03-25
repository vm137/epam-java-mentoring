package com.epam.tickets.storage;

import static com.epam.tickets.storage.CommonStorage.EVENT_KEY;
import static com.epam.tickets.storage.CommonStorage.TICKET_KEY;
import static com.epam.tickets.storage.CommonStorage.USER_KEY;

import com.epam.tickets.model.dto.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StorageHelper {

  private static final Logger logger = LogManager.getLogger(StorageHelper.class);

  public static Map<String, Object> readInitialStorage(String fileName) {
    Map<String, Object> initialStorage = new HashMap<>();

    BufferedReader reader;
    try {
      // file: spring-intro/storage/initial.data
      reader = new BufferedReader(new FileReader(fileName));
      String line = reader.readLine();
      while (line != null) {

        String[] dataLine = line.split(";");
        if (dataLine.length >= 3) {
          String key = dataLine[0];
          String[] split = key.split(":");
          String prefix = split[0] + ":";
          String txtId = split[1];
          switch (prefix) {
            case USER_KEY:
              Long id = Long.parseLong(txtId, 10);
              User user = new User(id, dataLine[1], dataLine[2]);
              initialStorage.put(key, user);
              break;

            case EVENT_KEY:
              break;

            case TICKET_KEY:
              break;

            default:
              logger.warn("Unknown record key in initial data file: " + key);
              break;
          }
        }
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException i) {
      logger.error("Initial data file could not be read.");
      i.printStackTrace();
    }

    return initialStorage;
  }
}
