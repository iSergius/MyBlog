package name.isergius.learn.myblog.dao;

import org.junit.Assert;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;

/**
 * Created by Kondratyev Sergey on 02.10.15.
 */

public class ArticleDaoTest {

    private ArticleDaoHibernate dao;

    @Test
    @DataSet
    public void testReadById() {
        Article article = dao.readBy(1L);
        Assert.assertEquals("My Firs Article", article.getTitle());
    }

}
