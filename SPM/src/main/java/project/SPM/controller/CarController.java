package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.CarDTO;
import project.SPM.dto.SessionIdDTO;
import project.SPM.dto.UserDTO;
import project.SPM.service.ICarListService;
import project.SPM.service.ICarService;
import project.SPM.validator.AddCarValidator;
import project.SPM.vo.AddCarVo;
import project.SPM.vo.UpdateCarListVo;

import javax.servlet.http.HttpSession;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/carManagement")
@RequiredArgsConstructor
public class CarController {

    private final ICarService iCarService;
    private final ICarListService iCarListService;
    private final AddCarValidator addCarValidator;

    @InitBinder("addCarVo")
    public void initSameObject(WebDataBinder webDataBinder) {
        log.info("webDataBinder={}, target={}", webDataBinder, webDataBinder.getTarget());
        webDataBinder.addValidators(addCarValidator);
    }

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
    public String add(@Validated @ModelAttribute AddCarVo addCarVo,BindingResult bindingResult, Model model) throws Exception {

        String msg, url;

        if (bindingResult.hasErrors()) {
            model.addAttribute("msg", "정보를 입력해주세요.");
            model.addAttribute("url", "/carManagement/addCar");
            return "redirect";
        }
        if (!addCarVo.getSort().equals("주민") && !addCarVo.getSort().equals("방문자") && !addCarVo.getSort().equals("무단")) {
            model.addAttribute("msg", "주민, 방문자, 무단 중 하나를 공백 없이 입력해주세요.");
            model.addAttribute("url", "/carManagement/addCar");
            return "redirect";
        }
        CarDTO carDTO = new CarDTO(
                addCarVo.getName(),
                addCarVo.getPhoneNumber(),
                addCarVo.getCarNumber(),
                addCarVo.getAddress(),
                addCarVo.getSort(),
                addCarVo.getUserId()
        );

        boolean res = iCarService.addCar(carDTO);

        if (res) {
            msg = "차량 등록을 완료하였습니다.";
            url = "/carManagement/carManagement";
        } else {
            msg = "차량 등록에 실패하였습니다. 다시 시도해주세요.";
            url = "/carManagement/addCar";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        return "redirect";
    }

    // 차량 관리 페이지 - 엑셀 등록 기본 화면
    @GetMapping("/addCsvCar")
    public String addCsvCarPage() {
        return "carManagement/addCsvCar";
    }

    // 차량 관리 페이지 - 엑셀 등록 기본 화면 - 엑셀 등록 로직
    @PostMapping("/csv")
    public String addCsvCar(@RequestParam(value = "fileUpload")MultipartFile mf, HttpSession session, Model model) throws Exception{

        UserEntity userEntity = (UserEntity) session.getAttribute("userDTO");

        if (mf == null) {
            model.addAttribute("msg", "엑셀 파일을 업로드해주세요.");
            model.addAttribute("url", "/carManagement/addCsvCar");
            return "redirect";
        }

        String orginalFileName = mf.getOriginalFilename();
        String ext = orginalFileName.substring(orginalFileName.lastIndexOf(".") + 1, orginalFileName.length()).toLowerCase();

        if (!ext.equals("xlsx")){
            model.addAttribute("msg", "파일 확장자를 확인해 주세요. 반드시 엑셀 파일을 업로드해주세요.");
            model.addAttribute("url", "/carManagement/addCsvCar");
            return "redirect";
        }

        SessionIdDTO sessionIdDTO = new SessionIdDTO();
        sessionIdDTO.setUserId(userEntity.getUserId());
        sessionIdDTO.setMf(mf);

        boolean res = iCarService.createCar(sessionIdDTO);

        String msg;
        String url = "/carManagement/carManagement";

        if (res == true) {
            msg = "엑셀 등록에 성공하였습니다.";
        } else {
            msg = "엑셀 등록에 실패하였습니다.";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        return "redirect";
    }

    // 차량 관리 페이지 - 차량 수정 기본 화면
    @GetMapping("/updateCar")
    public String updateCarPage(Model model, HttpSession session) throws Exception {

        UserEntity userEntity = (UserEntity) session.getAttribute("userDTO");

        UserDTO userDTO = new UserDTO(userEntity.getUserId());

        List<CarDTO> carDTOList = iCarListService.getFullCarList(userDTO);

        UpdateCarListVo updateCarListVo = new UpdateCarListVo();
        updateCarListVo.setCarDtoList(carDTOList);

        model.addAttribute("carDTOList", carDTOList);
        model.addAttribute("updateCarListVo", updateCarListVo);

        return "carManagement/updateCar";
    }

    // 차량 관리 페이지 - 차량 수정 기본 화면 - 차량 수정 및 삭제 로직
    @PostMapping("/update")
    public String updateCar(@ModelAttribute UpdateCarListVo updateCarListVo, HttpSession session, Model model) throws Exception {

        String msg, url;

        if (updateCarListVo.getCarDtoList() == null) {
            model.addAttribute("msg","등록된 차량이 없습니다. 차량 등록 후 이용해주세요.");
            model.addAttribute("url","/carManagement/updateCar");
            return "redirect";
        }

        UserEntity userEntity = (UserEntity) session.getAttribute("userDTO");

        UserDTO userDTO = new UserDTO(userEntity.getUserId());

        updateCarListVo.setUserId(userDTO.getUserId());

        boolean res = iCarService.updateCar(updateCarListVo);

        if (res == false) {
            msg = "차량 수정에 실패하였습니다. 다시 시도해주세요.";
            url = "/carManagement/updateCar";
        } else {
            msg = "차량 수정에 성공하였습니다.";
            url = "/carManagement/carManagement";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        return "redirect";
    }

    // 차량 데이터 전체 삭제 (초기화) 페이지
    @GetMapping("/dropCar")
    public String dropCarPage() {
        return "carManagement/dropCar";
    }

    // 차량 데이터 초기화 로직
    @PostMapping("/dropCar")
    public String dropCar(HttpSession session, Model model) throws Exception {

        UserEntity userEntity = (UserEntity) session.getAttribute("userDTO");

        UserDTO userDTO = new UserDTO(userEntity.getUserId());

        boolean res = iCarService.dropCar(userDTO);

        String msg, url;

        if (res == true) {
            msg = "데이터 초기화에 성공했습니다.";
            url = "/carManagement/carManagement";
        } else {
            msg = "데이터 초기화에 실패하였습니다. 다시 시도해주세요.";
            url = "/carManagement/dropCar";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        return "redirect";
    }
}
