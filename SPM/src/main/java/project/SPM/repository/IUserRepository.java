package project.SPM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    // 회원가입 이메일 및 아이디 중복 체크
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserId(String userId);


}
