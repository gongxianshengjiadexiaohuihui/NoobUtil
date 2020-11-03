package com.ggp.noob.bean.data;

/**
 * @Author:ggp
 * @Date:2020/11/2 20:47
 * @Description:
 */
public class Person {
    public Person(String name) {
        this.name = name;
    }

    String name;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
