package com.epam.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.testing.template.Template;
import com.epam.testing.template.TemplateEngine;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessengerTest {

  MailServer mailServer;
  TemplateEngine templateEngine;
  Messenger messenger;
  Client client;

  @BeforeEach
  private void init() {
    mailServer = new MailServer();
    templateEngine = new TemplateEngine();
    messenger = new Messenger(mailServer, templateEngine);
    client = new Client();
  }

  @Test
  public void givenMessage_whenDoSendMessage_thenCheckExpectedMessage() {
    client.setAddresses("address-1");
    HashMap<String, String> variables = new HashMap<>();
    variables.put("name", "Victor");
    variables.put("event", "Fashion Show");
    client.setVariables(variables);

    Template template = new Template();
    template.setTemplate("Dear #{name}, we'd like to invite You to #{event}.");

    messenger.sendMessage(client, template);
    String messageSent = mailServer.getMessageSent();
    String expected = "Dear Victor, we'd like to invite You to Fashion Show.";

    assertEquals(messageSent, expected);
  }
}
