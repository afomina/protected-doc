package pack.protdoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by alexa on 19.04.2016.
 */
@Controller
public class MainController {

    @RequestMapping("/write")
    public String writeMsg() {
        return "write";
    }

}
