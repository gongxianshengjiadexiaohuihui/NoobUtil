package com.ggp.noob.util.verify.param;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: ggp
 * @Date: 2019/12/27 17:13
 * @Description:
 */
public class CheckTest {

    @Test
    public void containNull() {
        Person person = new Person();
        person.setAge(1);
        Assert.assertFalse(Check.containNull(person));
    }
    @Test
    public void containNull1() {
        Person person = new Person();
        person.setAge(1);
        person.setName("test");
        Assert.assertTrue(Check.containNull(person));
    }

}