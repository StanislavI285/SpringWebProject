package softuni.unisports.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "registrations_log")
public class UserRegisterLogEntity extends BaseEntity{

    private UserEntity userEntity;
    private LocalDateTime registrationDateAndTime;

    public UserRegisterLogEntity() {
    }

    @OneToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public UserRegisterLogEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @Column(name = "registration_date_time", nullable = false)
    public LocalDateTime getRegistrationDateAndTime() {
        return registrationDateAndTime;
    }

    public UserRegisterLogEntity setRegistrationDateAndTime(LocalDateTime registrationDateAndTime) {
        this.registrationDateAndTime = registrationDateAndTime;
        return this;
    }
}
