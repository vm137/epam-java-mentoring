package com.epam.testing.template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.testing.exceptions.TemplateEngineException;
import com.epam.testing.service.Client;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
class TemplateEngineTest {
  private Client client;
  private Template template;
  private HashMap<String, String> variables;
  private final TemplateEngine engine = new TemplateEngine();

  @BeforeEach
  public void init() {
    client = new Client();
    template = new Template();
    variables = new HashMap<>();
  }

  @Test
  void generateMessageTest() {
    variables.put("one", "fox");
    variables.put("two", "dog");
    client.setVariables(variables);
    template.setTemplate("one: #{one}, two: #{two}");
    String expected = "one: fox, two: dog";

    String generated = engine.generateMessage(client, template);
    assertEquals(expected, generated);
  }

  @Test
  void generateMessageWithExceptionTest() {
    TemplateEngine engine = new TemplateEngine();
    variables.put("one", "fox");
    // variables.put("two", "dog"); - this variable we don't set and get an exception
    client.setVariables(variables);
    template.setTemplate("one: #{one}, two: #{two}");

    Exception exception =  assertThrows(TemplateEngineException.class, () -> {
      engine.generateMessage(client, template);
    });

    String expectedMessage = "Some placeholders don't have provided values.";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
}
