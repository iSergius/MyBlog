package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Marker;
import name.isergius.learn.myblog.domain.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

/**
 * Created by Kondratyev Sergey on 26.11.15.
 */
@Controller
@RequestMapping(path = "/marker")
public class MarkerController {

    @Autowired
    private NoteService noteService;

    @RequestMapping(path = {"/edit"}, method = RequestMethod.POST)
    public ModelAndView save(@Valid Marker marker) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/note"));
        noteService.save(marker);
        return modelAndView;
    }

    @RequestMapping(path = {"/{id}/delete"}, method = RequestMethod.POST)
    public ModelAndView delete(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/note"));
        noteService.deleteMarker(id);
        return modelAndView;
    }
}
