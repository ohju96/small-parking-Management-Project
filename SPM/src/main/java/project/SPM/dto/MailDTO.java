package project.SPM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MailDTO {

    private String address;
    private String title;
    private String message;

}
