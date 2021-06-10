package com.epam.tickets;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tickets.controllers.TicketsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringApplicationTest {

  @Autowired
  private TicketsController ticketsController;

  @Test
  public void contextLoads() {
    assertThat(ticketsController).isNotNull();
  }
}
