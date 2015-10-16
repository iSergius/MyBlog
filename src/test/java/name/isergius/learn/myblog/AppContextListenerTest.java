package name.isergius.learn.myblog;

import name.isergius.learn.myblog.domain.Note;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.unitils.UnitilsJUnit4;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * Created by Kondratyev Sergey on 15.10.15.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-config.xml","classpath:test-spring-config.xml"})
public class AppContextListenerTest extends UnitilsJUnit4 {

    private AppContextListener appContextListener;
    private ServletContext servletContext;
    private ServletContextEvent servletContextEvent;

    @Before
    public void setUp() throws Exception {
        servletContext = Mockito.mock(ServletContext.class);
        servletContextEvent = Mockito.mock(ServletContextEvent.class);
        Mockito.when(servletContextEvent.getServletContext()).thenReturn(servletContext);
        appContextListener = new AppContextListener();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddNoteAttribute() throws Exception {;
        appContextListener.contextInitialized(servletContextEvent);
        Mockito.verify(servletContext).setAttribute(Matchers.same("note"), Matchers.any(Note.class));
    }
}
