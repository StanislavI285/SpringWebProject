package softuni.unisports.service.impl;

import org.apache.tomcat.jni.Local;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import softuni.unisports.exception.NewsNotFoundException;
import softuni.unisports.model.entity.CategoryEntity;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.model.service.NewsAddServiceModel;
import softuni.unisports.model.service.NewsGetServiceModel;
import softuni.unisports.model.service.UserServiceModel;
import softuni.unisports.model.view.NewsViewModel;
import softuni.unisports.repository.NewsRepository;
import softuni.unisports.service.CategoryService;
import softuni.unisports.service.CloudinaryService;
import softuni.unisports.service.NewsService;
import softuni.unisports.service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public NewsServiceImpl(NewsRepository newsRepository, CloudinaryService cloudinaryService, UserService userService, CategoryService categoryService, ModelMapper modelMapper) {
        this.newsRepository = newsRepository;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }


    @Override
    public NewsGetServiceModel getNewsById(String id) {
        NewsEntity newsEntity = this.newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException("Seems like this article doesn't exist."));
        NewsGetServiceModel newsGetServiceModel = this.modelMapper.map(newsEntity, NewsGetServiceModel.class);

        return newsGetServiceModel;
    }

    @Override
    public List<NewsGetServiceModel> getAllNewsSortedByDate() {
        return this.newsRepository.findAllByAddedOn().
                stream().
                map(n -> modelMapper.map(n, NewsGetServiceModel.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<NewsGetServiceModel> getAllNewsSortedByComments() {
        return this.newsRepository.findAllByCommentsCount().
                stream().
                map(n -> modelMapper.map(n, NewsGetServiceModel.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<NewsGetServiceModel> getLatestNews() {
        if (this.newsRepository.count() > 2) {
            return this.newsRepository.findAllByAddedOn().
                    stream().
                    map(n -> modelMapper.map(n, NewsGetServiceModel.class)).
                    collect(Collectors.toList()).
                    subList(0, 3);
        }
        return new ArrayList<>();
    }

    @Override
    public void addNews(NewsAddServiceModel newsAddServiceModel) throws IOException {
        MultipartFile image = newsAddServiceModel.getImage();
        String imageUrl = cloudinaryService.uploadImage(image);
        NewsEntity newsEntity = modelMapper.map(newsAddServiceModel, NewsEntity.class);
        CategoryEntity category = this.categoryService.findByName(newsAddServiceModel.getCategory());
        UserServiceModel userServiceModel = this.userService.findUserByUsername(newsAddServiceModel.getAuthor());
        UserEntity author = modelMapper.map(userServiceModel, UserEntity.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        newsEntity.
                setImageUrl(imageUrl).
                setAuthor(author).
                setCategory(category).
                setAddedOn(LocalDateTime.now()).
                setLastUpdated(LocalDateTime.now());

        newsRepository.save(newsEntity);
    }

    @Override
    public void incrementViews(String newsId) {
        NewsEntity newsEntity = this.newsRepository.findById(newsId).get();
        newsEntity.setViews(newsEntity.getViews() + 1);
        this.newsRepository.save(newsEntity);
    }
}
