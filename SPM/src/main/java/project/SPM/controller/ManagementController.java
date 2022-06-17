package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.NoticeDTO;
import project.SPM.service.IManagementService;
import project.SPM.validator.NoticeValidator;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementController {

    private final NoticeValidator noticeValidator;
    private final IManagementService iManagementService;

    @InitBinder("noticeVo")
    public void initNotice(WebDataBinder dataBinder) {
        log.debug("### init binder : {}", dataBinder);
        dataBinder.addValidators(noticeValidator);
    }

    @GetMapping("/management")
    public String managementPage() {
        return "management/management";
    }

/*    @GetMapping("/notice")
    public String noticePage(Model model) {

        model.addAttribute("noticeDTO", new NoticeDTO());

        return "management/notice";
    }*/

/*    @PostMapping("/notice")
    public String visitForm(@Validated @ModelAttribute NoticeDTO noticeDTO, BindingResult bindingResult, HttpSession session, Model model) throws Exception {

        // TODO: 2022-06-09 메시지 전송 중 글자 수 실시간 카운팅하기 코드 넣기
        log.debug("### visitForm start");

        if (bindingResult.hasErrors()){
            log.debug("검증 걸림");
            model.addAttribute("msg", "메시지 전송에 실패했습니다. 다시 시도해주세요.");
            model.addAttribute("url", "/management/notice");
            return "redirect";
        }

        UserEntity userEntity = (UserEntity) session.getAttribute("userDTO");

        noticeDTO.setUserId(userEntity.getUserId());
        log.debug("### userEntity : {}", userEntity);

        boolean res = iManagementService.sendNotice(noticeDTO);

        String msg;
        String url;

        if (res == true) {

            msg = "메시지 전송에 성공했습니다.";

        } else {
            msg = "메시지 전송에 실패했습니다.";
        }

        url = "/management/management";

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        log.debug("### notice : {}", noticeDTO);

        return "redirect";
    }*/
}
