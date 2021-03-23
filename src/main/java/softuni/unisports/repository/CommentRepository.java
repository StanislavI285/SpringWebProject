package softuni.unisports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.unisports.model.entity.CommentEntity;
import softuni.unisports.model.service.CommentServiceModel;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, String> {

    @Query("SELECT c FROM CommentEntity c WHERE c.newsEntity.id = ?1")
    List<CommentEntity> findAllByNewsId(String id);
}
