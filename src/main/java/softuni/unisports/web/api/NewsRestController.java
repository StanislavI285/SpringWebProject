package softuni.unisports.web.api;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softuni.unisports.model.view.NewsViewModel;
import softuni.unisports.service.NewsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/news")
public class NewsRestController {

    private final NewsService newsService;
    private final ModelMapper modelMapper;

    public NewsRestController(NewsService newsService, ModelMapper modelMapper) {
        this.newsService = newsService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/api")
    public List<NewsViewModel> getNews() {
        return this.newsService.getAllNewsSortedByDate().
                stream().
                map(n -> modelMapper.map(n, NewsViewModel.class)).
                collect(Collectors.toList());
    }
}
