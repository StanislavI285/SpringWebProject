package softuni.unisports.model.binding;

import org.springframework.web.multipart.MultipartFile;
import softuni.unisports.model.validators.multipartFile.MultiPartNotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NewsAddBindingModel {

    private String title;
    private String content;
    private String author;
    private String category;
    private MultipartFile image;
    private String videoUrl;

    public NewsAddBindingModel() {
    }


    @Size(min = 10, max = 80, message = "Article title is required and it must be between 10 and 80 characters long.")
    public String getTitle() {
        return title;
    }

    public NewsAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    @NotBlank(message = "Article content cannot be blank.")
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

    @NotBlank(message = "Please select a category.")
    public String getCategory() {
        return category;
    }

    public NewsAddBindingModel setCategory(String category) {
        this.category = category;
        return this;
    }

    @MultiPartNotNull
    public MultipartFile getImage() {
        return image;
    }

    public NewsAddBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }



//    @Pattern(regexp = "^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+", message = "Invalid URL.")
//    public String getVideoUrl() {
//        return videoUrl;
//    }
//
//    public NewsAddBindingModel setVideoUrl(String videoUrl) {
//        this.videoUrl = videoUrl;
//        return this;
//    }
}
