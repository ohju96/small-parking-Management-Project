package project.SPM.service;

import org.springframework.web.multipart.MultipartFile;
import project.SPM.dto.CarDTO;

public interface ICarService {

    // 엑셀 파일 등록 로직
    void CreateCar(MultipartFile mf) throws Exception;

    // 직접 등록 로직
    boolean addCar(CarDTO carDTO) throws Exception;
}
