package com.ggp.noob.bean.data;

import com.ggp.noob.time.Example;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:ggp
 * @Date:2020/11/3 10:35
 * @Description:
 */
public class AutoDIBean {
    private String name;
    @Autowired
    private AutoBean autoBean;

    @Autowired
    private Example example;

    @Override
    public String toString() {
        return "AutoDIBean{" +
                "name='" + name + '\'' +
                ", autoBean=" + autoBean +
                ", example=" + example +
                '}';
    }
}
