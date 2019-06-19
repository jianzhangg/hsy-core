package com.hsy.core.constraintvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hsy.common.utils.ObjectUtils;
import com.hsy.core.constraints.LengthMin;

/**
 * @author 张梓枫
 * @Description
 * @date:   2019年1月3日 下午3:49:24
 */
public class LengthMinValidator implements ConstraintValidator<LengthMin, String> {

    private int minValue;
    
    public void initialize(LengthMin minValue) {
        this.minValue = minValue.value();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (ObjectUtils.isNotEmpty(value)) {
            return value.trim().length() >= this.minValue;
        }
        return true;
    }

}
