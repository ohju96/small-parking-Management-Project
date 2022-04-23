package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CarController {

    // 차량 관리 페이지
    @GetMapping("/carManagement/carManagement")
    public String test3() {
        return "carManagement/carManagement";
    }


    // 차량 관리 페이지 - 직접 등록
    @GetMapping("/carManagement/addCar")
    public String addCar() {
        return "carManagement/addCar";
    }
}
