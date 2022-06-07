package project.SPM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    // 회원가입 이메일 및 아이디 중복 체크
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserId(String userId);

    // 세션에 값을 담기 위해 userId를 기준으로 데이터를 가져온다.
    List<UserEntity> findAllByUserId(String userId);

    List<UserEntity> findAllByUserEmail(String userEmail);




}
