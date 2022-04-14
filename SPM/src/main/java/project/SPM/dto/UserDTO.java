package project.SPM.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String userName;
    private String userPn;
    private String userEmail;
    private String userId;
    private String userPw;
    private String userAddr;

    public UserDTO(String userName, String userPn, String userEmail, String userId, String userPw, String userAddr) {
        this.userName = userName;
        this.userPn = userPn;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userPw = userPw;
        this.userAddr = userAddr;
    }
}

