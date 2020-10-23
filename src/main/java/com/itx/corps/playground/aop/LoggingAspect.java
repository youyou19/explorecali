package com.itx.corps.playground.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;


@Component
@Aspect
public class LoggingAspect {

    private static  final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(com.itx.corps.playground.aop.Loggable)")
    public void executionLogging(){}

    @Before("executionLogging()")
    public void logMethodCall(JoinPoint joinPoint){
        System.out.println("Before Advice!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        StringBuilder message = new StringBuilder("Method : ");
        message.append(joinPoint.getSignature().getName());
        Object[] objects = joinPoint.getArgs();
        if(objects != null && objects.length>0) {
            message.append("Args[");
            Arrays.asList(objects).forEach(arg -> {
                message.append(arg).append("|");
            });
        }
        LOGGER.info(message.toString());
    }

    @Around("executionLogging()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint)throws Throwable{
        System.out.println("Around Advice Method!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long totalTime = System.currentTimeMillis() - startTime;
        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName());
        message.append("  Total Time : ").append(totalTime).append("  ms ");
        Object[] obj = joinPoint.getArgs();
        if(obj !=null && obj.length > 0){
            message.append("args[");
            Arrays.asList(obj).forEach(arg->{
                message.append(arg).append("|");
            });
        }
        if(returnValue instanceof Collection){
            message.append(" | returning : ").append(((Collection)returnValue).size()).append(" instance(s)");
        }
        LOGGER.info(message.toString());

        return returnValue;
    }


}
