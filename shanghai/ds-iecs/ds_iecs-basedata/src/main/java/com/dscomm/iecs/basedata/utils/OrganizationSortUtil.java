package com.dscomm.iecs.basedata.utils;

import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;

import java.util.*;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/14 15:58
 * @Describe 机构排序工具类
 */
public class OrganizationSortUtil {

    public static Map<String, OrganizationBean> transform(Map<String, OrganizationBean> organizationBeanMap){
        if (!organizationBeanMap.isEmpty()) {
            Map<String,Integer> map = new HashMap<>();
            //遍历map
            for (String key:organizationBeanMap.keySet()
                 ) {
                map.put(key,organizationBeanMap.get(key).getOrganizationOrderNum());
            }

            List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
            ValueComparator vc=new ValueComparator();
            list.sort(vc);

            Map<String, OrganizationBean> newOrganizationBeanMap=new LinkedHashMap<>();
            for(Iterator<Map.Entry<String,Integer>> it=list.iterator();it.hasNext();)
            {
               // System.out.println(it.next());
                String orgId = it.next().getKey();
                OrganizationBean bean = organizationBeanMap.get(orgId);
                newOrganizationBeanMap.put(orgId,bean);
            }
            return newOrganizationBeanMap;

        }
        return null;
    }


    //排序

    private static class ValueComparator implements Comparator<Map.Entry<String, Integer>>
    {
        public int compare(Map.Entry<String, Integer> mp1, Map.Entry<String, Integer> mp2)
        {
           // return mp2.getValue() - mp1.getValue();
            return mp1.getValue().compareTo(mp2.getValue());
        }
    }


}
