package com.dscomm.iecs.accept.utils;


import com.dscomm.iecs.accept.dal.po.StandardEntity;
import com.dscomm.iecs.accept.graphql.typebean.StandardBean;
import org.springframework.beans.BeanUtils;


public class StandardTransformUtil {
    /**
     * 描述：转换 人员
     *
     * @param source 人员 PO
     * @return 人员BO
     */
    public static StandardBean transform(StandardEntity source) {
        if( source != null ){
            StandardBean target = new StandardBean();
            BeanUtils.copyProperties(source,target);
            return target;
        }
        return  null ;
    }

}
