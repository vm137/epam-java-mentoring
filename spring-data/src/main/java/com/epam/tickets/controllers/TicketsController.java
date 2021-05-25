package com.epam.tickets.controllers;

import com.epam.tickets.model.Event;
import com.epam.tickets.model.Ticket;
import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.User;
import com.epam.tickets.services.EventService;
import com.epam.tickets.services.TicketService;
import com.epam.tickets.services.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("tickets")
public class TicketsController {

  @Autowired
  UserService userService;

  @Autowired
  EventService eventService;

  @Autowired
  TicketService ticketService;

  @PostMapping("/add")
  public String addTicket(ModelMap model,
      @RequestParam Long eventId,
      @RequestParam Long userId,
      @RequestParam int place,
      @RequestParam Category category,
      @RequestParam int price) {
    Ticket ticket = ticketService.bookTicket(eventId, userId, place, category, price);
    model.addAttribute("message", "Ticket is created");
    model.addAttribute("ticket", ticket);
    return "tickets/show-ticket";
  }

  @GetMapping("/{id}")
  public String getTicket(ModelMap model, @PathVariable Long id) {
    model.addAttribute("message", "Ticket Information");
    Ticket ticket = ticketService.getTicketById(id);
    model.addAttribute("ticket", ticket);
    return "tickets/show-ticket";
  }

  @GetMapping("/all")
  public String getAllTickets(ModelMap model) {
    model.addAttribute("message", "All Tickets");
    List<Ticket> tickets = ticketService.getAllTickets();
    model.addAttribute("ticket", tickets);
    return "tickets/show-tickets";
  }

  @GetMapping("/getBooked")
  public String getBookedTickets(ModelMap model,
      @RequestParam(required = false) Long userId,
      @RequestParam(required = false) Long eventId) {
    List<Ticket> tickets = new ArrayList<>();
    if (userId != null) {
      User user = userService.getUserById(userId);
      tickets.addAll(ticketService.getBookedTickets(user));
    }
    if (eventId != null) {
      Event event = eventService.getEventById(eventId);
      tickets.addAll(ticketService.getBookedTickets(event));
    }
    model.addAttribute("message", "Booked Tickets");
    model.addAttribute("tickets", tickets);
    return "tickets/show-tickets";
  }

  @DeleteMapping("/{id}")
  public String cancelTicket(ModelMap model, @PathVariable Long id) {
    ticketService.cancelTicket(id);
    model.addAttribute("message", "Ticket deleted.");
    return "info/show-info";
  }
}
