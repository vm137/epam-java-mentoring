package com.epam.tickets.services;

import com.epam.tickets.exceptions.UserAccountException;
import com.epam.tickets.model.dto.UserAccount;
import com.epam.tickets.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

  @Autowired
  UserAccountRepository userAccountRepository;

  @Override
  public void refill(Long userId, int refillAmount) {
    UserAccount account = userAccountRepository.findById(userId).orElseThrow(RuntimeException::new);
    account.setAmount(account.getAmount() + refillAmount);
    userAccountRepository.save(account);
  }

  @Override
  public void withdraw(Long userId, int withdrawAmount) {
    UserAccount account = userAccountRepository.findById(userId).orElseThrow(RuntimeException::new);
    int accountAmount = account.getAmount();
    if (accountAmount > withdrawAmount) {
      account.setAmount(accountAmount - withdrawAmount);
      userAccountRepository.save(account);
    } else {
      throw new UserAccountException("Not enough funds. id: " + userId);
    }
  }
}
