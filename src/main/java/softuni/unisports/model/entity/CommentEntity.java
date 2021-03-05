package softuni.unisports.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    private UserEntity author;
    private ArticleEntity articleEntity;
    private List<UserEntity> likes;
    private String parentCommentId;

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
    public ArticleEntity getArticleEntity() {
        return articleEntity;
    }

    public CommentEntity setArticleEntity(ArticleEntity articleEntity) {
        this.articleEntity = articleEntity;
        return this;
    }

    @OneToMany
    public List<UserEntity> getLikes() {
        return likes;
    }

    public CommentEntity setLikes(List<UserEntity> likes) {
        this.likes = likes;
        return this;
    }

    @Column(name = "parent_comment_id")
    public String getParentCommentId() {
        return parentCommentId;
    }

    public CommentEntity setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
        return this;
    }
}
