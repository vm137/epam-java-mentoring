package com.epam.tickets.model.mappers;

import com.epam.tickets.model.User;
import com.epam.tickets.model.dto.UserDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserDto userToUserDto(User user);

  User userDtoToUser(UserDto userDto);

  List<UserDto> userToUserDtoList(List<User> userList);
}
