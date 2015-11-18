package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.AuthorityDao;
import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.dao.Portion;
import name.isergius.learn.myblog.dao.UserDao;
import name.isergius.learn.myblog.ui.Page;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Kondratyev Sergey on 11.11.15.
 */

@ContextConfiguration(loader = SpringockitoContextLoader.class,
        locations = {"classpath:spring/webmvc-config.xml",
                "classpath:spring/spring-config.xml",
                "classpath:test-spring-config.xml",
                "classpath:spring/security-config.xml",
                "classpath:spring/aop-config.xml"})
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @ReplaceWithMock
    @Autowired
    private UserDao userDao;
    @ReplaceWithMock
    @Autowired
    private AuthorityDao authorityDao;
    private User user;
    private Set<GrantedAuthority> authorities = new HashSet<>();
    private GrantedAuthority authority;

    @Before
    public void setUp() throws Exception {
        user = Mockito.mock(User.class);
        authority = Mockito.mock(GrantedAuthority.class);
    }

    @Test
    public void testLoadUserByUsername() throws Exception {
        Mockito.when(userDao.readBy("admin@localhost")).thenReturn(user);
        Mockito.when(authority.getAuthority()).thenReturn("ROLE_ADMIN");
        Mockito.when(user.getUsername()).thenReturn("admin@localhost");
        Mockito.when(user.getPassword()).thenReturn("password");
        Mockito.when(user.isEnabled()).thenReturn(true);
        Mockito.when(user.getAuthorities()).thenReturn(authorities);
        authorities.add(authority);

        UserDetails userDetails = userService.loadUserByUsername("admin@localhost");

        Assert.assertNotNull(userDetails);
        Assert.assertEquals("password", userDetails.getPassword());
        Assert.assertTrue(userDetails.isEnabled());
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User("admin@localhost", "password");
        User userMock = Mockito.mock(User.class);
        Mockito.when(userMock.getId()).thenReturn(1L);
        Mockito.when(userMock.getPassword()).thenReturn("password");
        Mockito.when(userDao.create(user)).thenReturn(userMock);
        Mockito.when(userDao.readBy(user.getUsername())).thenThrow(DaoException.class);

        User result = userService.createUser(user);

        Assert.assertNotNull(result.getId());
        Mockito.verify(userDao).create(user);
    }

    @Test(expected = UserServiceException.class)
    public void testCreateUserWithContainUsername() throws Exception {
        User user = new User("admin@localhost", "password");
        User userMock = Mockito.mock(User.class);
        Mockito.when(userMock.getId()).thenReturn(1L);
        Mockito.when(userMock.getPassword()).thenReturn("password");
        Mockito.when(userDao.create(user)).thenReturn(userMock);
        Mockito.when(userDao.readBy(user.getUsername())).thenReturn(Mockito.mock(User.class));

        User result = userService.createUser(user);

        Mockito.verify(userDao,Mockito.never()).create(user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User("admin@gmail.com", "12345");
        user.setId(1L);

        userService.updateUser(user);

        Mockito.verify(userDao).update(user);
    }

    @Test
    public void testGetAllAuthorities() throws Exception {
        Portion<Authority> authorityPortion = Mockito.mock(Portion.class);
        Authority authority = Mockito.mock(Authority.class);
        Mockito.when(authority.getTitle()).thenReturn("ROLE_ADMIN");
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        Mockito.when(authorityDao.read()).thenReturn(authorityPortion);
        Mockito.when(authorityPortion.result(0L,0L)).thenReturn(authorities);

        List<Authority> testResult = userService.getAllAuthorities();

        Assert.assertNotNull(testResult);
        Assert.assertEquals("ROLE_ADMIN",testResult.get(0).getTitle());
    }

    @Test
    public void testGetUsers() throws Exception {
        Portion<User> userPortion = Mockito.mock(Portion.class);
        Mockito.when(userPortion.count()).thenReturn(10L);
        Mockito.when(userDao.read()).thenReturn(userPortion);

        Page<User> userPage = userService.getUsers(10L);

        Assert.assertNotNull(userPage);
    }
}
