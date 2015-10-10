package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.Article;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
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

    @Test
    @ExpectedDataSet
    public void testCreateWithSetId() throws Exception {
        Article article = new Article("My Second Article");
        article = dao.create(article);
        assertNotNull(article.getId());
    }

    @Test
    @ExpectedDataSet
    public void testUpdate() throws Exception {
        Article article = new Article("Changed title");
        article.setId(1l);
        dao.update(article);
    }
}
