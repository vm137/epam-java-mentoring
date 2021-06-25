package com.epam.testing;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.testing.template.Template;
import java.io.File;
import org.junit.jupiter.api.Test;

class FileEngineTest {

  @Test
  public void fileEngineTest() {
    Template template = new Template();
    template.setTemplate("#{name} is good #{artist}.");

    FileEngine fileEngine = new FileEngine();
    fileEngine.processFile(template, "template-data.txt", "output.txt");

    File file = new File("output.txt");
    assertTrue(file.exists());
  }
}
