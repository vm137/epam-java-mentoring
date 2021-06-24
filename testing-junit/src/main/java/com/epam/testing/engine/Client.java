package com.epam.testing.engine;

import java.util.HashMap;

public class Client {
    private String addresses;
    private HashMap<String, String> variables;

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public HashMap<String, String> getVariables() {
        return variables;
    }

    public void setVariables(HashMap<String, String> variables) {
        this.variables = variables;
    }
}
