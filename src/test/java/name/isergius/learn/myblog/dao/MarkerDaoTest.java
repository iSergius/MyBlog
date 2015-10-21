package name.isergius.learn.myblog.dao;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.Marker;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 11.10.15.
 */

public class MarkerDaoTest extends AbstractDbTest {

    @Autowired
    @Qualifier("markerDao")
    private MarkerDao dao;

    @After
    @DatabaseTearDown(value = "MarkerDaoTest.testDelete-result.xml",type = DatabaseOperation.DELETE)
    public void tearDown() throws Exception {

    }

    @Test
    @DatabaseSetup(value = "MarkerDaoTest.xml")
    public void testReadById() throws DaoException {
        Marker marker = dao.readBy(1L);
        assertEquals("My Firs Marker", marker.getTitle());
    }

    @Test
    @ExpectedDatabase(value = "MarkerDaoTest.testCreateWithSetId-result.xml",table = "marker_t",assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testCreateWithSetId() throws Exception {
        Marker marker = new Marker("My Second Marker");
        marker = dao.create(marker);
        assertNotNull(marker.getId());
    }

    @Test
    @DatabaseSetup(value = "MarkerDaoTest.xml")
    @ExpectedDatabase(value = "MarkerDaoTest.testUpdate-result.xml",table = "marker_t")
    public void testUpdate() throws Exception {
        Marker marker = new Marker("Changed title");
        marker.setId(1L);
        dao.update(marker);
    }

    @Test
    @DatabaseSetup(value = "MarkerDaoTest.xml")
    @ExpectedDatabase(value = "MarkerDaoTest.testDelete-result.xml",table = "marker_t")
    public void testDelete() throws Exception {
        dao.deleteBy(1L);
    }

    @Test(expected = DaoException.class)
    public void testReadNotContainEntity() throws Exception {
        Marker marker = dao.readBy(2L);
    }

    @Test(expected = DaoException.class)
    @DatabaseSetup(value = "MarkerDaoTest.testDelete-result.xml")
    @ExpectedDatabase(value = "MarkerDaoTest.testDelete-result.xml",table = "marker_t")
    public void testCreateWithNotEmptyId() throws Exception {
        Marker marker = new Marker("Wrong marker");
        marker.setId(2L);
        dao.create(marker);
    }

    @Test(expected = DaoException.class)
    @DatabaseSetup(value = "MarkerDaoTest.testDelete-result.xml")
    @ExpectedDatabase(value = "MarkerDaoTest.testDelete-result.xml",table = "marker_t")
    public void testUpdateNotContainEntity() throws Exception {
        Marker marker = new Marker("New marker");
        marker.setId(2L);
        dao.update(marker);
    }

    @Test(expected = DaoException.class)
    @DatabaseSetup(value = "MarkerDaoTest.testDelete-result.xml")
    @ExpectedDatabase(value = "MarkerDaoTest.testDelete-result.xml",table = "marker_t")
    public void testDeleteNotContainEntity() throws Exception {
        dao.deleteBy(2L);
    }

    @Test
    @DatabaseSetup(value = "ArticleDaoTest.testContainMarkers.xml")
    public void testContainArticles() throws Exception {
        Marker marker = dao.readBy(1L);
        List<Article> articles = marker.getArticles();
        assertEquals("My Firs Article", articles.get(0).getTitle());
    }

}
