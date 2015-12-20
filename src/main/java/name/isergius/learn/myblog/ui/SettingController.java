package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by Kondratyev Sergey on 27.12.15.
 */
@Controller
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    @Qualifier("configurationService")
    private ConfigurationService configurationService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("setting");
        SettingForm settingForm = configurationService.getSettings();
        modelAndView.addObject("settingsForm", settingForm);
        modelAndView.addObject("title", "Settings");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(SettingForm settingForm) {
        ModelAndView modelAndView = new ModelAndView();
        configurationService.saveSettings(settingForm);
        modelAndView.setView(new RedirectView("/setting"));
        return modelAndView;
    }

}
