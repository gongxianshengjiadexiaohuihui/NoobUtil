package com.ggp.noob.time;

import java.lang.annotation.*;

/**
 * @Author:ggp
 * @Date:2020/9/30 10:41
 * @Description:
 * 统计时间
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface  StatisticTime {
    String value() default "-----";

}
