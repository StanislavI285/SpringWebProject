package softuni.unisports.model.service;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class CommentServiceModel {

    private UserServiceModel author;
    private String newsId;
    private String content;
    private LocalDateTime addedOn;

    public CommentServiceModel() {
    }

    public UserServiceModel getAuthor() {
        return author;
    }

    public CommentServiceModel setAuthor(UserServiceModel author) {
        this.author = author;
        return this;
    }

    public String getNewsId() {
        return newsId;
    }

    public CommentServiceModel setNewsId(String newsId) {
        this.newsId = newsId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentServiceModel setContent(String content) {
        this.content = content;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public CommentServiceModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
