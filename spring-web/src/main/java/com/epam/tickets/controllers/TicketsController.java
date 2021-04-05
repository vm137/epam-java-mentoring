package com.epam.tickets.controllers;

import com.epam.tickets.exceptions.InvalidTicketException;
import com.epam.tickets.facade.BookingFacade;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.model.dto.TicketI.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ticket")
public class TicketsController {

  @Autowired
  BookingFacade facade;

  @GetMapping("/add")
  public String createTicket(ModelMap model,
      @RequestParam Long userId,
      @RequestParam Long eventId,
      @RequestParam int place,
      @RequestParam Category category) {
    Ticket ticket = facade.bookTicket(userId, eventId, place, category);
    model.addAttribute("message", "Ticket created");
    model.addAttribute("ticket", ticket);
    return "/tickets/show-ticket";
  }

  @GetMapping("/getById")
  public String cancelTicket(ModelMap model, @RequestParam Long id) throws InvalidTicketException {
    facade.cancelTicket(id);
    model.addAttribute("message", "Ticket with id: " + id + " canceled.");
    return "/tickets/show-ticket";
  }
}
