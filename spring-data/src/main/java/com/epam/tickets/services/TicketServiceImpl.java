package com.epam.tickets.services;

import com.epam.tickets.model.Ticket;
import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.dto.EventDto;
import com.epam.tickets.model.dto.TicketDto;
import com.epam.tickets.model.dto.UserDto;
import com.epam.tickets.model.mappers.TicketMapper;
import com.epam.tickets.repositories.TicketsRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

  @Autowired
  TicketsRepository ticketsRepository;

  @Autowired
  UserAccountService userAccountService;

  @Override
  public TicketDto bookTicket(Long eventId, Long userId, int place, Category category, int price) {
    TicketDto ticketDto = new TicketDto(eventId, userId, place, category);
    userAccountService.withdraw(userId, price);
    Ticket ticket = TicketMapper.INSTANCE.ticketDtoToTicket(ticketDto);
    Ticket savedTicket = ticketsRepository.save(ticket);
    return TicketMapper.INSTANCE.ticketToTicketDto(savedTicket);
  }

  @Override
  public TicketDto getTicketById(Long id) {
    Ticket ticket = ticketsRepository.findById(id).orElse(null);
    return TicketMapper.INSTANCE.ticketToTicketDto(ticket);
  }

  @Override
  public List<TicketDto> getBookedTickets(UserDto userDto) {
    List<Ticket> ticketList = ticketsRepository.findByUserId(userDto.getId());
    return TicketMapper.INSTANCE.ticketListToTicketDtoList(ticketList);
  }

  @Override  public List<TicketDto> getBookedTickets(EventDto eventDto) {
    List<Ticket> ticketList = ticketsRepository.findByEventId(eventDto.getId());
    return TicketMapper.INSTANCE.ticketListToTicketDtoList(ticketList);
  }

  @Override
  public List<TicketDto> getAllTickets() {
    Iterable<Ticket> allTickets = ticketsRepository.findAll();
    List<Ticket> ticketList = StreamSupport.stream(allTickets.spliterator(), false)
        .collect(Collectors.toList());
    return TicketMapper.INSTANCE.ticketListToTicketDtoList(ticketList);
  }

  @Override
  public void cancelTicket(Long ticketId) {
    ticketsRepository.findById(ticketId).ifPresent(ticket -> ticketsRepository.delete(ticket));
  }
}
