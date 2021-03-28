package softuni.unisports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.unisports.model.entity.UserRegisterLogEntity;

@Repository
public interface LogRepository extends JpaRepository<UserRegisterLogEntity, String> {
}
