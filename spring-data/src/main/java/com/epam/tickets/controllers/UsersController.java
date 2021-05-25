package com.epam.tickets.controllers;

import com.epam.tickets.model.User;
import com.epam.tickets.services.UserAccountService;
import com.epam.tickets.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("users")
public class UsersController {

  @Autowired
  UserService userService;

  @Autowired
  UserAccountService userAccountService;

  @GetMapping("/")
  public String usersHomePage() {
    return "users/index";
  }

  @GetMapping("/add")
  public String addUser(ModelMap model,
      @RequestParam String name,
      @RequestParam String email) {
    User user = userService.createUser(name, email);
    model.addAttribute("message", "User created.");
    model.addAttribute("user", user);
    return "users/show-user";
  }

  @GetMapping("/{id}")
  public String getUserById(ModelMap model, @PathVariable Long id) {
    User user = userService.getUserById(id);
    model.addAttribute("message", "User found by id: " + id);
    model.addAttribute("user", user);
    return "users/show-user";
  }

  @PostMapping("/{id}")
  public String refillAccount(ModelMap model,
      @PathVariable Long id,
      @RequestParam Integer amount) {
    userAccountService.refill(id, amount);
    model.addAttribute("message", "User account refilled. id: " + id);
    return "info/show-info";
  }

  @PatchMapping("/{id}")
  public String updateUser(ModelMap model,
      @PathVariable Long id,
      @RequestParam String userName,
      @RequestParam String userEmail) {
    userService.updateUser(id, userName, userEmail);
    model.addAttribute("message", "User with id:" + id + " is updated.");
    return "info/show-info";
  }

  @DeleteMapping("/{id}")
  public String deleteUser(ModelMap model, @PathVariable Long id) {
    userService.deleteUserById(id);
    model.addAttribute("message", "User with id: " + id + " deleted.");
    return "info/show-info";
  }
}
