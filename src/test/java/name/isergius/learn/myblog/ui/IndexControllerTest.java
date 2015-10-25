package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.Blog;
import name.isergius.learn.myblog.domain.Marker;
import name.isergius.learn.myblog.domain.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Kondratyev Sergey on 22.10.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    private IndexController indexController;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private ServletContext servletContext;
    @Mock
    private Blog blog;
    @Mock
    private Note note;
    @Mock
    private List<Article> articles;
    @Mock
    private List<Marker> markers;

    @Before
    public void setUp() throws Exception {
        indexController = new IndexController();
        Mockito.when(httpServletRequest.getRequestDispatcher("/index.jsp")).thenReturn(requestDispatcher);
        Mockito.when(httpServletRequest.getServletContext()).thenReturn(servletContext);
        Mockito.when(servletContext.getAttribute("note")).thenReturn(note);
        Mockito.when(servletContext.getAttribute("blog")).thenReturn(blog);
        Mockito.when(note.getAllPublishedArticles()).thenReturn(articles);
        Mockito.when(note.getAllPublishedMarkers()).thenReturn(markers);
        Mockito.when(blog.getTitle()).thenReturn("MyBlog");
    }

    @Test
    public void testArticleViewRoute() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1");
        indexController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(requestDispatcher).forward(Matchers.any(HttpServletRequest.class), Matchers.any(HttpServletResponse.class));
    }

    @Test
    public void testSettingPublishedArticlesInRequestAttribute() throws Exception {
        indexController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(httpServletRequest).setAttribute("articles",articles);
    }

    @Test
    public void testSettingPublishedMarkers() throws Exception {
        indexController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(httpServletRequest).setAttribute("markers", markers);
    }
    @Test
    public void testSetTitle() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1");

        indexController.doGet(httpServletRequest,httpServletResponse);

        Mockito.verify(httpServletRequest).setAttribute("title", "MyBlog");
    }
}
