package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.Marker;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBean;

/**
 * Created by Kondratyev Sergey on 11.10.15.
 */

public class MarkerDaoTest extends AbstractDbTest {

    @SpringBean("markerDao")
    private MarkerDaoHibernate dao;

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
/*
    @Test
    @ExpectedDataSet
    @DataSet(loadStrategy = CleanInsertLoadStrategy.class)
    public void testDelete() throws Exception {
        dao.deleteBy(1L);
    }

    @Test(expected = DaoException.class)
    public void testReadNotContainEntity() throws Exception {
        Markier marker = dao.readBy(2L);
    }

    @DataSet({"MarkierDaoTest.testDelete-result.xml"})
    @ExpectedDataSet({"MarkierDaoTest.testDelete-result.xml"})
    @Test(expected = DaoException.class)
    public void testCreateWithNotEmptyId() throws Exception {
        Markier marker = new Markier("Wrong marker");
        marker.setId(2L);
        dao.create(marker);
    }
    @DataSet({"MarkierDaoTest.testDelete-result.xml"})
    @ExpectedDataSet({"MarkierDaoTest.testDelete-result.xml"})
    @Test(expected = DaoException.class)
    public void testUpdateNotContainEntity() throws Exception {
        Markier marker = new Markier("New marker");
        marker.setId(2L);
        dao.update(marker);
    }

    @DataSet({"MarkierDaoTest.testDelete-result.xml"})
    @ExpectedDataSet({"MarkierDaoTest.testDelete-result.xml"})
    @Test(expected = DaoException.class)
    public void testDeleteNotContainEntity() throws Exception {
        dao.deleteBy(2L);
    }*/
}
