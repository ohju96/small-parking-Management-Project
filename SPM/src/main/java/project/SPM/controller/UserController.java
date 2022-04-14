package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.SPM.dto.UserDTO;
import project.SPM.vo.UserSaveForm;
import project.SPM.service.impl.UserService;

import javax.validation.Valid;

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
    public String regUserForm(Model model) {

        log.info(this.getClass().getName() + ".user/regUser 회원가입으로 이동 !!");

        model.addAttribute("userSaveForm", new UserSaveForm());

        return "user/regUser";
    }


    /**
     * 회원가입 로직 처리
     */
    @PostMapping("/user/regUser/insert")
    public String InsertRegUser(@Valid UserSaveForm form, BindingResult result) throws Exception{

        log.info(this.getClass().getName() + "회원가입 로직 처리 시작");

        /**
         * 1. @Valid 검증 로직 실행
         */
        if (result.hasErrors()) {
            log.info(" 회원가입 로직 처리 중 Errors 처리 result ={}", result);
            return "user/regUser";
        }

        /**
         * 2. DTO 생성자에 VO 값 세팅
         */
        UserDTO userDTO = new UserDTO(
                form.getUserName(),
                form.getUserPn(),
                form.getUserEmail(),
                form.getUserId(),
                form.getUserPw(),
                form.getUserAddr()
        );

        log.info("UserDTO ={}", userDTO);

        /**
         * 3. 회원 가입 정보 DTO를 Controller -> Service 전달
         */
        userService.InsertUser(userDTO);

        return "/user/logIn";
    }

}
