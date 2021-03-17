package softuni.unisports.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        LocalDateTime currentDate = LocalDateTime.now();
        String dateStr = currentDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm a"));
        modelAndView.addObject("currentDate", dateStr);
        modelAndView.setViewName("index");
        return modelAndView;
    }



    @GetMapping("/standings")
    public ModelAndView standings(ModelAndView modelAndView) {
        modelAndView.setViewName("league-standings");
        return modelAndView;
    }

    @GetMapping("/galleries")
    public ModelAndView galleries(ModelAndView modelAndView) {
        modelAndView.setViewName("galleries");
        return modelAndView;
    }

    @GetMapping("/politics")
    public ModelAndView category03(ModelAndView modelAndView) {
        modelAndView.setViewName("politics");
        return modelAndView;
    }

    @GetMapping("/results")
    public ModelAndView results(ModelAndView modelAndView) {
        modelAndView.setViewName("results");
        return modelAndView;
    }

    @GetMapping("/travel")
    public ModelAndView travel(ModelAndView modelAndView) {
        modelAndView.setViewName("travel");
        return modelAndView;
    }

    @GetMapping("/aboutus")
    public ModelAndView aboutus(ModelAndView modelAndView) {
        modelAndView.setViewName("aboutus");
        return modelAndView;
    }

    @GetMapping("/contactus")
    public ModelAndView contactus(ModelAndView modelAndView) {
        modelAndView.setViewName("contactus");
        return modelAndView;
    }

    @GetMapping("/admin-panel")
    public ModelAndView adminPanel(ModelAndView modelAndView) {
        modelAndView.setViewName("admin-panel");
        return modelAndView;
    }

    @GetMapping("/moderator-panel")
    public ModelAndView moderatorPanel(ModelAndView modelAndView) {
        modelAndView.setViewName("moderator-panel");
        return modelAndView;
    }




}
