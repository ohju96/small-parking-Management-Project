package project.SPM.mapper;

import project.SPM.dto.CarDTO;

import java.util.List;

public interface ICarListMapper {

    // 전체 차량 조회 로직
    List<CarDTO> getFullCarList() throws Exception;

    // 주민 차량 조회 로직
    List<CarDTO> getResidentList() throws Exception;

    // 방문자 차량 조회 로직
    List<CarDTO> getVisitList() throws Exception;

    // 블랙리스트 차량 조회 로직
    List<CarDTO> getBlackList() throws Exception;
}
