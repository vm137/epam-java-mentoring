package com.epam.tickets.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.epam.tickets.model.Ticket;
import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.dto.TicketDto;
import com.epam.tickets.model.mappers.TicketMapper;
import com.epam.tickets.repositories.TicketsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TicketServiceImplTest {

  @Autowired
  TicketService ticketService;

  @MockBean
  TicketsRepository ticketsRepository;

  @MockBean
  UserAccountService userAccountService;

  Ticket mockTicket;

  @BeforeEach
  void init(){
    mockTicket = new Ticket(1L, 1L, 1, Category.STANDARD);
  }

  @Test
  public void bookTicket() {
    when(ticketsRepository.save(any(Ticket.class))).thenReturn(mockTicket);

    TicketDto bookedTicket = ticketService.bookTicket(1L, 1L, 1, Category.STANDARD, 100);
    assertEquals(bookedTicket, TicketMapper.INSTANCE.ticketToTicketDto(mockTicket));
  }

  @Test
  public void getTicketById() {
    when(ticketsRepository.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(mockTicket));

    TicketDto ticket = ticketService.getTicketById(1L);
    assertEquals(ticket, TicketMapper.INSTANCE.ticketToTicketDto(mockTicket));
  }
}
