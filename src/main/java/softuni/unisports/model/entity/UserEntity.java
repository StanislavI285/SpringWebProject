package softuni.unisports.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String imageUrl;
    private List<NewsEntity> news = new ArrayList<>();
    private List<RoleEntity> roles = new ArrayList<>();


    public UserEntity() {
    }

    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 3, max = 20)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
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
    public List<NewsEntity> getNews() {
        return news;
    }

    public UserEntity setNews(List<NewsEntity> news) {
        this.news = news;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public UserEntity addRole(RoleEntity roleEntity) {
        this.roles.add(roleEntity);
        return this;
    }
}
