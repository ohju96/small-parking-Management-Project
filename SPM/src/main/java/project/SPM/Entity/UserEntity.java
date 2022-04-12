package project.SPM.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_INFO")
@ToString
public class UserEntity {

    /**
     * USER TABLE
     * DB : MariaDB
     * 대상 : 사용자, 경비원
     */

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "USER_NO", length = 10)
    private Long userNo;

    @Column(name = "USER_NAME", length = 20)
    //@NotBlank(message = "이름을 입력해주세요.")
    private String userName;

    @Column(name = "USER_PN", length = 40)
    //@NotBlank(message = "연락처를 입력해주세요.")
    private String userPn;

    @Column(name = "USER_EMAIL", length = 200)
    //@NotBlank(message = "이메일 주소를 입력해주세요.")
    //@Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String userEmail;

    @Column(name = "USER_ID", length = 200)
    //@NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @Column(name = "USER_PW", length = 200)
    //@NotBlank(message = "비밀번호를 입력해주세요.")
    //@Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String userPw;

    @Column(name = "USER_ADDR", length = 200)
    private String userAddr;

    @Builder
    public UserEntity(Long userNo,
                      String userName,
                      String userPn,
                      String userEmail,
                      String userId,
                      String userPw,
                      String userAddr) {
        this.userNo = userNo;
        this.userName = userName;
        this.userPn = userPn;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userPw = userPw;
        this.userAddr = userAddr;
    }

}

