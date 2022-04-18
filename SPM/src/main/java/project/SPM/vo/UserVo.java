package project.SPM.vo;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserVo {

    private Long userNo;

    @NotNull
    private String userName;

    @NotNull
    private String userPn;

    @NotNull
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String userEmail;

    @NotNull
    private String userId;

    @NotNull
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String userPw;

    @Column(name = "USER_ADDR", length = 200)
    private String userAddr;

}

