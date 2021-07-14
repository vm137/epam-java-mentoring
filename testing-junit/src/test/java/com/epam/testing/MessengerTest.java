package com.epam.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.condition.JRE.JAVA_11;

import com.epam.testing.service.Client;
import com.epam.testing.service.MailServer;
import com.epam.testing.service.Messenger;
import com.epam.testing.template.Template;
import com.epam.testing.template.TemplateEngine;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

@EnabledOnOs({OS.WINDOWS, OS.MAC})
@EnabledForJreRange(max = JAVA_11)
@Tag("IntegrationTest")
class MessengerTest {

  private MailServer mailServer;
  private TemplateEngine templateEngine;
  private Template template;
  private Messenger messenger;
  private Client client;
  private HashMap<String, String> variables;

  @BeforeEach
  public void init() {
    mailServer = new MailServer();
    templateEngine = new TemplateEngine();
    template = new Template();
    messenger = new Messenger(mailServer, templateEngine);
    client = new Client();
    client.setAddresses("address-1");
    variables = new HashMap<>();
  }

  @Test
  public void givenMessage_whenDoSendMessage_thenCheckExpectedMessage() {
    variables.put("name", "Victor");
    variables.put("event", "Fashion Show");
    client.setVariables(variables);
    template.setTemplate("Dear #{name}, we'd like to invite You to #{event}.");

    messenger.sendMessage(client, template);
    String messageSent = mailServer.getMessageSent();
    String expected = "Dear Victor, we'd like to invite You to Fashion Show.";

    assertEquals(messageSent, expected);
  }

  @ParameterizedTest
  @CsvSource({
      "John Dow, Musicale, 'Dear John Dow, welcome to Musicale.'",
      "Jane Air, Photo week, 'Dear Jane Air, welcome to Photo week.'",
      "Alice Cooper, Rave party, 'Dear Alice Cooper, welcome to Rave party.'"
  })
  void parametrizedMessengerTest(String name, String event, String expected) {
    variables.put("name", name);
    variables.put("event", event);
    client.setVariables(variables);
    template.setTemplate("Dear #{name}, welcome to #{event}.");

    messenger.sendMessage(client, template);
    String messageSent = mailServer.getMessageSent();
    assertEquals(expected, messageSent);
  }

  @ParameterizedTest
  @CsvFileSource(resources="data.csv")
  void parametrizedFileMessengerTest(String name, String event, String expected) {
    variables.put("name", name);
    variables.put("event", event);
    client.setVariables(variables);
    template.setTemplate("Dear #{name}, welcome to #{event}.");

    messenger.sendMessage(client, template);
    String messageSent = mailServer.getMessageSent();
    assertEquals(expected, messageSent);
  }
}
