package com.zwx.basedata;

import com.zwx.basedata.until.util.PinYinUtils;

import org.mx.spring.task.TaskFactory;
import org.springframework.scheduling.config.Task;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.testPinYin();
    }

    public void testPinYin(){
        String testString ="乘";
        String[] duoYinHanziPinYin = PinYinUtils.getDuoYinHanziPinYin(testString);
        System.out.println("获取汉字的拼音:"+Arrays.toString(duoYinHanziPinYin));
        String duoYinHeaderNumberInitials = PinYinUtils.getDuoYinHeaderNumberInitials(testString);
        System.out.println("获取汉字拼音首字母缩写:"+duoYinHeaderNumberInitials);
        String duoYinHeaderNumberInitialsLower = PinYinUtils.getDuoYinHeaderNumberInitialsLower(testString).split(",")[0];
        System.out.println("获取汉字的拼音首字母小写:"+duoYinHeaderNumberInitialsLower);
        String duoYinHeaderNumberInitialsUpper = PinYinUtils.getDuoYinHeaderNumberInitialsUpper(testString);
        System.out.println("获取汉字的拼音首字母大写:"+duoYinHeaderNumberInitialsUpper);
        System.out.println("获取汉字的拼音:"+PinYinUtils.getDuoYinInitials(testString));
        System.out.println("获取汉字的拼音首字母大写:" +PinYinUtils.getDuoYinInitialsUpper(testString));
    }

    public void testTaskFactory(){
        TaskFactory taskFactory = new TaskFactory();
//        Task task = new Task();
    }
}
