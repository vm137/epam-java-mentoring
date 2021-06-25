package com.epam.testing;

import com.epam.testing.template.Template;
import org.junit.jupiter.api.Test;

class FileEngineTest {

  @Test
  public void fileEngineTest() {
    Template template = new Template();
    template.setTemplate("#{name} is good #{artist}.");

    FileEngine fileEngine = new FileEngine();
    fileEngine.processFile(template, "template-data.txt", "output.txt");
  }
}