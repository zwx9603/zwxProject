package com.zwx.basedata.service.impl;

import com.alibaba.fastjson.JSON;
import com.zwx.basedata.entity.FunctionEntity;
import com.zwx.basedata.entity.StudentEntity;
import com.zwx.basedata.service.TestService;
import com.zwx.basedata.typeBean.StudentBean;
import com.zwx.basedata.until.FunctionUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@PropertySource(value = "classpath:config/test.properties")
public class TestServiceImpl implements TestService {

    @Value("${creatFileUrl}")
    public String creatFileUrl;

    @Override
    public String testStream() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(5);
        integerList.add(10);
        integerList.add(6);
        integerList.add(8);
        integerList.add(20);
        integerList.add(30);
        integerList.add(31);
        integerList.add(21);
        integerList.add(11);
        integerList.add(22);
        Stream<Integer> stream = integerList.stream();
        List<Integer> collect = stream.filter(value -> value > 10).collect(Collectors.toList());
        // 测试对象
        List<StudentEntity> studentEntityList = new ArrayList<>();
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAge("8");
        studentEntity.setSno("000001");
        studentEntity.setTel("1234");
        studentEntityList.add(studentEntity);

        StudentEntity studentEntity1 = new StudentEntity();
        studentEntity1.setAge("11");
        studentEntity1.setSno("000001");
        studentEntity1.setTel("1234");
        studentEntityList.add(studentEntity1);

        StudentEntity studentEntity2 = new StudentEntity();
        studentEntity2.setAge("15");
        studentEntity2.setSno("000001");
        studentEntity2.setTel("1234");
        studentEntityList.add(studentEntity2);

        StudentEntity studentEntity3 = new StudentEntity();
        studentEntity3.setAge("9");
        studentEntity3.setSno("000001");
        studentEntity3.setTel("1234");
        studentEntityList.add(studentEntity3);

        Stream<StudentEntity> stream1 = studentEntityList.stream();
        List<StudentEntity> collect1 = stream1.filter(student -> Integer.valueOf(student.getAge()) > 10).collect(Collectors.toList());

