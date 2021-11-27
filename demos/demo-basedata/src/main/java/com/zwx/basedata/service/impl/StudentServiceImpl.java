package com.zwx.basedata.service.impl;

import com.zwx.basedata.Logger.LoggerService;
import com.zwx.basedata.entity.StudentEntity;
import com.zwx.basedata.mapper.StudentMapper;
import com.zwx.basedata.service.StudentService;
import com.zwx.basedata.typeBean.StudentBean;
import com.zwx.basedata.until.transformUtil.StudentTransformUtil;
import org.apache.logging.log4j.util.Strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@CacheConfig(
        cacheNames = {"stuCache"}
)
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    public static Map<String,StudentBean> stuBeanCache = new HashMap<>();

    @Resource
    StudentMapper studentMapper;

    @Resource
    LoggerService loggerService;

    @Override
    @CachePut(key = "stu_id_bean")
    public Map<String, StudentBean> modifyStudentCache(String sno, StudentBean studentBean, String var) {
        if(Strings.isNotBlank(sno) && (var.equals("save") || var.equals("update"))){
            stuBeanCache.put(sno,studentBean);
        }
        if(Strings.isNotBlank(sno) && var.equals("delete")){
            stuBeanCache.remove(sno);
        }
        return stuBeanCache;
    }

    @Override
    @Cacheable(key = "stu_id_bean")
    public Map<String, StudentBean> findStudentCache() {
        return stuBeanCache;
    }

    @Override
    public List<StudentBean> queryStudentInfo() {
        List<StudentBean> transform = new ArrayList<>();
        try {
            List<StudentEntity> studentEntities = studentMapper.findAllStudent();
//            List<StudentEntity> studentEntities = studentRepository.findStudentAll();
            if (studentEntities != null) {
                for (StudentEntity ele : studentEntities) {
                    StudentBean transform1 = StudentTransformUtil.transform(ele);
                    modifyStudentCache(ele.getSno(), transform1, "save");
                    transform.add(transform1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            loggerService.erorLog(logger,"queryStudentInfo","queryStudentInfo","加载学生类缓存",e);
            System.out.println(e.toString());
        }finally {
            return transform;
        }
    }

    @Override
    public List<StudentBean> findAllStuCache() {
        List<StudentBean> studentBeanList = new ArrayList<>();
        Map<String, StudentBean> studentCache = findStudentCache();
        Iterator<String> iterator = studentCache.keySet().iterator();
        while (iterator.hasNext()){
            studentBeanList.add(studentCache.get(iterator.next()));
        }
        return studentBeanList;
    }

    @Override
    public List<StudentBean> findStudentCondition(StudentEntity studentEntity) {
        List<StudentBean> studentBeans = new ArrayList<>();
        List<StudentEntity> studentCondition = studentMapper.findStudentCondition(studentEntity);
        studentCondition.forEach(ele -> {
            StudentBean transform = StudentTransformUtil.transform(ele);
            studentBeans.add(transform);
        });
        return studentBeans;
    }

    @Override
    public StudentBean saveStudent(StudentEntity student) {
        int i = studentMapper.saveStudent(student);
        StudentBean res = null;
        if(i>0){
            res = StudentTransformUtil.transform(student);
            modifyStudentCache(res.getSno(),res,"save");
        }
        return res;
    }

    @Override
    public StudentBean updateStudent(StudentEntity student) {
        int i = studentMapper.updateStudent(student);
        StudentBean res = null;
        if(i > 0){
            res = StudentTransformUtil.transform(student);
            //更新缓存
            modifyStudentCache(res.getSno(),res,"update");
        }
        return res;
    }

    @Override
    public Boolean deleteStudent(String sno) {
        studentMapper.deleteStudent(sno);
        modifyStudentCache(sno,null,"delete");
        return true;
    }
}
