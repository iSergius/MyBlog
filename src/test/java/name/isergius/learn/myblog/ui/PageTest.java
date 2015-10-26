package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.dao.Portion;
import name.isergius.learn.myblog.domain.Article;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Kondratyev Sergey on 25.10.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class PageTest extends Assert {

    @Mock
    private Portion<Article> portion;
    @Mock
    private List<Article> articles;
    private Page page;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPageCount() throws Exception {
        when(portion.result(0L, 10L)).thenReturn(articles);
        when(portion.count()).thenReturn(20L);

        page = new Page<Article>(portion,10L);

        assertEquals(new Long(2), page.count());
    }

    @Test
    public void testReturnSecondPage() throws Exception {
        when(portion.result(10L,10L)).thenReturn(articles);
        when(portion.count()).thenReturn(20L);
        when(articles.size()).thenReturn(10);

        page = new Page<Article>(portion,10L);

        assertEquals(10,page.result(2L).size());
    }

    @Test
    public void testReturnFirstPage() throws Exception {
        when(portion.result(0L,10L)).thenReturn(articles);
        when(portion.count()).thenReturn(20L);
        when(articles.size()).thenReturn(10);

        page = new Page<Article>(portion,10L);

        assertEquals(10,page.result(1L).size());
    }
}
