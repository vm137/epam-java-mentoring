package com.epam.tickets.controllers;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.facade.BookingFacade;
import com.epam.tickets.model.dto.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UsersController {

  @Autowired
  BookingFacade facade;

  @GetMapping("/")
  public String infoPage(ModelMap model) {
    model.addAttribute("message", "User informatin page");
    return "users-template";
  }

  @GetMapping("/add")
  public String addUser(ModelMap model,
      @RequestParam(required = false, defaultValue = "name") String name,
      @RequestParam(required = false, defaultValue = "email") String email) throws InvalidUserException {

    User user = new User(name, email);
    User createdUser = facade.createUser(user);
    model.addAttribute("message", "User created: " + createdUser.toString());
    return "users-create-template";
  }

  @GetMapping("/getById")
  public String getUserById(ModelMap model, @RequestParam Long id) throws InvalidUserException {
    User user = facade.getUserById(id);
    model.addAttribute("message", "User found: " + user.toString());
    return "users-template";
  }

  @GetMapping("/getByEmail")
  public String getUserByEmail(ModelMap model, @RequestParam String email) throws InvalidUserException {
    User user = facade.getUserByEmail(email);
    model.addAttribute("message", "User found: " + user.toString());
    return "users-template";
  }

  @GetMapping("/getByName")
  public String getUsersByName(ModelMap model,
      @RequestParam String name,
      @RequestParam(required = false, defaultValue = "10") int pageSize,
      @RequestParam(required = false, defaultValue = "0") int pageNum) {

    List<User> usersByName = facade.getUsersByName(name, pageSize, pageNum);
    String message = "Users found by name: " + usersByName;
    model.addAttribute("message", message);
    return "users-template";
  }

  @GetMapping("/update")
  public String updaetUser(ModelMap model,
      @RequestParam Long userId,
      @RequestParam String userName,
      @RequestParam String userEmail) throws InvalidUserException {
    User user = new User(userId, userName, userEmail);
    facade.updateUser(user);
    model.addAttribute("message", "User is updated");
    return "users-template";
  }

  @GetMapping("/delete")
  public String deleteUser(ModelMap model, @RequestParam Long userId) throws InvalidUserException {
    facade.deleteUserById(userId);
    model.addAttribute("message", "User deleted");
    return "users-template";
  }
}
