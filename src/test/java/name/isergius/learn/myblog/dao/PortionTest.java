package name.isergius.learn.myblog.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import name.isergius.learn.myblog.dao.hibernate.PortionHibernate;
import name.isergius.learn.myblog.domain.Article;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Kondratyev Sergey on 26.10.15.
 */
public class PortionTest extends AbstractDbTest {

    private Portion<Article> portion;
    @Autowired
    private SessionFactory sessionFactory;
    private String selectQuery;
    private String countQuery;

    @Before
    public void setUp() throws Exception {


    }

    @Test
    @DatabaseSetup("PortionTest.xml")
    public void testCount() {
        String selectQuery = "from Article";
        String count = "select count (a) from Article a";
        portion = new PortionHibernate<>(sessionFactory,selectQuery,count,new HashMap<String,Object>());

        assertEquals(new Long(20), portion.count());
    }

    @Test
    @DatabaseSetup("PortionTest.xml")
    public void testResult() {
        String selectQuery = "from Article";
        String count = "select count (a) from Article a";
        portion = new PortionHibernate<>(sessionFactory,selectQuery,count,new HashMap<String,Object>());

        List<Article> articles = portion.result(0L,10L);

        assertNotNull(articles);
        assertEquals(Long.valueOf(1), articles.get(0).getId());
    }
}
