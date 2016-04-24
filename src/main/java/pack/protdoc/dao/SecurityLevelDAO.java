package pack.protdoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.protdoc.model.SecurityLevel;

/**
 * Created by alexa on 24.04.2016.
 */
public interface SecurityLevelDAO extends JpaRepository<SecurityLevel, Integer> {
}
