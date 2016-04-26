package pack.protdoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.protdoc.dao.MsgDAO;
import pack.protdoc.model.Message;
import pack.protdoc.model.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by contest on 21.04.2016.
 */
@Service
public class MsgService {
    @Autowired
    private MsgDAO msgDAO;

    public List<Message> findByReceiver(final User receiver) {
        List<Message> all = msgDAO.findAll();
        List<Message> group = all.stream()
                .filter(m -> m.getGroupReceiver() != null && m.getGroupReceiver().getUsers().contains(receiver))
                .collect(Collectors.toList());
        group.addAll(msgDAO.findByReceiverId(receiver.getId()));
        return group;
    }

    public List<Message> findBySender(User user) {
        return msgDAO.findBySender(user);
    }
}
