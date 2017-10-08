package com.xjb.exhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 * 只是对于自己定义的异常
 */
@ControllerAdvice
public class ExceptionHandlers {

    private final static Logger loger= LoggerFactory.getLogger(ExceptionHandlers.class);

    /**
     * 需要返货json格式的
     * 而且有没有RestController
     * 所以加上 @ResponseBody
     *   由于系统自带的异常类不能再传code了
     *   所以这个时候需要自定义一个异常了
     *     当然  处理ExceptionHandler时候 value =自定义异常的类名.class(或者处理的异常是这个异常的积累也是可以的)
     *     参数可以是  handleException（自定义异常的类名 ex）
     * @return
     */
    @ExceptionHandler(value =Exception.class )//处理的异常的类型  这个可以自定义
    @ResponseBody
    public ExceptionResult handleException(HttpServletRequest request, Exception ex){
        ExceptionResult error=new ExceptionResult("异常",100,ex.getMessage());
        return error;
    }

    /**
     * 自定义的异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value =PersonException.class )//处理的异常的类型  这个可以自定义
    @ResponseBody
    public ExceptionResult handleException(HttpServletRequest request, PersonException ex){
        int errorCode=ex.getCode();
        ExceptionResult error=new ExceptionResult("异常",errorCode,ex.getMsg());
        loger.error(ex.getMsg());
        return error;
    }


}
