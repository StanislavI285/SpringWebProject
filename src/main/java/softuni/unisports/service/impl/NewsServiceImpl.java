package softuni.unisports.service.impl;

import org.springframework.stereotype.Service;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.repository.NewsRepository;
import softuni.unisports.service.NewsService;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public NewsEntity getNewsById(String id) {
        return this.newsRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public List<NewsEntity> getAllNewsSortedByDate() {
        return this.newsRepository.findAllByAddedOn();
    }
}
