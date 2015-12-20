package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 16.10.15.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    public static final String MARKERS_CLOUD_PAGE_LENGTH = "name.isergius.learn.myblog.ui.ArticleController.markersCloudPageLength.Long";
    public static final String LOCALDATE_FORMAT = "name.isergius.learn.myblog.ui.ArticleController.localDateFormat.String";

    @Autowired
    @Qualifier("configurationService")
    private ConfigurationService configurationService;
    @Autowired
    @Qualifier("blogService")
    private BlogService blogService;
    @Autowired
    @Qualifier("noteService")
    private NoteService noteService;
    @Autowired
    private MarkerDao markerDao;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
        Page<Marker> markers = noteService.getMarkers(configurationService.getProperty(MARKERS_CLOUD_PAGE_LENGTH,Long.class));

        modelAndView.addObject("markers",markers.result());
        modelAndView.addObject("title", article.getTitle());
        modelAndView.addObject("article",article);

        return modelAndView;
    }

    @RequestMapping(path = {"/{id}/edit","/edit"}, method = RequestMethod.POST)
    public ModelAndView save(Article article) {
        ModelAndView modelAndView = new ModelAndView();
        Page<Marker> markers = noteService.getMarkers(configurationService.getProperty(MARKERS_CLOUD_PAGE_LENGTH,Long.class));
        noteService.save(article);

        modelAndView.addObject("markers",markers.result());
        modelAndView.addObject("title", article.getTitle());
        modelAndView.addObject("article", article);
        modelAndView.setView(new RedirectView("/note"));
        return modelAndView;
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("article-editor");
        Page<Marker> markers = noteService.getMarkers(configurationService.getProperty(MARKERS_CLOUD_PAGE_LENGTH,Long.class));
        modelAndView.addObject("markers",markers.result());
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
        //binder.registerCustomEditor(LocalDate.class, new LocalDatePropertyEditor(configurationService.getProperty(LOCALDATE_FORMAT)));
        binder.registerCustomEditor(List.class, "markers", new MarkerCollectionFormBinder<>(markerDao,List.class));
    }

}
