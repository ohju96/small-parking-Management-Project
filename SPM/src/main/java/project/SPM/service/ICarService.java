package project.SPM.service;

import org.springframework.web.multipart.MultipartFile;
import project.SPM.dto.CarDTO;
import project.SPM.vo.UpdateCarListVo;

public interface ICarService {

    // 엑셀 파일 등록 로직
    void createCar(MultipartFile mf) throws Exception;

    // 직접 등록 로직
    boolean addCar(CarDTO carDTO) throws Exception;

    // 수정 및 삭제
    boolean updateCar(UpdateCarListVo updateCarListVo) throws Exception;
}
