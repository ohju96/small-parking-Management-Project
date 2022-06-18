package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.CarDTO;
import project.SPM.dto.NoticeDTO;
import project.SPM.dto.SmsDTO;
import project.SPM.dto.VisitorDTO;
import project.SPM.service.IManagementService;
import project.SPM.validator.NoticeValidator;
import project.SPM.validator.VisitorValidator;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
public class SmsController {

    private final VisitorValidator visitorValidator;
    private final NoticeValidator noticeValidator;
    private final IManagementService iManagementService;

    @Value("${sms.key}")
    private String smsKey;

    @Value("${sms.id}")
    private String smsId;

    @Value("${sms.phone}")
    private String smsPhone;

    @InitBinder("visitorDTO")
    public void setVisitorValidator(WebDataBinder dataBinder) {
        log.debug("### init binder : {}", dataBinder);
        dataBinder.addValidators(visitorValidator);
    }

    @InitBinder("noticeDTO")
    public void setNoticeValidator(WebDataBinder dataBinder) {
        log.debug("### init binder : {}", dataBinder);
        dataBinder.addValidators(noticeValidator);
    }

    // 방문자 페이지
    @GetMapping("/management/visitForm")
    public ModelAndView visitFormPage(ModelAndView modelAndView) {
        modelAndView.setViewName("management/visitForm");
        modelAndView.addObject("visitorDTO", new VisitorDTO());
        return modelAndView;
    }

    // 방문자 보내기
    @PostMapping("/management/visitForm")
    public ModelAndView visitForm(@Validated @ModelAttribute VisitorDTO visitorDTO, BindingResult bindingResult) {

        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mav.addObject("msg", "휴대폰 번호 11자리를 모두 입력해주세요.('-' 생략)");
            mav.addObject("url", "/management/visitForm");
            mav.setViewName("redirect");
            return mav;
        }

        log.debug("### visitForm start");
        log.debug("### visitorDTO : {}", visitorDTO.getVisitorPhoneNumber());

        String api_key = smsId;
        String api_secret = smsKey;
        String to = smsPhone;
        Message message = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", to);
        params.put("from", visitorDTO.getVisitorPhoneNumber());
        params.put("type", "SMS");
        params.put("text", "양식 : [성함/연락처/차량번호/방문동,호수] 입력 후 보내주세요.");
        params.put("app_version", "test app 1.2"); // application name and version

        log.debug("### params : {}", params);

        try {
            JSONObject obj = (JSONObject) message.send(params);
            log.debug("### obj : {}", obj);
            System.out.println(obj.toString());

            JSONObject jsonObject = (JSONObject) obj;

            log.debug("### jsonObject : {}", (JSONObject) obj);
            log.debug("### jsonObejct - success_count : {}", (Long) jsonObject.get("success_count"));

            // {"group_id":"dkdlelrkqt","success_count":1,"error_count":0}
            // 리턴 값 중 'success_count'의 값이 1로 오면 전송이 성공인 것을 if문으로 msg에 담아준다.
            boolean isEnd = false;

            if ((Long) jsonObject.get("success_count") == 1) {
                mav.addObject("msg", "전송 성공");
                mav.addObject("url", "/management/management");
                isEnd = true;
            } else if ((Long) jsonObject.get("success_count") == null) {
                mav.addObject("msg", "전송 실패 - null");
                mav.addObject("url", "/management/management");
                isEnd = true;
            } else {
                mav.addObject("msg", "전송 실패");
                mav.addObject("url", "/management/management");
                isEnd = true;
            }

            if (isEnd) {
                mav.setViewName("redirect");
                return mav;
            }

        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        } finally {

        }
        mav.addObject("msg", "Sms 전송 기능이 종료되었습니다.");
        mav.addObject("url", "/management/management");
        mav.setViewName("redirect");

        return mav;
    }

    // 공지 사항 페이지
    @GetMapping("/management/notice")
    public ModelAndView noticePage(ModelAndView modelAndView) {

        modelAndView.setViewName("management/notice");
        modelAndView.addObject("noticeDTO", new NoticeDTO());

        return modelAndView;
    }

    // 공지 사항 보내기
    @PostMapping("/management/notice")
    public ModelAndView visitForm(@Validated @ModelAttribute NoticeDTO noticeDTO, BindingResult bindingResult, HttpSession session, Model model) throws Exception {

        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mav.addObject("msg", "1글자 이상 40글자 이하로 입력해주세요.");
            mav.addObject("url", "/management/notice");
            mav.setViewName("redirect");
            return mav;
        }

        UserEntity userEntity = (UserEntity) session.getAttribute("userDTO");

        noticeDTO.setUserId(userEntity.getUserId());
        log.debug("### userEntity : {}", userEntity);

        List<CarDTO> carDTOList = iManagementService.sendNotice(noticeDTO);

        String api_key = smsId;
        String api_secret = smsKey;
        String to = smsPhone;

        Message message = new Message(api_key, api_secret);
        HashMap<String, String> params = new HashMap<String, String>();

        for (CarDTO carDTO : carDTOList) {

            String res[] = carDTO.getPhoneNumber().split("-");
            String result = res[0] + res[1] + res[2];

            // 4 params(to, from, type, text) are mandatory. must be filled
            params.put("to", to);
            params.put("from", result);
            params.put("type", "SMS");
            params.put("text", noticeDTO.getMessage());
            params.put("app_version", "test app 1.2"); // application name and version

            try {
                JSONObject obj = (JSONObject) message.send(params);
                log.debug("### obj : {}", obj);
                System.out.println(obj.toString());

                JSONObject jsonObject = (JSONObject) obj;

                log.debug("### jsonObject : {}", (JSONObject) obj);
                log.debug("### jsonObejct - success_count : {}", (Long) jsonObject.get("success_count"));

                // {"group_id":"dkdlelrkqt","success_count":1,"error_count":0}
                // 리턴 값 중 'success_count'의 값이 1로 오면 전송이 성공인 것을 if문으로 msg에 담아준다.
                boolean isEnd = false;

                if ((Long) jsonObject.get("success_count") == 1) {
                    mav.addObject("msg", "전송 성공");
                    mav.addObject("url", "/management/management");
                    isEnd = true;
                } else if ((Long) jsonObject.get("success_count") == null) {
                    mav.addObject("msg", "전송 실패 - null");
                    mav.addObject("url", "/management/management");
                    isEnd = true;
                } else {
                    mav.addObject("msg", "전송 실패");
                    mav.addObject("url", "/management/management");
                    isEnd = true;
                }

                if (isEnd) {
                    mav.setViewName("redirect");
                    return mav;
                }

            } catch (CoolsmsException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCode());
            } finally {

            }
            mav.addObject("msg", "Sms 전송 기능이 종료되었습니다.");
            mav.addObject("url", "/management/management");
            mav.setViewName("redirect");

            return mav;
        }
        return mav;
    }
}