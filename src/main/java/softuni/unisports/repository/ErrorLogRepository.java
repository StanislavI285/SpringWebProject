package softuni.unisports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.unisports.model.entity.ErrorLogEntity;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLogEntity, String> {
}
