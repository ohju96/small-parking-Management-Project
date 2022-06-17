package project.SPM.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import project.SPM.vo.UserVo;

@Slf4j
@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserVo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserVo userVo = (UserVo) target;

        if (!StringUtils.hasText(userVo.getUserName())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "label");
            //errors.rejectValue("userName", "label", null, null);
        }

        if (!StringUtils.hasText(userVo.getUserPn())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPn", "label");
        }

        if (!StringUtils.hasText(userVo.getUserEmail())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userEmail", "label");
        }

        if (!StringUtils.hasText(userVo.getUserId())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "label");
        }

        if (!StringUtils.hasText(userVo.getUserPw())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPw", "label");
        }

        if (!StringUtils.hasText(userVo.getUserPwc())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPwc", "label");
        }

        if (!StringUtils.hasText(userVo.getUserAddr())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAddr", "label");
        }
    }
}
