package softuni.unisports.web.api;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import softuni.unisports.model.binding.CommentBindingModel;
import softuni.unisports.model.service.CommentServiceModel;
import softuni.unisports.model.view.CommentViewModel;
import softuni.unisports.service.CommentService;
import softuni.unisports.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentsRestController {

    private final CommentService commentService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public CommentsRestController(CommentService commentService, UserService userService, ModelMapper modelMapper) {

        this.commentService = commentService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }



    @GetMapping(value = "/{newsId}", produces = "application/json")
    public List<CommentViewModel> getComments(@PathVariable String newsId) {
        List<CommentViewModel> comments =
                this.commentService.
                        getAllCommentsByNewsIdSorted(newsId).
                        stream().
                        map(c -> modelMapper.map(c, CommentViewModel.class)).
                        collect(Collectors.toList());

        return comments;
    }

    @PostMapping("/add")
    public CommentBindingModel addComment(@RequestBody CommentBindingModel commentBindingModel, HttpServletResponse response) {

        CommentServiceModel commentServiceModel = this.modelMapper.map(commentBindingModel, CommentServiceModel.class);
        commentServiceModel.setAuthor(userService.findUserByUsername(commentBindingModel.getAuthor()));

        this.commentService.addCommentToNews(commentServiceModel);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return commentBindingModel;
    }
}
