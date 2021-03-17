package softuni.unisports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.unisports.model.entity.NewsEntity;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, String> {

    @Query("SELECT n FROM NewsEntity n ORDER BY n.addedOn DESC")
    List<NewsEntity> findAllByAddedOn();
}
