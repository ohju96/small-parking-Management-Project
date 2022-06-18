package project.SPM.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class VisitorDTO {

    @NotNull
    @Size(min = 11, message = "휴대폰 번호 11자리를 입력해주세요.")
    private String visitorPhoneNumber;
}
