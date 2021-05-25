package com.epam.tickets.repositories;

import com.epam.tickets.model.Ticket;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsRepository extends CrudRepository<Ticket, Long> {

  List<Ticket> findByUserId(Long id);

  List<Ticket> findByEventId(Long id);
}
