package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;
import project.SPM.service.IInfoService;
import project.SPM.validator.UserValidator;
import project.SPM.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/myInfo")
@RequiredArgsConstructor
public class InfoController {

    private final IInfoService iInfoService;
    private final UserValidator userValidator;

    @InitBinder("userVo")
    public void init(WebDataBinder dataBinder) {
        log.info("init binder {}", dataBinder);
        dataBinder.addValidators(userValidator);
    }

    // myInfo 페이지
    @GetMapping("/myInfo")
    public String myInfoPage() {
        return "myInfo/myInfo";
    }

    // 마이페이지
    @GetMapping("updateInfo")
    public String updateInfoPage(Model model) {
        model.addAttribute("userVo", new UserVo());
        return "myInfo/updateInfo";
    }

    // 마이페이지 로직
    @PostMapping("updateInfo")
    public String updateInfo(@Validated @ModelAttribute UserVo userVo, BindingResult bindingResult, HttpSession session, Model model) throws Exception {

        if (bindingResult.hasErrors()) {
            model.addAttribute("url", "/myInfo/updateInfo");
            model.addAttribute("msg", "정보를 입력해주세요.");
            return "redirect";
        }

        if (!userVo.getUserPw().equals(userVo.getUserPwc())) {
            model.addAttribute("msg", "비밀번호 체크에 실패하였습니다. 다시 입력해주세요");
            model.addAttribute("url", "/myInfo/updateInfo");
            return "redirect";
        }

        UserDTO userDTO = new UserDTO(
                userVo.getUserNo(),
                userVo.getUserName(),
                userVo.getUserPn(),
                userVo.getUserEmail(),
                userVo.getUserId(),
                userVo.getUserPw(),
                userVo.getUserAddr()
        );

        boolean res = iInfoService.updateInfo(userDTO);

        if (res == true) {
            model.addAttribute("내 정보 수정에 성공하였습니다.");
        } else {
            model.addAttribute("내 정보 수정에 실패하였습니다. 다시 시도해주세요.");
        }

        model.addAttribute("url","/myInfo/updateInfo");

        return "redirect";
    }

    // 회원 탈퇴 페이지
    @GetMapping("deleteUser")
    public String deleteUserPage() {
        return "myInfo/deleteUser";
    }

    // 회원 탈퇴 로직
    @PostMapping("deleteUser")
    public String deleteUser(HttpServletRequest request, HttpSession session, Model model) throws Exception {

        UserEntity userEntity = (UserEntity) session.getAttribute("userDTO");

        boolean res = iInfoService.deleteUser(userEntity);

        if (res == true) {
            session = request.getSession(false);

            if (session != null) {
                session.invalidate();
            }

            model.addAttribute("msg", "회원 탈퇴에 성공하였습니다.");
            model.addAttribute("url", "/user/logIn");

            return "redirect";
        } else {
            model.addAttribute("msg", "회원 탈퇴에 실패하였습니다. 다시 시도해주세요.");
            model.addAttribute("url", "/myInfo/deleteUser");
            return "redirect";
        }
    }
}
