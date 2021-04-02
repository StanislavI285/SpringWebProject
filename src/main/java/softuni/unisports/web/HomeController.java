package softuni.unisports.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.unisports.service.CategoryService;
import softuni.unisports.service.NewsService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    private final NewsService newsService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public HomeController(NewsService newsService, CategoryService categoryService, ModelMapper modelMapper) {
        this.newsService = newsService;
        this.categoryService = categoryService;

        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String home(Model model) {
        LocalDateTime currentDate = LocalDateTime.now();
        String dateStr = currentDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm a"));
        model.addAttribute("currentDate", dateStr);
        model.addAttribute("latestNews", this.newsService.getLatestNews());
        model.addAttribute("mostCommentedNews", this.newsService.getMostCommentedNews());
        model.addAttribute("categories", this.categoryService.getAllCategories());
        model.addAttribute("newsWithMoreThan10Views", this.newsService.getNewsWithViewsMoreThan10());

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

    @GetMapping("/results")
    public ModelAndView results(ModelAndView modelAndView) {
        modelAndView.setViewName("results");
        return modelAndView;
    }

    @GetMapping("/aboutus")
    public ModelAndView aboutUs(ModelAndView modelAndView) {
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



}
