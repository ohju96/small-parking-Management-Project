package project.SPM.repository;

import org.apache.catalina.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserSaveForm;


public interface IUserRepository extends JpaRepository<UserEntity, Long> {

}
