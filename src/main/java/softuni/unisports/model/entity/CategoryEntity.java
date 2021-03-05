package softuni.unisports.model.entity;

import softuni.unisports.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity{

    private Long id;
    private CategoryEnum name;

    public CategoryEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public CategoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    public CategoryEnum getName() {
        return name;
    }

    public CategoryEntity setName(CategoryEnum name) {
        this.name = name;
        return this;
    }
}
