package project.SPM.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.SPM.Entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import java.util.List;

@Slf4j
@Transactional
@Repository //스프링 빈으로 등록한다.
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 회원 데이터 저장
     * @param userEntity
     */
    public void save(UserEntity userEntity) {
        em.persist(userEntity);
    }

    /**
     * UserService에서 회원 가입 시 ID 중복 체크
     * @param userId
     * @return
     */
    public UserEntity findId(String userId) {
        log.info("레포지토리에서 아이디 조회 시작");
        log.info("입력한 아이디 값 ={}",userId);
        log.info("체크 된 아이디 값 ={}", em.find(UserEntity.class, userId));
        log.info("레포지토리에서 아이디 조회 끝");
        return em.find(UserEntity.class, userId);
    }



    public List<UserEntity> findAll() {
        return em.createQuery("select m from UserEntity  m", UserEntity.class)
                //JPQL, SQL은 테이블을 대상으로 쿼리를 하지만 JPQL은 엔티티 객체에 대한 쿼리를 한다.
                .getResultList();
    }

    /**
     * UserService에서 회원 가입 시 Email 중복 체크
     * @param userEmail
     * @return
     */
    public List<UserEntity> findByEmail(String userEmail) {
        log.info("레포지토리에서 이메일 조회 시작");
        log.info("입력한 이메일 값 ={}", userEmail);
        log.info("체크 된 이메일 값 = {}", em.createQuery("select m from UserEntity m where m.userEmail = :userEmail", UserEntity.class));
        log.info("레포지토리에서 이메일 조회 끝");
        return em.createQuery("select m from UserEntity m where m.userEmail = :userEmail", UserEntity.class)
                .setParameter("userEmail", userEmail)
                .getResultList();
    }
}
