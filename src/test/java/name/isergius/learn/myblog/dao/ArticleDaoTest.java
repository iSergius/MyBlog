package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.Article;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

/**
 * Created by Kondratyev Sergey on 02.10.15.
 */

public class ArticleDaoTest extends AbstractDbTest {

    @SpringBean("articleDao")
    private ArticleDaoHibernate dao;

    @Test
    @DataSet
    public void testReadById() {
        Article article = dao.readBy(1L);
        assertEquals("My Firs Article", article.getTitle());
    }

}
