package com.epam.testing.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.testing.service.Client;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class TemplateEngineDynamicTest {

  @TestFactory
  Collection<DynamicTest> generateMessageDynamicTest() {
    Client client = new Client();
    TemplateEngine engine = new TemplateEngine();

    HashMap<String, String> variables = new HashMap<>();
    variables.put("one", "fox");
    variables.put("two", "dog");
    client.setVariables(variables);

    Template template1 = new Template("one: #{one}, two: #{two}");
    String expected1 = "one: fox, two: dog";

    Template template2 = new Template("#{one} and two #{two}s");
    String expected2 = "fox and two dogs";

    Template template3 = new Template("Is #{one} a \"#{two}\"");
    String expected3 = "Is fox a \"dog\"";

    return Arrays.asList(
        DynamicTest.dynamicTest("General test",
            () -> assertEquals(expected1, engine.generateMessage(client, template1))),
        DynamicTest.dynamicTest("Beginning of string",
            () -> assertEquals(expected2, engine.generateMessage(client, template2))),
        DynamicTest.dynamicTest("Quotes",
            () -> assertEquals(expected3, engine.generateMessage(client, template3)))
    );
  }
}
