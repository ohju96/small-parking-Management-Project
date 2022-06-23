package project.SPM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long userNo;
    private String userName;
    private String userPn;
    private String userEmail;
    private String userId;
    private String userPw;
    private String userAddr;

    public UserDTO(Long userNo,
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

    public UserDTO(
                   String userId) {
        this.userId = userId;
    }

}

