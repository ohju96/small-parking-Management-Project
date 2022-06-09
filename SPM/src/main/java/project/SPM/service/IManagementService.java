package project.SPM.service;

import project.SPM.dto.NoticeDTO;

public interface IManagementService {
    void sendNotice(NoticeDTO noticeDTO) throws Exception;
}
