package pack.protdoc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack.protdoc.dao.MsgDAO;
import pack.protdoc.dao.SecurityLevelDAO;
import pack.protdoc.dao.UserDAO;
import pack.protdoc.model.Message;
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
//        log.error(msg.getReceiver().toString());
        if (msg.getSender() == null) {
            msg.setSender(UserService.getCurrentUser());
        }
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

    private void setupMsgModel(Model model) {
        Message msg = new Message();
        msg.setSender(UserService.getCurrentUser());
        model.addAttribute("msg", msg);
        model.addAttribute("users", userDAO.findAll());
        model.addAttribute("levels", securityLevelDAO.findAll());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(Model model) {
        List<Message> msgs = msgService.findByReceiver(UserService.getCurrentUser());
        model.addAttribute("msgs", msgs);
        return "msgs";
    }

//    @RequestMapping("/getMessages")
//    @ResponseBody
//    public List<Message> getMsgList(@RequestParam("id") Integer userId) { //TODO: current logged in user
//        return msgService.findByReceiver(userDAO.findOne(userId));
//    }

}
