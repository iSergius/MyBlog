package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
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
    @Qualifier("blogService")
    private BlogService blogService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private MarkerDao markerDao;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") long articleId) {
        ModelAndView modelAndView = new ModelAndView("article");

        Article article = blogService.retrieveArticleBy(articleId);
        List<Marker> markers = blogService.retrieveAllMarkers();

        modelAndView.addObject("title", article.getTitle());
        modelAndView.addObject("article", article);
        modelAndView.addObject("markers", markers);

        return modelAndView;
    }

    @RequestMapping(path = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("article-editor");
        Article article = noteService.getArticleBy(id);
        Page<Marker> markers = noteService.getMarkers(1000L);

        modelAndView.addObject("markers",markers.result(0L));
        modelAndView.addObject("title", article.getTitle());
        modelAndView.addObject("article",article);

        return modelAndView;
    }

    @RequestMapping(path = {"/{id}/edit","/edit"}, method = RequestMethod.POST)
    public ModelAndView save(Article article) {
        ModelAndView modelAndView = new ModelAndView();
        Page<Marker> markers = noteService.getMarkers(1000L);
        noteService.save(article);

        modelAndView.addObject("markers",markers.result(0L));
        modelAndView.addObject("title", article.getTitle());
        modelAndView.addObject("article", article);
        modelAndView.setView(new RedirectView("/article/"+article.getId()+"/edit",false));
        return modelAndView;
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("article-editor");
        Page<Marker> markers = noteService.getMarkers(1000L);
        modelAndView.addObject("markers",markers.result(0L));
        modelAndView.addObject("title", "New Article");
        Article article = new Article();
        modelAndView.addObject("article", article);
        return modelAndView;
    }

    @RequestMapping(path = "/{id}/delete", method = RequestMethod.POST)
    public ModelAndView delete(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        noteService.deleteArticle(id);
        modelAndView.setView(new RedirectView("/note"));
        return modelAndView;
    }

    @InitBinder
    public void binder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new LocalDatePropertyEditor("yyyy-MM-dd"));
        binder.registerCustomEditor(List.class, "markers", new MarkerCollectionFormBinder<>(markerDao,List.class));
    }

}
