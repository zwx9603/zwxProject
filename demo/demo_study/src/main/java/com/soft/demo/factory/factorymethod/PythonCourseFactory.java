package com.soft.demo.factory.factorymethod;


import com.soft.demo.factory.ICourse;
import com.soft.demo.factory.PythonCourse;

/**
 * Created by Tom.
 */
public class PythonCourseFactory implements ICourseFactory {

    public ICourse create() {
        return new PythonCourse();
    }
}
