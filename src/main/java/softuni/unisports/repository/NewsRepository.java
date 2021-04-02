package softuni.unisports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.unisports.model.entity.NewsEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, String> {

    @Query(value = "SELECT n FROM NewsEntity n ORDER BY n.addedOn DESC")
    List<NewsEntity> findAllByAddedOn();

    @Query(value = "SELECT n FROM NewsEntity n ORDER BY n.comments.size DESC")
    List<NewsEntity> findAllByCommentsCount();

    @Query(value = "SELECT n FROM NewsEntity n WHERE n.views > 10")
    List<NewsEntity> findAllByViewsMoreThan10();
}
