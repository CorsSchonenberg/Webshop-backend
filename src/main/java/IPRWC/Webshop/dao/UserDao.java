package IPRWC.Webshop.dao;

import IPRWC.Webshop.model.PromoCode;
import IPRWC.Webshop.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserDao {
    private final UserRepository userRepository;

    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveToDatabase(User user) {
        this.userRepository.save(user);
    }
    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) this.userRepository.findAll();
    }
    public void deleteUserFromDatabase(Integer id) {
        this.userRepository.deleteById(id);
    }
}
