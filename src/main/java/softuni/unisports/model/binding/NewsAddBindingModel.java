package softuni.unisports.model.binding;

import softuni.unisports.model.entity.UserEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class NewsAddBindingModel {

    private String title;
    private String content;
    private UserEntity author;
    private String category;
    private LocalDateTime addedOn;
    private LocalDateTime lastUpdated;
    private String imageUrl;

    public NewsAddBindingModel() {
    }

    @NotBlank
    @Size(min = 10, max = 80)
    public String getTitle() {
        return title;
    }

    public NewsAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    @NotBlank
    public String getContent() {
        return content;
    }

    public NewsAddBindingModel setContent(String content) {
        this.content = content;
        return this;
    }


    @NotBlank
    public UserEntity getAuthor() {
        return author;
    }

    public NewsAddBindingModel setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    @NotBlank
    public String getCategory() {
        return category;
    }

    public NewsAddBindingModel setCategory(String category) {
        this.category = category;
        return this;
    }

    @NotBlank
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public NewsAddBindingModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    @NotBlank
    public String getImageUrl() {
        return imageUrl;
    }

    public NewsAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
