package softuni.unisports.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "articles")
public class ArticleEntity extends BaseEntity {

    private String title;
    private String content;
    private UserEntity author;
    private CategoryEntity category;
    private List<CountryEntity> countries;
    private List<CommentEntity> comments;
    private int views;

    public ArticleEntity() {
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public ArticleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public ArticleEntity setContent(String content) {
        this.content = content;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public ArticleEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    @ManyToOne
    public CategoryEntity getCategory() {
        return category;
    }

    public ArticleEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    @ManyToMany
    public List<CountryEntity> getCountries() {
        return countries;
    }

    public ArticleEntity setCountries(List<CountryEntity> countries) {
        this.countries = countries;
        return this;
    }

    @OneToMany
    public List<CommentEntity> getComments() {
        return comments;
    }

    public ArticleEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public int getViews() {
        return views;
    }

    public ArticleEntity setViews(int views) {
        this.views = views;
        return this;
    }
}
