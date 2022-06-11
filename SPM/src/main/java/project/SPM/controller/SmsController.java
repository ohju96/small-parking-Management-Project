package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.SPM.dto.VisitorDTO;
import project.SPM.validator.VisitorValidator;

import java.util.HashMap;


@Slf4j
@RequiredArgsConstructor
@RestController
public class SmsController {

    private final VisitorValidator visitorValidator;

    @InitBinder("visitorDTO")
    public void init(WebDataBinder dataBinder) {
        log.debug("### init binder : {}", dataBinder);
        dataBinder.addValidators(visitorValidator);
    }

    @GetMapping("/management/visitForm")
    public ModelAndView visitFormPage(ModelAndView modelAndView) {
        modelAndView.setViewName("management/visitForm");
        modelAndView.addObject("visitorDTO", new VisitorDTO());
        return modelAndView;
    }

    @PostMapping("/management/visitForm")
    public ModelAndView visitForm(@Validated @ModelAttribute VisitorDTO visitorDTO, BindingResult bindingResult) {

        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()){
            mav.setViewName("redirect:/management/visitForm");
            mav.addObject("msg", "에러");
            return mav;
        }

        log.debug("### visitForm start");
        log.debug("### visitorDTO : {}", visitorDTO.getVisitorPhoneNumber());

        String api_key = "";
        String api_secret = "";
        String to = "";
        Message message = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", to);
        params.put("from", visitorDTO.getVisitorPhoneNumber());
        params.put("type", "SMS");
        params.put("text", "양식에 맞게 작성 후 답장 보내주세요.");
        params.put("app_version", "test app 1.2"); // application name and version

        log.debug("### params : {}", params);

        try {
            JSONObject obj = (JSONObject) message.send(params);
            log.debug("### obj : {}", obj);
            System.out.println(obj.toString());

            JSONObject jsonObject = (JSONObject) obj;

            log.debug("### jsonObject : {}", (JSONObject) obj);
            log.debug("### jsonObejct - success_count : {}" , (Long) jsonObject.get("success_count"));

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
            }else {
                mav.addObject("msg", "전송 실패");
                mav.addObject("url", "/management/management");
                isEnd = true;
            }

            if (isEnd) {
                mav.setViewName("/redirect");
                return mav;
            }

        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        } finally {

        }
        mav.addObject("msg", "Sms 전송 기능이 종료되었습니다.");
        mav.addObject("url", "/management/management");
        mav.setViewName("/redirect");

        return mav;
    }

}
