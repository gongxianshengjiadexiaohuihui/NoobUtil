package com.ggp.noob.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:ggp
 * @Date:2020/11/2 20:03
 * @Description:
 */
@Configuration
public class SpringBeanUtil implements ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(SpringBeanUtil.class);

    private static ConfigurableApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = (ConfigurableApplicationContext) applicationContext;
    }

    /**
     * 通过名字获取bean
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return context.getBean(name);
    }

    /**
     * 通过class类型获取bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    /**
     * 通过名字和class类型获取bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return context.getBean(name, clazz);
    }

    /**
     * 向spring容器中注册bean
     *
     * @param name     bean 名称
     * @param clazz    bean class对象
     * @param isReload 是否覆盖
     * @param args     参数
     * @param <T>
     * @return
     */
    public static <T> T setBean(String name, Class<T> clazz, boolean isReload, Object... args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        if (args.length > 0) {
            for (Object arg : args) {
                beanDefinitionBuilder.addConstructorArgValue(arg);
            }
        }
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) context.getBeanFactory();
        if (context.containsBean(name)) {
            Object bean = context.getBean(name);
            if (bean.getClass().isAssignableFrom(clazz) && !isReload) {
                return (T) bean;
            } else {
                beanFactory.removeBeanDefinition(name);
                logger.info("Bean[{}],type[{}],重新加载...", name, clazz);
            }
        }
        beanFactory.registerBeanDefinition(name, beanDefinition);
        return context.getBean(name, clazz);
    }
}
