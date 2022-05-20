package project.SPM.mapper;

import project.SPM.dto.CarDTO;

import java.util.List;

public interface ICheckMapper {

    /**
     * 직접 저장 로직
     * @param carDTOList 저장될 정보
     * @param colNm 저장할 컬렉션 이름
     * @return 저장 결과
     */
    boolean saveTouchCheck(List<CarDTO> carDTOList, String colNm) throws Exception;

    // 이미지 저장 로직
    List<CarDTO> saveImgCheck() throws Exception;
}
