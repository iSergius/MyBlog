package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Authority;
import name.isergius.learn.myblog.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kondratyev Sergey on 28.11.15.
 */
public class UserInformationFormTest {

    private static final String DEFAULT_USERNAME = "admin@localhost";
    private static final String DEFAULT_PASSWORD = "password";
    private static final String DEFAULT_FULLNAME = "Ivanov Ivan Ivanovich";
    private static final String DEFAULT_ABOUT = "Writer";
    private UserInformationForm userInformationForm;
    private User user;

    @Before
    public void setUp() throws Exception {
        userInformationForm = new UserInformationForm();
        user = new User();
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new Authority("ROLE_ADMIN"));

        userInformationForm.setUserName("admin@localhost");
        userInformationForm.setId(1L);
        userInformationForm.setFullName("Ivanov Ivan Ivanovich");
        userInformationForm.setAbout("Writer");
        userInformationForm.setNewEmail("");
        userInformationForm.setConfirmEmail("");
        userInformationForm.setNewPassword("");
        userInformationForm.setConfirmPassword("");
        userInformationForm.setPassword("password");

        user.setId(1L);
        user.setUsername("admin@localhost");
        user.setPassword("$2a$10$eQV9Qknz5Gk6t8hJZQtTTOXg0XEid75uPZoX9Ga08dYF6prTODW6K");
        user.setAuthorities(authorities);
        user.setEnabled(true);
        user.setFullName("Ivanov Ivan Ivanovich");
        user.setAbout("Writer");
        user.setEmail("admin@localhost");
    }

    @Test
    public void testExportToUserAbout() throws Exception {
        final String ABOUT = "Admin";
        userInformationForm.setAbout(ABOUT);

        userInformationForm.exportTo(user);

        Assert.assertEquals(ABOUT,user.getAbout());
        checkEmail(user);
        checkFullName(user);
        checkPassword(user);
        checkUsername(user);
    }

    @Test
    public void testExportToUserFullName() throws Exception {
        final String FULLNAME = "Petrov Petr Petrovich";
        userInformationForm.setFullName(FULLNAME);

        userInformationForm.exportTo(user);

        Assert.assertEquals(FULLNAME,user.getFullName());
        checkEmail(user);
        checkAbout(user);
        checkPassword(user);
        checkUsername(user);
    }

    @Test
    public void testExportToUserUserName() throws Exception {
        final String USERNAME = "admin@localhost.local";
        userInformationForm.setUserName(USERNAME);

        userInformationForm.exportTo(user);

        Assert.assertEquals(USERNAME,user.getUsername());
        checkEmail(user);
        checkAbout(user);
        checkPassword(user);
        checkFullName(user);
    }

    @Test
    public void testExportToUserEmail() throws Exception {
        final String EMAIL = "admin@localhost.local";
        userInformationForm.setNewEmail(EMAIL);
        userInformationForm.setConfirmEmail(EMAIL);

        userInformationForm.exportTo(user);

        Assert.assertEquals(EMAIL,user.getEmail());
        checkUsername(user);
        checkAbout(user);
        checkPassword(user);
        checkFullName(user);
    }

    @Test
    public void testExportToUserPassword() throws Exception {
        final String PASSWORD = "password123";
        userInformationForm.setNewPassword(PASSWORD);
        userInformationForm.setConfirmPassword(PASSWORD);

        userInformationForm.exportTo(user);

        Assert.assertEquals(PASSWORD,user.getPassword());
        checkUsername(user);
        checkAbout(user);
        checkEmail(user);
        checkFullName(user);
    }

    private void checkUsername(User user) throws Exception {
        Assert.assertEquals(DEFAULT_USERNAME,user.getUsername());
    }
    private void checkPassword(User user) throws Exception {
        Assert.assertEquals(DEFAULT_PASSWORD,user.getPassword());
    }
    private void checkFullName(User user) throws Exception {
        Assert.assertEquals(DEFAULT_FULLNAME,user.getFullName());
    }
    private void checkAbout(User user) throws Exception {
        Assert.assertEquals(DEFAULT_ABOUT,user.getAbout());
    }
    private void checkEmail(User user) throws Exception {
        Assert.assertEquals(DEFAULT_USERNAME,user.getEmail());
    }
}
