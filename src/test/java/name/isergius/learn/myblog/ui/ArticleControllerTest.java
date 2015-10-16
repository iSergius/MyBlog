package name.isergius.learn.myblog.ui;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Kondratyev Sergey on 16.10.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleControllerTest {

    private ArticleController articleController;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() throws Exception {
        articleController = new ArticleController();
        Mockito.when(httpServletRequest.getRequestDispatcher("article.jsp")).thenReturn(requestDispatcher);
    }

    @Test
    public void testArticleViewRoute() throws Exception {
        articleController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(requestDispatcher).forward(Matchers.any(HttpServletRequest.class),Matchers.any(HttpServletResponse.class));
    }
}
