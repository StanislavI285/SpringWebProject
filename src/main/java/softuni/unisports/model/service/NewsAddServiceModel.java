package softuni.unisports.model.service;

import org.springframework.web.multipart.MultipartFile;
import softuni.unisports.model.entity.UserEntity;

public class NewsAddServiceModel {

    private String title;
    private String content;
    private String author;
    private String category;
    private MultipartFile image;

    public NewsAddServiceModel() {
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

    public MultipartFile getImage() {
        return image;
    }

    public NewsAddServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
