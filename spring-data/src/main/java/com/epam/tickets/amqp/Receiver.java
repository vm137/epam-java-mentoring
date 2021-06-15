package com.epam.tickets.amqp;

import com.epam.tickets.exceptions.InvalidMessageException;
import com.epam.tickets.model.dto.TicketDto;
import com.epam.tickets.services.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  private static final String MY_QUEUE_NAME = "booking-line";
  private static final Logger log = LoggerFactory.getLogger(Receiver.class);

  @Autowired
  TicketService ticketService;

  @RabbitListener(queues = MY_QUEUE_NAME)
  public void listen(String message)  {

    ObjectMapper mapper = new ObjectMapper();
    try {
      TicketDto ticket = mapper.readValue(message, TicketDto.class);
      log.debug("incoming message: " + ticket.toString());

      // book ticket
      ticketService.bookTicket(ticket.getEventId(), ticket.getUserId(), ticket.getPlace(),
           ticket.getCategory(), ticket.getPrice());

    } catch (IOException ex) {
      throw new InvalidMessageException("Invalid incoming message format.");
    }
  }
}
