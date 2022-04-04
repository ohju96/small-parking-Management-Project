package project.SPM.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class TestController {

    @GetMapping("/")
    public String test() {
        return "index";
    }

    @GetMapping("/myInfo/myInfo")
    public String test1() {
        return "myInfo/myInfo";
    }

    @GetMapping("/management/management")
    public String test2() {
        return "management/management";
    }

    @GetMapping("/carManagement/carManagement")
    public String test3() {
        return "carManagement/carManagement";
    }

    @GetMapping("/carList/carList")
    public String test4() {
        return "carList/carList";
    }

    @GetMapping("/carCheck/carCheck")
    public String test5() {
        return "carCheck/carCheck";
    }

    @GetMapping("/user/logIn")
    public String test6() {
        return "user/logIn";
    }

    @GetMapping("/user/regUser")
    public String test7() {
        return "user/regUser";
    }

    @GetMapping("/user/changePassword")
    public String test8() {
        return "user/changePassword";
    }
}