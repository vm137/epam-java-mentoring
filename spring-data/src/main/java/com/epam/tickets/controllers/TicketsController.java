package com.epam.tickets.controllers;

import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.dto.EventDto;
import com.epam.tickets.model.dto.TicketDto;
import com.epam.tickets.model.dto.UserDto;
import com.epam.tickets.services.EventService;
import com.epam.tickets.services.TicketService;
import com.epam.tickets.services.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @GetMapping("/")
  public ResponseEntity<List<TicketDto>> getAllTickets(ModelMap model) {
    List<TicketDto> tickets = ticketService.getAllTickets();
    return new ResponseEntity<>(tickets, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<TicketDto> addTicket(
      @RequestParam Long eventId,
      @RequestParam Long userId,
      @RequestParam int place,
      @RequestParam Category category,
      @RequestParam int price) {
    TicketDto ticketDto = ticketService.bookTicket(eventId, userId, place, category, price);
    return new ResponseEntity<>(ticketDto, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TicketDto> getTicket(@PathVariable Long id) {
    TicketDto ticketDto = ticketService.getTicketById(id);
    return new ResponseEntity<>(ticketDto, HttpStatus.OK);
  }

  @GetMapping("/getBooked")
  public ResponseEntity<List<TicketDto>> getBookedTickets(
      @RequestParam(required = false) Long userId,
      @RequestParam(required = false) Long eventId) {
    List<TicketDto> tickets = new ArrayList<>();
    if (userId != null) {
      UserDto userDto = userService.getUserById(userId);
      tickets.addAll(ticketService.getBookedTickets(userDto));
    }
    if (eventId != null) {
      EventDto eventDto = eventService.getEventById(eventId);
      tickets.addAll(ticketService.getBookedTickets(eventDto));
    }
    return  new ResponseEntity<>(tickets, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public String cancelTicket(ModelMap model, @PathVariable Long id) {
    ticketService.cancelTicket(id);
    model.addAttribute("message", "Ticket deleted.");
    return "info/show-info";
  }
}
