package project.SPM.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_INFO")
@ToString
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userNo;

    @Column(name = "USER_NAME", length = 20)
    private String userName;

    @Column(name = "USER_PN", length = 40)
    private String userPn;

    @NotNull
    @Column(name = "USER_EMAIL", length = 200)
    private String userEmail;

    @NotNull
    @Column(name = "USER_ID", length = 200, unique = true)
    private String userId;

    @Column(name = "USER_PW", length = 200)
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

