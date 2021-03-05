package softuni.unisports.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private String imageUrl;
    private List<ArticleEntity> articles;
    private List<RoleEntity> roles;


    public UserEntity() {
    }

    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 5, max = 20)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(name = "password", nullable = false)
    @Size(min = 8)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(name = "email", nullable = false, unique = true)
    @Email
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public UserEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @OneToMany
    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public UserEntity setArticles(List<ArticleEntity> articles) {
        this.articles = articles;
        return this;
    }

    @OneToMany
    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
