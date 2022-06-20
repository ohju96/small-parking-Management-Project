package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.SPM.service.IManagementService;
import project.SPM.validator.NoticeValidator;


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
}
