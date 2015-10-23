package name.isergius.learn.myblog.ui;

import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Kondratyev Sergey on 23.10.15.
 */
public class PageMarkerIntegrationTest extends Assert {

    @Test
    public void testShowMarker() throws Exception {
        final String NEWS = "news";
        open("/marker/1");
        $$("article").filter(Condition.text(NEWS)).shouldHaveSize($$("article").size());
    }
}
