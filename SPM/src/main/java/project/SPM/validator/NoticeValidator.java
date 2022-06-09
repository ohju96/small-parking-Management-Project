package project.SPM.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import project.SPM.dto.NoticeDTO;

@Slf4j
@Component
public class NoticeValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NoticeDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        NoticeDTO noticeDTO = (NoticeDTO) target;

        if (!StringUtils.hasText(noticeDTO.getMessage())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "label");
        }
    }
}
