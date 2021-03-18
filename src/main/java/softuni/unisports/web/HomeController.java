package softuni.unisports.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.service.NewsService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HomeController {

    private final NewsService newsService;

    public HomeController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String home(Model model) {
        LocalDateTime currentDate = LocalDateTime.now();
        String dateStr = currentDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm a"));
        model.addAttribute("currentDate", dateStr);


        if (this.newsService.getAllNewsSortedByDate().size() > 2) {
            List<NewsEntity> latestNews = this.newsService.getAllNewsSortedByDate().subList(0, 3);
            model.addAttribute("latestNews", latestNews);
        }

        return "index";
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
