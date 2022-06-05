package project.SPM.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SessionIdDTO {

    private String userId;
    private MultipartFile mf;
}
