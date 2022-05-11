package project.SPM.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddCarVo {

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String carNumber;

    @NotNull
    private String address;

    private String sort;

}
