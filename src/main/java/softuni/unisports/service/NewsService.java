package softuni.unisports.service;

import softuni.unisports.model.entity.NewsEntity;

import java.util.List;

public interface NewsService {
    NewsEntity getNewsById(String id);

    List<NewsEntity> getAllNewsSortedByDate();
}
