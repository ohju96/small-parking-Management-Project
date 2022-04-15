package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;
import project.SPM.vo.UserVo;
import project.SPM.service.impl.UserService;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 페이지 이동
     */
    @GetMapping("/user/regUser")
    public String regUserForm(Model model) {

        log.info(this.getClass().getName() + ".user/regUser 회원가입으로 이동 !!");


        model.addAttribute("userVo", new UserVo());

        return "user/regUser";
    }


    /**
     * 회원가입 로직 처리
     */
    @PostMapping("/user/regUser/insert")
    public String InsertRegUser(@Validated @ModelAttribute UserVo userVo, BindingResult bindingResult) throws Exception{

        log.info(this.getClass().getName() + "회원가입 로직 처리 시작");

        /**
         * 1. @Valid 검증 로직 실행
         */

        if (!StringUtils.hasText(userVo.getUserName())) {
            bindingResult.addError(new FieldError("userVo", "userName", userVo.getUserName(),
                    false, null, null, "이름을 입력해주세요."));
        }

        if (!StringUtils.hasText(userVo.getUserPn())) {
            bindingResult.addError(new FieldError("userVo", "userPn", userVo.getUserPn(),
                    false, null, null, "휴대폰 번호를 입력해주세요."));
        }

        if (!StringUtils.hasText(userVo.getUserEmail())) {
            bindingResult.addError(new FieldError("userVo", "userEmail", userVo.getUserEmail(),
                    false, null, null, "이메일을 입력해주세요."));
        }

        if (!StringUtils.hasText(userVo.getUserId())) {
            bindingResult.addError(new FieldError("userVo", "userId", userVo.getUserId(),
                    false, null, null, "아이디를 입력해주세요."));
        }

        if (!StringUtils.hasText(userVo.getUserPw())) {
            bindingResult.addError(new FieldError("userVo", "userPw", userVo.getUserPw(),
                    false, null, null, "비밀번호를 입력해주세요."));
        }

        if (!StringUtils.hasText(userVo.getUserAddr())) {
            bindingResult.addError(new FieldError("userVo", "userAddr", userVo.getUserAddr(),
                    false, null, null, "주소를 입력해주세요."));
        }

        if (bindingResult.hasErrors()) {
            log.info(" 회원가입 로직 처리 중 Errors 처리 bindingResult ={}", bindingResult);
            return "user/regUser";
        }

        /**
         * 2. DTO 생성자에 VO 값 세팅
         */
        UserDTO userDTO = new UserDTO(
                userVo.getUserNo(),
                userVo.getUserName(),
                userVo.getUserPn(),
                userVo.getUserEmail(),
                userVo.getUserId(),
                userVo.getUserPw(),
                userVo.getUserAddr()
        );

        log.info("UserDTO ={}", userDTO);

        /**
         * 3. 회원 가입 정보 DTO를 Controller -> Service 전달
         */
        userService.InsertUser(userDTO);


        return "/user/logIn";
    }

    /**
     * 로그인 페이지 이동
     */
    @GetMapping("/user/logIn")
    public String userLogin(Model model) {

        log.info(this.getClass().getName() + ".user/login 로그인으로 이동 !!");

        model.addAttribute("userVo", new UserVo());

        return "user/logIn";
    }

    /**
     * 로그인 로직 처리
     */
    @PostMapping("/user/logIn/page")
    public String Login(@ModelAttribute UserVo userVo, HttpSession session) throws Exception {

        log.info(this.getClass().getName() + "로그인 로직 처리 시작");

        int res = 0;

        try {
            log.info("UserDTO에 Vo값 담기 시작");
            UserDTO userDTO = new UserDTO(userVo.getUserNo(), userVo.getUserId(), userVo.getUserPw());

            log.info("userDTO = {}", userDTO);

            res = userService.login(userDTO);

            log.info("res = {}", res);

            if (res == 1) {
                log.info("res = {}", res);
                log.info("로그인 성공");
            } else {
                log.info("res = {}", res);
                log.info("로그인 실패");
                return "user/login";
            }

        } catch (Exception e) {

            res = 2;
            log.info(e.toString());
            e.printStackTrace();

        } finally {

            log.info(this.getClass().getName() + "로그인 로직 처리 끝");
            userVo = null;

        }
        return "index";
    }



}
