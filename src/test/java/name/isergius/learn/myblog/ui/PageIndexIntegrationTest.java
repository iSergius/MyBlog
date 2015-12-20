package name.isergius.learn.myblog.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Kondratyev Sergey on 22.10.15.
 */
public class PageIndexIntegrationTest extends Assert {

    @Test
    public void testShowIndexPage() throws Exception {
        open("/");
        $(".markers").$$(".marker").shouldHaveSize(2);
        $$("article").shouldHaveSize(10);
        assertEquals("iSergius Copyright 2015", $(By.tagName("footer")).getText());
    }

    @Test
    public void testJumpToMarkerPage() throws Exception {
        open("/");
        $(".markers").$(Selectors.byText("News")).followLink();
        assertEquals("News",title());
    }

    @Test
    public void testShowFilterMarker() throws Exception {
        open("/filter/marker/1");
        $$("article").filter(Condition.text("News")).shouldHaveSize($$("article").size());
    }
}
