package com.hanking.pr.base;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理类
 * @author 金洋
 *
 */
@Controller
public abstract class BaseController {
	
    protected Logger logger = Logger.getLogger(BaseController.class);

    @ExceptionHandler(value = {Exception.class })
    protected ModelAndView handlerServiceException(Exception e) {
        logger.error("发生异常:", e);
        ModelAndView mav = new ModelAndView("/error/error");
        return mav;
    }
}

