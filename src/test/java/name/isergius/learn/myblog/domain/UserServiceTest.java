package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.UserDao;
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

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kondratyev Sergey on 11.11.15.
 */

@ContextConfiguration(loader = SpringockitoContextLoader.class,
        locations = {"classpath:spring/webmvc-config.xml","classpath:spring/spring-config.xml","classpath:test-spring-config.xml"})
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @ReplaceWithMock
    @Autowired
    private UserDao userDao;
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
}
