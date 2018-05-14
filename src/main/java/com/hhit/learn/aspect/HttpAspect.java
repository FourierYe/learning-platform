package com.hhit.learn.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Http aspect.
 *
 * @author GeekYe
 */
@Component
@Aspect
public class HttpAspect {

    /**
     * The constant SIMPLE_DATE_FORMAT.
     */
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     * Log.
     */
    @Pointcut("execution(public * com.hhit.learn.controller.*.*(..))")
    public void log(){

    }

    /**
     * Do before.
     *
     * @param joinPoint the join point
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){

        //url
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest=attributes.getRequest();
        logger.info("url={}", httpServletRequest.getRequestURL());
        //method
        logger.info("method={}", httpServletRequest.getMethod());
        //ip
        logger.info("ip={}", httpServletRequest.getRemoteAddr());
        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //参数
        logger.info("args={}", joinPoint.getArgs());

    }

    /**
     * Da after.
     */
    @After("log()")
    public void doAfter(){
        SimpleDateFormat simpleDateFormat = SIMPLE_DATE_FORMAT;
        String date = simpleDateFormat.format(new Date());
        logger.info("-----------------------------------------结束时间-----------------------------------------"+date);
    }

    /**
     * Ad after returning.
     *
     * @param object the object
     */
    @AfterReturning(returning = "object",pointcut = "log()")
    public void adAfterReturning(Object object){
        logger.info("response={}",object);
    }

}
