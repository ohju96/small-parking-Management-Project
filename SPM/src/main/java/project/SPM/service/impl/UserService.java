package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;
import project.SPM.repository.IUserRepository;
import project.SPM.repository.impl.UserRepository;
import project.SPM.service.IUserService;
import project.SPM.util.EncryptUtil;
import project.SPM.validator.UserValidator;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service("UserService")
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final IUserRepository iUserRepository;

    // 회원 가입 로직
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
        boolean res = validateDuplicateUser(userEntity);

        if (res == false) {
            // 중복 체크 완료 후 회원 가입 로직
            iUserRepository.save(userEntity);
        }
    }

    private boolean validateDuplicateUser(UserEntity userEntity) {

        log.info(this.getClass().getName() + "중복 체크 로직 실행");

        log.info("중복 체크를 위해 받아온 아이디 값 = {}", userEntity.getUserId());
        log.info("중복 체크를 위해 받아온 이메일 값 = {}", userEntity.getUserEmail());

        // 아이디 중복 체크
        boolean id = iUserRepository.existsByUserId(userEntity.getUserId());
        if (id == true) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        // 이메일 중복 체크
        boolean email = iUserRepository.existsByUserEmail(userEntity.getUserEmail());
        if (email == true) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        return false;
    }

    // 로그인 로직
    // TODO: 2022-06-02 로그인 로직 IUserRepository 사용하도록 수정 필요 
    @Override
    public boolean login(UserDTO userDTO) throws Exception {

        boolean res;

        // 빌더 패턴 활용하여 Entity 값 셋팅
        UserEntity userEntity = UserEntity.builder()
                .userNo(userDTO.getUserNo())
                .userName(userDTO.getUserName())
                .userPn(userDTO.getUserPn())
                .userEmail(EncryptUtil.encHashSHA256(userDTO.getUserEmail()))
                .userId(userDTO.getUserId())
                .userPw(EncryptUtil.encHashSHA256(userDTO.getUserPw()))
                .userAddr(userDTO.getUserAddr())
                .build();

        log.info("Service에서 로그인 체크 DTO 받은 Entity 값 = {}", userEntity);

        // 로그인을 위해 ID, PW 조회
        res = checkLogin(userEntity);
/*        boolean id = iUserRepository.findById(userEntity.getUserId());
        boolean pw = iUserRepository.findByPw(userEntity.getUserPw());

        if (id == true || pw == true) {
            return true;
        } else {
            return false;
        }*/
        return res;
    }

    private boolean checkLogin(UserEntity userEntity) {
        log.info(this.getClass().getName() + "로그인 체크 로직 실행");
        log.info("로그인 체크를 위해 받아온 비밀번호 값 = {}", userEntity.getUserPw());

        boolean res;

        List<UserEntity> byPw = userRepository.checkLogin(userEntity.getUserId(),userEntity.getUserPw());

        if (byPw != null && !byPw.isEmpty() ) {
            log.info("res 1 ={}", byPw);
            res = true;
        } else {
            log.info("res 0 ={}", byPw);
            res = false;
        }

        return res;

    }

    // 세션에 담기 위해 값 조회
    @Override
    public UserEntity loginSession(UserDTO userDTO) throws Exception {

        List<UserEntity> userDTOList = iUserRepository.findAll();
        UserEntity userEntity = userDTOList.get(0);

        return userEntity;
    }
}