package com.ggp.noob.bean;

import com.ggp.noob.Application;
import com.ggp.noob.bean.data.AutoBean;
import com.ggp.noob.bean.data.AutoConfiguration;
import com.ggp.noob.bean.data.AutoDIBean;
import com.ggp.noob.bean.data.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;

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
        System.out.println(SpringBeanUtil.setBean("person", Person.class,true,"ggp"));
        System.out.println(SpringBeanUtil.setBean("person",Person.class,true,"fz"));

    }
    @Test
    public void test_set_di_bean(){
        SpringBeanUtil.setBean("autoBean", AutoBean.class,false);
        SpringBeanUtil.setBean("autoDIBean", AutoDIBean.class,false);
        System.out.println(SpringBeanUtil.getBean("autoBean"));
        System.out.println(SpringBeanUtil.getBean("autoDIBean"));
    }
    @Test
    public void test_configuration_bean(){
        SpringBeanUtil.setBean("autoBean", AutoBean.class,false);
        System.out.println(SpringBeanUtil.getBean("autoBean"));
        SpringBeanUtil.setBean("autoConfiguration", AutoConfiguration.class,false);
        System.out.println(SpringBeanUtil.getBean("autoBean"));
    }
}