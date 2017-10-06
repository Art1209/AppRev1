package AppRev1.App1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class LoginController {
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginer(){
        return "login";
    }
}
