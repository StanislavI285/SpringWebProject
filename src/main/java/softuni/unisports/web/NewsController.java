package softuni.unisports.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.unisports.model.binding.NewsAddBindingModel;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.service.NewsService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    @GetMapping("/all")
    public String news(Model model) {
        ModelAndView modelAndView = new ModelAndView("news");
        List<NewsEntity> allNews = this.newsService.getAllNewsSortedByDate();
        model.addAttribute("allNews", allNews);
        List<NewsEntity> latestNews = allNews.subList(0, 2);
        model.addAttribute("latestNews", latestNews);
        return "news";
    }


    @GetMapping("/{id}")
    public ModelAndView showNewsArticle(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView("article");
        NewsEntity newsEntity = this.newsService.getNewsById(id);
        modelAndView.addObject("newsEntity", newsEntity);
        List<String> paragraphs =
                Arrays.
                        stream(newsEntity.getContent().trim().split("\n")).
                        filter(p -> p.length() > 0).
                        collect(Collectors.toList());
        modelAndView.addObject("paragraphs", paragraphs);
        return modelAndView;
    }


    @GetMapping("/add")
    public ModelAndView addNews() {
        ModelAndView modelAndView = new ModelAndView("add-news");
        return modelAndView;
    }


    @PostMapping("/add")
    public ModelAndView addNewsConfirm(@Valid @ModelAttribute NewsAddBindingModel newsAddBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newsAddBindingModel", newsAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newsAddBindingModel", bindingResult);
            modelAndView.setViewName("redirect:/add-news");
        }

        //TODO save in DB
        System.out.println();

        return modelAndView;
    }


}
