package softuni.unisports.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewsAddBindingModel {

    private String title;
    private String content;
    private String author;
    private String category;
    private MultipartFile image;

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
    public String getAuthor() {
        return author;
    }

    public NewsAddBindingModel setAuthor(String author) {
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

    @NotNull
    public MultipartFile getImage() {
        return image;
    }

    public NewsAddBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
