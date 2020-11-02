package com.ggp.noob.time.aop;


import com.ggp.noob.Application;
import com.ggp.noob.time.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author:ggp
 * @Date:2020/9/30 15:32
 * @Description:
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class StatisticTimeAspectTest {
    @Autowired
    private Example example;

    @Test
    public void test_static_time() {
        example.testTime();
    }
}