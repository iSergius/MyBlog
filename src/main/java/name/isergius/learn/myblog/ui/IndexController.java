package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.BlogService;
import name.isergius.learn.myblog.domain.ConfigurationService;
import name.isergius.learn.myblog.domain.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 22.10.15.
 */
@Controller
@RequestMapping(path = {"/"})
public class IndexController {

    public static final String ARTICLE_PAGE_LENGTH = "name.isergius.learn.myblog.ui.IndexController.pageLength.Long";

    @Autowired
    @Qualifier("blogService")
    private BlogService blogService;
    @Autowired
    @Qualifier("configurationService")
    private ConfigurationService configurationService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main(@RequestParam(name = "page", required = false) Long page) {
        if (page == null) page = 0L;
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Article> articlePage = blogService.retrieveArticles(configurationService.getProperty(ARTICLE_PAGE_LENGTH,Long.class));
        articlePage.setPage(page);
        List<Marker> markers = blogService.retrieveAllMarkers();
        modelAndView.addObject("title", blogService.getTitle());
        modelAndView.addObject("blogTitle", blogService.getTitle());
        modelAndView.addObject("articles", articlePage);
        modelAndView.addObject("markers",markers);

        return modelAndView;
    }

    @RequestMapping(path = "/filter/marker/{id}", method = RequestMethod.GET)
    public ModelAndView filter(@PathVariable("id") long id, @RequestParam(name = "page", required = false) Long page) {
        if (page == null) page = 0L;
        ModelAndView modelAndView = new ModelAndView("index");
        Marker marker = blogService.retrieveMarkerBy(id);
        Page<Article> articlePage = blogService.retrieveArticlesHasMarkerBy(id, configurationService.getProperty(ARTICLE_PAGE_LENGTH,Long.class));
        articlePage.setPage(page);
        List<Marker> markers = blogService.retrieveAllMarkers();
        modelAndView.addObject("title", marker.getTitle());
        modelAndView.addObject("blogTitle", marker.getTitle());
        modelAndView.addObject("articles", articlePage);
        modelAndView.addObject("markers", markers);
        return  modelAndView;
    }

}
