package name.isergius.learn.myblog.domain;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by Kondratyev Sergey on 13.10.15.
 */
public class NoteTest extends AbstractSpringContext {

    @Autowired
    @Qualifier("note")
    private Note note;

    @Test
    public void testGetPublishedArticleBy() throws Exception {
        Article article = note.getPublishedArticleBy(1L);
        assertTrue(article.getPublished());
    }
}
