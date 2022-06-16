package project.SPM.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import project.SPM.Entity.UserEntity;
import project.SPM.dto.CarDTO;
import project.SPM.dto.UserDTO;
import project.SPM.dto.ViewCarDTO;
import project.SPM.service.ICarListService;
import project.SPM.service.ICheckService;
import project.SPM.util.DateUtil;
import project.SPM.util.FileUtil;
import project.SPM.vo.CheckListVo;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/carCheck")
@RequiredArgsConstructor
public class CarCheckController {

    private final ICarListService iCarListService;
    private final ICheckService iCheckService;

    private final RestTemplate restTemplate;

    final private String FILE_UPLOAD_SAVE_PATH = "/user/image";

    @Value("${kakao.appkey}")
    private String appkey;

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
    @RequestMapping("imgOcr")
    @ResponseBody
    public ModelAndView imgOcr(@RequestParam(value = "fileUpload") MultipartFile mf, HttpSession session) throws Exception {

        log.debug("### start");

        ModelAndView mav = new ModelAndView();

        String orginalFileName = mf.getOriginalFilename();
        String ext = orginalFileName.substring(orginalFileName.lastIndexOf(".") + 1, orginalFileName.length()).toLowerCase();

        // 저장한 경우 및 체크 확인
        String carNumber = null;
        boolean check = false;

        if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {

            String saveFileName = DateUtil.getDateTime("24hhmmss") + "." + ext;
            String saveFilePath = FileUtil.mkdirForDate(FILE_UPLOAD_SAVE_PATH);
            String fullFileInfo = saveFilePath + "/" + saveFileName;

            log.debug("### fullFileInfo : {}", fullFileInfo);

            // 파일 저장
            mf.transferTo(new File(fullFileInfo));
            log.debug("### 파일 저장 완료");

            // 저장한 파일 읽어오기
            Resource resource = new FileSystemResource(fullFileInfo);

            // 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.add("Authorization", appkey);
            log.debug("### headers : {}", headers);

            // 바디 설정
            MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
            form.add("image", resource);
            log.debug("### form : {}", form);

            // 헤더랑 바디 합치기
            HttpEntity<MultiValueMap<String, Object>> resquestEntity = new HttpEntity<>(form, headers);
            log.debug("### resquestEntity : ", resquestEntity);

            // api 서버
            String serverUrl = "https://dapi.kakao.com/v2/vision/text/ocr";

            // restTemplate에 Post 날리기
            ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, resquestEntity, String.class);
            log.debug("### response : {}", response);
            log.debug("### response.body : {}", response.getBody());
            log.debug("### response.body.result : {}", response.getBody().toString());

            // 파일 존재 시 파일 삭제
            File file = new File(fullFileInfo);
            if(file.exists()) {
                file.delete();
            }

            // JSON 파싱
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
            JSONArray resultArray = (JSONArray) jsonObject.get("result");

            // 파싱 데이터 뺴기
            List<String> list = new ArrayList<String>();

            // list에 JSON -> String으로 타입 변경해서 담기
            for(int i=0 ; i<resultArray.size() ; i++){

                JSONObject tempObj = (JSONObject) resultArray.get(i);
                System.out.println(""+(i+1)+"번호 : "+tempObj.get("recognition_words"));

                String res[] = tempObj.get("recognition_words").toString().split("\"");

                list.add(res[1]);
            }

            log.debug("### list : {}", list);


            // 로직 실행 ( 메소드로 만들어서 따로 빼야함)
            if (list.get(0).length() == 3 || list.get(1).length() == 4) {

                log.debug("### 1번 문항");
                carNumber = list.get(0) + list.get(1);
                check = true;
                mav.addObject("msg", "자동차 번호는" + carNumber + "입니다.");

            } else if (list.get(0).length() == 4 || list.get(1).length() == 4) {

                log.debug("### 2번 문항");
                carNumber = list.get(0) + list.get(1);
                check = true;
                mav.addObject("msg", "자동차 번호는" + carNumber + "입니다.");


            } else if (list.get(0).length() == 7) {

                log.debug("### 3번 문항");
                carNumber = list.get(0);
                check = true;
                mav.addObject("msg", "자동차 번호는" + carNumber + "입니다.");

            } else if (list.get(0).length() == 8) {

                log.debug("### 4번 문항");
                carNumber = list.get(0);
                check = true;
                mav.addObject("msg", "자동차 번호는" + carNumber + "입니다.");

            } else if (list.get(1).length() == 8) {

                log.debug("### 5번 문항");
                carNumber = list.get(1);
                check = true;
                mav.addObject("msg", "자동차 번호는" + carNumber + "입니다.");

            } else {

                log.debug("### 6번 문항");
                mav.addObject("msg", "자동차 번호를 인식할 수 없습니다. 다른 사진으로 시도해주세요.");
            }

        }

        log.debug("### carNumber : {}", carNumber);
        log.debug("### check : {}", check);

        UserEntity userEntity = (UserEntity) session.getAttribute("userDTO");

        UserDTO userDTO = new UserDTO(userEntity.getUserId());
        log.debug("### userDTO : {}", userDTO);

        int res = iCheckService.saveImageCheck(userDTO, carNumber);
        log.debug("### res : {}", res);

        if (res == 1) {
            mav.addObject("msg", "이미지 저장에 성공하였습니다.");
            mav.addObject("url", "/carCheck/imgCheck");
        } else if (res == 2){
            mav.addObject("msg", "이미지 저장에 실패하였습니다. 다시 시도해주세요.");
            mav.addObject("url", "/carCheck/imgCheck");
        } else {
            mav.addObject("msg", "데이터에 없는 차량 번호입니다. 확인 후 다시 시도해주세요.");
            mav.addObject("url", "/carCheck/imgCheck");

        }
        mav.setViewName("/redirect");
        return mav;
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
    @GetMapping("/detail/{checkCollectionName}")
    public String detail(@PathVariable("checkCollectionName") String checkCollectionName, Model model) throws Exception {

        log.debug("### id : {}", checkCollectionName);

        List<CarDTO> carDTOList = iCheckService.detail(checkCollectionName);

        model.addAttribute(carDTOList);

        return "/carCheck/detail";
    }
}