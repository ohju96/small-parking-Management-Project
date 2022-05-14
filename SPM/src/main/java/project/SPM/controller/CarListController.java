package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.SPM.dto.CarDTO;
import project.SPM.service.ICarListService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CarListController {

    private final ICarListService iCarListService;

    // 차량 리스트 페이지 - 기본 화면
    @GetMapping("/carList/carList")
    public String carList() {
        return "carList/carList";
    }

     //차량 전체 리스트 페이지 및 로직
    @GetMapping("/carList/fullCarList")
    public String fullCarList(Model model) throws Exception {

        List<CarDTO> carDTOList = iCarListService.getFullCarList();

        model.addAttribute(carDTOList);

        return "carList/fullCarList";

    }

    // 차량 주민 리스트 페이지 및 로직
    @GetMapping("/carList/resident")
    public String resident(Model model) throws Exception {

        List<CarDTO> carDTOList = iCarListService.getResidentList();

        model.addAttribute(carDTOList);

        return "carList/resident";
    }

    // 차량 주민 리스트 페이지 및 로직
    @GetMapping("/carList/visit")
    public String visit() {
        return "carList/visit";
    }

    // 차량 블랙리스트 페이지 및 로직
    @GetMapping("/carList/blackList")
    public String blackList() {
        return "carList/blackList";
    }

}
