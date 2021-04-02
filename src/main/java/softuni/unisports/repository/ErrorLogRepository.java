package softuni.unisports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.unisports.model.entity.ErrorLogEntity;
import softuni.unisports.model.view.ErrorsLogViewModel;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLogEntity, String> {
    @Query(value = "SELECT e FROM ErrorLogEntity e WHERE e.date = ?1")
    List<ErrorLogEntity> findAllByDate(LocalDateTime date);
}
