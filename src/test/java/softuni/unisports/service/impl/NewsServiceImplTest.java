package softuni.unisports.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import softuni.unisports.enums.CategoryEnum;
import softuni.unisports.model.entity.CategoryEntity;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.model.service.NewsGetServiceModel;
import softuni.unisports.model.service.UserServiceModel;
import softuni.unisports.model.view.NewsViewModel;
import softuni.unisports.repository.NewsRepository;
import softuni.unisports.repository.UserRepository;
import softuni.unisports.service.CategoryService;
import softuni.unisports.service.CloudinaryService;
import softuni.unisports.service.NewsService;
import softuni.unisports.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class NewsServiceImplTest {

    private NewsService serviceToTest;
    private NewsEntity newsEntity;
    private CategoryEntity categoryEntity;
    private UserEntity userEntity;

    @Mock
    private NewsRepository mockNewsRepository;
    @Mock
    private CloudinaryService mockCloudinaryService;
    @Mock
    private UserService mockUserService;
    @Mock
    private CategoryService mockCategoryService;
    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        serviceToTest = new NewsServiceImpl(mockNewsRepository, mockCloudinaryService, mockUserService,
                mockCategoryService, mockModelMapper);

        categoryEntity = new CategoryEntity().setName(CategoryEnum.FOOTBALL);
        userEntity = new UserEntity().setUsername("author");

        Mockito.when(mockCategoryService.findByName("FOOTBALL"))
                .thenReturn(categoryEntity);



        newsEntity = new NewsEntity();
        newsEntity.
                setImageUrl("https://www.validimageurl.com").
                setAddedOn(LocalDateTime.now()).
                setCategory(mockCategoryService.findByName("FOOTBALL")).
                setContent("some interesting content").
                setAuthor(userEntity).
                setTitle("title").
                setId("1234-5678-9012-3456");

    }


    @Test
    public void testGetNewsById() {
        NewsGetServiceModel newsGetServiceModel = new NewsGetServiceModel();
        newsGetServiceModel.setId("1234-5678-9012-3456");

        Mockito.when(mockNewsRepository.findById("1234-5678-9012-3456")).
                thenReturn(Optional.of(newsEntity));
        Mockito.when(mockModelMapper.map(newsEntity, NewsGetServiceModel.class))
                .thenReturn(newsGetServiceModel);

        NewsGetServiceModel result = serviceToTest.getNewsById("1234-5678-9012-3456");

        Assertions.assertEquals(newsEntity.getId(), result.getId());
    }
}
