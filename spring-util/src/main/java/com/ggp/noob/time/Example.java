package com.ggp.noob.time;

import org.springframework.stereotype.Component;

/**
 * @Author:ggp
 * @Date:2020/9/30 16:02
 * @Description:
 */
@Component
public class Example {
    @StatisticTime(value = "test")
    public void testTime(){
        int j =100000;
        for (int i = 0; i <1000 ; i++) {
            j+=i;
        }
    }
}
