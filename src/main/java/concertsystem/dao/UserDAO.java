package concertsystem.dao;

import concertsystem.model.User;

import java.util.List;

/*
 * User DAO layer with Hibernate usage
 */
public interface UserDAO {
    void addUser(User user);
    void deleteUser(int id);
    User findByUsername(String username);
    List<User> getUsersList();
}