        return collect.toString() + collect1.toString();
    }


    /*@Override
    public String testOut(DispatchOderEntity dto) {
        String incidentUrl = "http://117.143.57.250:45000/v1/microstation/doDealStationAlarm";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(incidentUrl);

        String s = JSON.toJSONString(dto);
        StringEntity entity = new StringEntity(s,"UTF-8");
        httpPost.setHeader("Content-Type","application/json;charset=UTF-8");
        httpPost.setHeader("accessToken","MC0xMjA2NTMtNjE2M2JhZDNjNzRiZg==");
        CloseableHttpResponse response = null;
        HttpEntity entity1 = null;
        try{
            response = httpClient.execute(httpPost);
            entity1 = response.getEntity();
            System.out.println(entity1.toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(httpClient != null){
                    httpClient.close();
                }
                if(response != null){
                    response.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return entity1.toString();
    }*/

    @Override
    public List<StudentBean> test1() {
        List<StudentBean> studentBeans = new ArrayList<>();
        StudentBean studentBean1 = new StudentBean("1110","name1","24","0","17803829936");
        StudentBean studentBean2 = new StudentBean("1111","name2","24","0","17803829936");
        StudentBean studentBean3 = new StudentBean("111a","name3","24","0","17803829936");
        StudentBean studentBean4 = new StudentBean("1112","name4","24","0","17803829936");
        StudentBean studentBean5 = new StudentBean("111b","name5","24","0","17803829936");
        StudentBean studentBean6 = new StudentBean("1120","name6","24","0","17803829936");
        StudentBean studentBean7 = new StudentBean("112a","name7","24","0","17803829936");
        StudentBean studentBean8 = new StudentBean("1212","name8","24","0","17803829936");
        StudentBean studentBean9 = new StudentBean("112b","name9","24","0","17803829936");
        StudentBean studentBean10 = new StudentBean("121a","name10","24","0","17803829936");
        studentBeans.add(studentBean1);
        studentBeans.add(studentBean2);
        studentBeans.add(studentBean3);
        studentBeans.add(studentBean4);
        studentBeans.add(studentBean5);
        studentBeans.add(studentBean6);
        studentBeans.add(studentBean7);
        studentBeans.add(studentBean8);
        studentBeans.add(studentBean9);
        studentBeans.add(studentBean10);
//        studentBeans.sort(null);
//        Collections.sort(studentBeans);
        Collections.sort(studentBeans, new Comparator<StudentBean>() {
            @Override
            public int compare(StudentBean o1, StudentBean o2) {
                if(o1.getSno().compareTo(o2.getSno()) == 0){
                    return 1;
                }
                return o1.getSno().compareTo(o2.getSno());
            }
        });
        return studentBeans;
    }

    @Override
    public void testConlletions() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("2");
        list.add("2");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("5");
        list.add("5");
        list.add("6");
        list.add("6");
        list.add("6");
        list.add("6");
        list.add("6");
        list.add("6");
        list.add("7");
        list.add("7");
        Collections.sort(list);
        /*System.out.println("正常输出:" + list.toString());
        Collections.reverse(list);
        System.out.println("执行翻转后的数据:" + list.toString());
        List<String> list1 = new ArrayList<>();
        Collections.addAll(list1);
        list.removeIf(ele -> Integer.valueOf(ele) > 2);
        System.out.println(list.toString());*/
        // 记录重复元素的个数
        Map<String,Integer> map = new HashMap<>();
        list.forEach(ele -> {
           Integer i = 1;
           if(map.get(ele) != null){
              i=map.get(ele)+1;
           }
           map.put(ele,i);
        });
        System.out.println("集合中重复的元素的数量:"+map.toString());
    }

    /*
    * 统计list1中和list相同元素出现的次数，及list1中独有的元素出现的次数
    * */
    public void test2(){
        List<FunctionEntity> list = new ArrayList<>();
        list.add(new FunctionEntity("130","泵浦",3));
        list.add(new FunctionEntity("120","抢险",2));
        list.add(new FunctionEntity("110","照明",1));
        list.add(new FunctionEntity("100","泡沫",1));
        list.add(new FunctionEntity("140","云梯",2));
        List<FunctionEntity> list1 = new ArrayList<>();
        list1.add(new FunctionEntity("130","泵浦1",0));
        list1.add(new FunctionEntity("130","泵浦2",0));
        list1.add(new FunctionEntity("130","泵浦3",0));
        list1.add(new FunctionEntity("120","抢险1",0));
        list1.add(new FunctionEntity("120","抢险2",0));
        list1.add(new FunctionEntity("120","抢险3",0));
        list1.add(new FunctionEntity("120","抢险4",0));
        list1.add(new FunctionEntity("110","照明",0));
        list1.add(new FunctionEntity("90","水罐",0));
        list1.add(new FunctionEntity("80","排烟",0));
        list1.add(new FunctionEntity("150","搜救犬",0));
        List<FunctionEntity> list2 = new ArrayList<>();
        Long startTime = System.currentTimeMillis();
        // 统计list和list1中code相等的数据
        list.forEach(ele ->{
            list1.stream().filter(ele1 -> ele1.getCode().equals(ele.getCode()));
            Map<String, Integer> stringIntegerMap = countNumber(list1);
            if(stringIntegerMap.get(ele.getCode()) != null){
                ele.setCzNum(stringIntegerMap.get(ele.getCode()));
            }else{
                ele.setCzNum(0);
            }
        });
        list2 = list;
        // 统计list 和 list1中不相等的数据
        list.forEach(ele ->{
            // 除去list1中和list相同的数据
            list1.removeIf(ele1 -> ele1.getCode().equals(ele.getCode()));
        });
        if(list1 != null){
            Map<String, Integer> stringIntegerMap = countNumber(list1);
            for (FunctionEntity ele : list1) {
                ele.setCzNum(stringIntegerMap.get(ele.getCode()));
                list2.add(ele);
            }
        }
        System.out.println("结果:" + list2.toString());
        Long endTime = System.currentTimeMillis();
        System.out.println("所用时间是:"+ (endTime - startTime));
    }
    public static Map<String,Integer> countNumber(List<FunctionEntity> t ){
        Map<String,Integer> map = new HashMap<>();
        t.forEach(ele -> {
            Integer i = 1;
            if(map.get(ele.getCode()) != null){
                i=map.get(ele.getCode())+1;
            }
            map.put(ele.getCode(),i);
        });
        return map;
    }

    /*
    * 统计list和list1中相同的元素并排在最前方，不同的元素的放在后方
    * */
    public void test3(){
        List<FunctionEntity> list = new ArrayList<>();
        FunctionEntity target = new FunctionEntity();
        target.setCode("120");
        target.setName("抢险");
        FunctionEntity target1 = new FunctionEntity();
        target1.setCode("110");
        target1.setName("照明");
        FunctionEntity target2 = new FunctionEntity();
        target2.setCode("100");
        target2.setName("泵浦");
        FunctionEntity target3 = new FunctionEntity();
        target3.setCode("130");
        target3.setName("语文");
        FunctionEntity target4 = new FunctionEntity();
        target4.setCode("140");
        target4.setName("数学");
        FunctionEntity target5 = new FunctionEntity();
        target5.setCode("160");
        target5.setName("云梯");
        FunctionEntity target6 = new FunctionEntity();
        target6.setCode("150");
        target6.setName("泡沫");
        Collections.addAll(list,target,target1,target2,target3,target4,target5,target6);
        List<FunctionEntity> list1 = new ArrayList<>();
        FunctionEntity target7 = new FunctionEntity();
        target7.setCode("120");
        target7.setName("抢险");
        FunctionEntity target8 = new FunctionEntity();
        target8.setCode("110");
        target8.setName("照明");
        FunctionEntity target9 = new FunctionEntity();
        target9.setCode("100");
        target9.setName("泵浦");
        FunctionEntity target10 = new FunctionEntity();
        target10.setCode("220");
        target10.setName("政治");
        FunctionEntity target11 = new FunctionEntity();
        target11.setCode("140");
        target11.setName("数学");
        FunctionEntity target12 = new FunctionEntity();
        target12.setCode("210");
        target12.setName("英语");
        FunctionEntity target13 = new FunctionEntity();
        target13.setCode("200");
        target13.setName("化学");
        Collections.addAll(list1,target7,target8,target9,target10,target11,target12,target13);
        // 将list1集合中的元素打乱
        Collections.shuffle(list1);
        System.out.println(list.toString());
        System.out.println(list1.toString());
        // 先挑选出 list 和list1 相同的元素
        List<FunctionEntity> same = new ArrayList<>();
        // 把list和list1中相同的元素排在前方,不同的元素排在后方
        list = FunctionUtil.setListOrder(list, list1);
        list1 = FunctionUtil.setListOrder(list1,list);
        for (FunctionEntity ele: list) {
            list1.forEach(ele1 -> {
                if(ele.getCode().equals(ele1.getCode())){
                    same.add(ele);
                }
            });
        }
        System.out.println("相同的元素"+same.toString());
        System.out.println("应该是: 120  110  100 140");
        System.out.println("list:" + list.toString());
        System.out.println("list1:" + list1.toString());
        List<FunctionEntity> functionEntities = coutTwoNum(list, list1);
        System.out.println(functionEntities.toString());

    }

    @Override
    public void readColumn(File file, int index,int cloumn) {
        InputStream inputStream = null;
        Workbook workbook = null;
        try {
            inputStream = new FileInputStream(file.getAbsoluteFile());
            workbook = Workbook.getWorkbook(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();
            List<String> paramList = new ArrayList<>();
            for (int i = cloumn; i < rows; i++) {
                Cell cell = sheet.getCell(index, i);
                paramList.add(cell.getContents());
            }
            System.out.println(paramList.toString());
            paramList.forEach(ele -> {
                FileOutputStream fos = null;
                File file1 = new File(creatFileUrl + ele + ".xls");
                try {
                    fos = new FileOutputStream(file1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }


    public static void main(String[] args) {
        TestService testService = new TestServiceImpl();
        testService.test3();

    }

    /**
    * @Description: 把两个集合的数据合在一起(相同的放在前部,不同的放在后面)
    *
    * @param: [list(等级), list1(智能), same]
    * @return: list3(合并后)
    */
    public List<FunctionEntity> coutTwoNum(List<FunctionEntity> list,List<FunctionEntity> list1){
        // 在list中装入相同功能的list1调派数量
        list.forEach(ele -> {
            list1.forEach(ele1 ->{
                if(ele.getCode().equals(ele1.getCode())){
                    ele.setSmartNum(ele1.getDispatNum());
                    ele.setFlag(2);
                    ele1.setFlag(2);
                }
            });
        });
        list1.forEach(ele ->{
            if(ele.getFlag() != 2){
                list.add(ele);
            }
        });
        return list;
    }

}
