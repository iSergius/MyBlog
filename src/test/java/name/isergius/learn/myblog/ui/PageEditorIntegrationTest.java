package name.isergius.learn.myblog.ui;

import com.codeborne.selenide.Condition;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import name.isergius.learn.myblog.dao.AbstractDbTest;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Kondratyev Sergey on 26.10.15.
 */
public class PageEditorIntegrationTest extends AbstractDbTest {

    @Test
    public void testShowEditor() throws Exception {
        open("/article/1/edit");
        assertEquals("My First Article",title());
    }

    @Test
    @ExpectedDatabase(table = "article_t",assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSaveArticle() throws Exception {
        open("/article/1/edit");
        $("form").$(".btn").click();
    }

    @Test
    public void testNewArticle() throws Exception {
        open("/article/new");
        $("form").$(By.name("title")).shouldBe(Condition.empty);
    }
}
