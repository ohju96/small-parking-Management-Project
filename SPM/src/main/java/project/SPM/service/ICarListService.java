package project.SPM.service;

import project.SPM.dto.CarDTO;
import project.SPM.dto.UserDTO;

import java.util.List;

public interface ICarListService {

    // 전체 차량 조회 로직
    List<CarDTO> getFullCarList(UserDTO userDTO) throws Exception;

    // 주민 차량 조회 로직
    List<CarDTO> getResidentList(UserDTO userDTO) throws Exception;

    // 방문자 차량 조회 로직
    List<CarDTO> getVisitList(UserDTO userDTO) throws Exception;

    // 블랙리스트 차량 조회 로직
    List<CarDTO> getBlackList(UserDTO userDTO) throws Exception;
}
