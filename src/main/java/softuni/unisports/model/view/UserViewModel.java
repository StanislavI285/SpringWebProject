package softuni.unisports.model.view;

public class UserViewModel {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;

    public UserViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }


}
