package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.Blog;
import name.isergius.learn.myblog.domain.Marker;
import name.isergius.learn.myblog.domain.Note;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Blog blog;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("index");
        Note note = blog.getNote();
        Page<Article> articles = blog.getArticles(10L);
        List<Marker> markers = blog.getAllMarkers();
        modelAndView.addObject("title", blog.getTitle());
        modelAndView.addObject("articles", articles.result(0L));
        modelAndView.addObject("markers",markers);

        return modelAndView;
    }

    @RequestMapping(params = "marker", method = RequestMethod.GET)
    public ModelAndView filter(@RequestParam("marker") long id) {
        ModelAndView modelAndView = new ModelAndView("index");
        Note note = blog.getNote();
        Marker marker = blog.getMarkerBy(id);
        Page<Article> articlePage = blog.getArticlesHasMarkerBy(id,10L);
        List<Marker> markers = blog.getAllMarkers();
        modelAndView.addObject("title", marker.getTitle());
        modelAndView.addObject("articles", articlePage.result(0L));
        modelAndView.addObject("markers", markers);
        return  modelAndView;
    }

}
