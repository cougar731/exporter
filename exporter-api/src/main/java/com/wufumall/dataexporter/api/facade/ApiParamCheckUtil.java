package com.wufumall.dataexporter.api.facade;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ApiParamCheckUtil {
  
  
private static Validator validator = null;
  
  public static <T> String checkParam(T obj){
      if(validator == null){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();   
            validator = validatorFactory.getValidator();
      }
      Set<ConstraintViolation<T>> valideSet = validator.validate(obj);
      if(valideSet != null && valideSet.size() > 0){
        StringBuilder strBuff = new StringBuilder();
            for(ConstraintViolation<T> cv : valideSet){
              strBuff.append(cv.getPropertyPath()).append(":").append(cv.getMessage()).append(";");
            }
            return strBuff.toString();
        }else{
          return null;
        }
    }
}
