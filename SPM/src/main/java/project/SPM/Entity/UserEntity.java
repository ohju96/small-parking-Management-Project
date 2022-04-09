package project.SPM.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.SPM.dto.UserDTO;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public UserEntity(UserDTO userDTO) {
        this.userName = userDTO.getUserName();
        this.userPn = userDTO.getUserPn();
        this.userEmail = userDTO.getUserEmail();
        this.userId = userDTO.getUserId();
        this.userPw = userDTO.getUserPw();
        this.userAddr = userDTO.getUserAddr();
    }

}

