package softuni.unisports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.unisports.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByUsername(String username);
}
