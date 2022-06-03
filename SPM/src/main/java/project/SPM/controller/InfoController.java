package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.SPM.vo.UserVo;

@Slf4j
@Controller
@RequestMapping("/myInfo")
@RequiredArgsConstructor
public class InfoController {


    @GetMapping("/updateInfo")
    public String updateInfoPage(Model model) {

        model.addAttribute("userVo", new UserVo());

        return "myInfo/updateInfo";
    }

}
