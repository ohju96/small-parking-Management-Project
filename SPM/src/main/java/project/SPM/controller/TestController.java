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
}
