package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.SPM.dto.CarDTO;
import project.SPM.dto.ViewCarDTO;
import project.SPM.mapper.ICarListMapper;
import project.SPM.mapper.ICheckMapper;
import project.SPM.service.ICheckService;
import project.SPM.util.DateUtil;
import project.SPM.vo.CheckListVo;

import java.util.ArrayList;
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
    public boolean saveTouchCheck(CheckListVo checkListVo) throws Exception {

        log.debug("### CheckService saveTouchCheck Start! : {}", this.getClass().getName());

        boolean res;
        List<CarDTO> list = new ArrayList<>();


        log.debug("####### checkListVo : {}", checkListVo);

        for (CarDTO carDTO : checkListVo.getCarDtoList()) {
            list.add(carDTO);
        }

        log.debug("### CheckService checkListVo = carDTO = list ####### : {}", list);
        // 생성할 컬렉션 명
        String colNm = "CHECK_" + DateUtil.getDateTime("yyyyMMdd hh:mm:ss");

        log.debug("### CheckService iter End checkListVo : {}", checkListVo);
        log.debug("### CheckService colNm : {}", colNm);

        // MongoDB에 데이터 저장
        res = iCheckMapper.saveTouchCheck(list, colNm);

        log.debug("### CheckService saveTouchCheck End! : {}", this.getClass().getName());

        log.debug("### CheckSErvice saveTouchCheck Logic End res = : ", res);

        return res;
    }

    // 이미지 체크 로직
    @Override
    public List<CarDTO> saveImgCheck() throws Exception {
        return null;
    }

    // 완료 항목 보여주기 로직
    @Override
    public List<ViewCarDTO> viewCheck() throws Exception {

        List<ViewCarDTO> viewCarDTOList = null;

        viewCarDTOList = iCheckMapper.viewCheck();
        if (viewCarDTOList == null) {
            viewCarDTOList = new LinkedList<>();
        }

        return viewCarDTOList;
    }
}
