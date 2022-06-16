package project.SPM.mapper;

import project.SPM.dto.CarDTO;
import project.SPM.dto.UserDTO;
import project.SPM.dto.ViewCarDTO;

import java.util.List;

public interface ICheckMapper {

    // 터치 저장 로직
    boolean saveTouchCheck(List<CarDTO> list, String colNm) throws Exception;

    // 완료 항목 보기
    List<ViewCarDTO> viewCheck(UserDTO userDTO) throws Exception;

    // 완료 항목 상세 보기
    List<CarDTO> detail(String checkCollectionName) throws Exception;


    // 이미지 저장을 위해 userDTO id로 CarDTO를 불러온다.
    CarDTO checkId(UserDTO userDTO, String carNumber) throws Exception;

    // 이미지 저장 로직
    int saveImageCheck(CarDTO carDTO, String colNm) throws Exception;
}
