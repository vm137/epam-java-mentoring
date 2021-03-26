package com.epam.tickets.storage;

import static com.epam.tickets.storage.CommonStorage.EVENT_KEY;
import static com.epam.tickets.storage.CommonStorage.TICKET_KEY;
import static com.epam.tickets.storage.CommonStorage.USER_KEY;

import com.epam.tickets.model.TicketInterface.Category;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.model.dto.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
          Long id = Long.parseLong(txtId, 10);

          switch (prefix) {
            case USER_KEY:
              User user = new User(id, dataLine[1], dataLine[2]);
              initialStorage.put(key, user);
              break;

            case EVENT_KEY:
              DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
              Date date = format.parse(dataLine[2]);
              Event event = new Event(id, dataLine[1], date);
              initialStorage.put(key, event);
              break;

            case TICKET_KEY:
              Long eventId = Long.parseLong(dataLine[1]);
              Long userId = Long.parseLong(dataLine[2]);
              int place = Integer.parseInt(dataLine[3]);
              Category category = Category.valueOf(dataLine[4]);
              Ticket ticket = new Ticket(id, eventId, userId, place, category);
              initialStorage.put(key, ticket);
              break;

            default:
              logger.warn("Unknown record key in initial data file: " + key);
              break;
          }
        }
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException | ParseException e) {
      logger.error("Initial data file could not be read. " + e);
      e.printStackTrace();
    }

    return initialStorage;
  }
}
