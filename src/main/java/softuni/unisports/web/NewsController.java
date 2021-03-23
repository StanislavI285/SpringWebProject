package softuni.unisports.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.unisports.model.binding.CommentBindingModel;
import softuni.unisports.model.binding.NewsAddBindingModel;
import softuni.unisports.model.service.NewsAddServiceModel;
import softuni.unisports.model.view.NewsViewModel;
import softuni.unisports.service.CategoryService;
import softuni.unisports.service.NewsService;
import softuni.unisports.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public NewsController(NewsService newsService, UserService userService, ModelMapper modelMapper, CategoryService categoryService) {
        this.newsService = newsService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }


    @GetMapping("/all")
    public String news(Model model) {
        List<NewsViewModel> allNews = this.newsService.getAllNewsSortedByDate().
                stream().
                map(n -> modelMapper.map(n, NewsViewModel.class)).
                collect(Collectors.toList());
        model.addAttribute("allNews", allNews);

        if (allNews.size() > 2) {
            List<NewsViewModel> latestNews = allNews.subList(0, 3);
            model.addAttribute("latestNews", latestNews);
        }

        return "news";
    }


    @GetMapping("/{id}")
    public ModelAndView showNewsArticle(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView("article");

        NewsViewModel newsViewModel = this.newsService.getNewsById(id);
        modelAndView.addObject("newsViewModel", newsViewModel);
        modelAndView.addObject("commentBidingModel", new CommentBindingModel());
        List<String> paragraphs =
                Arrays.
                        stream(newsViewModel.getContent().trim().split("\n")).
                        filter(p -> p.length() > 0).     //------- filter empty rows
                        collect(Collectors.toList());
        modelAndView.addObject("paragraphs", paragraphs);
        return modelAndView;
    }


    @GetMapping("/add")
    public String addNews(Principal principal, Model model) {


        if (!model.containsAttribute("newsAddBindingModel")) {
            model.addAttribute("newsAddBindingModel", new NewsAddBindingModel());
        }


        model.addAttribute("categories", this.categoryService.getAllCategories());
        model.addAttribute("author", principal.getName());

        return "add-news";
    }


    @PostMapping("/add")
    public String addNewsConfirm(@Valid @ModelAttribute NewsAddBindingModel newsAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) throws IOException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newsAddBindingModel", newsAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newsAddBindingModel", bindingResult);
            return "redirect:add";
        }

        NewsAddServiceModel newsAddServiceModel = modelMapper.map(newsAddBindingModel, NewsAddServiceModel.class);
        this.newsService.addNews(newsAddServiceModel);

        return "redirect:/";
    }


}
