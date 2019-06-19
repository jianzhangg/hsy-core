package com.hsy.core.constraintvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hsy.core.constraints.NotBlank;

/**
 * @author 张梓枫
 * @Description
 * @date: 2019年1月3日 下午3:24:30
 */
public class NotBlankValidator implements ConstraintValidator<NotBlank, CharSequence> {

    public void initialize(NotBlank parameters) {
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        } else {
            return value.toString().trim().length() > 0;
        }
    }

}
