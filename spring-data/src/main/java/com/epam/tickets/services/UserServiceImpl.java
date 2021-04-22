package com.epam.tickets.services;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.User;
import com.epam.tickets.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public User createUser(String name, String email) {
    User user = new User(name, email);
    return userRepository.save(user);
  }

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  @Override
  public List<User> getUsersByName(String name) {
    return userRepository.findByName(name);
  }

  @Override
  public void updateUser(Long id, String name, String email) {
    User user = getUserById(id);
    if (user != null) {
      if (name != null) {
        user.setName(name);
      }
      if (email != null) {
        user.setEmail(email);
      }
      userRepository.save(user);
    } else {
      throw new InvalidUserException("User with id: " + id + " can't be updated because it's not found.");
    }
  }

  @Override
  public void deleteUserById(Long id) {
    User user = getUserById(id);
    if (user != null) {
      userRepository.delete(user);
    } else {
      throw new InvalidUserException("User with id: " + id + " can't be deleted because it's not found.");
    }
  }
}
