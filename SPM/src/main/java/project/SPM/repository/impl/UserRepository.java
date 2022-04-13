package project.SPM.repository.impl;

import org.springframework.stereotype.Repository;
import project.SPM.Entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //스프링 빈으로 등록한다.
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 데이터 저장하기
     * @param userEntity
     */
    public void save(UserEntity userEntity) {
        em.persist(userEntity);
    }

    /**
     * ID 조회하기
     * @param userId
     * @return
     */
    public UserEntity findId(String userId) {
        return em.find(UserEntity.class, userId);
    }

    public UserEntity findEmail(String userEmail) {
        return em.find(UserEntity.class, userEmail);
    }

    public List<UserEntity> findAll() {
        return em.createQuery("select m from UserEntity  m", UserEntity.class) //JPQL, SQL은 테이블을 대상으로 쿼리를 하지만 JPQL은 엔티티 객체에 대한 쿼리를 한다.
                .getResultList();
    }
}
