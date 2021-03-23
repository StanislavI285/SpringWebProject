package softuni.unisports.service;

import softuni.unisports.model.service.NewsAddServiceModel;
import softuni.unisports.model.service.NewsGetServiceModel;
import softuni.unisports.model.view.NewsViewModel;

import java.io.IOException;
import java.util.List;

public interface NewsService {
    NewsViewModel getNewsById(String id);

    List<NewsGetServiceModel> getAllNewsSortedByDate();

    void addNews(NewsAddServiceModel newsAddServiceModel) throws IOException;
}
