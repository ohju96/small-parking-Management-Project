package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String InsertRegUser(HttpServletRequest request,
                                UserEntity userEntity) throws Exception{


        log.info(this.getClass().getName() + ".InsertRegUser Start");

/*        UserEntity.UserEntityBuilder builder = UserEntity.builder()
                .userName(request.getParameter("userName"))
                .userPn(request.getParameter("userPn"))
                .userEmail(request.getParameter("userEmail"))
                .userId(request.getParameter("userId"))
                .userPw(request.getParameter("userPw"))
                .userAddr(request.getParameter("userAddr"))
                .build()*/


/*        String userName = CmmUtil.nvl(request.getParameter("userName"));
        String userPn = CmmUtil.nvl(request.getParameter("userPn"));
        String userEmail = CmmUtil.nvl(request.getParameter("userEmail"));
        String userId = CmmUtil.nvl(request.getParameter("userId"));
        String userPw = CmmUtil.nvl(request.getParameter("userPw"));
        String userAddr = CmmUtil.nvl(request.getParameter("userAddr"));

        log.info("userName ={}", userName);
        log.info("userPn ={}", userPn);
        log.info("userEmail ={}", userEmail);
        log.info("userId ={}", userId);
        log.info("UserPw ={}", userPw);
        log.info("userAddr ={}", userAddr);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userName);
        userDTO.setUserPn(userPn);
        userDTO.setUserEmail(EncryptUtil.encAES128CBC(userEmail));
        userDTO.setUserId(userId);
        userDTO.setUserPw(EncryptUtil.encHashSHA256(userPw));
        userDTO.setUserAddr(userAddr);*/

        userService.createUser(userEntity);

        return "/user/logIn";
    }

}
