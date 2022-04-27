package project.SPM.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class TestController {

    // git pull request test

    @GetMapping("/myInfo/myInfo")
    public String test1() {
        return "myInfo/myInfo";
    }

    @GetMapping("/manamgement/management")
    public String test2() {
        return "management/management";
    }

    @GetMapping("/carList/carList")
    public String test4() {
        return "carList/carList";
    }

    @GetMapping("/carCheck/carCheck")
    public String test5() {
        return "carCheck/carCheck";
    }

    @GetMapping("/user/changePassword")
    public String test8() {
        return "user/changePassword";
    }
}