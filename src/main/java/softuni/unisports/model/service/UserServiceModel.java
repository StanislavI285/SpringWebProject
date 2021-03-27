package softuni.unisports.model.service;

import softuni.unisports.model.entity.CommentEntity;
import softuni.unisports.model.entity.NewsEntity;
import softuni.unisports.model.entity.RoleEntity;

import java.util.HashSet;
import java.util.Set;

public class UserServiceModel {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;
    private String password;
    private Set<NewsEntity> news = new HashSet<>();
    private Set<RoleServiceModel> roles = new HashSet<>();
    private Set<CommentEntity> comments = new HashSet<>();

    public UserServiceModel() {
    }

    public String getId() {
        return id;
    }

    public UserServiceModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<NewsEntity> getNews() {
        return news;
    }

    public UserServiceModel setNews(Set<NewsEntity> news) {
        this.news = news;
        return this;
    }

    public Set<RoleServiceModel> getRoles() {
        return roles;
    }

    public UserServiceModel setRoles(Set<RoleServiceModel> roles) {
        this.roles = roles;
        return this;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public UserServiceModel setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
