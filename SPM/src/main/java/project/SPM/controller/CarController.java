package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.SPM.service.ICarService;


@Slf4j
@Controller
@RequestMapping("/carManagement")
@RequiredArgsConstructor
public class CarController {

    private final ICarService iCarService;


    // 차량 관리 페이지 - 기본 화면
    @GetMapping("/carManagement")
    public String addCarPage() {
        return "carManagement/carManagement";
    }


    // 차량 관리 페이지 - 직접 등록 로직
    @GetMapping("/addCar")
    public String addCar() {
        return "carManagement/addCar";
    }

    // 차량 관리 페이지 - 엑셀 등록 기본 화면
    @GetMapping("/addCsvCar")
    public String addCsvCarPage() {
        return "carManagement/addCsvCar";
    }

    // 차량 관리 페이지 - 엑셀 등록 기본 화면 - 엑셀 등록 로직
    @PostMapping("/csv")
    public String addCsvCar(@RequestParam(value = "fileUpload")MultipartFile mf, Model model) throws Exception{

        iCarService.CreateCar(mf);

        return "carManagement/carManagement";
    }
}
