package softuni.unisports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.unisports.model.entity.UserRegisterLogEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationLogRepository extends JpaRepository<UserRegisterLogEntity, String> {

    @Query(value = "SELECT l FROM UserRegisterLogEntity l ORDER BY l.registrationDateAndTime ASC")
    List<UserRegisterLogEntity> findAllSortedByDate();
}
