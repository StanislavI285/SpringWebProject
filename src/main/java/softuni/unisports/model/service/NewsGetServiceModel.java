package softuni.unisports.model.service;

import softuni.unisports.enums.CategoryEnum;
import softuni.unisports.model.entity.CategoryEntity;
import softuni.unisports.model.entity.CommentEntity;

import java.util.HashSet;
import java.util.Set;

public class NewsGetServiceModel {

    private String id;
    private String title;
    private String content;
    private UserServiceModel author;
    private CategoryEntity category;
    private Set<CommentEntity> comments = new HashSet<>();
    private String imageUrl;

    public NewsGetServiceModel() {
    }

    public String getId() {
        return id;
    }

    public NewsGetServiceModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsGetServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NewsGetServiceModel setContent(String content) {
        this.content = content;
        return this;
    }

    public UserServiceModel getAuthor() {
        return author;
    }

    public NewsGetServiceModel setAuthor(UserServiceModel author) {
        this.author = author;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public NewsGetServiceModel setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public NewsGetServiceModel setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public NewsGetServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
