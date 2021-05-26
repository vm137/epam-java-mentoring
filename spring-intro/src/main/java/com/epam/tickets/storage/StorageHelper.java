package com.epam.tickets.storage;

import static com.epam.tickets.storage.CommonStorage.EVENT_KEY;
import static com.epam.tickets.storage.CommonStorage.TICKET_KEY;
import static com.epam.tickets.storage.CommonStorage.USER_KEY;

import com.epam.tickets.model.Event;
import com.epam.tickets.model.EventImpl;
import com.epam.tickets.model.Ticket;
import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.TicketImpl;
import com.epam.tickets.model.User;
import com.epam.tickets.model.UserImpl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
              User user = new UserImpl(id, dataLine[1], dataLine[2]);
              initialStorage.put(key, user);
              break;

            case EVENT_KEY:
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
              LocalDateTime date = LocalDateTime.parse(dataLine[2], formatter);
              Event event = new EventImpl(id, dataLine[1], date);
              initialStorage.put(key, event);
              break;

            case TICKET_KEY:
              Long eventId = Long.parseLong(dataLine[1]);
              Long userId = Long.parseLong(dataLine[2]);
              int place = Integer.parseInt(dataLine[3]);
              Category category = Category.valueOf(dataLine[4]);
              Ticket ticket = new TicketImpl(id, eventId, userId, place, category);
              initialStorage.put(key, ticket);
              break;

            default:
              logger.error("Unknown record key in initial data file: " + key);
              break;
          }
        }
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      logger.error("Initial data file could not be read. " + e);
      e.printStackTrace();
    }

    return initialStorage;
  }
}
