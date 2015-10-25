package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.Marker;
import name.isergius.learn.myblog.domain.Note;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Kondratyev Sergey on 25.10.15.
 */
public class NoteControllerTest extends AbstractControllerTest {

    private NoteController noteController;
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
    @Mock
    private List<Article> articles;

    @Before
    public void setUp() throws Exception {
        noteController = new NoteController();
        Mockito.when(httpServletRequest.getRequestDispatcher("/note.jsp")).thenReturn(requestDispatcher);
        Mockito.when(httpServletRequest.getServletContext()).thenReturn(servletContext);
        Mockito.when(servletContext.getAttribute("note")).thenReturn(note);
        Mockito.when(note.getAllArticles()).thenReturn(articles);
        Mockito.when(note.getAllMarkers()).thenReturn(markers);
    }

    @Test
    public void testRoute() throws Exception {
        noteController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(requestDispatcher).forward(httpServletRequest,httpServletResponse);
    }

    @Test
    public void testGetAllArticles() throws Exception {
        noteController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(note).getAllArticles();
    }

    @Test
    public void testGetAllMarkers() throws Exception {
        noteController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(note).getAllMarkers();
    }

    @Test
    public void testSetArticles() throws Exception {
        noteController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(httpServletRequest).setAttribute("articles",articles);
    }

    @Test
    public void testSetMarkers() throws Exception {
        noteController.doGet(httpServletRequest,httpServletResponse);
        Mockito.verify(httpServletRequest).setAttribute("markers",markers);
    }

}
