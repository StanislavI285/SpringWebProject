package softuni.unisports.model.binding;

import javax.validation.constraints.NotBlank;

public class CommentBindingModel {
    private String author;
    private String content;
    private String newsId;

    public CommentBindingModel() {
    }

    @NotBlank
    public String getAuthor() {
        return author;
    }

    public CommentBindingModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    @NotBlank
    public String getContent() {
        return content;
    }

    public CommentBindingModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getNewsId() {
        return newsId;
    }

    public CommentBindingModel setNewsId(String newsId) {
        this.newsId = newsId;
        return this;
    }
}
