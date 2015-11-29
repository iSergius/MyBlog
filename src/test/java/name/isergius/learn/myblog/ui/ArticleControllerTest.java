package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.BlogService;
import name.isergius.learn.myblog.domain.Marker;
import name.isergius.learn.myblog.domain.NoteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.GenericXmlContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Kondratyev Sergey on 16.10.15.
 */

@ContextConfiguration(loader = GenericXmlContextLoader.class,
        locations = {"classpath:spring/webmvc-config.xml",
                "classpath:spring/spring-config.xml",
                "classpath:test-spring-config.xml",
                "classpath:spring/security-config.xml",
                "classpath:spring/aop-config.xml"})
public class ArticleControllerTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @InjectMocks
    private ArticleController articleController;
    @Mock
    private NoteService noteService;
    @Mock
    private BlogService blogService;

    private MockMvc mockMvc;

    private List<Article> articles = new ArrayList<>();
    private List<Marker> markers = new ArrayList<>();
    private Article article;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
        MockitoAnnotations.initMocks(this);
        articles.add(new Article());
        article = Mockito.mock(Article.class);
        Page<Marker> markersPage = Mockito.mock(Page.class);
        Page<Article> articlePage = Mockito.mock(Page.class);
        Mockito.when(article.getTitle()).thenReturn("My First Article");
        Mockito.when(noteService.getMarkers(1000L)).thenReturn(markersPage);
        Mockito.when(noteService.getArticleBy(1L)).thenReturn(article);
        Mockito.when(markersPage.result(0L)).thenReturn(markers);
        Mockito.when(articlePage.result(10L)).thenReturn(articles);
        Mockito.when(blogService.retrieveArticles(10L)).thenReturn(articlePage);
        Mockito.when(blogService.retrieveAllMarkers()).thenReturn(markers);
        Mockito.when(blogService.retrieveArticleBy(1L)).thenReturn(article);
        Mockito.when(blogService.getTitle()).thenReturn("MyBlog");
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

        Mockito.verify(noteService).save(article);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/article/1/delete"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/note"));

        Mockito.verify(noteService).deleteArticle(1L);
    }
}
