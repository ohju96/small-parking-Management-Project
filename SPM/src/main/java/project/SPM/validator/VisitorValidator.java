package project.SPM.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import project.SPM.dto.VisitorDTO;

@Component
@Slf4j
public class VisitorValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return VisitorDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        VisitorDTO visitorDTO = (VisitorDTO) target;

        if (!StringUtils.hasText(visitorDTO.getVisitorPhoneNumber())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "visitorPhoneNumber", "label");
        }

    }
}
