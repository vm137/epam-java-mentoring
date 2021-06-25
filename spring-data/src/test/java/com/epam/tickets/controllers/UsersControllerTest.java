package com.epam.tickets.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.tickets.model.dto.UserDto;
import com.epam.tickets.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  UserService usersService;

  @Test
  public void whenDoGetAllUsers_thenCheckOkResponse() throws Exception {
    this.mockMvc
        .perform(get("/users/"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void givenUser_whenDoCreateUser_thenCheckOkResponse() throws Exception {
    String name = "John Dow";
    String email = "email@m.com";
    when(usersService.createUser(any(String.class), any(String.class))).thenReturn(new UserDto());

    this.mockMvc
        .perform(get("/users/add?name=" + name + "&email=" + email))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void givenUser_whenDoUpdateUser_thenCheckOkResponse() throws Exception {
    String name = "John Dow";
    String email = "email@m.com";
    this.mockMvc
        .perform(patch("/users/1?userName=" + name + "&userEmail=" + email))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void givenUserId_whenDoDeleteUser_thenCheckOkResponse() throws Exception {
    this.mockMvc
        .perform(delete("/users/1"))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
