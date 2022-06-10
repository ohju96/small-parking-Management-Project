package project.SPM.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class VisitorDTO {

    @NotNull
    @Size(min = 1, message = "한 글자 이상 입력해 주세요.")
    private String visitorPhoneNumber;
}
