package pack.protdoc.model;

import javax.persistence.*;

/**
 * Created by alexa on 19.04.2016.
 */
@Entity
@Table
public class Message extends BaseObject {
    private Integer id;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private String text;
    private SecurityLevel securityLevel;
    private User sender;
    private User receiver;
    private UserGroup groupReceiver;
    private boolean wasRead;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @JoinColumn
    @ManyToOne
    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }

    @JoinColumn
    @ManyToOne
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @JoinColumn
    @ManyToOne
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @JoinColumn
    @ManyToOne
    public UserGroup getGroupReceiver() {
        return groupReceiver;
    }

    public void setGroupReceiver(UserGroup groupReceiver) {
        this.groupReceiver = groupReceiver;
    }

    public boolean isWasRead() {
        return wasRead;
    }

    public void setWasRead(boolean wasRead) {
        this.wasRead = wasRead;
    }
}
