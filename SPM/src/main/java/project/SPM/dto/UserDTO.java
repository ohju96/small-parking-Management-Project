package project.SPM.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class UserDTO {

    private Long userNo;
    private String userName;
    private String userPn;
    private String userEmail;
    private String userId;
    private String userPw;
    private String userAddr;

}
