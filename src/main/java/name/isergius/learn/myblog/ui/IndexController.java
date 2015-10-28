package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.Blog;
import name.isergius.learn.myblog.domain.Marker;
import name.isergius.learn.myblog.domain.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ModelAndView doGet() {
        ModelAndView modelAndView = new ModelAndView("index");
        Note note = blog.getNote();
        List<Article> articles = note.getAllPublishedArticles();
        List<Marker> markers = note.getAllPublishedMarkers();
        modelAndView.addObject("title", blog.getTitle());
        modelAndView.addObject("articles", articles);
        modelAndView.addObject("markers",markers);

        return modelAndView;
    }
}
