package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.SPM.dto.CarDTO;
import project.SPM.mapper.ICarListMapper;
import project.SPM.mapper.ICheckMapper;
import project.SPM.service.ICheckService;
import project.SPM.util.DateUtil;

import java.util.LinkedList;
import java.util.List;
@Slf4j
@Service("CheckService")
@RequiredArgsConstructor
public class CheckService implements ICheckService {
    // TODO: 2022-05-16 View에서 받아온 값이 null로 체크되는데 이거 확인해야 한다. 
    private final ICheckMapper iCheckMapper;
    private final ICarListMapper iCarListMapper;

    // 직접 체크 로직
    @Override
    public boolean saveTouchCheck(CarDTO carDTO) throws Exception {

        log.debug("### CheckService saveTouchCheck Start! : {}", this.getClass().getName());

        boolean res;

        // 결과 값
        List<CarDTO> carDTOList = null;

        carDTOList = iCarListMapper.getFullCarList();

        log.debug("### CheckService CarListMapper => carDTOList : {}" , carDTOList);

        // 데이터 저장하기
        for (CarDTO dto : carDTOList) {
            carDTO.setCheck(carDTO.isCheck());
            carDTO.setCheckTime(DateUtil.getDateTime("yyyyMMddhhmmss"));

            log.debug("### CheckService iter dto : {}", dto);
            log.debug("### CheckService iter catDTOList : {}", carDTOList);

            carDTOList.add(dto);
        }

        // 생성할 컬렉션 명
        String colNm = "CHECK_" + DateUtil.getDateTime("yyyyMMdd");

        log.debug("### CheckService iter End catDTOList : {}", carDTOList);
        log.debug("### CheckService colNm : {}", colNm);

        // MongoDB에 데이터 저장
        res = iCheckMapper.saveTouchCheck(carDTOList, colNm);

        log.debug("### CheckService saveTouchCheck End! : {}", this.getClass().getName());

        log.debug("### CheckSErvice saveTouchCheck Logic End res = : ", res);

        return res;
    }

    // 이미지 체크 로직
    @Override
    public List<CarDTO> saveImgCheck() throws Exception {
        return null;
    }
}
