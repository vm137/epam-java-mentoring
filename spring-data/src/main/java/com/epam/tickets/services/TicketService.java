package com.epam.tickets.services;

import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.dto.EventDto;
import com.epam.tickets.model.dto.TicketDto;
import com.epam.tickets.model.dto.UserDto;
import java.util.List;

public interface TicketService {

  TicketDto bookTicket(Long userId, Long eventId, int place, Category category, int price);

  TicketDto getTicketById(Long id);

  List<TicketDto> getBookedTickets(UserDto userDto);

  List<TicketDto> getBookedTickets(EventDto eventDto);

  List<TicketDto> getAllTickets();

  void cancelTicket(Long ticketId);
}
