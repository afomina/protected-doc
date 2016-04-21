package pack.protdoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pack.protdoc.dao.MsgDAO;
import pack.protdoc.dao.UserDAO;
import pack.protdoc.model.Message;
import pack.protdoc.secure.SecurityCheckService;
import pack.protdoc.service.MsgService;

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

    @RequestMapping("/write")
    public String writeMsg() {
        return "write";
    }

    @RequestMapping("/send")
    public ResponseEntity send(@RequestBody Message msg) {
        if (securityCheckService.check(msg)) {
            msgDAO.save(msg);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/")
    public String indexPage() {
        return "msgs";
    }

    @RequestMapping("/getMessages")
    @ResponseBody
    public List<Message> getMsgList(@RequestParam("id") Integer userId) { //TODO: current logged in user
        return msgService.findByReceiver(userDAO.findOne(userId));
    }

}
