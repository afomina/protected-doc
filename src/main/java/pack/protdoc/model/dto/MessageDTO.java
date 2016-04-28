package pack.protdoc.model.dto;

import pack.protdoc.model.Message;
import pack.protdoc.model.SecurityLevel;
import pack.protdoc.model.User;
import pack.protdoc.model.UserGroup;
import pack.protdoc.secure.SecurityCheckService;

/**
 * Created by alexa on 24.04.2016.
 */
public class MessageDTO {
    private Message message;
    private boolean valid;

    public Integer getId() {
        return message.getId();
    }

    public String getText() {
        return message.getText();
    }

    public SecurityLevel getSecurityLevel() {
        return message.getSecurityLevel();
    }

    public User getSender() {
        return message.getSender();
    }

    public User getReceiver() {
        return message.getReceiver();
    }

    public UserGroup getGroupReceiver() {
        return message.getGroupReceiver();
    }

    public boolean isWasRead() {
        return message.isWasRead();
    }

    public boolean checkValid() {
        return SecurityCheckService.checkIntegrity(message);
    }

}
