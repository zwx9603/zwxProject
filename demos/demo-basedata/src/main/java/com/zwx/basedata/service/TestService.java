package com.zwx.basedata.service;


import com.zwx.basedata.typeBean.StudentBean;

import java.io.File;
import java.util.List;

public interface TestService {
    /**
    * @Description: 1.测试List集合中的Stream的作用 2.Stream下的filter()的作用
    *
    * @param:
    * @return:
    */
    String testStream();

    /*String testOut(DispatchOderEntity dto);*/

    List<StudentBean> test1();

    void testConlletions();

    void test2();

    void test3();

    void readColumn(File file, int index, int cloumn);
}
