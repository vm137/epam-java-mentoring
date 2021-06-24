package com.epam.testing.engine;

/**
 * Mail server class.
 */
public class MailServer {
    private String addressSent;
    private String messageSent;

    public String getAddressSent() {
        return addressSent;
    }

    public String getMessageSent() {
        return messageSent;
    }

    public void send(String addresses, String messageContent) {
        this.addressSent = addresses;
        this.messageSent = messageContent;
    }
}
