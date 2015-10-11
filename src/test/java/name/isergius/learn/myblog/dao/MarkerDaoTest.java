package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.Marker;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
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

}
