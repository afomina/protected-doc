package pack.protdoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.protdoc.model.Message;
import pack.protdoc.model.User;

import java.util.List;

/**
 * Created by contest on 21.04.2016.
 */
public interface MsgDAO extends JpaRepository<Message, Integer> {
    List<Message> findByReceiverId(Integer receiverId);
}
