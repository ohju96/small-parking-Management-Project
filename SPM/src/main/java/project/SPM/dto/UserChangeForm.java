package project.SPM.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserChangeForm {

    @Column(name = "USER_NO", length = 10)
    private Long userNo;

    @NotBlank(message = "이름을 입력해주세요.")
    private String userName;

    @NotBlank(message = "연락처를 입력해주세요.")
    private String userPn;

    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String userEmail;

    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String userPw;

    @Column(name = "USER_ADDR", length = 200)
    private String userAddr;

}

