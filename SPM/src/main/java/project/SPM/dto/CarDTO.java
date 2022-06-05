package project.SPM.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {

    private String name;
    private String phoneNumber;
    private String carNumber;
    private String address;
    private String sort;
    private boolean check;

    private String userId;

    public CarDTO(String name, String phoneNumber, String carNumber, String address, String sort) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.carNumber = carNumber;
        this.address = address;
        this.sort = sort;
    }

    public CarDTO(String name, String phoneNumber, String carNumber, String address, String sort, boolean check) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.carNumber = carNumber;
        this.address = address;
        this.sort = sort;
        this.check = check;
    }

    public CarDTO() {

    }

    public CarDTO(String name, String phoneNumber, String carNumber, String address, String sort, String userId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.carNumber = carNumber;
        this.address = address;
        this.sort = sort;
        this.userId = userId;
    }
}
