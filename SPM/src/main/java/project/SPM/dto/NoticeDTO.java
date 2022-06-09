package project.SPM.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NoticeDTO {

    @NotNull
    @Size(min = 1, max = 40, message = "40글자 이내로 작성해주세요.(SMS 기준 : 한글 40글자)")
    private String message;

    private String userId;
}
