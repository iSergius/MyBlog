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

import java.util.Date;
import java.util.List;

/**
 * Created by Kondratyev Sergey on 13.10.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest extends Assert {

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

    private NoteService noteService = null;


    @Before
    public void setUp() throws Exception {
        noteService = new NoteServiceImpl(articleDao,markerDao);
    }

    @Test
    public void testGettingAllArticles() throws Exception {
        Mockito.when(articleDao.read()).thenReturn(articlePortion);
        Mockito.when(articlePortion.result(0L,10L)).thenReturn(articles);

        Page<Article> articles = noteService.getArticles(10L);

        assertNotNull(articles);
    }

    @Test
    public void testGettingAllMarkers() throws Exception {
        Mockito.when(markerDao.read()).thenReturn(markerPortion);
        Mockito.when(markerPortion.result(0L,10L)).thenReturn(markers);

        Page<Marker> markers = noteService.getMarkers(10L);

        assertNotNull(markers);
    }

    @Test
    public void testGetArticleById() throws Exception {
        Mockito.when(articleDao.readBy(1L)).thenReturn(article);

        Article article = noteService.getArticleBy(1L);

        assertNotNull(article);
    }

    @Test
    public void testSaveArticle() throws Exception {
        Mockito.when(article.getId()).thenReturn(1L);

        noteService.save(article);

        Mockito.verify(articleDao).update(article);
    }

    @Test
    public void testSavePublishedArticle() throws Exception {
        Mockito.when(article.getPublished()).thenReturn(true);

        noteService.save(article);

        Mockito.verify(article).setPublishedDate(Matchers.any(Date.class));
    }

    @Test
    public void testSaveNewArticle() throws Exception {
        Mockito.when(article.getId()).thenReturn(null);

        noteService.save(article);

        Mockito.verify(articleDao).create(article);
    }
}
