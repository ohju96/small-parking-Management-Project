package project.SPM.service;

import project.SPM.dto.CarDTO;
import project.SPM.dto.MailDTO;
import project.SPM.dto.NoticeDTO;
import project.SPM.dto.SmsDTO;

import java.util.List;

public interface IManagementService {
    List<CarDTO> sendNotice(NoticeDTO noticeDTO) throws Exception;
}
