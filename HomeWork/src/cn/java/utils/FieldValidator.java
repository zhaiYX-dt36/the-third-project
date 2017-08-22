/**
 * Project Name:HomeWork
 * File Name:FieldValidator.java
 * Package Name:cn.java.utils
 * Date:2017年8月2日上午11:04:43
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.java.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Description: <br/>
 * Date: 2017年8月2日 上午11:04:43 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
public class FieldValidator {

    public static Map<String, Object> checkFiled(BindingResult errors) {
        Map<String, Object> fieldMap = null;
        boolean flag = errors.hasErrors();
        List<FieldError> errorlist = errors.getFieldErrors();
        if (flag) {
            fieldMap = new HashMap<String, Object>();
            for (FieldError fieldError : errorlist) {
                String field = fieldError.getField();
                String errorMessage = fieldError.getDefaultMessage();
                fieldMap.put(field, errorMessage);
            }
        }
        return fieldMap;
    }
}
