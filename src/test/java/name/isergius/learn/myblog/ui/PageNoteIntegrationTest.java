package name.isergius.learn.myblog.ui;

import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Kondratyev Sergey on 26.10.15.
 */
public class PageNoteIntegrationTest {

    @Test
    public void testShowPage() throws Exception {
        open("/note/");
        logIn();
        $("#articlesTable").$$("tr").shouldHaveSize(5+1);
        $("#markersTable").$$("tr").shouldHaveSize(3);
    }

    public void logIn() throws Exception {
        $(By.name("username")).append("admin@localhost");
        $(By.name("password")).append("password");
        $(By.name("submit")).click();
    }
}
