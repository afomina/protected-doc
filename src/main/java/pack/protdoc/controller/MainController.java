package pack.protdoc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack.protdoc.dao.MsgDAO;
import pack.protdoc.dao.SecurityLevelDAO;
import pack.protdoc.dao.UserDAO;
import pack.protdoc.dao.UserGroupDAO;
import pack.protdoc.model.Message;
import pack.protdoc.model.User;
import pack.protdoc.secure.*;
import pack.protdoc.secure.SecurityException;
import pack.protdoc.service.MsgService;
import pack.protdoc.service.UserService;

import java.util.List;

/**
 * Created by alexa on 19.04.2016.
 */
@Controller
public class MainController {

    @Autowired
    private MsgDAO msgDAO;
    @Autowired
    private SecurityCheckService securityCheckService;
    @Autowired
    private MsgService msgService;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SecurityLevelDAO securityLevelDAO;
    @Autowired
    private UserGroupDAO groupDAO;

    private Logger log = LoggerFactory.getLogger(MainController.class) ;

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeMsg(Model model) {
        setupMsgModel(model);
        return "write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String send(@ModelAttribute Message msg, Model model) {
        if (msg.getSender() == null) {
            msg.setSender(UserService.getCurrentUser());
        }
        msg.setHash(SecurityCheckService.calcHash(msg.getText()));
        try {
            if (securityCheckService.check(msg)) {
                msgDAO.save(msg);
            }
        } catch (SecurityException e) {
            model.addAttribute("err", e.getMessage());
            setupMsgModel(model);
            return "write";
        }
        List<Message> msgs = msgService.findByReceiver(UserService.getCurrentUser());
        model.addAttribute("msgs", msgs);
        return "msgs";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String inbox(Model model) {
        List<Message> msgs = msgService.findByReceiver(UserService.getCurrentUser());
        model.addAttribute("msgs", msgs);
        return "msgs";
    }

    @RequestMapping(value = "/msgs/{id}", method = RequestMethod.GET)
    public String readMsg(@PathVariable("id") Integer msgId, Model model) {
        Message msg = msgDAO.findOne(msgId);
        User currentUser = UserService.getCurrentUser();
        if (currentUser.equals(msg.getReceiver()) ||
                (msg.getGroupReceiver() != null && msg.getGroupReceiver().getUsers().contains(currentUser))) {
            msg.setWasRead(true);
            msgDAO.save(msg);
        }
        model.addAttribute("msg", msg);
        return "msg";
    }

    @RequestMapping(value = "/sent", method = RequestMethod.GET)
    public String sentMsgs(Model model) {
        List<Message> msgs = msgService.findBySender(UserService.getCurrentUser());
        model.addAttribute("msgs", msgs);
        return "msgs";
    }

    private void setupMsgModel(Model model) {
        Message msg = new Message();
        msg.setSender(UserService.getCurrentUser());
        model.addAttribute("msg", msg);
        model.addAttribute("users", userDAO.findAll());
        model.addAttribute("levels", securityLevelDAO.findAll());
        model.addAttribute("groups", groupDAO.findAll());
    }
}
