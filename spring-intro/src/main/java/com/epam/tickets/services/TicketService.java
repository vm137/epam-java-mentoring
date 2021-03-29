package com.epam.tickets.services;

import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.model.dto.User;
import java.util.List;

public interface TicketService {

  List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

  List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);
}
