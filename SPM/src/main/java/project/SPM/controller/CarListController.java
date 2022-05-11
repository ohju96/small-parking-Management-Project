package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CarListController {

    // 차량 리스트 페이지 - 기본 화면
    @GetMapping("/carList/carList")
    public String carList() {
        return "carList/carList";
    }

    // 차량 전체 리스트 페이지 및 로직
    @GetMapping("/carList/fullCarList")
    public String fullCarList() {
        return "carList/fullCarList";
    }

    // 차량 주민 리스트 페이지 및 로직
    @GetMapping("/carList/resident")
    public String resident() {
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
