package pack.protdoc.secure;

import org.springframework.stereotype.Service;
import pack.protdoc.model.Message;
import pack.protdoc.model.Receiver;
import pack.protdoc.model.User;

/**
 * Created by contest on 21.04.2016.
 */
@Service
public class SecurityCheckService {
    public boolean check(Message msg) {
        if (msg.getReceiver() != null) {
            if (!check(msg, msg.getReceiver())) {
                return false;
            }
        } else if (msg.getGroupReceiver() != null) {
            for (User user : msg.getGroupReceiver().getUsers()) {
                if (!check(msg, user)) {
                    return false;
                }
            }
        } else {
            throw new IllegalArgumentException("no message receiver");
        }
        return true;
    }

    private boolean check(Message msg, User receiver) {
        return msg.getSecurityLevel().compareTo(receiver.getSecurityLevel()) <= 0;
    }
}
