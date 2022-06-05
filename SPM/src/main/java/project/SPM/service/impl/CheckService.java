package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
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
    public OcrDTO saveImgCheck(OcrDTO ocrDTO) throws Exception {

        log.debug("### CheckService Start ");

        File imageFile = new File(CmmUtil.nvl(ocrDTO.getFilePath() + "//" + CmmUtil.nvl(ocrDTO.getFileName())));


        log.debug("### file : {}", imageFile);

        // 테서렉트 객체 생성
        Tesseract instence = new Tesseract();

        // OCR 학습 데이터 경로
        instence.setDatapath("C:\\git\\SPM\\SPM\\src\\main\\resources\\static\\bootstrap\\tess-data");

        // 한국어 학습 데이터 선택
        instence.setLanguage("kor");

        String result = instence.doOCR(imageFile);

        log.debug("### Ocr 결과 result : {}", result);

        ocrDTO.setTextFromImage(result);
        log.debug("### Ocr 결과 ocrDTO : {}", ocrDTO.getTextFromImage());
        log.debug("### CheckService End ");

        return ocrDTO;
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
}
