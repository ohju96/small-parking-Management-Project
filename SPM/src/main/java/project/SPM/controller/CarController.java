package project.SPM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.SPM.dto.CarDTO;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/carManagement")
@RequiredArgsConstructor
public class CarController {

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

        List<CarDTO> list = new ArrayList<CarDTO>();

        OPCPackage opcPackage = OPCPackage.open(mf.getInputStream());
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);

        // 첫 번째 시트를 불러온다.
        XSSFSheet sheet = workbook.getSheetAt(0);

        // i는 몇 번째 행 부터 체크를 할 것인지 정한다.
        for (int i=0; i<sheet.getLastRowNum() + 1; i++) {
            CarDTO carDTO = new CarDTO();
            XSSFRow row = sheet.getRow(i);

            // 행이 존재하지 않으면 패스한다.
            if (null == row) {
                continue;
            }

            // 행의 첫 번째 열(이름)
            XSSFCell cell = row.getCell(0);
            if (null != cell) {
                carDTO.setName(cell.getStringCellValue());
            }

            // 행의 두 번째 열(핸드폰번호)
            cell = row.getCell(1);
             if (null != cell){
                 carDTO.setPhoneNumber(cell.getStringCellValue());
             }

            // 행의 세 번째 열(차량번호)
             cell = row.getCell(2);
             if (null != cell) {
                 carDTO.setCarNumber(cell.getStringCellValue());
             }

            // 행의 네 번째 열(주소)
            cell = row.getCell(3);
            if (null != cell) {
                carDTO.setAddress(cell.getStringCellValue());
            }

            // 리스트에 담는다.
            list.add(carDTO);

            log.debug("리스트 안에 옮겨 담기 : {}", carDTO);
        }


        return "carManagement/carManagement";
    }
}
