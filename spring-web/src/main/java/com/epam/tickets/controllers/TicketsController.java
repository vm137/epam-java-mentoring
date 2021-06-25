package com.epam.tickets.controllers;

import com.epam.tickets.exceptions.InvalidEventException;
import com.epam.tickets.exceptions.InvalidTicketException;
import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.facade.BookingFacade;
import com.epam.tickets.model.Event;
import com.epam.tickets.model.Ticket;
import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.User;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tickets")
public class TicketsController {

  @Autowired
  private BookingFacade facade;

  private static final Logger logger = LogManager.getLogger(TicketsController.class);

  @GetMapping("/")
  public String getTickets(ModelMap model,
      @RequestParam(required = false) Long userId,
      @RequestParam(required = false) Long eventId)
      throws InvalidUserException, InvalidEventException {
    if (userId != null) {
      User user = facade.getUserById(userId);
      List<Ticket> bookedTickets = facade.getBookedTickets(user, 10, 0);
      model.addAttribute("message", "Requested tickets:");
      model.addAttribute("tickets", bookedTickets);
      return "tickets/show-tickets";
    } else if (eventId != null) {
      Event event = facade.getEventById(eventId);
      List<Ticket> bookedTickets = facade.getBookedTickets(event, 10, 0);
      model.addAttribute("message", "Requested tickets:");
      model.addAttribute("tickets", bookedTickets);
      return "tickets/show-tickets";
    } else {
      model.addAttribute("message", "Tickets not found.");
      return "info/show-info";
    }
  }

  @GetMapping("/book")
  public String bookTicket(ModelMap model,
      @RequestParam Long userId,
      @RequestParam Long eventId,
      @RequestParam int place,
      @RequestParam Category category) {
    Ticket ticket = facade.bookTicket(userId, eventId, place, category);
    model.addAttribute("message", "Ticket created");
    model.addAttribute("ticket", ticket);
    return "/tickets/show-ticket";
  }

  @DeleteMapping("/{id}")
  public String deleteEvent(ModelMap model,
      @PathVariable Long id) throws InvalidTicketException {
    facade.cancelTicket(id);
    logger.info(String.format("Ticket with id:%d is deleted.", id));
    model.addAttribute("message", "Ticket deleted");
    return "info/show-info";
  }
}
