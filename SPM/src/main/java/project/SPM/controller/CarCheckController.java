package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.SPM.dto.CarDTO;
import project.SPM.dto.CheckDTO;
import project.SPM.service.ICarListService;
import project.SPM.service.ICheckService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/carCheck")
@RequiredArgsConstructor
public class CarCheckController {

    private final ICarListService iCarListService;
    private final ICheckService iCheckService;

    // 체크 페이지
    @GetMapping("/carCheck")
    public String carCheck() {
        return "carCheck/carCheck";
    }


    // 터치 체크 로직 페이지
    @GetMapping("/touchCheck")
    public String touchCheck(Model model) throws Exception {

        List<CarDTO> carDTOList = iCarListService.getFullCarList();

        model.addAttribute(carDTOList);
        model.addAttribute("checkDTO", new CheckDTO());

        return "carCheck/touchCheck";
    }

    // 터치 체크 저장 로직
    @PostMapping("/touchCheckSave")
    public String touchCheckSave(@Validated @ModelAttribute CarDTO carDTO, @ModelAttribute CheckDTO checkDTO, @ModelAttribute ArrayList<CheckDTO> checkDTOList) throws Exception {

        log.debug("### CarCheckController touchCheckSave Start! : {}", this.getClass().getName());


        log.debug("### View에서 받아온 carDTO : {}", carDTO);
        log.debug("### View에서 받아온 checkDTO : {}", checkDTO);
        log.debug("### View에서 받아온 checkDTOList : {}", checkDTOList);

        boolean res = iCheckService.saveTouchCheck(carDTO);

        /// TODO: 2022-05-19 msg, url로 return 값 대체하기 필요 !

        log.debug("### Check logic End = res : {}", res);

        if (res == false) {
            log.debug("### CarCheckController touchCheckSave false End! : {}", this.getClass().getName());
            return "carCheck/touchCheckSave";
        } else {
            log.debug("### CarCheckController touchCheckSave true End! : {}", this.getClass().getName());
            return "carCheck/carCheck";
        }

    }

    // 이미지 체크 로직 페이지
    @GetMapping("/imgCheck")
    public String imgCheck() {
        return "carCheck/imgCheck";
    }
}