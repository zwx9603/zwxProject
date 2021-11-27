package com.zwx.basedata.until;

import com.zwx.basedata.entity.FunctionEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionUtil {
    /*
    * 选出两个集合相同的元素
    * */
    public static List<FunctionEntity> selectSame(List<FunctionEntity> list,List<FunctionEntity> list1){
        // 先挑选出 list 和list1 相同的元素
        List<FunctionEntity> samePara = new ArrayList<>();
        for (FunctionEntity ele: list) {
            list1.forEach(ele1 -> {
                if(ele.getCode().equals(ele1.getCode())){
                    samePara.add(ele);
                }
            });
        }
        return samePara;
    }

    /*
     * source: 要排序的集合
     * source1: 排序要参照的集合
     * */
    public static List<FunctionEntity> setListOrder(List<FunctionEntity> source , List<FunctionEntity> source1) {
        List<FunctionEntity> target = new ArrayList<>();
        source1.forEach(ele ->{
            source.forEach(ele1 ->{
                // 把要排序的集合中相同的元素挑选出来
                if ((ele.getCode()).equals(ele1.getCode())) {
                    target.add(ele1);
                    ele1.setFlag(1);
                }
            });
        });
        source.forEach(ele ->{
            if(ele.getFlag() == 0){
                target.add(ele);
            }
        });
        return target;
    }

    /*
    * 统计同一功能下参战车辆的数量
    * list:应派的数据
    * list1: 参战数据
    * */
    public static List<FunctionEntity> countCZNumber(List<FunctionEntity> list,List<FunctionEntity> list1){
        List<FunctionEntity> list2 = new ArrayList<>();
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

        return list2;
    }

    /*
    * 统计一个集合中元素出现的此数
    * */
    public static Map<String,Integer> countNumber(List<FunctionEntity> source ){
        Map<String,Integer> map = new HashMap<>();
        source.forEach(ele -> {
            Integer i = 1;
            if(map.get(ele.getCode()) != null){
                i=map.get(ele.getCode())+1;
            }
            map.put(ele.getCode(),i);
        });
        return map;
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
