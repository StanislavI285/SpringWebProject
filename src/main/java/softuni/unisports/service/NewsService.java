package softuni.unisports.service;

import softuni.unisports.model.binding.NewsAddBindingModel;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.model.service.NewsAddServiceModel;

import java.io.IOException;
import java.util.List;

public interface NewsService {
    NewsEntity getNewsById(String id);

    List<NewsEntity> getAllNewsSortedByDate();

    void addNews(NewsAddServiceModel newsAddServiceModel) throws IOException;
}
