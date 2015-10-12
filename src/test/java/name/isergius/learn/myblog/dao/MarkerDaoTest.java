package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.Marker;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.dbunit.datasetloadstrategy.impl.CleanInsertLoadStrategy;
import org.unitils.spring.annotation.SpringBean;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 11.10.15.
 */

public class MarkerDaoTest extends AbstractDbTest {

    @SpringBean("markerDao")
    private MarkerDao dao;

    @Test
    @DataSet
    public void testReadById() throws DaoException {
        Marker marker = dao.readBy(1L);
        assertEquals("My Firs Marker", marker.getTitle());
    }

    @Test
    @ExpectedDataSet
    public void testCreateWithSetId() throws Exception {
        Marker marker = new Marker("My Second Marker");
        marker = dao.create(marker);
        assertNotNull(marker.getId());
    }

    @Test
    @DataSet
    @ExpectedDataSet
    public void testUpdate() throws Exception {
        Marker marker = new Marker("Changed title");
        marker.setId(1L);
        dao.update(marker);
    }

    @Test
    @ExpectedDataSet
    @DataSet(loadStrategy = CleanInsertLoadStrategy.class)
    public void testDelete() throws Exception {
        dao.deleteBy(1L);
    }

    @Test(expected = DaoException.class)
    public void testReadNotContainEntity() throws Exception {
        Marker marker = dao.readBy(2L);
    }

    @DataSet({"MarkerDaoTest.testDelete-result.xml"})
    @ExpectedDataSet({"MarkerDaoTest.testDelete-result.xml"})
    @Test(expected = DaoException.class)
    public void testCreateWithNotEmptyId() throws Exception {
        Marker marker = new Marker("Wrong marker");
        marker.setId(2L);
        dao.create(marker);
    }

    @DataSet({"MarkerDaoTest.testDelete-result.xml"})
    @ExpectedDataSet({"MarkerDaoTest.testDelete-result.xml"})
    @Test(expected = DaoException.class)
    public void testUpdateNotContainEntity() throws Exception {
        Marker marker = new Marker("New marker");
        marker.setId(2L);
        dao.update(marker);
    }

    @DataSet({"MarkerDaoTest.testDelete-result.xml"})
    @ExpectedDataSet({"MarkerDaoTest.testDelete-result.xml"})
    @Test(expected = DaoException.class)
    public void testDeleteNotContainEntity() throws Exception {
        dao.deleteBy(2L);
    }

    @Test
    @DataSet("ArticleDaoTest.testContainMarkers.xml")
    public void testContainArticles() throws Exception {
        Marker marker = dao.readBy(1L);
        List<Article> articles = marker.getArticles();
        assertEquals("My Firs Article", articles.get(0).getTitle());
    }

}
