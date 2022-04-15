package project.SPM.service;

import project.SPM.dto.UserDTO;

public interface IUserService {

    /**
     * 회원가입
     */
    void InsertUser(UserDTO userDTO) throws Exception;

    /**
     * 로그인
     */
    int login(UserDTO userDTO) throws Exception;
}
