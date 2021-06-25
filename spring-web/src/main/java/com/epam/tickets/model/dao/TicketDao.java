package com.epam.tickets.model.dao;

import com.epam.tickets.exceptions.InvalidTicketException;
import com.epam.tickets.model.Ticket;
import java.util.List;

public interface TicketDao {

  Ticket addTicket(Ticket ticket);

  List<Ticket> getAllTickets();

  void deleteTicketById(Long id) throws InvalidTicketException;
}
