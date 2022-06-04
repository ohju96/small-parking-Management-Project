package project.SPM.service;

import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;

public interface IInfoService {

    // 마이페이지
    boolean updateInfo(UserDTO userDTO) throws Exception;

    // 회원탈퇴
    boolean deleteUser(UserEntity userEntity) throws Exception;
}
