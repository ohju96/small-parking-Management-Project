package project.SPM.service;

import project.SPM.dto.UserDTO;

public interface IInfoService {

    // 마이페이지
    Boolean updateInfo(UserDTO userDTO) throws Exception;
}
