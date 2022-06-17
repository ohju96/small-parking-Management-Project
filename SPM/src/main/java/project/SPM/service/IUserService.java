package project.SPM.service;

import project.SPM.Entity.UserEntity;
import project.SPM.dto.MailDTO;
import project.SPM.dto.UserDTO;

public interface IUserService {

    // 회원가입
    String insertUser(UserDTO userDTO) throws Exception;

    // 로그인 : 아이디 , 비밀번호 체크
    boolean login(UserDTO userDTO) throws Exception;

    UserEntity loginSession(UserDTO userDTO) throws Exception;

    // 비밀번호 찾기
    String findPw(String userEmail) throws Exception;

    // 메일 보내기
//    void sendMail(MailDTO mailDTO) throws Exception;

    // 아이디 찾기
    String findId(String userEmail) throws Exception;

}
