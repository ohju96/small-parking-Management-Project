package project.SPM.mapper;

import project.SPM.dto.CarDTO;
import project.SPM.dto.NoticeDTO;

import java.util.List;

public interface IManagementMapper {
    List<CarDTO> getResidentPhoneNmList(NoticeDTO noticeDTO) throws Exception;
}
