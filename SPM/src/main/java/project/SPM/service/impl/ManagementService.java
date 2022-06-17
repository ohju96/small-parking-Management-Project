package project.SPM.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.SPM.dto.CarDTO;
import project.SPM.dto.MailDTO;
import project.SPM.dto.NoticeDTO;
import project.SPM.dto.SmsDTO;
import project.SPM.mapper.IManagementMapper;
import project.SPM.service.IManagementService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagementService implements IManagementService {

    private final IManagementMapper iManagementMapper;
    private DefaultMessageService messageService;

    @Value("${sms.key}")
    private String smsKey;

    @Value("${sms.id}")
    private String smsId;

    @Value("${sms.phone}")
    private String smsPhone;

    // Sms 전송하기
    @Override
    public List<CarDTO> sendNotice(NoticeDTO noticeDTO) throws Exception {

        log.debug("### sendNotice start");
        log.debug("### Controller에서 넘어온 notice : {}", noticeDTO);

        boolean check = false;

        // 주민 전화번호 리스트 가져오기
        List<CarDTO> carDTOList = iManagementMapper.getResidentPhoneNmList(noticeDTO);

//        log.debug("### carDTOList : {}", carDTOList);
//        SmsDTO smsDTO = new SmsDTO();
//
//        // 주민 리스트만큼 문자를 보내기 위한 DTO 셋팅
//        for (CarDTO carDTO : carDTOList) {
//
//            // 전화번호 중간에 들어가는 '-'문자 제거해서 String 만들기
//            String res[] = carDTO.getPhoneNumber().split("-");
//            String result = res[0] + res[1] + res[2];
//            log.debug("### res[0] : {}", res[0]);
//            log.debug("### res[1] : {}", res[1]);
//            log.debug("### res[2] : {}", res[2]);
//            log.debug("### result : {}", result);
//
//            // 전화번호 형식은 붙여서 사용
//            smsDTO.setFrom(smsPhone);
//            smsDTO.setTo(result);
//            smsDTO.setText(noticeDTO.getMessage());
//
//            // sendSms 메소드에 smsDTO 전달해 메시지 전송하기
////            sendSms(smsDTO);
//
//            check = true;
//
//
//            log.debug("### carDTO : {}", carDTO.getPhoneNumber()); //주민 휴대폰 번호 출력 로그
//        }
//
//        log.debug("### chekc : {}", check);
//        log.debug("### sendNotice end");

        return carDTOList;
    }

    // TODO: 2022-06-17 메시지 충전 할 것, 후 테스트 할 것
    // 문자 전송 메소드
    public SingleMessageSentResponse sendSms(SmsDTO smsDTO) {

        log.debug("### sendSms Start");
        log.debug("### sendSms - smsDTO : {}", smsDTO);

        messageService = NurigoApp.INSTANCE.initialize(smsId, smsKey,  "https://api.coolsms.co.kr");

        Message message = new Message();
        message.setFrom(smsDTO.getFrom());
        message.setTo(smsDTO.getTo());
        message.setText(smsDTO.getText());

        SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
        log.debug("### response : {}", response);

        return response;

    }
}
