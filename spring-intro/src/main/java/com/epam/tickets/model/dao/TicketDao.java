package com.epam.tickets.model.dao;

import com.epam.tickets.model.dto.Ticket;
import java.util.List;

public interface TicketDao {

  List<Ticket> getAllTickets();

  Ticket addTicket(Ticket ticket);

  boolean deleteTicket(Long id);
}
