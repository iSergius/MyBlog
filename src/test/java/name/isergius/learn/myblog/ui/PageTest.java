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
        when(portion.result(0L,10L)).thenReturn(articles);
        when(portion.count()).thenReturn(20L);
        when(articles.size()).thenReturn(10);

        page = new Page<Article>(portion,10L);

        assertEquals(10,page.result().size());
    }

    @Test
    public void testReturnFirstPage() throws Exception {
        when(portion.result(0L,10L)).thenReturn(articles);
        when(portion.count()).thenReturn(20L);
        when(articles.size()).thenReturn(10);

        page = new Page<Article>(portion,10L);

        assertEquals(10,page.result().size());
    }

    @Test
    public void testPaginationMinThenRange() throws Exception {
        when(portion.count()).thenReturn(20L);

        page = new Page<Article>(portion,10L);
        page.setPagination(2L);

        assertEquals(new Long(2), page.count());
        assertEquals(new Long(1), page.beginPagination());
        assertEquals(new Long(2), page.endPagination());
        assertEquals(new Long(2), page.forwardPagination());
        assertEquals(new Long(1), page.backwardPagination());
    }

    @Test
    public void testPaginationMaxThenRangeWithRightMove() throws Exception {
        when(portion.count()).thenReturn(60L);

        page = new Page<Article>(portion,10L);
        page.setPage(4L);
        page.setPagination(2L);

        assertEquals(new Long(6), page.count());
        assertEquals(new Long(2), page.beginPagination());
        assertEquals(new Long(6), page.endPagination());
        assertEquals(new Long(6), page.forwardPagination());
        assertEquals(new Long(1), page.backwardPagination());
    }

    @Test
    public void testPaginationMaxThenRangeWithLeftMove() throws Exception {
        when(portion.count()).thenReturn(60L);

        page = new Page<Article>(portion,10L);
        page.setPage(2L);
        page.setPagination(2L);

        assertEquals(new Long(6), page.count());
        assertEquals(new Long(1), page.beginPagination());
        assertEquals(new Long(5), page.endPagination());
        assertEquals(new Long(6), page.forwardPagination());
        assertEquals(new Long(1), page.backwardPagination());
    }

    @Test
    public void testPagination() throws Exception {
        when(portion.count()).thenReturn(100L);

        page = new Page<Article>(portion,10L);
        page.setPage(5L);
        page.setPagination(2L);

        assertEquals(new Long(10), page.count());
        assertEquals(new Long(3), page.beginPagination());
        assertEquals(new Long(7), page.endPagination());
        assertEquals(new Long(8), page.forwardPagination());
        assertEquals(new Long(2), page.backwardPagination());
    }

    @Test
    public void testPaginationWithReturnEmptyPortion() throws Exception {
        when(portion.count()).thenReturn(0L);

        page = new Page<Article>(portion,10L);

        assertEquals(new Long(0), page.count());
        assertEquals(new Long(0), page.beginPagination());
        assertEquals(new Long(0), page.endPagination());
        assertEquals(new Long(0), page.forwardPagination());
        assertEquals(new Long(0), page.backwardPagination());
    }
}
