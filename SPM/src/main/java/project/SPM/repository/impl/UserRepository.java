package project.SPM.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import project.SPM.Entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

/*    *//**
     * UserService에서 회원 가입 시 ID 중복 체크
     *//*
    public List<UserEntity> findById(String userId) {
        log.info("레포지토리에서 이메일 조회 시작");
        log.info("입력한 이메일 값 ={}", userId);
        log.info("체크 된 이메일 값 = {}", em.createQuery("select m from UserEntity m where m.userId = :userId", UserEntity.class));
        log.info("레포지토리에서 이메일 조회 끝");
        return em.createQuery("select m from UserEntity m where m.userId = :userId", UserEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    *//**
     * UserService에서 회원 가입 시 Email 중복 체크
     *//*
    public List<UserEntity> findByEmail(String userEmail) {
        log.info("레포지토리에서 이메일 조회 시작");
        log.info("입력한 이메일 값 ={}", userEmail);
        log.info("체크 된 이메일 값 = {}", em.createQuery("select m from UserEntity m where m.userEmail = :userEmail", UserEntity.class));
        log.info("레포지토리에서 이메일 조회 끝");
        return em.createQuery("select m from UserEntity m where m.userEmail = :userEmail", UserEntity.class)
                .setParameter("userEmail", userEmail)
                .getResultList();
    }*/

    public List<UserEntity> findAll() {
        return em.createQuery("select m from UserEntity  m", UserEntity.class)
                //JPQL, SQL은 테이블을 대상으로 쿼리를 하지만 JPQL은 엔티티 객체에 대한 쿼리를 한다.
                .getResultList();
    }

    public List<UserEntity> checkLogin(String userId, String userPw) {
        log.info("레포지토리에서 비밀번호 조회 시작");
        log.info("입력한 비밀번호 값 ={}", userPw);
        log.info("입력한 아이디 값 ={}", userId);
        log.info("체크 된 값 = {}", em.createQuery("select m from UserEntity m where m.userPw = :userPw and m.userId = :userId", UserEntity.class));
        log.info("레포지토리에서 비밀번호 조회 끝");
        return em.createQuery("select m from UserEntity m where m.userPw = :userPw and m.userId = :userId", UserEntity.class)
                .setParameter("userId", userId)
                .setParameter("userPw", userPw)
                .getResultList();
     }

    public List<UserEntity> check(Long userNo,
                                       String userName,
                                       String userPn,
                                       String userEmail,
                                       String userId,
                                       String userPw,
                                       String userAddr) {
        return em.createQuery("select m from UserEntity m where m.userAddr = :userAddr and m.userEmail = :userEmail and m.userPn = :userPn and m.userName = :userName and m.userNo = :userNo and m.userPw = :userPw and m.userId = :userId", UserEntity.class)
                .setParameter("userNo", userNo)
                .setParameter("userName", userName)
                .setParameter("userPn", userPn)
                .setParameter("userEmail", userEmail)
                .setParameter("userId", userId)
                .setParameter("userPw", userPw)
                .setParameter("userAddr", userAddr)
                .getResultList();
    }
}
