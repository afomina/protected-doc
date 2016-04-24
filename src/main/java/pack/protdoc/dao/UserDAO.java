package pack.protdoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.protdoc.model.User;

/**
 * Created by contest on 21.04.2016.
 */
public interface UserDAO extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
