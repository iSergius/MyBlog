package name.isergius.learn.myblog.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import name.isergius.learn.myblog.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by Kondratyev Sergey on 11.11.15.
 */
public class UserDaoTest extends AbstractDbTest {

    @Autowired
    @Qualifier("userDao")
    private UserDao dao;

    @Test
    @DatabaseSetup("LoadUserByUsername.xml")
    public void testReadByUsername() throws Exception {
        User user = dao.readBy("admin@localhost");
        assertNotNull(user);
        assertNotNull(user.getAuthorities());
        assertEquals(1,user.getAuthorities().size());
        assertEquals("password", user.getPassword());
        assertEquals(true,user.isEnabled());
    }

}
