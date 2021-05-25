package com.epam.tickets.controllers;

import com.epam.tickets.exceptions.InvalidEventException;
import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.facade.BookingFacade;
import com.epam.tickets.model.Event;
import com.epam.tickets.services.EventService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/events")
public class EventsController {

  @Autowired
  private BookingFacade facade;

  @Autowired
  private EventService eventService;

  private static final Logger logger = LogManager.getLogger(EventsController.class);

  @ExceptionHandler({InvalidEventException.class, InvalidUserException.class})
  public ModelAndView handleException(HttpServletRequest req, Exception ex) {
    logger.info("InvalidEventException.class");
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", ex);
    mav.addObject("message", "Events Controller Error");
    mav.addObject("url", req.getRequestURL());
    mav.setViewName("error");
    return mav;
  }

  @GetMapping("/")
  public String getEvents(ModelMap model,
      @RequestParam(required = false, defaultValue = "") String title,
      @RequestParam(required = false, defaultValue = "") String date) {
    List<Event> events;
    if (StringUtils.isNotBlank(title)) {
      events = facade.getEventsByTitle(title, 10, 0);
      model.addAttribute("message", "Events by title: " + title);
    } else if (StringUtils.isNotBlank(date)) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
      LocalDateTime dateForSearch = LocalDateTime.parse("12/02/2020 12:00", formatter);
      events = facade.getEventsForDay(dateForSearch, 10, 0);
      model.addAttribute("message", "Events by date: " + date);
    } else {
      events= facade.getAllEvents();
      model.addAttribute("message", "All Events");
    }
    model.addAttribute("events", events);
    return "events/show-events";
  }

  @GetMapping("/{id}")
  public String showEvent(ModelMap model, @PathVariable Long id) throws InvalidEventException {
    model.addAttribute("message", "Event Information");
    Event event = facade.getEventById(id);
    model.addAttribute("event", event);
    return "events/show-event";
  }

  @PostMapping("/add")
  public String createEvent(ModelMap model,
      @RequestParam String title,
      @RequestParam String date) {
    Event event = eventService.createEvent(title, parseDate(date));
    Event eventCreated = facade.createEvent(event);
    String msg = String.format("Event with id:%d, title: '%s', date: %s is created.",
        eventCreated.getId(), eventCreated.getTitle(), eventCreated.getDate().toString());
    logger.info(msg);
    model.addAttribute("message", "Event created");
    model.addAttribute("event", eventCreated);
    return "events/show-event";
  }

  @PatchMapping("/{id}")
  public String updateEvent(ModelMap model,
      @PathVariable Long id,
      @RequestParam String title,
      @RequestParam String date) throws InvalidEventException {
    Event event = new Event(id, title, parseDate(date));
    facade.updateEvent(event);
    logger.info(String.format("Event with id:%d is updated.", id));
    model.addAttribute("message", "Event updated");
    model.addAttribute("event", event);
    return "events/show-event";
  }

    @DeleteMapping("/{id}")
    public String deleteEvent(ModelMap model,
        @PathVariable Long id) {
      facade.deleteEventById(id);
      logger.info(String.format("Event with id:%d is deleted.", id));
      model.addAttribute("message", "Event deleted");
      return "info/show-info";
    }

  private LocalDateTime parseDate(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    return LocalDateTime.parse(date, formatter);
  }
}
