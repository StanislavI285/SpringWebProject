package softuni.unisports.service;

import softuni.unisports.exception.NewsNotFoundException;
import softuni.unisports.model.service.NewsAddServiceModel;
import softuni.unisports.model.service.NewsGetServiceModel;
import softuni.unisports.model.view.NewsViewModel;

import java.io.IOException;
import java.util.List;

public interface NewsService {
    NewsGetServiceModel getNewsById(String id) throws NewsNotFoundException;

    List<NewsGetServiceModel> getAllNewsSortedByDate();

    List<NewsGetServiceModel> getAllNewsSortedByComments();

    List<NewsGetServiceModel> getLatestNews();


    void addNews(NewsAddServiceModel newsAddServiceModel) throws IOException;

    void incrementViews(String newsId);
}
