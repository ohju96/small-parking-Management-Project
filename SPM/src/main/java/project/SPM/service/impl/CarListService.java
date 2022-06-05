package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import project.SPM.dto.CarDTO;
import project.SPM.dto.UserDTO;
import project.SPM.mapper.ICarListMapper;
import project.SPM.service.ICarListService;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component("CarListService")
@RequiredArgsConstructor
public class CarListService implements ICarListService {

    private final ICarListMapper iCarListMapper;


    // 전체 차량 조회
    @Override
    public List<CarDTO> getFullCarList(UserDTO userDTO) throws Exception {

        // 결과 값
        List<CarDTO> carDTOList = null;

        carDTOList = iCarListMapper.getFullCarList(userDTO);

        if (carDTOList == null) {
            carDTOList = new LinkedList<>();
        }

        return carDTOList;
    }

    // 주민 차량 조회
    @Override
    public List<CarDTO> getResidentList(UserDTO userDTO) throws Exception {

        // 결과 값
        List<CarDTO> carDTOList = null;

        carDTOList = iCarListMapper.getResidentList(userDTO);

        if (carDTOList == null) {
            carDTOList = new LinkedList<>();
        }

        return carDTOList;
    }

    // 방문자 차량 조회
    @Override
    public List<CarDTO> getVisitList(UserDTO userDTO) throws Exception {

        // 결과 값
        List<CarDTO> carDTOList = null;

        carDTOList = iCarListMapper.getVisitList(userDTO);

        if (carDTOList == null) {
            carDTOList = new LinkedList<>();
        }

        return carDTOList;
    }

    // 블랙리스트 조회
    @Override
    public List<CarDTO> getBlackList(UserDTO userDTO) throws Exception {

        // 결과 값
        List<CarDTO> carDTOList = null;

        carDTOList = iCarListMapper.getBlackList(userDTO);

        if (carDTOList == null) {
            carDTOList = new LinkedList<>();
        }

        return carDTOList;
    }
}
