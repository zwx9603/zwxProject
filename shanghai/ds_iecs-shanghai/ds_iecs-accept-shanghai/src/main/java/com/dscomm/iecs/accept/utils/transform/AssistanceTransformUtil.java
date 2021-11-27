package com.dscomm.iecs.accept.utils.transform;

import com.dscomm.iecs.accept.dal.po.AssistanceEntity;
import com.dscomm.iecs.accept.graphql.typebean.AssistanceBean;

public class AssistanceTransformUtil {
    /*
    *根据id查询信息 进行转换
    * */
    public static AssistanceBean transform(AssistanceEntity ass){

        if (ass != null) {
            AssistanceBean assBean = new AssistanceBean();
            assBean.setPldwdm(ass.getPldwdm());
            assBean.setZgdwdm(ass.getZgdwdm());
            assBean.setBz(ass.getBz());
            assBean.setCszt(ass.getCszt());
            assBean.setJb(Integer.valueOf(ass.getJb()));
            assBean.setJksjbb(ass.getJksjbb());
            assBean.setSjbb(ass.getSjbb());
            assBean.setXfjgdm(ass.getXfjgdm());
            assBean.setJlzt(ass.getJlzt());
            assBean.setJb(Integer.valueOf(ass.getJb()));
            assBean.setYxj(ass.getYxj());
            assBean.setSjc(ass.getSjc());
            return assBean;
        }
        return null;

    }
}
