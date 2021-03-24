package com.epam.tickets.model;

import java.util.Date;

public interface EventInterface {
    /**
     * Event id. UNIQUE.
     * @return Event Id
     */
    Long getId();
    void setId(Long id);
    String getTitle();
    void setTitle(String title);
    Date getDate();
    void setDate(Date date);
}
