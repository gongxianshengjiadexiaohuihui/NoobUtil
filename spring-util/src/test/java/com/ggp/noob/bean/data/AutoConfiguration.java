package com.ggp.noob.bean.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:ggp
 * @Date:2020/11/3 10:56
 * @Description:
 */
@Configuration
public class AutoConfiguration {
    @Bean
    public AutoBean autoBean(){
        return new AutoBean();
    }
}
