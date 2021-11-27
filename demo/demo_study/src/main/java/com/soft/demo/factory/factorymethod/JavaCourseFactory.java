package com.soft.demo.factory.factorymethod;


import com.soft.demo.factory.ICourse;
import com.soft.demo.factory.JavaCourse;

/**
 * Created by Tom.
 */
public class JavaCourseFactory implements ICourseFactory {
    public ICourse create() {
        return new JavaCourse();
    }
}
