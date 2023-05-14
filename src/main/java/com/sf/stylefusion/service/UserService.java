package com.sf.stylefusion.service;

import com.sf.stylefusion.dto.UserDto;
import com.sf.stylefusion.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
