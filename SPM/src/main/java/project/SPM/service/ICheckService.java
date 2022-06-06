package project.SPM.service;

import project.SPM.dto.CarDTO;
import project.SPM.dto.OcrDTO;
import project.SPM.dto.UserDTO;
import project.SPM.dto.ViewCarDTO;
import project.SPM.vo.CheckListVo;

import java.util.List;

public interface ICheckService {

    // 직접 저장 로직
    boolean saveTouchCheck(CheckListVo checkListVo) throws Exception;

    // 이미지 저장 로직
    OcrDTO saveImgCheck(OcrDTO ocrDTO) throws Exception;

    // 완료 항목 보여주기
    List<ViewCarDTO> viewCheck(UserDTO userDTO) throws Exception;

    List<CarDTO> detail(String checkCollectionName) throws Exception;
}
