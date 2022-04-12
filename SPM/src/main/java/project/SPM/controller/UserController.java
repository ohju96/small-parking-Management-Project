package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.SPM.Entity.UserEntity;
import project.SPM.service.impl.UserService;
import project.SPM.util.EncryptUtil;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 회원가입 페이지로 이동
     */
    @GetMapping("/user/regUser")
    public String regUserForm() {

        log.info(this.getClass().getName() + ".user/regUser 회원가입으로 이동 !!");

        return "user/regUser";
    }


    /**
     * 회원가입 로직 처리
     */
    @PostMapping("/user/regUser/insert")
    public String InsertRegUser(HttpServletRequest request) throws Exception{

        log.info(this.getClass().getName() + ".InsertRegUser 회원가입 로직 처리 시작 !!");


        UserEntity userEntity = UserEntity.builder()
                .userName(request.getParameter("userName"))
                .userPn(request.getParameter("userPn"))
                .userEmail(EncryptUtil.encAES128CBC(request.getParameter("userEmail")))
                .userId(request.getParameter("userId"))
                .userPw(EncryptUtil.encHashSHA256(request.getParameter("userPw")))
                .userAddr(request.getParameter("userAddr"))
                .build();

        log.info("UserEntity ={}", userEntity);

        userService.createUser(userEntity);

        return "/user/logIn";
    }

}
