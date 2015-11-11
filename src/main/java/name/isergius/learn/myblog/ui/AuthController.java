package name.isergius.learn.myblog.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Kondratyev Sergey on 08.11.15.
 */
@Controller
public class AuthController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error",required = false) String error) {
        ModelAndView modelAndView = new ModelAndView("login");
        if (error != null) modelAndView.addObject("error","Invalid Username or Password!");
        return modelAndView;
    }
}
