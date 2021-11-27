package com.dscomm.iecs.basedata.utils;

import com.dscomm.iecs.base.utils.PinYinUtils;
import com.dscomm.iecs.basedata.dal.po.DictionaryEntity;
import com.dscomm.iecs.basedata.graphql.typebean.DictionaryBean;
import org.apache.logging.log4j.util.Strings;

/**
 * 字典 转换类
 */
public class DictionaryTransformUtil {

    /**
     * 描述：字典 转换
     *
     * @param source  字典实体PO
     * @return 字典BO
     */
    public static DictionaryBean transform( DictionaryEntity source   ) {
        if( source != null ){
            DictionaryBean target = new DictionaryBean();
            target.setId(  source.getCode() );
            target.setTypeCode( source.getTypeCode() );
            target.setTypeDesc( source.getTypeDesc() );
            target.setCode( source.getCode() );
            target.setName( source.getName() );
            if( Strings.isNotBlank( source.getName() )){
                target.setPinyinHeader(  PinYinUtils.getDuoYinHeaderNumberInitialsLower( source.getName().replaceAll("\\\\s*","") )  );
            }
            target.setDesc( source.getDesc() );
            target.setOrderNum( source.getOrderNum() );
            target.setParentCode( source.getParentCode() );
            target.setIncidentTypeCode( source.getIncidentTypeCode() );
            target.setConvertCode( source.getConvertCode() );
            target.setConvertName( source.getConvertName() );
            target.setRemarks( source.getRemarks() );

            target.setParentId(  source.getParentCode() );

            return target;
        }
        return  null ;
    }



    /**
     * 描述：字典 复制
     *
     * @param source  字典实体PO
     * @return 字典BO
     */
    public static DictionaryBean transform( DictionaryBean source   ) {
        if( source != null ){
            DictionaryBean target = new DictionaryBean();
            target.setId(  source.getCode() );
            target.setTypeCode( source.getTypeCode() );
            target.setTypeDesc( source.getTypeDesc() );
            target.setCode( source.getCode() );
            target.setName( source.getName() );
            target.setPinyinHeader( source.getPinyinHeader()   );
            target.setDesc( source.getDesc() );
            target.setOrderNum( source.getOrderNum() );
            target.setParentId(  source.getParentCode() );
            target.setParentCode( source.getParentCode() );
            target.setIncidentTypeCode( source.getIncidentTypeCode() );
            target.setConvertCode( source.getConvertCode() );
            target.setConvertName( source.getConvertName() );
            target.setRemarks( source.getRemarks() );
            return target;
        }
        return  null ;
    }
}
