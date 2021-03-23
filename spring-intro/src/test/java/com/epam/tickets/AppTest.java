package com.epam.tickets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.epam.tickets.facade.BookingFacadeImpl;
import com.epam.tickets.model.dto.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {

    BookingFacadeImpl facade;


    @Before
    public void shouldAnswerWithTrue() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        facade = (BookingFacadeImpl) ctx.getBean("facade");
    }

    @Test
    public void createNewUserTest() {
        User user = new User("Alex", "al@gmail.com");
        User savedUser = facade.createUser(user);

        assertEquals("Alex", savedUser.getName());
        assertEquals("al@gmail.com", savedUser.getEmail());
    }
}
