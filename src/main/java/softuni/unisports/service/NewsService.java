package softuni.unisports.service;

import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.model.service.NewsServiceModel;
import softuni.unisports.model.view.NewsViewModel;

import java.io.IOException;
import java.util.List;

public interface NewsService {
    NewsViewModel getNewsById(String id);

    List<NewsEntity> getAllNewsSortedByDate();

    void addNews(NewsServiceModel newsServiceModel) throws IOException;
}
