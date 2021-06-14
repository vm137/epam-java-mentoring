package com.epam.tickets.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.tickets.model.dto.EventDto;
import com.epam.tickets.services.EventService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class EventsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  EventService eventService;


  @Test
  public void getAllEvents() throws Exception {
    this.mockMvc.perform(get("/events/")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  public void createEvent() throws Exception {
    String testDate = "01/03/2020 12:00";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.parse(testDate, formatter);
    when(eventService.createEvent(any(String.class), any(LocalDateTime.class))).thenReturn(new EventDto("Event1", date));

    this.mockMvc.perform(post("/events/add?title=Event1&date=" + testDate))
        .andDo(print()).andExpect(status().isOk());
  }

  @Test
  public void deleteEvent() throws Exception {
    this.mockMvc.perform(delete("/events/1")).andDo(print()).andExpect(status().isOk());
  }
}
