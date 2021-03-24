package softuni.unisports.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "news")
public class NewsEntity extends BaseEntity {

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

    public NewsEntity() {
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public NewsEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public NewsEntity setContent(String content) {
        this.content = content;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    public UserEntity getAuthor() {
        return author;
    }

    public NewsEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    @ManyToOne
    public CategoryEntity getCategory() {
        return category;
    }

    public NewsEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<CountryEntity> getCountries() {
        return countries;
    }

    public NewsEntity setCountries(Set<CountryEntity> countries) {
        this.countries = countries;
        return this;
    }

    @OneToMany(mappedBy = "newsEntity", fetch = FetchType.EAGER)
    public Set<CommentEntity> getComments() {
        return comments;
    }

    public NewsEntity setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public int getViews() {
        return views;
    }

    public NewsEntity setViews(int views) {
        this.views = views;
        return this;
    }

    @Column(name = "added_on")
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public NewsEntity setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    @Column(name = "last_updated")
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public NewsEntity setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public NewsEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
