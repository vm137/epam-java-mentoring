package com.epam.testing.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.testing.Client;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TemplateEngineTest {
  Client client;
  Template template;
  HashMap<String, String> variables;

  @BeforeEach
  public void init() {
    client = new Client();
    template = new Template();
    variables = new HashMap<>();
  }

  @Test
  void generateMessageTest() {
    TemplateEngine engine = new TemplateEngine();
    variables.put("one", "fox");
    variables.put("two", "dog");
    client.setVariables(variables);
    template.setTemplate("one: #{one}, two: #{two}");
    String expected = "one: fox, two: dog";

    String generated = engine.generateMessage(client, template);
    assertEquals(expected, generated);
  }
}
