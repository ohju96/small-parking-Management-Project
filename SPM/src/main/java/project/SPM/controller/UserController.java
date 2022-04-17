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
import project.SPM.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
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
    // TODO: 2022-04-17 아이디 비밀번호 체크 로직 필요, Session 값 넘기는 로직 필요, 예외 처리 필요
    @PostMapping("/user/logIn/page")
    public String login(@ModelAttribute UserVo userVo, HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + "로그인 로직 처리 시작");

        HttpSession session = request.getSession();

        int res = 0;

            UserDTO userDTO = new UserDTO(userVo.getUserNo(), userVo.getUserId(), userVo.getUserPw());

            res = userService.login(userDTO);

            if (res == 1) {
                session.getAttribute("res");
                session.setAttribute(SessionConst.LOGIN_MEMBER, userDTO);
            } else {
                return "user/login";
            }

            log.info(this.getClass().getName() + "로그인 로직 처리 끝");

        return "index";
    }



}
