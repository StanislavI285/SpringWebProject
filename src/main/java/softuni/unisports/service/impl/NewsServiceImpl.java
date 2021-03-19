package softuni.unisports.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import softuni.unisports.model.entity.CategoryEntity;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.model.service.NewsServiceModel;
import softuni.unisports.model.service.UserServiceModel;
import softuni.unisports.model.view.NewsViewModel;
import softuni.unisports.repository.NewsRepository;
import softuni.unisports.service.CategoryService;
import softuni.unisports.service.CloudinaryService;
import softuni.unisports.service.NewsService;
import softuni.unisports.service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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
    public NewsViewModel getNewsById(String id) {
        NewsEntity newsEntity = this.newsRepository.findById(id).orElseThrow(NullPointerException::new);
        return this.modelMapper.map(newsEntity, NewsViewModel.class);
    }

    @Override
    public List<NewsEntity> getAllNewsSortedByDate() {
        return this.newsRepository.findAllByAddedOn();
    }

    @Override
    public void addNews(NewsServiceModel newsServiceModel) throws IOException {
        MultipartFile image = newsServiceModel.getImage();
        String imageUrl = cloudinaryService.uploadImage(image);
        NewsEntity newsEntity = modelMapper.map(newsServiceModel, NewsEntity.class);
        CategoryEntity category = this.categoryService.findByName(newsServiceModel.getCategory());
        UserServiceModel userServiceModel = this.userService.findUserByUsername(newsServiceModel.getAuthor());
        UserEntity author = modelMapper.map(userServiceModel, UserEntity.class);
        newsEntity.
                setImageUrl(imageUrl).
                setAuthor(author).
                setCategory(category).
                setAddedOn(LocalDateTime.now()).
                setLastUpdated(LocalDateTime.now());

        newsRepository.save(newsEntity);


        //    private UserEntity author;
        //    private CategoryEntity category;
        //    private Set<CountryEntity> countries = new HashSet<>();
        //    private Set<CommentEntity> comments = new HashSet<>();
        //    private int views;
        //    private LocalDateTime addedOn;
        //    private LocalDateTime lastUpdated;
        //    private String imageUrl;


    }
}
