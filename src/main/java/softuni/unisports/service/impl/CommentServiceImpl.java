package softuni.unisports.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.unisports.model.entity.CommentEntity;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.model.entity.UserEntity;
import softuni.unisports.model.service.CommentServiceModel;
import softuni.unisports.repository.CommentRepository;
import softuni.unisports.service.CommentService;
import softuni.unisports.service.NewsService;
import softuni.unisports.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final NewsService newsService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;


    public CommentServiceImpl(NewsService newsService, UserService userService, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.newsService = newsService;
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CommentServiceModel> getAllCommentsByNewsId(String id) {
        List<CommentServiceModel> result = this.commentRepository.
                findAllByNewsId(id).
                stream().
                map(c -> modelMapper.map(c, CommentServiceModel.class)).
                collect(Collectors.toList());


        return result;
    }

    @Override
    public void addCommentToNews(CommentServiceModel commentServiceModel) {
        CommentEntity commentEntity = this.modelMapper.map(commentServiceModel, CommentEntity.class);
        UserEntity author = modelMapper.map(userService.findUserByUsername(commentServiceModel.getAuthor().getUsername()), UserEntity.class);
        NewsEntity newsEntity = modelMapper.map(this.newsService.getNewsById(commentServiceModel.getNewsEntity()), NewsEntity.class);
        commentEntity.setAuthor(author);
        commentEntity.setNewsEntity(newsEntity);
        commentEntity.setAddedOn(LocalDateTime.now());
        this.commentRepository.saveAndFlush(commentEntity);
    }
}
