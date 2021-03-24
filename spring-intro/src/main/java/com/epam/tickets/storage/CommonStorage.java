package com.epam.tickets.storage;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.User;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class CommonStorage {

  Map<String, Object> storage = new HashMap<>();

  AtomicLong userCounter = new AtomicLong(10);

  public User addUser(User user) throws InvalidUserException {
    Long id = userCounter.incrementAndGet();
    user.setId(id);
    String userKey = "user:" + id;

    // check for unique email
    boolean emailExists = storage.entrySet().stream()
        .filter(entry -> entry.getKey().startsWith("user:"))
        .anyMatch(entry -> "s".equalsIgnoreCase(((User) entry.getValue()).getEmail()));
    if (emailExists) {
      // log error
      throw new InvalidUserException("User with the same email already exists.");
    }
    storage.put(userKey, user);
    return (User) storage.get(userKey);
  }

  public User update(User user) {
    Long id = user.getId();
    // TODO: check if user exists, else throw exception
    return (User) storage.put("user:" + id, user);
  }

  public User getUserById(Long id) {
    return (User) storage.get("user:" + id);
  }

  public boolean removeUser(Long id) {
    User removedUser = (User) storage.remove("user:" + id);
    return removedUser != null;
  }
}
