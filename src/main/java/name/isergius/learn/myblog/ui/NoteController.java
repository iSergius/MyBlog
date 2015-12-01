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

    @Autowired
    @Qualifier("noteService")
    private NoteService noteService;

    @Autowired
    @Qualifier("blogService")
    private BlogService blogService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main(@RequestParam(name = "articlePage", required = false) Long articlePage, @RequestParam(name = "markerPage", required = false) Long markerPage) {
        ModelAndView modelAndView = new ModelAndView("note");
        Page<Article> articles = noteService.getArticles(10L);
        Page<Marker> markers = noteService.getMarkers(10L);

        if (articlePage == null) articlePage = 0L;
        if (markerPage == null) markerPage = 0L;

        articles.setPage(articlePage);
        markers.setPage(markerPage);

        modelAndView.addObject("articles", articles);
        modelAndView.addObject("markers", markers);
        modelAndView.addObject("title", blogService.getTitle());
        return modelAndView;
    }

}
