package pack.protdoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.protdoc.model.UserGroup;

/**
 * Created by alexa on 26.04.2016.
 */
public interface UserGroupDAO extends JpaRepository<UserGroup, Integer> {
}
