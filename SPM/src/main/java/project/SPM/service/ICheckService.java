package project.SPM.service;

import project.SPM.dto.CarDTO;

import java.util.List;

public interface ICheckService {

    // 직접 저장 로직
    boolean saveTouchCheck(CarDTO carDTO) throws Exception;

    // 이미지 저장 로직
    List<CarDTO> saveImgCheck() throws Exception;
}
