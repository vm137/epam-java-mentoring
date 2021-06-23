package com.epam.tickets.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.epam.tickets.model.Ticket;
import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.dto.TicketDto;
import com.epam.tickets.model.dto.UserDto;
import com.epam.tickets.model.mappers.TicketMapper;
import com.epam.tickets.repositories.TicketsRepository;
import java.util.ArrayList;
import java.util.List;
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
  public void whenDoBookTicket_thenCheckExpectedTicket() {
    when(ticketsRepository.save(any(Ticket.class))).thenReturn(mockTicket);

    TicketDto bookedTicket = ticketService.bookTicket(1L, 1L, 1, Category.STANDARD, 100);
    assertEquals(bookedTicket, TicketMapper.INSTANCE.ticketToTicketDto(mockTicket));
  }

  @Test
  public void whenDoGetTicketById_thenCheckExpectedTicket() {
    when(ticketsRepository.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(mockTicket));

    TicketDto ticket = ticketService.getTicketById(1L);
    assertEquals(ticket, TicketMapper.INSTANCE.ticketToTicketDto(mockTicket));
  }

  @Test
  public void whenDoGetBookedTickets_thenCheckTicketList() {
    List<Ticket> ticketList = new ArrayList<>();
    ticketList.add(new Ticket(1L, 1L, 1, Category.STANDARD));
    ticketList.add(new Ticket(2L, 2L, 2, Category.PREMIUM));
    when(ticketsRepository.findByUserId(any(Long.class))).thenReturn(ticketList);

    UserDto userDto = new UserDto(1L, "", "");
    List<TicketDto> ticketDtos = ticketService.getBookedTickets(userDto);
    assertEquals(TicketMapper.INSTANCE.ticketListToTicketDtoList(ticketList), ticketDtos);
  }

  @Test
  public void whenDoCancelTicket_thenCheckCallPasses() {
    ticketService.cancelTicket(1L);
  }
}
