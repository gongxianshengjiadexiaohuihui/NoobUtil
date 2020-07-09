package com.ggp.noob.verification.param;

import com.ggp.noob.verification.annotation.NotNull;

import java.lang.reflect.Field;

/**
 * @author: ggp
 * @Date: 2019/12/27 17:04
 * @Description:
 */
public class Check {
    //TODO 线程是否安全 是否要搞单例

    public static Object object;

    public static boolean containNull() {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                Object o = null;
                try {
                    o = field.get(object);
                } catch (IllegalAccessException e) {
                    /**
                     * 不存在此异常
                     */
                }
                if (null == o) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean containNull(Object obj){
        object = obj;
        return containNull();
    }
}
