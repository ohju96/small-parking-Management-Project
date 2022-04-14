package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;
import project.SPM.repository.impl.UserRepository;
import project.SPM.service.IUserService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void InsertUser(UserDTO userDTO) throws Exception {

        log.info(this.getClass().getName() + "로그인 처리 Controller -> Service");

        /**
         *  1. builder pattern 활용하여 Entity에 값을 세팅
         */
        UserEntity userEntity = UserEntity.builder()
                .userName(userDTO.getUserName())
                .userPn(userDTO.getUserPn())
                .userEmail(userDTO.getUserEmail())
                .userId(userDTO.getUserId())
                .userPw(userDTO.getUserPw())
                .userAddr(userDTO.getUserAddr())
                .build();

        log.info("userEntity에 담긴 값 확인 ={}", userEntity);

        /**
         *  2. ID 및 Email 중복 체크
         */
        validateDuplicateUser(userEntity);

        /**
         * 5. 중복 체크 완료 후 회원 가입
         */
        userRepository.save(userEntity);
    }

    private void validateDuplicateUser(UserEntity userEntity) {

        log.info(this.getClass().getName() + "중복 체크 로직 실행");
        log.info("중복 체크를 위해 받아온 아이디 값 = {}", userEntity.getUserId());
        log.info("중복 체크를 위해 받아온 이메일 값 = {}", userEntity.getUserEmail());

        /**
         * 3. 회원가입 중 ID 중복 체크
         */
        UserEntity findUserId = userRepository.findId(userEntity.getUserId());

        if (findUserId != null) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        /**
         * 4. 회원가입 중 Email 중복 체크
         */
        List<UserEntity> findUserEmail = userRepository.findByEmail(userEntity.getUserEmail());

        log.info("로직 후 이메일 ={}", findUserEmail);
        if (!findUserEmail.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        log.info(this.getClass().getName() + "중복 체크 로직 종료");
    }

}
