package com.epam.tickets.repositories;

import com.epam.tickets.model.dto.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsRepository extends CrudRepository<Ticket, Long> {

}
