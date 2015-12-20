package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Kondratyev Sergey on 25.10.15.
 */
@ContextConfiguration(loader = GenericXmlContextLoader.class,
        locations = {"classpath:spring/webmvc-config.xml",
                "classpath:spring/spring-config.xml",
                "classpath:test-spring-config.xml",
                "classpath:spring/security-config.xml",
                "classpath:spring/aop-config.xml"})
public class NoteControllerTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @InjectMocks
    private NoteController noteController;
    @Mock
    private NoteService noteService;
    @Mock
    private BlogService blogService;
    @Mock
    private ConfigurationService configurationService;

    private List<Article> articles = new ArrayList<>();
    private List<Marker> markers = new ArrayList<>();
    private Page<Article> articlePage;
    private Page<Marker> markerPage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        articles = Mockito.mock(articles.getClass());
        markers = Mockito.mock(markers.getClass());
        articlePage = Mockito.mock(Page.class);
        markerPage = Mockito.mock(Page.class);
        Mockito.when(configurationService.getProperty(NoteController.ARTICLES_PAGE_LENGTH,Long.class)).thenReturn(10L);
        Mockito.when(configurationService.getProperty(NoteController.MARKERS_PAGE_LENGTH,Long.class)).thenReturn(10L);
        Mockito.when(noteService.getArticles(10L)).thenReturn(articlePage);
        Mockito.when(noteService.getMarkers(10L)).thenReturn(markerPage);
        Mockito.stub(articlePage.result()).toReturn(articles);
        Mockito.stub(markerPage.result()).toReturn(markers);
        Mockito.when(blogService.getTitle()).thenReturn("MyBlog");
    }

    @Test
    public void testSetArticlesInModel() throws Exception {
        ModelAndView modelAndView = noteController.main(0L,0L);
        Map<String, Object> model = modelAndView.getModel();

        Assert.assertTrue(model.containsKey("articles"));
        Page<Article> articles = (Page<Article>)model.get("articles");
        Assert.assertEquals(this.articlePage, articles);
    }

    @Test
    public void testSetMarkersInModel() throws Exception {
        ModelAndView modelAndView = noteController.main(0L,0L);
        Map<String, Object> model = modelAndView.getModel();

        Assert.assertTrue(model.containsKey("markers"));
        Page<Marker> markers = (Page<Marker>)model.get("markers");
        Assert.assertEquals(this.markerPage, markers);

    }

    @Test
    public void testSetTitleInModel() throws Exception {
        ModelAndView modelAndView = noteController.main(0L,0L);
        Map<String, Object> model = modelAndView.getModel();
        Assert.assertTrue(model.containsKey("title"));
        Assert.assertEquals("MyBlog",model.get("title"));
    }
}
