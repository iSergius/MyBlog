package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.ArticleDao;
import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.dao.Portion;
import name.isergius.learn.myblog.ui.Page;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
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
    @Mock
    private Portion<Article> articlePortion;
    @Mock
    private Portion<Marker> markerPortion;
    @Mock
    private Page<Article> articlePage;
    @Mock
    private Page<Marker> markerPage;

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
    public void testGettingAllPublishedArticles() throws Exception {
        Mockito.when(articleDao.readAll(true)).thenReturn(articles);

        List<Article> articles = note.getAllPublishedArticles();

        assertNotNull(articles);
    }

    @Test
    public void testGettingAllArticles() throws Exception {
        Mockito.when(articleDao.read()).thenReturn(articlePortion);
        Mockito.when(articlePortion.result(0L,10L)).thenReturn(articles);

        Page<Article> articles = note.getArticles(10L);

        assertNotNull(articles);
    }

    @Test
    public void testGettingAllMarkers() throws Exception {
        Mockito.when(markerDao.read()).thenReturn(markerPortion);
        Mockito.when(markerPortion.result(0L,10L)).thenReturn(markers);

        Page<Marker> markers = note.getMarkers(10L);

        assertNotNull(markers);
    }

    @Test
    public void testGetArticleById() throws Exception {
        Mockito.when(articleDao.readBy(1L)).thenReturn(article);

        Article article = note.getArticleBy(1L);

        assertNotNull(article);
    }

    @Test
    public void testSaveArticle() throws Exception {
        note.save(article);

        Mockito.verify(articleDao).update(article);
    }

    @Test
    public void testSavePublishedArticle() throws Exception {
        Mockito.when(article.getPublished()).thenReturn(true);

        note.save(article);

        Mockito.verify(article).setPublishedDate(Matchers.any(LocalDate.class));
    }
}
