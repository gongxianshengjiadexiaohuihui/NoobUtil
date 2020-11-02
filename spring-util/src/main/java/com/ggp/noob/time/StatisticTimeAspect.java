package com.ggp.noob.time;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author:ggp
 * @Date:2020/9/30 10:44
 * @Description:
 */
@Aspect
@Component
public class StatisticTimeAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Pointcut("@annotation(com.ggp.noob.time.StatisticTime)")
    public void pointCut(){

    }
    @Around("pointCut()")
    public Object statistic(ProceedingJoinPoint point) throws Throwable {
        Long start = System.currentTimeMillis();
        Object o =point.proceed();
        long end = System.currentTimeMillis();
        StatisticTime statisticTime = ((MethodSignature)point.getSignature()).getMethod().getAnnotation(StatisticTime.class);
        logger.info("性能压测-----------------模块名[{}]------------耗时[{}]",statisticTime.value(),end-start);
        return o;
    }
}
