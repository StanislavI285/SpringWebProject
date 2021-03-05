package softuni.unisports.model.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    private String id;
    private LocalDateTime created;
    private LocalDateTime updated;


    public BaseEntity() {
    }

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    public BaseEntity setId(String id) {
        this.id = id;
        return this;
    }

    @CreatedDate
    public LocalDateTime getCreated() {
        return created;
    }

    public BaseEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    @UpdateTimestamp
    public LocalDateTime getUpdated() {
        return updated;
    }

    public BaseEntity setUpdated(LocalDateTime updated) {
        this.updated = updated;
        return this;
    }
}
