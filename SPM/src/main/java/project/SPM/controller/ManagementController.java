package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/notice")
    public String noticePage(Model model) {

        model.addAttribute("noticeDTO", new NoticeDTO());

        return "management/notice";
    }

    @PostMapping("/notice")
    public String visitForm(@Validated @ModelAttribute NoticeDTO noticeDTO, BindingResult bindingResult, HttpSession session) throws Exception {

        // TODO: 2022-06-09 메시지 전송 중 글자 수 실시간 카운팅하기 코드 넣기 
        
        if (bindingResult.hasErrors()){
            return "management/notice";
        }

        UserEntity userEntity = (UserEntity) session.getAttribute("userDTO");

        noticeDTO.setUserId(userEntity.getUserId());

        iManagementService.sendNotice(noticeDTO);

        log.debug("### notice : {}", noticeDTO);


        return "/management/management";
    }
}
