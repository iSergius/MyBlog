package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 22.10.15.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    @Qualifier("blogService")
    private BlogService blogService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Article> articles = blogService.retrieveArticles(10L);
        List<Marker> markers = blogService.retrieveAllMarkers();
        modelAndView.addObject("title", blogService.getTitle());
        modelAndView.addObject("articles", articles.result(0L));
        modelAndView.addObject("markers",markers);

        return modelAndView;
    }

    @RequestMapping(params = "marker", method = RequestMethod.GET)
    public ModelAndView filter(@RequestParam("marker") long id) {
        ModelAndView modelAndView = new ModelAndView("index");
        Marker marker = blogService.retrieveMarkerBy(id);
        Page<Article> articlePage = blogService.retrieveArticlesHasMarkerBy(id, 10L);
        List<Marker> markers = blogService.retrieveAllMarkers();
        modelAndView.addObject("title", marker.getTitle());
        modelAndView.addObject("articles", articlePage.result(0L));
        modelAndView.addObject("markers", markers);
        return  modelAndView;
    }

}
