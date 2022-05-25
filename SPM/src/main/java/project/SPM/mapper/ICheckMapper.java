package project.SPM.mapper;

import project.SPM.dto.CarDTO;
import project.SPM.vo.CheckListVo;

import java.util.List;

public interface ICheckMapper {

    // 터치 저장 로직
    boolean saveTouchCheck(List<CarDTO> list, String colNm) throws Exception;

    // 이미지 저장 로직
    List<CarDTO> saveImgCheck() throws Exception;
}
