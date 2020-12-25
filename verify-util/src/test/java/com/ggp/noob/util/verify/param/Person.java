package com.ggp.noob.util.verify.param;


import com.ggp.noob.util.verify.annotation.NotNull;

/**
 * @author: ggp
 * @Date: 2019/12/27 17:13
 * @Description:
 */
public class Person {
    @NotNull
    private String name;
    @NotNull
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
