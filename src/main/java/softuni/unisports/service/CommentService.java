package softuni.unisports.service;

import softuni.unisports.model.service.CommentServiceModel;
import softuni.unisports.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {
    List<CommentServiceModel> getAllCommentsByNewsIdSorted(String id);

    void addCommentToNews(CommentServiceModel commentServiceModel);
}
