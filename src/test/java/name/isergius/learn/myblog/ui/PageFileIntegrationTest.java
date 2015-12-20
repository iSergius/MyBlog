package name.isergius.learn.myblog.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Kondratyev Sergey on 19.12.15.
 */
public class PageFileIntegrationTest {

    @Before
    public void setUp() throws Exception {
        logIn();
    }

    @After
    public void tearDown() throws Exception {
        logOut();
    }

    @Test
    public void testShowFilePage() throws Exception {
        open("/file");

        Assert.assertEquals("File Manager", title());
    }

    @Test
    public void testUploadFile() throws Exception {
        open("/file");
        $$("input").findBy(Condition.type("file")).uploadFile(new File("src/test/resources/hackerman.jpg"));

        $(".btn-success").click();
        Assert.assertEquals("hackerman.jpg",title());
    }

    @Test
    public void testEditFile() throws Exception {
        open("/file");
        $(".btn-primary").click();
        Assert.assertEquals("picture.jpg",title());
    }

    public void logIn() throws Exception {
        open("/login");
        $(By.name("username")).append("admin@localhost");
        $(By.name("password")).append("password");
        $(By.name("submit")).click();
    }

    public void logOut() throws Exception {
        $(Selectors.byValue("Log out")).click();
    }

}
