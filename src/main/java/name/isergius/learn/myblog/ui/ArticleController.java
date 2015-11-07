package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.Blog;
import name.isergius.learn.myblog.domain.Marker;
import name.isergius.learn.myblog.domain.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kondratyev Sergey on 16.10.15.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private Blog blog;
    @Autowired
    private MarkerDao markerDao;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") long articleId) {
        ModelAndView modelAndView = new ModelAndView("article");
        Note note = blog.getNote();
        Article article = blog.getArticleBy(articleId);
        List<Marker> markers = blog.getAllMarkers();

        modelAndView.addObject("title", article.getTitle());
        modelAndView.addObject("article", article);
        modelAndView.addObject("markers", markers);

        return modelAndView;
    }

    @RequestMapping(path = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("article-editor");
        Note note = blog.getNote();
        Article article = note.getArticleBy(id);
        Page<Marker> markers = note.getMarkers(1000L);

        modelAndView.addObject("markers",markers.result(0L));
        modelAndView.addObject("title", article.getTitle());
        modelAndView.addObject("article",article);

        return modelAndView;
    }

    @RequestMapping(path = {"/{id}/edit","/edit"}, method = RequestMethod.POST)
    public ModelAndView save(Article article) {
        ModelAndView modelAndView = new ModelAndView();
        Note note = blog.getNote();
        Page<Marker> markers = note.getMarkers(1000L);
        note.save(article);

        modelAndView.addObject("markers",markers.result(0L));
        modelAndView.addObject("title", article.getTitle());
        modelAndView.addObject("article", article);
        modelAndView.setView(new RedirectView("edit"));
        return modelAndView;
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("article-editor");
        Note note = blog.getNote();
        Page<Marker> markers = note.getMarkers(1000L);
        modelAndView.addObject("markers",markers.result(0L));
        modelAndView.addObject("title", "New Article");
        Article article = new Article();
        modelAndView.addObject("article", article);
        return modelAndView;
    }

    @InitBinder
    public void binder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new LocalDatePropertyEditor("yyyy-MM-dd"));
        binder.registerCustomEditor(List.class, "markers", new MarkerCollectionFormBinder<>(markerDao,List.class));
    }

}
