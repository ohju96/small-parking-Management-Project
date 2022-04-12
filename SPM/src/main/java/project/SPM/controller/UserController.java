package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;
import project.SPM.service.impl.UserService;
import project.SPM.util.CmmUtil;
import project.SPM.util.EncryptUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        log.info(this.getClass().getName() + ".InsertRegUser Start");

        //log.info(userDTO.getUserId());

        UserEntity userEntity = UserEntity.builder()
                .userName(request.getParameter("userName"))
                .userPn(request.getParameter("userPn"))
                .userEmail(request.getParameter("userEmail"))
                .userId(request.getParameter("userId"))
                .userPw(request.getParameter("userPw"))
                .userAddr(request.getParameter("userAddr"))
                .build();

        userService.createUser(userEntity);

        return "/user/logIn";
    }

}
