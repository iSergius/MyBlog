package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.ArticleDao;
import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.dao.Portion;
import name.isergius.learn.myblog.ui.Page;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 04.11.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class BlogsServiceTest {

    @Mock
    private MarkerDao markerDao;
    @Mock
    private ArticleDao articleDao;
    @Mock
    private List<Marker> markers;
    @Mock
    private List<Article> articles;
    @Mock
    private Portion<Marker> markerPortion;
    @Mock
    private Portion<Article> articlePortion;
    @Mock
    private Page<Marker> markerPage;
    @Mock
    private Page<Article> articlePage;
    @Mock
    private Marker marker;
    @Mock
    private Article article;

    private BlogService blogService = null;

    @Before
    public void setUp() throws Exception {
        BlogServiceImpl blogImpl = new BlogServiceImpl();
        blogImpl.setMarkerDao(markerDao);
        blogImpl.setArticleDao(articleDao);
        blogService = blogImpl;
    }

    @Test
    public void testGettingPublishedMarker() throws Exception {
        Mockito.when(markerDao.readBy(1L, true)).thenReturn(marker);

        Marker marker = blogService.retrieveMarkerBy(1L);

        Assert.assertNotNull(marker);
    }

    @Test
    public void testGetAllPublishedMarkers() throws Exception {
        Mockito.when(markerDao.readAll(true)).thenReturn(markerPortion);

        List<Marker> markers = blogService.retrieveAllMarkers();

        Assert.assertNotNull(markers);
    }

    @Test
    public void testGetPublishedArticleBy() throws Exception {
        Mockito.when(articleDao.readBy(1L,true)).thenReturn(article);
        Mockito.when(article.getPublished()).thenReturn(true);

        Article article = blogService.retrieveArticleBy(1L);

        Assert.assertTrue(article.getPublished());
    }

    @Test
    public void testGettingAllPublishedArticles() throws Exception {
        Mockito.when(articleDao.readAll(true)).thenReturn(articlePortion);
        Mockito.when(articlePortion.result(0L,10L)).thenReturn(articles);
        Mockito.when(articlePortion.count()).thenReturn(20L);

        Page<Article> articles = blogService.retrieveArticles(10L);

        Assert.assertNotNull(articles);
    }

    @Test
    public void testGetArticlesHasMarkerById() throws Exception {
        Mockito.when(articleDao.readByMarker(1L,true)).thenReturn(articlePortion);
        Mockito.when(articlePortion.result(0L,10L)).thenReturn(articles);
        Mockito.when(articlePortion.count()).thenReturn(20L);

        Page<Article> articlePage = blogService.retrieveArticlesHasMarkerBy(1L, 10L);

        Assert.assertNotNull(articlePage);
    }
}
