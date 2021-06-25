package com.epam.tickets.services;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.User;
import com.epam.tickets.model.dto.UserDto;
import com.epam.tickets.model.mappers.UserMapper;
import com.epam.tickets.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDto createUser(String name, String email) {
    User user = userRepository.save(new User(name, email));
    return UserMapper.INSTANCE.userToUserDto(user);
  }

  @Override
  public UserDto getUserById(Long id) {
    User user = userRepository.findById(id).orElse(null);
    return UserMapper.INSTANCE.userToUserDto(user);
  }

  @Override
  public List<UserDto> getUsersByName(String name) {
    List<User> userList = userRepository.findByName(name);
    return UserMapper.INSTANCE.userToUserDtoList(userList);
  }

  @Override
  public void updateUser(Long id, String name, String email) {
    UserDto userDto = getUserById(id);
    if (userDto != null) {
      if (name != null) {
        userDto.setName(name);
      }
      if (email != null) {
        userDto.setEmail(email);
      }
      userRepository.save(UserMapper.INSTANCE.userDtoToUser(userDto));
    } else {
      throw new InvalidUserException("User with id: " + id + " can't be updated because it's not found.");
    }
  }

  @Override
  public void deleteUserById(Long id) {
    UserDto userDto = getUserById(id);
    if (userDto != null) {
      userRepository.delete(UserMapper.INSTANCE.userDtoToUser(userDto));
    } else {
      throw new InvalidUserException("User with id: " + id + " can't be deleted because it's not found.");
    }
  }
}
