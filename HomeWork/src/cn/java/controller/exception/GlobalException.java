/**
 * Project Name:dt36_ssm1
 * File Name:GlobalException.java
 * Package Name:cn.java.controller.exception
 * Date:2017年7月28日上午11:54:34
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.java.controller.exception;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Description: <br/>
 * Date: 2017年7月28日 上午11:54:34 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
@ControllerAdvice
public class GlobalException {
    private static Logger logger = Logger.getLogger(GlobalException.class);

    @ExceptionHandler(Throwable.class)
    public String handAllException(Throwable ex) {
        logger.info("------------------恭喜您，出错了---------------------------");
        // logger.error(ex.getMessage());
        ex.printStackTrace();
        return "redirect:/pages/error/error.jsp";
    }

}
