package softuni.unisports.web.api;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import softuni.unisports.model.binding.CommentBindingModel;
import softuni.unisports.model.service.CommentServiceModel;
import softuni.unisports.model.view.CommentViewModel;
import softuni.unisports.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentsRestController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentsRestController(CommentService commentService, ModelMapper modelMapper) {

        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = "/{newsId}", produces = "application/json")
    public List<CommentViewModel> getComments(@PathVariable String newsId) {
        List<CommentViewModel> comments =
                this.commentService.
                        getAllCommentsByNewsId(newsId).
                        stream().
                        map(c -> modelMapper.map(c, CommentViewModel.class)).
                        collect(Collectors.toList());

        return comments;
    }

    @PostMapping("/add")
    public CommentBindingModel addComment(@RequestBody CommentBindingModel commentBindingModel) {

        System.out.println();
        CommentServiceModel commentServiceModel = this.modelMapper.map(commentBindingModel, CommentServiceModel.class);


//        this.commentService.addCommentToNews(commentServiceModel);

        return commentBindingModel;
    }
}
