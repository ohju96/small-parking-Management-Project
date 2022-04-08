package project.SPM.Entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "USER_INFO")
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
    private String userName;

    @Column(name = "USER_PN", length = 40)
    private String userPn;

    @Column(name = "USER_EMAIL", length = 200)
    private String userEmail;

    @Column(name = "USER_ID", length = 200)
    private String userId;

    @Column(name = "USER_PW", length = 50)
    private String userPw;

    @Column(name = "USER_ADDR", length = 200)
    private String userAddr;

    /**
     *  setter 사용을 지양하기 위해 만든 생성자.
     *
     * @param userNo 시퀸스 넘버
     * @param userName 이름
     * @param userPn 핸드폰 번호
     * @param userEmail 이메일
     * @param userId 아이디
     * @param userPw 비밀번호
     * @param userAddr 근무 주소
     */

    public void changeUserInfo(Long userNo,
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

