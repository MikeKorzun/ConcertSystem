package concertsystem.service;

import concertsystem.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User findByUsername(String username);
    List<User> getUsersList();
}
