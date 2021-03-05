package softuni.unisports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.unisports.model.entity.ArticleEntity;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {
}
