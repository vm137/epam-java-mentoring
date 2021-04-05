package com.epam.tickets.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TicketsController {

  @GetMapping("/ticket")
  public String index(ModelMap model) {
    model.addAttribute("message", "Spring MVC XML Config Example");
    return "tickets-template";
  }
}
