package pack.protdoc.service;

import org.springframework.security.core.context.SecurityContextHolder;
import pack.protdoc.model.User;

/**
 * Created by alexa on 24.04.2016.
 */
public class UserService {
    public static User getCurrentUser() {
        User user = null;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (NullPointerException e) { }
        return user;
    }
}
