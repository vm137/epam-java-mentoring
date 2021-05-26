package com.epam.tickets.model.mappers;

import com.epam.tickets.model.Ticket;
import com.epam.tickets.model.dto.TicketDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {

  TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

  TicketDto ticketToTicketDto(Ticket ticket);

  Ticket ticketDtoToTicket(TicketDto ticketDto);

  List<TicketDto> ticketListToTicketDtoList(List<Ticket> ticketList);
}
