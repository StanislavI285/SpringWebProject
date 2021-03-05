package softuni.unisports.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class CountryEntity extends BaseEntity {

    private String name;

    public CountryEntity() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public CountryEntity setName(String name) {
        this.name = name;
        return this;
    }
}
