package pl.coderslab.manageinspections.service;

import pl.coderslab.manageinspections.model.User;

public interface UserService {
    User findByUserName(String name);
    void saveUser(User user);
}
