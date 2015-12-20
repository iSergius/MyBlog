package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Kondratyev Sergey on 25.10.15.
 */
@Controller
@RequestMapping("/note")
public class NoteController {

    public static final String ARTICLES_PAGE_LENGTH = "name.isergius.learn.myblog.ui.NoteController.articlesPageLength.Long";
    public static final String MARKERS_PAGE_LENGTH = "name.isergius.learn.myblog.ui.NoteController.markersPageLength.Long";

    @Autowired
    @Qualifier("configurationService")
    private ConfigurationService configurationService;
    @Autowired
    @Qualifier("noteService")
    private NoteService noteService;
    @Autowired
    @Qualifier("blogService")
    private BlogService blogService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main(@RequestParam(name = "articlePage", required = false, defaultValue = "0") Long articlePage,
                             @RequestParam(name = "markerPage", required = false, defaultValue = "0") Long markerPage) {
        ModelAndView modelAndView = new ModelAndView("note");
        Page<Article> articles = noteService.getArticles(configurationService.getProperty(ARTICLES_PAGE_LENGTH,Long.class));
        Page<Marker> markers = noteService.getMarkers(configurationService.getProperty(MARKERS_PAGE_LENGTH,Long.class));

        articles.setPage(articlePage);
        markers.setPage(markerPage);

        modelAndView.addObject("articles", articles);
        modelAndView.addObject("markers", markers);
        modelAndView.addObject("title", blogService.getTitle());
        return modelAndView;
    }

}
