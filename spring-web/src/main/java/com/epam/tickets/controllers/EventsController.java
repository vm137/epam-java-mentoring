package com.epam.tickets.controllers;

import com.epam.tickets.facade.BookingFacade;
import com.epam.tickets.model.dto.Event;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/event")
public class EventsController {

  @Autowired
  BookingFacade facade;

  @GetMapping("/")
  public String infoPage(ModelMap model) {
    model.addAttribute("message", "Event Information Page");
    return "events/events-info";
  }

  @GetMapping("/add")
  public String createEvent(ModelMap model, @RequestParam String title, String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime dateParsed = LocalDateTime.parse(date, formatter);
    Event event = new Event(title, dateParsed);
    Event eventCreated = facade.createEvent(event);
    model.addAttribute("message", "Event created");
    model.addAttribute("event", eventCreated);
    return "events/show-event";
  }

  @GetMapping("/getByTitle")
  public String getEventByTitle(ModelMap model,
      @RequestParam String title,
      @RequestParam(required = false, defaultValue = "10") int pageSize,
      @RequestParam(required = false, defaultValue = "1") int pageNum) {
    List<Event> events = facade.getEventsByTitle(title, pageSize, pageNum);
    model.addAttribute("message", "Event Information Page");
    model.addAttribute("events", events);
    return "events/show-events";
  }
}
