package com.epam.tickets.repositories;

import com.epam.tickets.model.dto.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

}
