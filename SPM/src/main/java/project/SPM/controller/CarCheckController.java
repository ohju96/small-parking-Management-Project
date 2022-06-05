package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.CarDTO;
import project.SPM.dto.OcrDTO;
import project.SPM.dto.UserDTO;
import project.SPM.dto.ViewCarDTO;
import project.SPM.service.ICarListService;
import project.SPM.service.ICheckService;
import project.SPM.util.CmmUtil;
import project.SPM.util.DateUtil;
import project.SPM.util.FileUtil;
import project.SPM.vo.CheckListVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/carCheck")
@RequiredArgsConstructor
public class CarCheckController {

    private final ICarListService iCarListService;
    private final ICheckService iCheckService;

    final private String FILE_UPLOAD_SAVE_PATH = "c:/upload";

    // 체크 페이지
    @GetMapping("/carCheck")
    public String carCheck() {
        return "carCheck/carCheck";
    }


    // 터치 체크 로직 페이지
    @GetMapping("/touchCheck")
    public String touchCheck(Model model, HttpSession session) throws Exception {

        UserEntity userEntity = (UserEntity) session.getAttribute("userDTO");

        UserDTO userDTO = new UserDTO(userEntity.getUserId());

        List<CarDTO> carDTOList = iCarListService.getFullCarList(userDTO);

        CheckListVo checkListVo = new CheckListVo();
        checkListVo.setCarDtoList(carDTOList);

        model.addAttribute("carDTOList", carDTOList);
        model.addAttribute("checkListVo", checkListVo);

        return "carCheck/touchCheck";
    }

    // 터치 체크 저장 로직
    @PostMapping("/touchCheckSave")
     public String touchCheckSave(@ModelAttribute CheckListVo checkListVo, HttpSession session) throws Exception {

        log.debug("### CarCheckController touchCheckSave Start! : {}", this.getClass().getName());

        log.debug("### View에서 받아온 checkListVo : {}", checkListVo);

        UserEntity userEntity = (UserEntity) session.getAttribute("userDTO");

        checkListVo.setUserId(userEntity.getUserId());

        boolean res = iCheckService.saveTouchCheck(checkListVo);

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

    // 이미지 체크 로직
    @PostMapping("/imgCheck")
    public String image(HttpServletRequest request, HttpServletResponse response, Model model,
                        @RequestParam(value = "fileUpload") MultipartFile mf) throws Exception {

        String res = "";

        String originalFilename = mf.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length()).toLowerCase();

        if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {

            String saveFileName = DateUtil.getDateTime("24hhmmss") + "." + ext;
            String saveFilePath = FileUtil.mkdirForDate(FILE_UPLOAD_SAVE_PATH);
            String fullFileInfo = saveFilePath + "/" + saveFileName;

            mf.transferTo(new File(fullFileInfo));

            OcrDTO ocrDTO = new OcrDTO();
            ocrDTO.setFileName(saveFileName);
            ocrDTO.setFilePath(saveFilePath);

            OcrDTO dto = iCheckService.saveImgCheck(ocrDTO);

            if (dto == null){
                dto = new OcrDTO();
            }

            res = CmmUtil.nvl(dto.getTextFromImage());
            log.debug("### ocr 읽은 값 : {}", dto.getTextFromImage());
            ocrDTO = null;
            dto = null;

        }


        return "carCheck/imgCheck";
    }

    // 완료 항목 보기
    @GetMapping("/viewCheck")
    public String viewCheck(Model model, HttpSession session) throws Exception {

        UserEntity userEntity = (UserEntity) session.getAttribute("userDTO");

        UserDTO userDTO = new UserDTO(userEntity.getUserId());

        List<ViewCarDTO> viewCarDTOList = iCheckService.viewCheck(userDTO);

        model.addAttribute(viewCarDTOList);

        return "carCheck/viewCheck";
    }

    // 완료 항목 상세 보기
    @PostMapping("/viewCheck/{}")
    public String viewCheck() throws Exception {
        return "redirect: carCheck/viewCheck";
    }
}