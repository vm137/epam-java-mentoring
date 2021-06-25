package com.epam.tickets.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.dto.TicketDto;
import com.epam.tickets.services.TicketService;
import com.epam.tickets.services.UserAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TicketService ticketService;

  @MockBean
  private UserAccountService userAccountService;

  @Test
  public void whenDoQueryAllTickets_thenCheckOkResponseAndContentType() throws Exception {
    this.mockMvc
        .perform(get("/tickets/"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void givenTicket_whenDoAddTicket_thenCheckExpectedJson () throws Exception {
    TicketDto ticketDto = new TicketDto(1L, 1L, 1, Category.STANDARD);
    when(ticketService.bookTicket(1L, 1L, 1, Category.STANDARD, 100)).thenReturn(ticketDto);
    String expectedJson = "{\"id\":null,\"eventId\":1,\"userId\":1,\"place\":1,\"category\":\"STANDARD\",\"price\":0}";

    MvcResult result = this.mockMvc
        .perform(post("/tickets/add?eventId=1&userId=1&place=1&category=STANDARD&price=100"))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

    String content = result.getResponse().getContentAsString();
    assertEquals(content, expectedJson);
  }

  @Test
  public void whenDoGetTicket_thenCheckOkResponse() throws Exception {
    this.mockMvc
        .perform(get("/tickets/1"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void whenDoGetBookedTickets_thenCheckOkResponseAndContentType() throws Exception {
    this.mockMvc
        .perform(get("/tickets/getBooked"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void whenDoDeleteTicket_thenCheckOkResponse() throws Exception {
    this.mockMvc
        .perform(delete("/tickets/1"))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
