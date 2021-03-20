package softuni.unisports.model.view;

import softuni.unisports.model.entity.CategoryEntity;
import softuni.unisports.model.entity.CommentEntity;
import softuni.unisports.model.entity.CountryEntity;
import softuni.unisports.model.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class NewsViewModel {
    private String title;
    private String content;
    private UserEntity author;
    private CategoryEntity category;
    private Set<CountryEntity> countries = new HashSet<>();
    private Set<CommentEntity> comments = new HashSet<>();
    private int views = 0;
    private LocalDateTime addedOn;
    private LocalDateTime lastUpdated;
    private String imageUrl;


    public NewsViewModel() {
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

    public UserEntity getAuthor() {
        return author;
    }

    public NewsViewModel setAuthor(UserEntity author) {
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

    public Set<CountryEntity> getCountries() {
        return countries;
    }

    public NewsViewModel setCountries(Set<CountryEntity> countries) {
        this.countries = countries;
        return this;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public NewsViewModel setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public int getViews() {
        return views;
    }

    public NewsViewModel setViews(int views) {
        this.views = views;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public NewsViewModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public NewsViewModel setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public NewsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
