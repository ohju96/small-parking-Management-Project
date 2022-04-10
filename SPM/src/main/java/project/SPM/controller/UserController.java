package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.SPM.dto.UserDTO;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @PostMapping("/regUser")
    public String regUser() throws Exception{

        UserDTO userDTO = null;

        userDTO.setUserId("userName");
        userDTO.setUserPn("userPn");
        userDTO.setUserEmail("userEmail");
        userDTO.setUserId("userId");
        userDTO.setUserPw("userPw");
        userDTO.setUserAddr("userAddr");
        

        return "/logIn";
    }

}
