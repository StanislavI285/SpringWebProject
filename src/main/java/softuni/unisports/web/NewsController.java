package softuni.unisports.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.unisports.model.binding.NewsAddBindingModel;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.model.service.NewsAddServiceModel;
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
        ModelAndView modelAndView = new ModelAndView("news");
        List<NewsEntity> allNews = this.newsService.getAllNewsSortedByDate();
        model.addAttribute("allNews", allNews);

        if (allNews.size() > 2) {
            List<NewsEntity> latestNews = allNews.subList(0, 3);
            model.addAttribute("latestNews", latestNews);
        }

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
                        filter(p -> p.length() > 0).     //------- filter empty rows
                        collect(Collectors.toList());
        modelAndView.addObject("paragraphs", paragraphs);
        return modelAndView;
    }


    @GetMapping("/add")
    public ModelAndView addNews(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("add-news");

        if (!modelAndView.getModel().containsKey("newsAddBindingModel")) {
            modelAndView.addObject("newsAddBindingModel", new NewsAddBindingModel());
        }

        modelAndView.addObject("categories", this.categoryService.getAllCategories());
        modelAndView.addObject("author", principal.getName());

        return modelAndView;
    }


    @PostMapping("/add")
    public ModelAndView addNewsConfirm(@Valid @ModelAttribute NewsAddBindingModel newsAddBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) throws IOException {
        ModelAndView modelAndView = new ModelAndView();


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newsAddBindingModel", newsAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newsAddBindingModel", bindingResult);
            modelAndView.setViewName("redirect:/news/add");
            return modelAndView;
        }

        MultipartFile multipartFile = newsAddBindingModel.getImage();
        boolean isEmpty = multipartFile.isEmpty();

        NewsAddServiceModel newsAddServiceModel = modelMapper.map(newsAddBindingModel, NewsAddServiceModel.class);
        this.newsService.addNews(newsAddServiceModel);

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }


}
