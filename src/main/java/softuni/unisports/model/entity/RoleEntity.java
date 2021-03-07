package softuni.unisports.model.entity;

import softuni.unisports.enums.RoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {
    private RoleEnum name;

    public RoleEntity() {
    }

    @Column(name = "name", unique = true)
    @Enumerated(EnumType.STRING)
    public RoleEnum getName() {
        return name;
    }

    public RoleEntity setName(RoleEnum name) {
        this.name = name;
        return this;
    }

}
