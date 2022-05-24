package project.SPM.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@Slf4j
public class TestController {

    // git pull request test

    @GetMapping("/myInfo/myInfo")
    public String test1() {
        return "myInfo/myInfo";
    }

    @GetMapping("/management/management")
    public String test2() {
        return "management/management";
    }

    @GetMapping("/user/changePassword")
    public String test8() {
        return "user/changePassword";
    }

}