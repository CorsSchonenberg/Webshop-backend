package IPRWC.Webshop.dao;

import IPRWC.Webshop.model.PromoCode;
import IPRWC.Webshop.model.User;
import IPRWC.Webshop.service.ReturnNewIdService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class UserDao {
    private final UserRepository userRepository;
    private final ReturnNewIdService returnNewIdService;

    public UserDao(UserRepository userRepository, ReturnNewIdService returnNewIdService) {
        this.userRepository = userRepository;
        this.returnNewIdService = returnNewIdService;
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

    public Optional<User> findByEmail(String email) {
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();

        for (User user : users) {
            if (user.getEmail().contains(email)) {

                return Optional.ofNullable(user);
            }
        }
        return Optional.empty();
    }

    public int giveUserNewId() {
        ArrayList<User> users =
                (ArrayList<User>) this.userRepository.findAll();
        return this.returnNewIdService.returnNewUserId(users);
    }
}
