package name.isergius.learn.myblog.ui;

import com.codeborne.selenide.Selectors;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Kondratyev Sergey on 17.10.15.
 */

public class PageArticleIntegrationTest extends Assert {
    //@Ignore
    @Test
    public void testShowArticle() throws Exception {
        open("/article/1");
        assertEquals("h1", $(Selectors.byText("MyBlog")).getTagName());
        $("header").$(".markers").$$("span").shouldHaveSize(2);
        assertEquals("h2", $(Selectors.byText("My First Article")).getTagName());
        assertEquals("My First Article", $("article").findElement(By.tagName("h2")).getText());
        assertEquals("Article content", $("article").findElement(By.tagName("p")).getText());
        assertEquals("2015-10-21",$("article").findElement(By.className("date")).getText());
        assertEquals("News",$("article").findElement(By.className("marker")).getText());
        assertEquals("iSergius Copyright 2015", $(By.tagName("footer")).getText());
    }
}
