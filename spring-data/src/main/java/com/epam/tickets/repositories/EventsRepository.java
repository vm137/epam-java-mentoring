package com.epam.tickets.repositories;

import com.epam.tickets.model.dto.Event;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends CrudRepository<Event, Long> {

    List<Event> getEventsByTitle(String title);

    List<Event> getEventsByDate(LocalDateTime date);
}
