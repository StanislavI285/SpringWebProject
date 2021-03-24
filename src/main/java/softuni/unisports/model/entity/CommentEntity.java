package softuni.unisports.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    private UserEntity author;
    private NewsEntity newsEntity;
    private String content;
    private LocalDateTime addedOn;

    public CommentEntity() {
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    public UserEntity getAuthor() {
        return author;
    }

    public CommentEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    @ManyToOne
    @JoinColumn(name="news_entity_id")
    public NewsEntity getNewsEntity() {
        return newsEntity;
    }

    public CommentEntity setNewsEntity(NewsEntity newsEntity) {
        this.newsEntity = newsEntity;
        return this;
    }


    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    public String getContent() {
        return content;
    }

    public CommentEntity setContent(String content) {
        this.content = content;
        return this;
    }

    @Column(name = "added_on")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public CommentEntity setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
