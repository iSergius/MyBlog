package name.isergius.learn.myblog.ui;

import com.codeborne.selenide.Selectors;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Kondratyev Sergey on 22.10.15.
 */
public class PageIndexIntegrationTest extends Assert {

    @Test
    public void testShowIndexPage() throws Exception {
        open("/");
        assertEquals("h1", $(Selectors.byText("MyBlog")).getTagName());
        $(".markers").$$(".marker").shouldHaveSize(2);
        $$("article").shouldHaveSize(2);
        assertEquals("iSergius Copyright 2015", $(By.tagName("footer")).getText());
    }
}
