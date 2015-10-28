package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.Blog;
import name.isergius.learn.myblog.domain.Marker;
import name.isergius.learn.myblog.domain.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 16.10.15.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private Blog blog;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    protected ModelAndView doGet(@PathVariable("id") long articleId) {
        ModelAndView modelAndView = new ModelAndView("article");
        Note note = blog.getNote();
        Article article = note.getPublishedArticleBy(articleId);
        List<Marker> markers = note.getAllPublishedMarkers();

        modelAndView.addObject("title", article.getTitle());
        modelAndView.addObject("article", article);
        modelAndView.addObject("markers", markers);

        return modelAndView;
    }

}
