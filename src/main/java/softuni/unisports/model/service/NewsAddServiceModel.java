package softuni.unisports.model.service;

import org.springframework.web.multipart.MultipartFile;
import softuni.unisports.model.entity.CommentEntity;

import java.util.HashSet;
import java.util.Set;

public class NewsAddServiceModel {
    private String id;
    private String title;
    private String content;
    private String author;
    private String category;
    private Set<CommentEntity> comments = new HashSet<>();
    private MultipartFile image;
    private String videoUrl;

    public NewsAddServiceModel() {
    }

    public String getId() {
        return id;
    }

    public NewsAddServiceModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsAddServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NewsAddServiceModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public NewsAddServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public NewsAddServiceModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public NewsAddServiceModel setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public NewsAddServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public NewsAddServiceModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
}
