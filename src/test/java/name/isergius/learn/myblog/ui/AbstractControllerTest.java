package name.isergius.learn.myblog.ui;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Kondratyev Sergey on 23.10.15.
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractControllerTest {

    @Mock
    protected HttpServletRequest httpServletRequest;
    @Mock
    protected HttpServletResponse httpServletResponse;
    @Mock
    protected RequestDispatcher requestDispatcher;
    @Mock
    protected ServletContext servletContext;


}
