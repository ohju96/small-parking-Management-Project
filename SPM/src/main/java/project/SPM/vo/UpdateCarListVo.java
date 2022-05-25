package project.SPM.vo;

import lombok.Data;
import project.SPM.dto.CarDTO;

import java.util.List;

@Data
public class UpdateCarListVo {

    private List<CarDTO> carDtoList;
}
