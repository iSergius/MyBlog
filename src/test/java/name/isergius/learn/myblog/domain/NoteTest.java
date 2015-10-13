package name.isergius.learn.myblog.domain;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

/**
 * Created by Kondratyev Sergey on 13.10.15.
 */
public class NoteTest extends AbstractSpringContext {

    @SpringBean("note")
    private Note note;

    @Test
    public void testGetPublishedArticleBy() throws Exception {
        Article article = note.getPublishedArticleBy(1L);
        assertTrue(article.isPublished());
    }
}
