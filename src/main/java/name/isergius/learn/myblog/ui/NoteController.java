package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("noteService")
    private NoteService noteService;

    @Autowired
    @Qualifier("blogService")
    private BlogService blogService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("note");
        Page<Article> articles = noteService.getArticles(10L);
        Page<Marker> markers = noteService.getMarkers(10L);
        modelAndView.addObject("articles",articles.result(0L));
        modelAndView.addObject("markers", markers.result(0L));
        modelAndView.addObject("title", blogService.getTitle());
        return modelAndView;
    }

}
