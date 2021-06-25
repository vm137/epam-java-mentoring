package com.epam.tickets.model.mappers;

import com.epam.tickets.model.Event;
import com.epam.tickets.model.dto.EventDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {

  EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

  EventDto eventToEventDto(Event event);

  Event eventDtoToEvent(EventDto eventDto);

  List<Event> eventDtoListToEventList(List<EventDto> eventDtoList);

  List<EventDto> eventListToEventDtoList(List<Event> eventList);
}
