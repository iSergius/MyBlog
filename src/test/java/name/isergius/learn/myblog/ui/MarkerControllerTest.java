package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.Marker;
import name.isergius.learn.myblog.domain.Note;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Kondratyev Sergey on 23.10.15.
 */

public class MarkerControllerTest extends AbstractControllerTest {

    private MarkerController markerController;
    @Mock
    private Note note;
    @Mock
    private List<Marker> markers;
    @Mock
    private List<Article> articles;
    @Mock
    private Marker marker;
    @Mock
    private Article article;

    @Before
    public void setUp() throws Exception {
        markerController = new MarkerController();
        Mockito.when(httpServletRequest.getRequestDispatcher("/index.jsp")).thenReturn(requestDispatcher);
        Mockito.when(httpServletRequest.getServletContext()).thenReturn(servletContext);
        Mockito.when(servletContext.getAttribute("note")).thenReturn(note);
        Mockito.when(marker.getArticles()).thenReturn(articles);
        Mockito.when(note.getPublishedMarkerBy(1)).thenReturn(marker);
        Mockito.when(note.getAllPublishedMarkers()).thenReturn(markers);
        Mockito.when(marker.getTitle()).thenReturn("News");
    }

    @Test
    public void testRoute() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1");

        markerController.doGet(httpServletRequest,httpServletResponse);

        Mockito.verify(requestDispatcher).forward(Matchers.any(HttpServletRequest.class), Matchers.any(HttpServletResponse.class));
    }

    @Test
    public void testGettingMarkerFromNote() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1");

        markerController.doGet(httpServletRequest,httpServletResponse);

        Mockito.verify(note).getPublishedMarkerBy(1L);
    }

    @Test
    public void testSetArticlesInRequestAttribute() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1");

        markerController.doGet(httpServletRequest,httpServletResponse);

        Mockito.verify(httpServletRequest).setAttribute("articles",articles);
    }
    @Test
    public void testWrongIdUrl() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1a");

        markerController.doGet(httpServletRequest,httpServletResponse);

        Mockito.verify(httpServletResponse).sendError(404);
    }

    @Test
    public void testNullIdUrl() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn(null);

        markerController.doGet(httpServletRequest,httpServletResponse);

        Mockito.verify(httpServletResponse).sendError(404);
    }

    @Test
    public void testSettingMarkersInRequestAttribute() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1");

        markerController.doGet(httpServletRequest,httpServletResponse);

        Mockito.verify(httpServletRequest).setAttribute("markers", markers);
    }

    @Test
    public void testSetTitle() throws Exception {
        Mockito.when(httpServletRequest.getPathInfo()).thenReturn("/1");

        markerController.doGet(httpServletRequest,httpServletResponse);

        Mockito.verify(httpServletRequest).setAttribute("title", "News");
    }
}
