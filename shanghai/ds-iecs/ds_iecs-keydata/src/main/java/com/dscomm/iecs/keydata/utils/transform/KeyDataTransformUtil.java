package com.dscomm.iecs.keydata.utils.transform;

import com.dscomm.iecs.base.enums.BasicEnumNumberBean;
import com.dscomm.iecs.base.enums.BasicEnumNumberUtils;
import com.dscomm.iecs.keydata.dal.po.AuditLogEntity;
import com.dscomm.iecs.keydata.dal.po.KeyDataChangeRecordEntity;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.graphql.inputbean.KeyDataChangeRecordSaveInputInfo;
import com.dscomm.iecs.keydata.graphql.typebean.AuditLogBean;
import com.dscomm.iecs.keydata.graphql.typebean.KeyDataChangeRecordBean;
import org.apache.commons.lang3.StringUtils;

/**
 * 描述 : 关键数据转换工具
 *
 */
public class KeyDataTransformUtil {


    /**
     * 关键数据变更记录转换
     *
     * @param source 关键数据变更记录INPUT
     * @return 关键数据变更记录PO
     */
    public static KeyDataChangeRecordEntity transform(KeyDataChangeRecordSaveInputInfo source ,  Long currentSystemTime ) {
        if (null != source) {
            KeyDataChangeRecordEntity target = new KeyDataChangeRecordEntity();

            target.setBusinessTableName(source.getBusinessTableName() );
            target.setBusinessDataId( source.getBusinessDataId() );
            target.setBusinessField( source.getBusinessField() );
            target.setChangeTime( currentSystemTime );
            target.setOriginalDate( source.getOriginalDate() );
            target.setChangeDate( source.getChangeDate() );
            target.setOrganizationId( source.getOrganizationId() );
            target.setPersonNumber( source.getPersonNumber() );
            target.setSeatNumber( source.getSeatNumber() );
            target.setTerminalType( source.getTerminalType() );
            target.setRemarks( source.getRemarks() );
            return target;
        }
        return null;
    }

    /**
     * 关键数据变更记录转换
     *
     * @param source 关键数据变更记录PO
     * @return 关键数据变更记录BO
     */
    public static KeyDataChangeRecordBean transform(KeyDataChangeRecordEntity source) {
        if (null != source) {
            KeyDataChangeRecordBean target = new KeyDataChangeRecordBean();

            target.setId( source.getId() );
            target.setBusinessTableName(source.getBusinessTableName() );
            target.setBusinessDataId( source.getBusinessDataId() );
            target.setBusinessField( source.getBusinessField() );
            target.setChangeTime( source.getChangeTime()  );
            target.setOriginalDate( source.getOriginalDate() );
            target.setChangeDate( source.getChangeDate() );
            target.setOrganizationId( source.getOrganizationId() );
            target.setPersonpNumber( source.getPersonNumber() );
            target.setSeatNumber( source.getSeatNumber() );
            target.setTerminalType( source.getTerminalType() );
            target.setRemarks( source.getRemarks() );

            return target;
        }
        return null;
    }


    /**
     * 系统操作日志转换
     *
     * @param source 系统操作日志INPUT
     * @return 系统操作日志PO
     */
    public static AuditLogEntity transform(AuditLogSaveInputInfo source ,  Long currentSystemTime ) {
        if ( null != source ) {
            AuditLogEntity target = new AuditLogEntity();
            target.setOperateTime( currentSystemTime );
            target.setOperateType( source.getOperateType() );
            target.setOrganizationId( source.getOrganizationId() );
            target.setOrganizationName( source.getOrganizationName() );
            target.setOperateSeatNumber( source.getOperateSeatNumber() );
            target.setOperateSeatName( source.getOperateSeatName() );
            target.setAcceptancePersonNumber( source.getAcceptancePersonNumber() );
            target.setAcceptancePersonName( source.getAcceptancePersonName() );
            target.setIpAddress( source.getIpAddress() );
            target.setDesc( source.getDesc() );
            target.setRemarks( source.getRemarks() );
            target.setDocumentNumber(source.getDocumentNumber());
            target.setRelevantDocumentNumber(source.getRelevantDocumentNumber());
            return target;
        }
        return null;
    }



    /**
     * 系统操作日志转换
     *
     * @param source 系统操作日志INPUT
     * @return 系统操作日志PO
     */
    public static AuditLogBean transform(AuditLogEntity source) {
        if ( null != source ) {
            AuditLogBean target = new AuditLogBean();
            target.setId( source.getId() ) ;
            target.setOperateTime(  source.getOperateTime()  );
            target.setOperateType( source.getOperateType() );
            target.setOrganizationId( source.getOrganizationId() );
            target.setOrganizationName( source.getOrganizationName() );
            target.setOperateSeatNumber( source.getOperateSeatNumber() );
            target.setOperateSeatName( source.getOperateSeatName() );
            target.setAcceptancePersonNumber( source.getAcceptancePersonNumber() );
            target.setAcceptancePersonName( source.getAcceptancePersonName() );
            target.setIpAddress( source.getIpAddress() );
            target.setDesc( source.getDesc() );
            target.setRemarks( source.getRemarks() );
            target.setDocumentNumber(source.getDocumentNumber());
            target.setRelevantDocumentNumber(source.getRelevantDocumentNumber());
            if (!org.mx.StringUtils.isBlank(source.getOperateType())){
                BasicEnumNumberBean enumBean = BasicEnumNumberUtils.getBasicEnumBeanByCode(OperationTypeEnum.class, Integer.valueOf(source.getOperateType()));
                if (enumBean!=null){
                    target.setOperateTypeName(enumBean.getName());
                }
            }


            return target;
        }
        return null;
    }

    public static Boolean judgeChange(Long var1,Long var2){
        if (var1==null&&var2==null){
            return false;
        }else {
            if ((var1==null&&var2!=null)||(var1!=null&&var2==null)){
                return true;
            }else {
                return var1.equals(var2);
            }
        }
    }


}
