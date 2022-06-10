package project.SPM.dto;

import lombok.Data;

@Data
public class SmsDTO {

    private String to;
    private String from;
    private String text;
}
