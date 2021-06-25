package com.epam.tickets.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.epam.tickets.model.User;
import com.epam.tickets.model.dto.UserDto;
import com.epam.tickets.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceImplTest {

  private static final Long USER_ID = 1L;
  private static final String USER_NAME = "John Dow";
  private static final String USER_EMAIL = "email@mail.com";

  @Autowired
  UserService userService;

  @MockBean
  UserRepository userRepository;

  User mockUser;
  UserDto mockUserDto;

  @BeforeEach
  void init() {
    mockUser = new User(USER_ID, USER_NAME, USER_EMAIL);
    mockUserDto = new UserDto(USER_ID, USER_NAME, USER_EMAIL);
  }

  @Test
  public void whenDoCreateUser_thenCheckExpectedUser() {
    when(userRepository.save(any(User.class))).thenReturn(mockUser);

    UserDto createdUser = userService.createUser(USER_NAME, USER_EMAIL);
    assertEquals(createdUser, mockUserDto);
  }

  @Test
  public void whenDoGetUserById_thenCheckExpectedUser() {
    when(userRepository.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(mockUser));

    UserDto userById = userService.getUserById(USER_ID);
    assertEquals(userById, mockUserDto);
  }

  @Test
  public void whenDoDeleteUserById_thenCheckCallPasses() {
    when(userRepository.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(mockUser));

    userService.deleteUserById(USER_ID);
  }
}
