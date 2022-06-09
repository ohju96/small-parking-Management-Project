package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Service;
import project.SPM.dto.CarDTO;
import project.SPM.dto.NoticeDTO;
import project.SPM.mapper.IManagementMapper;
import project.SPM.service.IManagementService;

import javax.mail.Message;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagementService implements IManagementService {

    private final IManagementMapper iManagementMapper;
//    private final DefaultMessageService messageService;

    @Override
    public void sendNotice(NoticeDTO noticeDTO) throws Exception {

        log.debug("### sendNotice start");
        log.debug("### Controller에서 넘어온 notice : {}", noticeDTO);

        // 주민 전화번호 리스트 가져오기
        List<CarDTO> carDTOList = iManagementMapper.getResidentPhoneNmList(noticeDTO);

        log.debug("### carDTOList : {}", carDTOList);

        for (CarDTO carDTO : carDTOList) {

            log.debug("### carDTO : {}", carDTO.getPhoneNumber()); //주민 휴대폰 번호 출력 로그
        }

        log.debug("### sendNotice end");

    }
}
