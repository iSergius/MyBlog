package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.AuthorityDao;
import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.dao.UserDao;
import name.isergius.learn.myblog.ui.Page;
import name.isergius.learn.myblog.ui.UserInformationForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 11.11.15.
 */
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder;
    private UserDao userDao;
    private AuthorityDao authorityDao;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserDao userDao, AuthorityDao authorityDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.authorityDao = authorityDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            user = userDao.readBy(username);
        } catch (DaoException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.isEnabled(),true,true,true,user.getAuthorities());
    }

    @Override
    public User createUser(User user) throws UserServiceException {
        if (user.getId() != null) throw new IllegalArgumentException("User contain id"+user.getId());
        encodeUserPassword(user);
        User result = null;
        try {
            User check = userDao.readBy(user.getUsername());
            throw new UserServiceException("User don't created becouse this username: "+user.getUsername()+" is containsed");
        } catch (DaoException e1) {
            try {
                result = userDao.create(user);
            } catch (DaoException e) {
                throw new UserServiceException("User don't created",e);
            }
        }
        return result;
    }

    @Override
    public void updateUser(User user) throws UserServiceException {
        encodeUserPassword(user);
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new UserServiceException();
        }
    }

    @Override
    public void updateUser(UserInformationForm userInformationForm) throws UserServiceException {
        User user = null;
        try {
            user = userDao.readBy(userInformationForm.getId());
        } catch (DaoException e) {
            throw new UserServiceException("User is not found");
        }
        if (passwordEncoder.matches(userInformationForm.getPassword(),user.getPassword())) {
            userInformationForm.exportTo(user);
            encodeUserPassword(user);
            try {
                userDao.update(user);
            } catch (DaoException e) {
                throw new UserServiceException(e.getMessage(),e);
            }
        } else {
            throw new UserServiceException("Password incorrect");
        }
    }

    @Override
    public Page<User> getUsers(Long size) {
        return new Page<>(userDao.read(),size);
    }

    @Override
    public List<Authority> getAllAuthorities() {
        return authorityDao.read().result(0L,0L);
    }

    @Override
    public User getCurrentUser() throws UserServiceException {
        User result = new User();
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            try {
                result = userDao.readBy(((UserDetails) principal).getUsername());
            } catch (DaoException e) {
                throw new UserServiceException("User is not found", e);
            }
        } else if (principal instanceof String) {
            result.setUsername((String) authentication.getPrincipal());
        }
        return result;
    }

    private void encodeUserPassword(User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
    }
}
