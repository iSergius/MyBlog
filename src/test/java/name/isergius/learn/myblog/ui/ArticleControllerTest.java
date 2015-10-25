package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
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
    @Mock
    private ServletContext servletContext;
    @Mock
    private Note note;
    @Mock
    private Article article;
    @Mock
    private List<Marker> markers;

    @Before
    public void setUp() throws Exception {
        articleController = new ArticleController();
        Mockito.when(httpServletRequest.getRequestDispatcher("/article.jsp")).thenReturn(requestDispatcher);
        Mockito.when(httpServletRequest.getServletContext()).thenReturn(servletContext);
        Mockito.when(servletContext.getAttribute("note")).thenReturn(note);
        Mockito.when(note.getPublishedArticleBy(1L)).thenReturn(article);
        Mockito.when(note.getAllPublishedMarkers()).thenReturn(markers);
    }

    @Test
    public void testArticleViewRoute() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1");
        articleController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(requestDispatcher).forward(Matchers.any(HttpServletRequest.class), Matchers.any(HttpServletResponse.class));
    }

    @Test
    public void testGettingRightArticle() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1");
        articleController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(note).getPublishedArticleBy(1L);
    }

    @Test
    public void testWrongIdUrl() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1a");
        articleController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(httpServletResponse).sendError(404);
    }

    @Test
    public void testNullIdUrl() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn(null);
        articleController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(httpServletResponse).sendError(404);
    }

    @Test
    public void testSettingArticleInRequestAttribute() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1");
        articleController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(httpServletRequest).setAttribute("article", article);
    }

    @Test
    public void testSettingMarkersInRequestAttribute() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1");
        articleController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(httpServletRequest).setAttribute("markers", markers);
    }
    @Test
    public void testSetTitle() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1");
        Mockito.when(article.getTitle()).thenReturn("My First Article");

        articleController.doGet(httpServletRequest,httpServletResponse);

        Mockito.verify(httpServletRequest).setAttribute("title", "My First Article");
    }
}
