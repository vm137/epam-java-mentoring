package com.epam.tickets.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.dto.TicketDto;
import com.epam.tickets.repositories.TicketsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TicketServiceImplTest {

  @MockBean
  TicketService ticketService;

  @MockBean
  TicketsRepository ticketsRepository;

  @MockBean
  UserAccountService userAccountService;

  @Test
  public void bookTicket() {
    TicketDto mockTicket = new TicketDto();
    when(ticketService.bookTicket(1L, 1L, 1, Category.STANDARD, 100)).thenReturn(mockTicket);

    TicketDto bookedTicket = ticketService.bookTicket(1L, 1L, 1, Category.STANDARD, 100);
    assertEquals(mockTicket, bookedTicket);
  }
}
