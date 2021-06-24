package com.epam.testing.template;

import com.epam.testing.Client;
import exception.TemplateEngineException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (!arePlaceholdersSupplied(template, variables)) {
            throw new TemplateEngineException("Some placeholders don't have provided values.");
        }

        for (Entry<String, String> entry : entrySet) {
            String pattern = "#\\{" + entry.getKey() + "}";
            message = message.replaceAll(pattern, entry.getValue());
        }

        return message;
    }

    private boolean arePlaceholdersSupplied(Template template, HashMap<String, String> variables) {
        Pattern pattern = Pattern.compile("#\\{(.[^}]*)}");
        Matcher matcher = pattern.matcher(template.getTemplate());
        while(matcher.find()) {
            String plh = matcher.group(1);
            if (!variables.containsKey(plh)) {
                return false;
            }
        }
        return true;
    }
}
