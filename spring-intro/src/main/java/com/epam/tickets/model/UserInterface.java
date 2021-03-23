package com.epam.tickets.model;

public interface UserInterface {
    /**
     * User Id. UNIQUE.
     * @return User Id.
     */
    long getId();
//    void setId(long id); - not needed as userId is created in CommonStorage

    String getName();
    void setName(String name);

    /**
     * User email. UNIQUE.
     * @return User email.
     */
    String getEmail();
    void setEmail(String email);
}
