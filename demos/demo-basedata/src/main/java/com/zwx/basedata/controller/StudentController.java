package com.zwx.basedata.controller;

import com.zwx.basedata.entity.StudentEntity;
import com.zwx.basedata.service.StudentService;
import com.zwx.basedata.typeBean.StudentBean;
import com.zwx.basedata.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/studentController")
@ResponseBody
public class StudentController {

    @Resource
    StudentService studentService;

    /*
     * 全查询学生信息
     * */
    @RequestMapping("/findAllStuCache")
    public ResultVo<List<StudentBean>> findAllStuCache(){
        List<StudentBean> allStuCache = studentService.findAllStuCache();
        return new ResultVo<>(allStuCache);
    }

    /*
     * 根据条件询学生信息
     * */
    @RequestMapping("/findStudentCondition")
    public ResultVo<List<StudentBean>> findStudentCondition(StudentEntity studentEntity){
        List<StudentBean> allStuCache = studentService.findStudentCondition(studentEntity);
        return new ResultVo<>(allStuCache);
    }

    /*
     * 新增学生信息
     * */
    @RequestMapping("/saveStudent")
    public ResultVo<StudentBean> saveStudent(@RequestBody StudentEntity studentEntity){
        StudentBean res = studentService.saveStudent(studentEntity);
        return new ResultVo<>(res);
    }

    /*
     * 修改学生信息
     * */
    @RequestMapping("/updateStudent")
    public ResultVo<StudentBean> updateStudent(@RequestBody StudentEntity studentEntity){
        StudentBean res = studentService.updateStudent(studentEntity);
        return new ResultVo<>(res);
    }

    /*
     * 删除学生信息
     * */
    @RequestMapping("/deleteStudent")
    public ResultVo<Boolean> deleteStudent(@RequestParam("sno") String sno){
        Boolean res = studentService.deleteStudent(sno);
        return new ResultVo<>(res);
    }
}
