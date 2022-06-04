package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.UserDTO;
import project.SPM.service.IInfoService;
import project.SPM.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/myInfo")
@RequiredArgsConstructor
public class InfoController {

    private final IInfoService iInfoService;

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
    public String updateInfo(@ModelAttribute UserVo userVo, HttpSession session) throws Exception {

        log.debug("### 마이페이지 로직 시작");

        log.debug("### 마이페이지 시작 후 UserVo : {}", userVo);

        UserDTO userDTO = new UserDTO(
                userVo.getUserNo(),
                userVo.getUserName(),
                userVo.getUserPn(),
                userVo.getUserEmail(),
                userVo.getUserId(),
                userVo.getUserPw(),
                userVo.getUserAddr()
        );

        log.debug("### 마이페이지 로직 userDTO : {}", userDTO);

        boolean res = iInfoService.updateInfo(userDTO);

        if (res == true) {
            return "myInfo/updateInfo";
        } else {
            return "myInfo/updateInfo";
        }
    }

    // 회원 탈퇴 페이지
    @GetMapping("deleteUser")
    public String deleteUserPage() {
        return "myInfo/deleteUser";
    }

    // 회원 탈퇴 로직
    @PostMapping("deleteUser")
    public String deleteUser(HttpServletRequest request, HttpSession session) throws Exception {

        UserEntity userEntity = (UserEntity) session.getAttribute("userDTO");

        boolean res = iInfoService.deleteUser(userEntity);

        if (res == true) {

            session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            return "redirect:/user/logIn";
        } else {
            return "myInfo/deleteUser";
        }
    }
}
