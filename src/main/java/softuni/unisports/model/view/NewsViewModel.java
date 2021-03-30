package softuni.unisports.model.view;

import softuni.unisports.enums.CategoryEnum;
import softuni.unisports.model.entity.CategoryEntity;
import softuni.unisports.model.entity.CommentEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class NewsViewModel {
    private String id;
    private String title;
    private String content;
    private UserViewModel author;
    private CategoryEntity category;
    private Set<CommentEntity> comments = new HashSet<>();
    private String imageUrl;
    private LocalDateTime addedOn;
    private int views;


    public NewsViewModel() {
    }

    public String getId() {
        return id;
    }

    public NewsViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NewsViewModel setContent(String content) {
        this.content = content;
        return this;
    }

    public UserViewModel getAuthor() {
        return author;
    }

    public NewsViewModel setAuthor(UserViewModel author) {
        this.author = author;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public NewsViewModel setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }


    public Set<CommentEntity> getComments() {
        return comments;
    }

    public NewsViewModel setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public NewsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public NewsViewModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public int getViews() {
        return views;
    }

    public NewsViewModel setViews(int views) {
        this.views = views;
        return this;
    }
}
