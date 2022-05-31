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
import project.SPM.service.ICarListService;
import project.SPM.service.ICarService;
import project.SPM.vo.AddCarVo;
import project.SPM.vo.UpdateCarListVo;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/carManagement")
@RequiredArgsConstructor
public class CarController {

    private final ICarService iCarService;
    private final ICarListService iCarListService;


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

        iCarService.createCar(mf);

        return "carManagement/carManagement";
    }

    // 차량 관리 페이지 - 차량 수정 기본 화면
    @GetMapping("/updateCar")
    public String updateCarPage(Model model) throws Exception {

        List<CarDTO> carDTOList = iCarListService.getFullCarList();

        UpdateCarListVo updateCarListVo = new UpdateCarListVo();
        updateCarListVo.setCarDtoList(carDTOList);

        model.addAttribute("carDTOList", carDTOList);
        model.addAttribute("updateCarListVo", updateCarListVo);

        return "carManagement/updateCar";
    }

    // 차량 관리 페이지 - 차량 수정 기본 화면 - 차량 수정 및 삭제 로직
    @PostMapping("/update")
    public String updateCar(@ModelAttribute UpdateCarListVo updateCarListVo) throws Exception {

        log.debug("### CarController updateCar Start : {}", this.getClass().getName());

        log.debug("### View에서 받아온 updateCarListVo : {}", updateCarListVo);

        boolean res = iCarService.updateCar(updateCarListVo);

        log.debug("### CarController res : {}", res);

        if (res == false) {

            log.debug("### CarController updateCar false End : {}", this.getClass().getName());
            return "carManagement/updateCar";
        } else {

            log.debug("### CarController updateCar true End : {}", this.getClass().getName());
            return "carManagement/carManagement";
        }

    }

    // 차량 데이터 전체 삭제 (초기화) 페이지
    @GetMapping("/dropCar")
    public String dropCarPage() {
        return "carManagement/dropCar";
    }

    // 차량 데이터 초기화 로직
    @PostMapping("/dropCar")
    public String dropCar() throws Exception {

        boolean res = iCarService.dropCar();

        if (res == true) {

            return "carManagement/carManagement";
        } else {

            return "carManagement/dropCar";
        }
    }
}
