package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.ui.Page;
import name.isergius.learn.myblog.ui.UserInformationForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 11.11.15.
 */
public interface UserService extends UserDetailsService {

    User createUser(User user) throws UserServiceException;

    void updateUser(User user) throws UserServiceException;

    void updateUser(UserInformationForm userInformationForm) throws UserServiceException;

    Page<User> getUsers(Long size);

    List<Authority> getAllAuthorities();

    User getCurrentUser() throws UserServiceException;

}
