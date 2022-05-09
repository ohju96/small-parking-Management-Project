package project.SPM.mapper;

import project.SPM.dto.CarDTO;

import java.util.List;

public interface ICarMapper {

    // 엑셀 등록
    void CreateCar(List<CarDTO> list) throws Exception;

    // 직접 등록
    boolean addCar(CarDTO carDTO) throws Exception;
}
