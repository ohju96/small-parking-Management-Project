package project.SPM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.SPM.Entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
   //UserEntity findByUserEmail(String userEmail);
}
