package IPRWC.Webshop.service;

import IPRWC.Webshop.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckIfUserIsAdminService {

    public boolean IsUserAdmin(Optional<User> user) {
        return user.map(User::isAdmin).orElse(false);
    }
}
