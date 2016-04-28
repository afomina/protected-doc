package pack.protdoc.secure;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import pack.protdoc.model.Message;
import pack.protdoc.model.Receiver;
import pack.protdoc.model.User;

/**
 * Created by contest on 21.04.2016.
 */
@Service
public class SecurityCheckService {
    public boolean check(Message msg) throws SecurityException {
        if (msg.getReceiver() != null) {
            if (!check(msg, msg.getReceiver())) {
                throw new SecurityException(msg.getSecurityLevel() +
                        " message can not be sent to user with '" + msg.getReceiver().getSecurityLevel() + "' level!");
            }
        } else if (msg.getGroupReceiver() != null) {
            for (User user : msg.getGroupReceiver().getUsers()) {
                if (!check(msg, user)) {
                    throw new SecurityException(msg.getSecurityLevel() +
                            " message can not be sent to group containing user with " + user.getSecurityLevel() + " level");
                }
            }
        } else {
            throw new IllegalArgumentException("no message receiver");
        }
        return true;
    }

    public static String calcHash(String message) {
        return DigestUtils.md5DigestAsHex(message.getBytes());
    }

    private boolean check(Message msg, User receiver) {
        return msg.getSecurityLevel().compareTo(receiver.getSecurityLevel()) <= 0;
    }

    public static boolean checkIntegrity(Message message) {
        return message.getHash().equals(calcHash(message.getText()));
    }
}
