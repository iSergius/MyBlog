package name.isergius.learn.myblog.dao;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.Marker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 02.10.15.
 */

public class ArticleDaoTest extends AbstractDbTest {

    @Autowired
    @Qualifier("articleDao")
    private ArticleDao dao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    @DatabaseTearDown(value = "ArticleDaoTest.testDelete-result.xml",type = DatabaseOperation.DELETE_ALL)
    public void tearDown() throws Exception {
    }

    @Test
    @DatabaseSetup("ArticleDaoTest.xml")
    public void testReadById() throws DaoException {
        Article article = dao.readBy(1L);
        assertEquals("My Firs Article", article.getTitle());
    }

    @Test
    @ExpectedDatabase(value = "ArticleDaoTest.testCreateWithSetId-result.xml",table = "article_t",assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testCreateWithSetId() throws Exception {
        Article article = new Article("My Second Article");
        article = dao.create(article);
        assertNotNull(article.getId());
    }

    @Test
    @DatabaseSetup("ArticleDaoTest.xml")
    @ExpectedDatabase()
    public void testUpdate() throws Exception {
        Article article = new Article("Changed title");
        article.setId(1l);
        dao.update(article);
    }

    @Test
    @DatabaseSetup("ArticleDaoTest.xml")
    @ExpectedDatabase(value = "ArticleDaoTest.testDelete-result.xml",table = "article_t")
    public void testDelete() throws Exception {
        dao.deleteBy(1L);
    }

    @Test(expected = DaoException.class)
    public void testReadNotContainEntity() throws Exception {
        Article article = dao.readBy(2L);
    }

    @Test(expected = DaoException.class)
    @DatabaseSetup(value = "ArticleDaoTest.testDelete-result.xml")
    @ExpectedDatabase(value = "ArticleDaoTest.testDelete-result.xml",table = "article_t")
    public void testCreateWithNotEmptyId() throws Exception {
        Article article = new Article("Wrong article");
        article.setId(2L);
        dao.create(article);
    }

    @Test(expected = DaoException.class)
    @ExpectedDatabase(value = "ArticleDaoTest.testDelete-result.xml",table = "article_t")
    public void testUpdateNotContainEntity() throws Exception {
        Article article = new Article("New article");
        article.setId(2L);
        dao.update(article);
    }

    @Test(expected = DaoException.class)
    @DatabaseSetup(value = "ArticleDaoTest.testDelete-result.xml")
    @ExpectedDatabase(value = "ArticleDaoTest.testDelete-result.xml",table = "article_t")
    public void testDeleteNotContainEntity() throws Exception {
        dao.deleteBy(2L);
    }

    @Test
    @DatabaseSetup("ArticleDaoTest.testContainMarkers.xml")
    public void testContainMarkers() throws Exception {
        Article article = dao.readBy(2L);
        List<Marker> markers = article.getMarkers();
        assertEquals("News", markers.get(0).getTitle());
    }

    @Test
    @DatabaseSetup("ArticleDaoTest.testReadPublishedBy.xml")
    public void testReadPublishedBy() throws Exception {
        Article article = dao.readBy(1L, true);
        assertTrue(article.getPublished());
    }

    @Test
    @DatabaseSetup("ArticleDaoTest.testReadPublishedBy.xml")
    public void testReadAllPublished() throws Exception {
        List<Article> articles = dao.readAll(true);
        assertNotNull(articles);
        assertTrue(articles.get(0).getPublished());
    }
}
