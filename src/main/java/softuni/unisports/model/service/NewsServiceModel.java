package softuni.unisports.model.service;

import org.springframework.web.multipart.MultipartFile;

public class NewsServiceModel {

    private String title;
    private String content;
    private String author;
    private String category;
    private MultipartFile image;

    public NewsServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public NewsServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NewsServiceModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public NewsServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public NewsServiceModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public NewsServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
