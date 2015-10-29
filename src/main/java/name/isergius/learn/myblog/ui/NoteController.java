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

/**
 * Created by Kondratyev Sergey on 25.10.15.
 */
@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private Blog blog;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("note");
        Note note = blog.getNote();
        Page<Article> articles = note.getArticles(10L);
        Page<Marker> markers = note.getMarkers(10L);
        modelAndView.addObject("articles",articles.result(0L));
        modelAndView.addObject("markers", markers.result(0L));
        modelAndView.addObject("title",blog.getTitle());
        return modelAndView;
    }

}
