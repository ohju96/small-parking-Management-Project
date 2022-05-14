package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import project.SPM.dto.CarDTO;
import project.SPM.mapper.ICarListMapper;
import project.SPM.service.ICarListService;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component("CarListService")
@RequiredArgsConstructor
public class CarListService implements ICarListService {

    private final ICarListMapper iCarListMapper;


    @Override
    public List<CarDTO> getFullCarList() throws Exception {

        // 결과 값
        List<CarDTO> carDTOList = null;

        carDTOList = iCarListMapper.getFullCarList();

        if (carDTOList == null) {
            carDTOList = new LinkedList<>();
        }

        return carDTOList;
    }

    @Override
    public List<CarDTO> getResidentList() throws Exception {

        // 결과 값
        List<CarDTO> carDTOList = null;

        carDTOList = iCarListMapper.getResidentList();

        if (carDTOList == null) {
            carDTOList = new LinkedList<>();
        }

        return carDTOList;
    }
}
