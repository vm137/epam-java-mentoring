package com.epam.tickets.model;

public interface TicketInterface {
    enum Category { STANDARD, PREMIUM, BAR }

    /**
     * Ticket Id. UNIQUE.
     * @return Ticket Id.
     */
    Long getId();
    void setId(Long id);
    Long getEventId();
    Long setEventId(Long eventId);
    Long getUserId();
    void setUserId(Long userId);
    Category getCategory();
    void setCategory(Category category);
    int getPlace();
    void setPlace(int place);
}
