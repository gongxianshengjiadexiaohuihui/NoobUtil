package com.ggp.noob.bean;

import com.ggp.noob.Application;
import com.ggp.noob.time.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author:ggp
 * @Date:2020/11/2 20:23
 * @Description:
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringBeanUtilTest {

    @Test
    public void setBean() {
        System.out.println(SpringBeanUtil.setBean("person",Person.class,true,"ggp"));
        System.out.println(SpringBeanUtil.setBean("person",Person.class,true,"fz"));

    }
}