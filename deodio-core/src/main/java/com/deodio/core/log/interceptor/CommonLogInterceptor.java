package com.deodio.core.log.interceptor;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deodio.core.constants.Constants;

@Aspect
public class CommonLogInterceptor {



	@AfterReturning("execution(* com.sinotrans.commerce..*.*(..))")
    public void afterReturning(JoinPoint joinPoint) throws Exception {
        Logger logger = LoggerFactory.getLogger(CommonLogInterceptor.class);
        String info = joinPoint.getTarget().getClass() + Constants.STRING_DOT + joinPoint.getSignature().getName() + Constants.STRING_BRACKET;
        logger.info("Exceute Time: {}, Method: {}, Status: End", new Date(), info);
    }

    @Before("execution(* com.sinotrans.commerce..*.*(..))")
    public void before(JoinPoint joinPoint) throws Exception {
        Logger logger = LoggerFactory.getLogger(CommonLogInterceptor.class);
        String info = joinPoint.getTarget().getClass() + Constants.STRING_DOT + joinPoint.getSignature().getName() + Constants.STRING_BRACKET;
        logger.info("Exceute Time: {}, Method: {}, Status: Start", new Date(), info);

    }

    @AfterThrowing(pointcut = "execution(* com.sinotrans.commerce..*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        Logger logger = LoggerFactory.getLogger(CommonLogInterceptor.class);
        String info = joinPoint.getTarget().getClass() + Constants.STRING_DOT + joinPoint.getSignature().getName() + Constants.STRING_BRACKET;
        logger.info("Exceute Time: {}, Method: {}, Status: {}, Exception: {}", new Date(), info, "Exception",
                ex.getMessage());
    }

}
