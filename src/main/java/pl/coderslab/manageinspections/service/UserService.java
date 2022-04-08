package pl.coderslab.manageinspections.service;

import pl.coderslab.manageinspections.dtos.UserDto;
import pl.coderslab.manageinspections.model.User;

public interface UserService {
    User findByUserName(String name);
    void saveUser(UserDto user);
}
