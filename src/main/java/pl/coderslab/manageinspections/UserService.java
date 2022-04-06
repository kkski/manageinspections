package pl.coderslab.manageinspections;

public interface UserService {
    User findByUserName(String name);
    void saveUser(User user);
}
