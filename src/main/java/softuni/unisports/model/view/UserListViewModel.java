package softuni.unisports.model.view;

import java.util.Set;

public class UserListViewModel {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;
    private Set<String> roles;

    public UserListViewModel() {
    }

    public String getId() {
        return id;
    }

    public UserListViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserListViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserListViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserListViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserListViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserListViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public UserListViewModel setRoles(Set<String> roles) {
        this.roles = roles;
        return this;
    }
}
