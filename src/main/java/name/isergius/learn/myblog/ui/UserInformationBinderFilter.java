package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.User;
import name.isergius.learn.myblog.domain.UserService;
import name.isergius.learn.myblog.domain.UserServiceException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Kondratyev Sergey on 23.11.15.
 */
public class UserInformationBinderFilter extends GenericFilterBean {

    private UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession(true);
        try {
            User user = userService.getCurrentUser();
            session.setAttribute("user",user);
        } catch (UserServiceException e) {
            e.printStackTrace();
        }
        chain.doFilter(request,response);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
