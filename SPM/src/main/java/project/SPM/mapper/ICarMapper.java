package project.SPM.mapper;

import project.SPM.dto.CarDTO;

import java.util.List;

public interface ICarMapper {

    void CreateCar(List<CarDTO> list) throws Exception;
}
