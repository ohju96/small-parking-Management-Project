package project.SPM.service;

import project.SPM.Entity.UserEntity;
import project.SPM.dto.MailDTO;
import project.SPM.dto.UserDTO;

import java.util.List;

public interface IUserService {

    // 회원가입
    void InsertUser(UserDTO userDTO) throws Exception;

    // 로그인 : 아이디 , 비밀번호 체크
    boolean login(UserDTO userDTO) throws Exception;

    UserEntity loginSession(UserDTO userDTO) throws Exception;

    MailDTO findPw(String userEmail) throws Exception;

    void sendMail(MailDTO mailDTO) throws Exception;

}
