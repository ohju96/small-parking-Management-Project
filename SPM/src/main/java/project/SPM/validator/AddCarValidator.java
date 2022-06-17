package project.SPM.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import project.SPM.vo.AddCarVo;

@Slf4j
@Component
public class AddCarValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AddCarVo.class.isAssignableFrom(clazz);
//        return clazz.isAssignableFrom(AddCarVo.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        AddCarVo addCarVo = (AddCarVo) target;

        if (!StringUtils.hasText(addCarVo.getName())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "label");
        }

        if (!StringUtils.hasText(addCarVo.getPhoneNumber())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "label");
        }

        if (!StringUtils.hasText(addCarVo.getCarNumber())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "carNumber", "label");
        }

        if (!StringUtils.hasText(addCarVo.getAddress())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "label");
        }

        if (!StringUtils.hasText(addCarVo.getSort())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sort", "label");
        }

    }
}
