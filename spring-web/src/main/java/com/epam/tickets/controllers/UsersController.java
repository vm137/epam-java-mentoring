package com.epam.tickets.controllers;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.facade.BookingFacade;
import com.epam.tickets.model.dto.User;
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

  @GetMapping
  public String index(ModelMap model, @RequestParam String id, @RequestParam String id2) {
    model.addAttribute("message", id + ":" + id2);

    return "user-template";
  }

  @GetMapping("/adduser")
  public String addUser(ModelMap model, @RequestParam String name, @RequestParam String email) throws InvalidUserException {
    User user = new User(name, email);
    facade.createUser(user);
    model.addAttribute("message", name + ":" + email);
    return "user-template";
  }

  @GetMapping("/all")
  public String getAllUsers(ModelMap model) {
    model.addAttribute("message", "Users-all");
    return "user-template";
  }
}
