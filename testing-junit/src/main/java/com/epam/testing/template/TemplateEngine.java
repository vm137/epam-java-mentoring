package com.epam.testing.template;

import com.epam.testing.Client;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Client client, Template template) {
        String message = template.getTemplate();
        HashMap<String, String> variables = client.getVariables();
        Set<Entry<String, String>> entrySet = variables.entrySet();

        for (Entry<String, String> entry : entrySet) {
            String pattern = "#\\{" + entry.getKey() + "}";
            message = message.replaceAll(pattern, entry.getValue());
        }

        return message;
    }
}
