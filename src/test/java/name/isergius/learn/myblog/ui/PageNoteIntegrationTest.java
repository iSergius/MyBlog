package name.isergius.learn.myblog.ui;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Kondratyev Sergey on 26.10.15.
 */
public class PageNoteIntegrationTest {

    @Test
    public void testShowPage() throws Exception {
        open("/note/");
        $("#articlesTable").$$("tr").shouldHaveSize(11);
        $("#markersTable").$$("tr").shouldHaveSize(3);
    }
}
