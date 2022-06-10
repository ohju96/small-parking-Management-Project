package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.json.simple.JSONObject;
import org.springframework.ui.Model;
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

    @InitBinder("visitorValidator")
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

        String api_key = "키ID";
        String api_secret = "키PW";
        String to = "발신자 연락처";
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
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }



        mav.setViewName("management/management");
        mav.addObject("msg", "전송 완료");
        return mav;
    }

}
