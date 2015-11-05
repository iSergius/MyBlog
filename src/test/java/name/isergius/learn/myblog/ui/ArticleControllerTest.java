package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.Blog;
import name.isergius.learn.myblog.domain.Marker;
import name.isergius.learn.myblog.domain.Note;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by Kondratyev Sergey on 16.10.15.
 */
@Ignore
@ContextConfiguration(loader = SpringockitoContextLoader.class,
        locations = {"classpath:spring/webmvc-config.xml","classpath:spring/spring-config.xml","classpath:test-spring-config.xml"})
public class ArticleControllerTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ArticleController articleController;
    @ReplaceWithMock
    @Autowired
    private Note note;
    @ReplaceWithMock
    @Autowired
    private Blog blog;
    private List<Article> articles;
    private List<Marker> markers;
    private Article article;

    @Before
    public void setUp() throws Exception {
        articles = Mockito.mock(List.class);
        markers = Mockito.mock(List.class);
        article = Mockito.mock(Article.class);
        Page<Marker> markersPage = Mockito.mock(Page.class);
        Mockito.when(article.getTitle()).thenReturn("My First Article");
        Mockito.when(articles.get(0)).thenReturn(Article.class.newInstance());
        Mockito.when(note.getAllPublishedArticles()).thenReturn(articles);
        Mockito.when(blog.getAllMarkers()).thenReturn(markers);
        Mockito.when(note.getMarkers(1000L)).thenReturn(markersPage);
        Mockito.when(markersPage.result(0L)).thenReturn(markers);
        Mockito.when(note.getPublishedArticleBy(1L)).thenReturn(article);
        Mockito.when(note.getArticleBy(1L)).thenReturn(article);
        Mockito.when(blog.getNote()).thenReturn(note);
        Mockito.when(blog.getTitle()).thenReturn("MyBlog");
    }

    @Test
    public void testShowSetArticlesInModel() throws Exception {
        ModelAndView modelAndView = articleController.show(1L);
        Map<String, Object> model = modelAndView.getModel();

        Assert.assertTrue(model.containsKey("article"));
        Article article = (Article)model.get("article");
        Assert.assertEquals(this.article, article);
    }

    @Test
    public void testShowSetMarkersInModel() throws Exception {
        ModelAndView modelAndView = articleController.show(1L);
        Map<String, Object> model = modelAndView.getModel();

        Assert.assertTrue(model.containsKey("markers"));
        List<Marker> markers = (List<Marker>)model.get("markers");
        Assert.assertEquals(this.markers, markers);

    }

    @Test
    public void testShowSetTitleInModel() throws Exception {
        ModelAndView modelAndView = articleController.show(1L);
        Map<String, Object> model = modelAndView.getModel();
        Assert.assertTrue(model.containsKey("title"));
        Assert.assertEquals("My First Article",model.get("title"));
    }

    @Test
    public void testEditSetArticleFor() throws Exception {
        ModelAndView modelAndView = articleController.edit(1L);
        Map<String, Object> model = modelAndView.getModel();

        Assert.assertTrue(model.containsKey("article"));
        Article article = (Article)model.get("article");
        Assert.assertEquals(this.article, article);
    }

    @Test
    public void testEditSetTitleFor() throws Exception {
        ModelAndView modelAndView = articleController.edit(1L);
        Map<String, Object> model = modelAndView.getModel();

        Assert.assertTrue(model.containsKey("title"));
        String title = (String)model.get("title");
        Assert.assertEquals(this.article.getTitle(), title);
    }

    @Test
    public void testEditSaveArticle() throws Exception {
        ModelAndView modelAndView = articleController.save(article);

        Mockito.verify(note).save(article);
    }
}
