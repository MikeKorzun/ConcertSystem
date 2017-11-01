package concertsystem.service;

;
import concertsystem.dao.UserDAO;
import concertsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(user.getPassword());
        user.setRole("ROLE_USER");
        userDAO.addUser(user);
    }

    @Transactional
    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    @Transactional
    public List<User> getUsersList() {
        return userDAO.getUsersList();
    }
}
