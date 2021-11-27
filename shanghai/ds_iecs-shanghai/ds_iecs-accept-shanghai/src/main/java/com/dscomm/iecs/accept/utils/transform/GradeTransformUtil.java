package com.dscomm.iecs.accept.utils.transform;

import com.dscomm.iecs.accept.dal.po.GradeEntity;
import com.dscomm.iecs.accept.graphql.typebean.GradeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GradeTransformUtil {
    /*
     *根据id查询信息 进行转换
     * */
    public static List<GradeBean> transform(GradeEntity gradeEntity, Map<String, Map<String, String>>  dicsMap) {
        if (gradeEntity != null){
            List<GradeBean> list = new ArrayList<>();
            String cllx = gradeEntity.getCllx(); // 车辆类型
            String sl = gradeEntity.getSl(); // 派车数量
            String[] split1 = cllx.split(",");
            String[] split2 = sl.split(",");
            for (int i = 0;i<split1.length;i++) {
                GradeBean gradeBean = new GradeBean();
                gradeBean.setXfjg(gradeEntity.getXfjg());
                gradeBean.setAjdj(gradeEntity.getAjdj());
                gradeBean.setCzdx(gradeEntity.getCzdx());
                gradeBean.setZzsj(gradeEntity.getZzsj());
                gradeBean.setZzrxm(gradeEntity.getZzrxm());
                gradeBean.setZzrid(gradeEntity.getZzrid());
                gradeBean.setMc(gradeEntity.getMc());
                String str1 = split1[i]; // 车辆类型
                String str2 = split2[i]; // 车辆数量
                //车辆类型
                gradeBean.setCllx(dicsMap.get( "WLCLLX").get(str1));
//                gradeBean.setCllxName( dicsMap.get( "WLCLLX").get(str1));
                gradeBean.setBz(gradeEntity.getBz());
                gradeBean.setSl(str2);
                gradeBean.setAjlx(gradeEntity.getAjlx());
                list.add(gradeBean);
                }
            return list;
            }
        return null;
    }
}
