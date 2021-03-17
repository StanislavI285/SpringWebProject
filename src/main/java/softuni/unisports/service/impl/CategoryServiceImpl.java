package softuni.unisports.service.impl;

import org.springframework.stereotype.Service;
import softuni.unisports.enums.CategoryEnum;
import softuni.unisports.model.entity.CategoryEntity;
import softuni.unisports.repository.CategoryRepository;
import softuni.unisports.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(String categoryName) {

        //TODO
    }

    @Override
    public CategoryEntity findByName(String name) {
       return this.categoryRepository.findByName(CategoryEnum.valueOf(name)).get(); // <---- no validation //TODO add drop down for category selection
    }

    @Override
    public List<CategoryEntity> getAllCategories() {

        return this.categoryRepository.findAllOrderedAlphabetically();
    }
}
