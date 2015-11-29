package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.User;
import name.isergius.learn.myblog.domain.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by Kondratyev Sergey on 22.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/webmvc-config.xml",
                    "classpath:spring/spring-config.xml",
                    "classpath:test-spring-config.xml",
                    "classpath:spring/security-config.xml",
                    "classpath:spring/aop-config.xml"})
public class UserControllerTest {

    @Autowired
    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    @Mock
    private User user;
    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowUserInformationPage() throws Exception {
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        String view = userController.showUserInformation();
        Assert.assertEquals("user", view);
    }

    @Test
    public void testShowEditUserInformationPage() throws Exception {
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        String view = userController.editUserInformation();
        Assert.assertEquals("user-editor", view);

    }

    @Test
    public void testSaveUserInformation() throws Exception {
        final String USERNAME = "admin@localhost";
        final String PASSWORD = "password";
        final String EMAIL = "admin@localhost";
        final String FULLNAME = "Ivanov Ivan Ivanovich";
        final String ABOUT = "Writer";

        User user = new User();
        user.setUsername(USERNAME);
        user.setFullName(FULLNAME);
        user.setEmail(EMAIL);
        user.setAbout(ABOUT);
        UserDetails userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(userDetails.getUsername()).thenReturn(USERNAME);
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        mockMvc.perform(post("/user/edit")
                        .sessionAttr("user",user)
                        .contentType("application/x-www-form-urlencoded")
                        .param("id","1")
                        .param("fullName", FULLNAME)
                        .param("about", ABOUT)
                        .param("userName", USERNAME)
                        .param("newEmail","")
                        .param("confirmEmail","")
                        .param("newPassword","")
                        .param("confirmPassword","")
                        .param("password", PASSWORD))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user"));

        Mockito.verify(userService).updateUser(Matchers.any(UserInformationForm.class));
    }

    @Test
    public void testSaveWrongUserInformation() throws Exception {
        User user = new User();
        user.setFullName("");
        mockMvc.perform(post("/user/edit").sessionAttr("user",user).requestAttr("confirmEmail","dddd"))
                .andExpect(MockMvcResultMatchers.status().is(200));
        Mockito.verify(userService,Mockito.never()).updateUser(Matchers.any(UserInformationForm.class));
    }
}
