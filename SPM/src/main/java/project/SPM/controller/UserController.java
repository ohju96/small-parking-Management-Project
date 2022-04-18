package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.SPM.dto.UserDTO;
import project.SPM.validator.UserValidator;
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
    private final UserValidator userValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        log.info("init binder {}", dataBinder);
        dataBinder.addValidators(userValidator);
    }

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

        if (bindingResult.hasErrors()) {
            log.info(" 회원가입 로직 처리 중 Errors 처리 bindingResult ={}", bindingResult);
            return "user/regUser";
        }

        UserDTO userDTO = new UserDTO(userVo.getUserNo(), userVo.getUserName(), userVo.getUserPn(), userVo.getUserEmail(), userVo.getUserId(), userVo.getUserPw(), userVo.getUserAddr());

        log.info("UserDTO ={}", userDTO);

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

        int res = 0;

        UserDTO userDTO = new UserDTO(userVo.getUserNo(), userVo.getUserId(), userVo.getUserPw());

        res = userService.login(userDTO);

        if (res == 1) {
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_MEMBER, userDTO);
        } else {
            return "user/login";
        }

    log.info(this.getClass().getName() + "로그인 로직 처리 끝");

    return "index";
    }

    /**
     * 로그아웃 기능 구현하기
     */
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); //세션에 있는 정보가 싹 날라간다.
        }
        return "redirect:/user/logIn";
    }


}
