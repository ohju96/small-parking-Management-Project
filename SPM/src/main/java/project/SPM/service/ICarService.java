package project.SPM.service;

import project.SPM.dto.CarDTO;
import project.SPM.dto.SessionIdDTO;
import project.SPM.dto.UserDTO;
import project.SPM.vo.UpdateCarListVo;

public interface ICarService {

    // 엑셀 파일 등록 로직
    boolean createCar(SessionIdDTO sessionIdDTO) throws Exception;

    // 직접 등록 로직
    boolean addCar(CarDTO carDTO) throws Exception;

    // 수정 및 삭제
    boolean updateCar(UpdateCarListVo updateCarListVo) throws Exception;

    // 초기화
    boolean dropCar(UserDTO userDTO) throws Exception;
}
