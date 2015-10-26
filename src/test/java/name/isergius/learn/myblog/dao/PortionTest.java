package name.isergius.learn.myblog.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import name.isergius.learn.myblog.dao.hibernate.PortionHibernate;
import name.isergius.learn.myblog.domain.Article;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        Session session = sessionFactory.openSession();
        Query selectQuery = session.createQuery("from Article");
        Query countQuery = session.createQuery("select count (a) from Article a");

        portion = new PortionHibernate<>(session,selectQuery,countQuery);

        assertEquals(new Long(20), portion.count());
    }

    @Test
    @DatabaseSetup("PortionTest.xml")
    public void testResult() {
        Session session = sessionFactory.openSession();
        Query selectQuery = session.createQuery("from Article");
        Query countQuery = session.createQuery("select count (a) from Article a");

        portion = new PortionHibernate<>(session,selectQuery,countQuery);
        List<Article> articles = portion.result(0L,10L);

        assertNotNull(articles);
        assertEquals(Long.valueOf(1), articles.get(0).getId());
    }
}
