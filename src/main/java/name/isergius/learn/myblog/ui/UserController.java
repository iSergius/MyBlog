package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.User;
import name.isergius.learn.myblog.domain.UserService;
import name.isergius.learn.myblog.domain.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

/**
 * Created by Kondratyev Sergey on 13.11.15.
 */
@Controller
@RequestMapping(path = "/user")
@SessionAttributes(types = {User.class})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String showUserInformation() {
        return "user";
    }

    @RequestMapping(path = "/edit",method = RequestMethod.GET)
    public String editUserInformation() {
        return "user-editor";
    }

    @RequestMapping(path = "/edit",method = RequestMethod.POST)
    public ModelAndView SaveUserInformation(@Valid UserInformationForm userInformationForm, SessionStatus sessionStatus) {

        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.updateUser(userInformationForm);
            sessionStatus.setComplete();
            SecurityContextHolder.getContext().setAuthentication(null);
            modelAndView.setView(new RedirectView("/user"));
        } catch (UserServiceException e) {
            modelAndView.addObject("error",e.getMessage());
            modelAndView.setViewName("user-editor");
        }
        return modelAndView;
    }

    @ExceptionHandler(BindException.class)
    public ModelAndView formValidationHandler(/*ConstraintViolationException e, */BindException be) {
        ModelAndView modelAndView = new ModelAndView("user-editor");
        //modelAndView.addObject("error",be.getMessage());
        for (FieldError fieldError : be.getFieldErrors()) {
            switch (fieldError.getField()) {
                case "userName": {
                    modelAndView.addObject("userNameError", "Username must like email");
                    break;
                }
                case "newEmail" :
                case "confirmEmail": {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(fieldError.getDefaultMessage());
                    modelAndView.addObject("errorConfirmEmail", stringBuilder);
                    break;
                }
                case "newPassword" :
                case "confirmPassword" : {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(fieldError.getDefaultMessage());
                    modelAndView.addObject("errorConfirmPassword", stringBuilder);
                    break;
                }
                case "password": {
                    modelAndView.addObject("passwordError", fieldError.getDefaultMessage());
                    break;
                }
            }
        }
        return modelAndView;
    }

}
