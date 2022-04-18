package project.SPM.vo;

import javax.validation.constraints.NotBlank;

public class LoginVo {

    private Long userNo;
    private String userName;
    private String userPn;
    private String userEmail;

    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String userPw;

    private String userAddr;
}
