package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.SPM.dto.CarDTO;
import project.SPM.service.ICarService;
import project.SPM.vo.AddCarVo;


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


    // 차량 관리 페이지 - 직접 등록 기본 화면
    @GetMapping("/addCar")
    public String addCar(Model model) {

        model.addAttribute("addCarVo", new AddCarVo());

        return "carManagement/addCar";
    }

    // 차량 관리 페이지 - 직접 등록 로직
    @PostMapping("/addCar")
    public String add(@Validated @ModelAttribute AddCarVo addCarVo, BindingResult bindingResult) throws Exception {

        log.debug("#### Controller AddCarVo : {}", addCarVo);

        CarDTO carDTO = new CarDTO(
                addCarVo.getName(),
                addCarVo.getPhoneNumber(),
                addCarVo.getCarNumber(),
                addCarVo.getAddress(),
                addCarVo.getSort()
        );

        log.debug("#### Controller CarDTO : {}", carDTO);

        boolean res = iCarService.addCar(carDTO);

        log.debug("#### Controller 마지막 로직에서 체크한 res 값 : {}", res);
        String msg;
        String url;

        // TODO: 2022-05-11 로직 추가 해야한다. 
        if (res) {
            msg = "차량 등록을 완료하였습니다.";
            url = "carManagement/carManagement";
        } else {
            msg = "차량 등록에 실패하였습니다. 다시 시도해주세요.";
            url = "carManagement/addCar";
        }

        return url;
    }

    // 차량 관리 페이지 - 엑셀 등록 기본 화면
    @GetMapping("/addCsvCar")
    public String addCsvCarPage() {
        return "carManagement/addCsvCar";
    }

    // 차량 관리 페이지 - 엑셀 등록 기본 화면 - 엑셀 등록 로직
    @PostMapping("/csv")
    public String addCsvCar(@RequestParam(value = "fileUpload")MultipartFile mf) throws Exception{

        iCarService.CreateCar(mf);

        return "carManagement/carManagement";
    }
}
