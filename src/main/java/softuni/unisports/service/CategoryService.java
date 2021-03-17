package softuni.unisports.service;

import softuni.unisports.model.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    void addCategory(String categoryName);

    CategoryEntity findByName(String name);

    List<CategoryEntity> getAllCategories();
}
