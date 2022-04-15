package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;
import project.SPM.repository.IUserRepository;
import project.SPM.repository.impl.UserRepository;
import project.SPM.service.IUserService;
import project.SPM.util.EncryptUtil;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository iUserRepository;

    private final UserRepository userRepository;

    @Override
    public void InsertUser(UserDTO userDTO) throws Exception {

        log.info(this.getClass().getName() + "로그인 처리 Controller -> Service");

        /**
         *  1-1. builder pattern 활용하여 Entity에 값을 세팅
         */
        UserEntity userEntity = UserEntity.builder()
                .userNo(userDTO.getUserNo())
                .userName(userDTO.getUserName())
                .userPn(userDTO.getUserPn())
                .userEmail(EncryptUtil.encHashSHA256(userDTO.getUserEmail()))
                .userId(userDTO.getUserId())
                .userPw(EncryptUtil.encHashSHA256(userDTO.getUserPw()))
                .userAddr(userDTO.getUserAddr())
                .build();

        log.info("userEntity에 담긴 값 확인 ={}", userEntity);

        /**
         *  1-2. ID 및 Email 중복 체크
         */
        validateDuplicateUser(userEntity);

        /**
         * 1-5. 중복 체크 완료 후 회원 가입
         */
        userRepository.save(userEntity);
    }

    private void validateDuplicateUser(UserEntity userEntity) {
        log.info(this.getClass().getName() + "중복 체크 로직 실행");
        log.info("중복 체크를 위해 받아온 아이디 값 = {}", userEntity.getUserId());
        log.info("중복 체크를 위해 받아온 이메일 값 = {}", userEntity.getUserEmail());


        /**
         * 1-3. 회원가입 중 ID 중복 체크
         */

        List<UserEntity> findUserId = userRepository.findById(userEntity.getUserId());

        log.info("로직 후 아이디 ={}", findUserId);

        if (!findUserId.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        /**
         * 1-4. 회원가입 중 Email 중복 체크
         */
        List<UserEntity> findUserEmail = userRepository.findByEmail(userEntity.getUserEmail());

        log.info("로직 후 이메일 ={}", findUserEmail);

        if (!findUserEmail.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

    }

    @Override
    public int login(UserDTO userDTO) throws Exception {

        int res = 0;

        /**
         * 2-1. builder pattern 활용하여 Entity에 값을 세팅
         */
        UserEntity userEntity = UserEntity.builder()
                .userNo(userDTO.getUserNo())
                .userId(userDTO.getUserId())
                .userPw(EncryptUtil.encHashSHA256(userDTO.getUserPw()))
                .build();

        log.info("Service에서 로그인 체크 DTO 받은 Entity 값 = {}", userEntity);

        /**
         * 2-2. ID, PW 조회
         */
        res = checkLogin(userEntity);

        log.info("로그인 로직 체크 후 userENtity 값 = {}", userEntity);
        log.info("res = {}", res);
        return res;
    }

    private int checkLogin(UserEntity userEntity) {
        log.info(this.getClass().getName() + "로그인 체크 로직 실행");
        log.info("로그인 체크를 위해 받아온 비밀번호 값 = {}", userEntity.getUserPw());

        int res = 0;

        List<UserEntity> byPw = userRepository.checkLogin(userEntity.getUserId(),userEntity.getUserPw());

        if (byPw != null && !byPw.isEmpty() ) {
            log.info("res 1 ={}", byPw);
            res = 1;
        } else {
            log.info("res 0 ={}", byPw);
            res = 0;
        }

        return res;

    }
}