package AppRev1.App1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by aalbutov on 26.10.2017.
 */
@Controller
@RequestMapping("/rest")
public class SecurityTestController {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String testService(){
        return "hello";
    }
}
