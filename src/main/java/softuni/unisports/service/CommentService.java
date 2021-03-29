package softuni.unisports.service;

import softuni.unisports.exception.NewsNotFoundException;
import softuni.unisports.model.service.CommentServiceModel;

import java.util.List;

public interface CommentService {
    List<CommentServiceModel> getAllCommentsByNewsIdSorted(String id);

    void addCommentToNews(CommentServiceModel commentServiceModel) throws NewsNotFoundException;
}
