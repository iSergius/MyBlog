package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.ArticleDao;
import name.isergius.learn.myblog.dao.MarkerDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 13.10.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class NoteTest extends Assert {

    @Mock
    private ArticleDao articleDao;
    @Mock
    private MarkerDao markerDao;
    @Mock
    private Article article;
    @Mock
    private Marker marker;
    @Mock
    private List<Article> articles;
    @Mock
    private List<Marker> markers;

    private Note note = null;

    @Before
    public void setUp() throws Exception {
        note = new Note(articleDao,markerDao);
    }

    @Test
    public void testGetPublishedArticleBy() throws Exception {
        Mockito.when(articleDao.readBy(1L,true)).thenReturn(article);
        Mockito.when(article.getPublished()).thenReturn(true);

        Article article = note.getPublishedArticleBy(1L);

        assertTrue(article.getPublished());
    }

    @Test
    public void testGetAllPublishedMarkers() throws Exception {
        Mockito.when(markerDao.readAll(true)).thenReturn(markers);

        List<Marker> markers = note.getAllPublishedMarkers();

        assertNotNull(markers);
    }

    @Test
    public void testGettingAllPublishedArticles() throws Exception {
        Mockito.when(articleDao.readAll(true)).thenReturn(articles);

        List<Article> articles = note.getAllPublishedArticles();

        assertNotNull(articles);
    }

    @Test
    public void testGettingPublishedMarker() throws Exception {
        Mockito.when(markerDao.readBy(1L,true)).thenReturn(marker);

        Marker marker = note.getPublishedMarkerBy(1L);

        assertNotNull(marker);
    }
}
