package com.epam.tickets.controllers;

import com.epam.tickets.model.dto.EventDto;
import com.epam.tickets.services.EventService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("events")
public class EventsController {

  @Autowired
  EventService eventService;

  @GetMapping("/")
  public String getAllEvents(ModelMap model) {
    List<EventDto> events = eventService.getAllEvents();
    model.addAttribute("message", "All Events");
    model.addAttribute("events", events);
    return "events/show-events";
  }

  @PostMapping("/add")
  public String createEvent(ModelMap model,
      @RequestParam String title,
      @RequestParam String date) {
    EventDto eventDto = eventService.createEvent(title, parseDate(date));
    String msg = String.format("Event with id:%d, title: '%s', date: %s is created.",
        eventDto.getId(), eventDto.getTitle(), eventDto.getDate().toString());
    model.addAttribute("message", msg);
    model.addAttribute("event", eventDto);
    return "events/show-event";
  }

  @GetMapping("/{id}")
  public String getEvent(ModelMap model, @PathVariable Long id) {
    model.addAttribute("message", "Event Information");
    EventDto eventDto = eventService.getEventById(id);
    model.addAttribute("event", eventDto);
    return "events/show-event";
  }

  @PatchMapping("/{id}")
  public String updateEvent(ModelMap model,
      @PathVariable Long id,
      @RequestParam String title,
      @RequestParam String date) {
    EventDto eventDto = eventService.getEventById(id);
    if (StringUtils.isNotBlank(title)) {
      eventDto.setTitle(title);
    }
    if (StringUtils.isNotBlank(date)) {
      eventDto.setDate(parseDate(date));
    }
    eventService.updateEvent(eventDto);
    String msg = String.format("Event with id:%d is updated.", id);
    model.addAttribute("message", msg);
    model.addAttribute("event", eventDto);
    return "events/show-event";
  }

  @DeleteMapping("/{id}")
  public String deleteEvent(ModelMap model, @PathVariable Long id) {
    EventDto eventDto = eventService.getEventById(id);
    eventService.delete(eventDto);
    model.addAttribute("message", "Event deleted");
    return "info/show-info";
  }

  private LocalDateTime parseDate(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    return LocalDateTime.parse(date, formatter);
  }
}
