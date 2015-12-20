package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.BlogService;
import name.isergius.learn.myblog.domain.ConfigurationService;
import name.isergius.learn.myblog.domain.Marker;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by Kondratyev Sergey on 22.10.15.
 */

@ContextConfiguration(loader = GenericXmlContextLoader.class,
        locations = {"classpath:spring/webmvc-config.xml",
                "classpath:spring/spring-config.xml",
                "classpath:test-spring-config.xml",
                "classpath:spring/security-config.xml",
                "classpath:spring/aop-config.xml"})
public class IndexControllerTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @InjectMocks
    private IndexController indexController;
    @Mock
    private BlogService blogService;
    @Mock
    private ConfigurationService configurationService;
    @Mock
    private Page<Article> articles;
    @Mock
    private List<Marker> markers;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(blogService.retrieveArticles(10L)).thenReturn(articles);
        Mockito.when(blogService.retrieveAllMarkers()).thenReturn(markers);
        Mockito.when(blogService.getTitle()).thenReturn("MyBlog");
        Mockito.when(configurationService.getProperty(IndexController.ARTICLE_PAGE_LENGTH,Long.class)).thenReturn(10L);
    }

    @Test
    public void testSetArticlesInModel() throws Exception {
        ModelAndView modelAndView = indexController.main(null);
        Map<String, Object> model = modelAndView.getModel();

        Assert.assertTrue(model.containsKey("articles"));
        Page<Article> articles = (Page<Article>)model.get("articles");
        Assert.assertEquals(this.articles, articles);
    }

    @Test
    public void testSetMarkersInModel() throws Exception {
        ModelAndView modelAndView = indexController.main(null);
        Map<String, Object> model = modelAndView.getModel();

        Assert.assertTrue(model.containsKey("markers"));
        List<Marker> markers = (List<Marker>)model.get("markers");
        Assert.assertEquals(this.markers, markers);

    }

    @Test
    public void testSetTitleInModel() throws Exception {
        ModelAndView modelAndView = indexController.main(null);
        Map<String, Object> model = modelAndView.getModel();
        Assert.assertTrue(model.containsKey("title"));
        Assert.assertEquals("MyBlog",model.get("title"));
    }
}
