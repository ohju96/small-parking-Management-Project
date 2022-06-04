package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;
import project.SPM.repository.IUserRepository;
import project.SPM.repository.impl.UserRepository;
import project.SPM.service.IInfoService;
import project.SPM.util.EncryptUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class InfoService implements IInfoService {

    private final IUserRepository iUserRepository;
    private final UserRepository userRepository;

    @Override
    public boolean updateInfo(UserDTO userDTO) throws Exception {

        UserEntity userEntity = UserEntity.builder()
                .userNo(userDTO.getUserNo())
                .userName(userDTO.getUserName())
                .userPn(userDTO.getUserPn())
                .userEmail(userDTO.getUserEmail())
                .userId(userDTO.getUserId())
                .userPw(EncryptUtil.encHashSHA256(userDTO.getUserPw()))
                .userAddr(userDTO.getUserAddr())
                .build();

        iUserRepository.save(userEntity);
        boolean res = true;

        return res;
    }

    // 회원탈퇴
    @Override
    public boolean deleteUser(UserEntity userEntity) throws Exception {

        iUserRepository.delete(userEntity);
        boolean res = true;

        return res;
    }
}
