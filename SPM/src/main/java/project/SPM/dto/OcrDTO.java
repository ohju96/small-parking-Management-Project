package project.SPM.dto;

import lombok.Data;

@Data
public class OcrDTO {

    private String filePath; // 저장된 이미지 파일의 파일 저장 경로
    private String fileName; // 저장된 이미지 파일 이름
    private String textFromImage; // 저장된 이미지로부터 읽은 글씨

}
