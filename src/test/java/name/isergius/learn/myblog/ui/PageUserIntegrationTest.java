package name.isergius.learn.myblog.ui;

import com.codeborne.selenide.Selectors;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Kondratyev Sergey on 21.11.15.
 */
public class PageUserIntegrationTest {
    @Before
    public void logIn() throws Exception {
        open("/login");
        $(By.name("username")).append("admin@localhost");
        $(By.name("password")).append("password");
        $(By.name("submit")).click();
    }

    @After
    public void logOut() throws Exception {
        $(Selectors.byValue("Log out")).click();
    }

    @Test
    public void testShowUserProfile() throws Exception {
        open("/user");
        Assert.assertTrue($(Selectors.byText("Ivanov Petr Vasilevich")).exists());
    }

    @Test
    public void test() throws Exception {

    }
}
