package com.epam.testing;

import com.epam.testing.exceptions.TemplateEngineException;
import com.epam.testing.service.Client;
import com.epam.testing.template.Template;
import com.epam.testing.template.TemplateEngine;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileEngine {

  TemplateEngine templateEngine = new TemplateEngine();

  /**
   * Reads file with data for template engine from input file
   * and writes generated messages to output file.
   *
   * @param template template string with #{placeholder}s
   * @param inputFileName filename
   * @param outputFileName filename
   */
  public void processFile(Template template, String inputFileName, String outputFileName) {
    URL inputUrl = FileEngine.class.getClassLoader().getResource(inputFileName);
    if (Objects.isNull(inputUrl)) {
      throw new TemplateEngineException("File " + inputFileName + " is not found.");
    }

    Pattern pattern = Pattern.compile("#\\{(.[^}]*)}");
    Matcher matcher = pattern.matcher(template.getTemplate());
    List<String> placeholders = new ArrayList<>();
    while(matcher.find()) {
      placeholders.add(matcher.group(1));
    }
    int numberOfPlaceholders = placeholders.size();

    try (BufferedReader br = new BufferedReader(new FileReader(inputUrl.getPath()));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputFileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] split = line.split(",");
        if (split.length < numberOfPlaceholders) {
          throw new TemplateEngineException("Not enough data fields in the input file.");
        }

        // create and fill placeholder variables
        HashMap<String, String> variables = new HashMap<>();
        for (int i = 0; i < numberOfPlaceholders; i++) {
          variables.put(placeholders.get(i), split[i].trim());
        }
        Client client = new Client();
        client.setVariables(variables);

        // run template engine
        String message = templateEngine.generateMessage(client, template);

        // write generated message to file
        fileWriter.write(message);
        fileWriter.newLine();
      }
    } catch (IOException ex) {
      System.out.println("I/O Error");
    }
  }
}
