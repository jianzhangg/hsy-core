package com.hsy.core.constraintvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hsy.common.utils.ObjectUtils;
import com.hsy.core.constraints.NotNull;

/**
 * @author 张梓枫
 * @Description
 * @date:   2019年1月3日 下午3:12:26
 */
public class NotNullValidator implements ConstraintValidator<NotNull, Object>{
    
    public void initialize(NotNull parameters) {
    }
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
		return ObjectUtils.isNotEmpty(value);
    }

}
