package com.epam.tickets.model;

public interface UserInterface {
    /**
     * User Id. UNIQUE.
     * @return User Id.
     */
    Long getId();
    void setId(Long id);

    String getName();
    void setName(String name);

    /**
     * User email. UNIQUE.
     * @return User email.
     */
    String getEmail();
    void setEmail(String email);
}
