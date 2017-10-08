package com.xjb.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component//这个是引入spring容器中
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     * 这是一个切点
     */
    @Pointcut("execution(public * com.xjb.controller.PersonController.*(..))")
    public void PersonPointCut() {
    }

    //@Before("execution(public * com.xjb.controller.PersonController.*(..))")
    @Before("PersonPointCut()")//使用切点
    public void log(JoinPoint joinPoint) {
        //记录日志
       ServletRequestAttributes  attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //请求的路径url
        HttpServletRequest request= attributes.getRequest();
        logger.info("URL={}", request.getRequestURL());
        //请求的IP
        logger.info("Ip={}", request.getRemoteAddr());

        //请求的 类方法
        logger.info("类名={}",  joinPoint.getSignature().getDeclaringTypeName());
        logger.info("类方法={}",  joinPoint.getSignature().getName());

        //请求的参数
        logger.info("参数={}", joinPoint.getArgs());

    }

    //请求运行完成之后
    @AfterReturning(pointcut = "PersonPointCut()",returning = "object")
    public void DoAfterReturning(Object object){
        logger.info(object.toString());
    }



}
