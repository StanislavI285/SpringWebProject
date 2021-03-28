package softuni.unisports.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class CommentViewModel {
    private UserViewModel author;
    private String content;
    private LocalDateTime addedOn;

    public CommentViewModel() {
    }

    public UserViewModel getAuthor() {
        return author;
    }

    public CommentViewModel setAuthor(UserViewModel author) {
        this.author = author;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentViewModel setContent(String content) {
        this.content = content;
        return this;
    }

    @JsonFormat(pattern="yyyy-MM-dd / HH:mm")
    public LocalDateTime getDateAdded() {
        return addedOn;
    }

    public CommentViewModel setDateAdded(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
