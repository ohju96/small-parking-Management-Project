package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import project.SPM.dto.UserDTO;
import project.SPM.service.impl.UserService;
import project.SPM.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {


    @GetMapping("/")
    public String index(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)UserDTO userDTO, Model model) {

        if (userDTO == null ) {
            return "/user/logIn";
        }

        model.addAttribute("member", userDTO);

        return "/index";
    }
}
