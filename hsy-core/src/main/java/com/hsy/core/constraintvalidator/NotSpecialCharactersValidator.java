package com.hsy.core.constraintvalidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hsy.common.utils.ObjectUtils;
import com.hsy.core.constant.ConfigConstant;
import com.hsy.core.constraints.NotSpecialCharacters;

/**
 * @author 张梓枫
 * @Description
 * @date:   2019年1月20日 下午5:03:18
 */
public class NotSpecialCharactersValidator implements ConstraintValidator<NotSpecialCharacters, String>{
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (ObjectUtils.isEmpty(value)){
            return true;
        }
        Pattern pattern = Pattern.compile(ConfigConstant.REGEX); 
        Matcher m = pattern.matcher(value);
        return !m.find();
    }

}
