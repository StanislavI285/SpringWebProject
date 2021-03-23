package softuni.unisports.model.entity;

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
    public UserEntity getAuthor() {
        return author;
    }

    public CommentEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    @ManyToOne
    public NewsEntity getNewsEntity() {
        return newsEntity;
    }

    public CommentEntity setNewsEntity(NewsEntity newsEntity) {
        this.newsEntity = newsEntity;
        return this;
    }


    @Column(name = "content", columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public CommentEntity setContent(String content) {
        this.content = content;
        return this;
    }

    @Column(name = "added_on")
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public CommentEntity setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
