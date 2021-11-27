package com.zwx.basedata.controller;

import com.zwx.basedata.service.StudentService;
import com.zwx.basedata.service.TestService;
import com.zwx.basedata.typeBean.StudentBean;
import com.zwx.basedata.vo.ResultVo;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/testController")
@Repository
public class TestController {

    @Resource
    TestService testService;

    /*
     * 全查询学生信息
     * */
    @RequestMapping("/testSort")
    public ResultVo<List<StudentBean>> testSort(){
        List<StudentBean> studentBeans = testService.test1();
        if(studentBeans != null){
            return new ResultVo<>("200","查询数据成功",studentBeans);
        }
        return new ResultVo<>("500","查询数据失败");
    }
}
