package AppRev1.App1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by aalbutov on 26.10.2017.
 */
@Controller
@RequestMapping("/rest")
public class SecurityTestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testService(){
        return "hello";
    }
}
