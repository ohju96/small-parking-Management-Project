package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import project.SPM.dto.CarDTO;
import project.SPM.dto.OcrDTO;
import project.SPM.dto.UserDTO;
import project.SPM.dto.ViewCarDTO;
import project.SPM.mapper.ICarListMapper;
import project.SPM.mapper.ICheckMapper;
import project.SPM.service.ICheckService;
import project.SPM.util.CmmUtil;
import project.SPM.util.DateUtil;
import project.SPM.vo.CheckListVo;

import java.io.File;
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
    private final Environment env;

    @Value("${file.dir}")
    private String fileDir;

    // 직접 체크 로직
    @Override
    public boolean saveTouchCheck(CheckListVo checkListVo) throws Exception {

        log.debug("### CheckService saveTouchCheck Start! : {}", this.getClass().getName());

        boolean res;
        List<CarDTO> list = new ArrayList<>();


        log.debug("####### checkListVo : {}", checkListVo);

        for (CarDTO carDTO : checkListVo.getCarDtoList()) {
            carDTO.setUserId(checkListVo.getUserId());
            list.add(carDTO);
        }

        log.debug("### CheckService checkListVo = carDTO = list ####### : {}", list);
        // 생성할 컬렉션 명
        String colNm = checkListVo.getUserId() + "_" + DateUtil.getDateTime("yyyyMMdd hh:mm:ss");

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
    public int saveImageCheck(UserDTO userDTO, String carNumber) throws Exception {

        log.debug("### saveImageCheck Start");

        try {

            int res;

            log.debug("### userDTO.ID : {}", userDTO.getUserId());

            String colNm = userDTO.getUserId() + "_" + DateUtil.getDateTime("yyyyMMdd hh:mm:ss");
            log.debug("### colNm : {}", colNm);

            // userDTO id로 CarDTO를 불러온다.
            CarDTO carDTO = iCheckMapper.checkId(userDTO, carNumber);
            log.debug("### carDTO : {}", carDTO);

            if (carDTO == null) {

            }

            // 셋팅한다.
            if (carDTO.getCarNumber().equals(carNumber)) {
                carDTO.setCheck(true);
                log.debug("### carDTO : {}", carDTO);
                // 데이터를 저장한다.
                res = iCheckMapper.saveImageCheck(carDTO, colNm);
                return res;

            } else if(carDTO == null){
                carDTO = new CarDTO();
                return 3;

            } else {
                log.debug("### carDTO : {}", carDTO);
                log.debug("### carNumber : {}", carNumber);
                return 2;

            }

        } catch (NullPointerException httpStatusCodeException) {
            return 3;
        }

    }

    // 완료 항목 보여주기 로직
    @Override
    public List<ViewCarDTO> viewCheck(UserDTO userDTO) throws Exception {

        List<ViewCarDTO> viewCarDTOList = null;

        viewCarDTOList = iCheckMapper.viewCheck(userDTO);
        if (viewCarDTOList == null) {
            viewCarDTOList = new LinkedList<>();
        }

        return viewCarDTOList;
    }

    // 완료 항목 상세 보기
    @Override
    public List<CarDTO> detail(String checkCollectionName) throws Exception {

        List<CarDTO> carDTOList = iCheckMapper.detail(checkCollectionName);

        if (carDTOList == null) {
            carDTOList = new LinkedList<>();
        }

        return carDTOList;
    }
}
