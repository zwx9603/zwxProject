package com.dscomm.iecs.accept.utils.transform;

import com.dscomm.iecs.accept.dal.po.VehicleStatusChangeCheckEntity;
import com.dscomm.iecs.accept.graphql.inputbean.VehicleStatusChangeCheckSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.VehicleStatusChangeCheckBean;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import org.mx.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 车辆审计对象转换工具
 */
public class AuditVehicleTransformUtil {


    public static  VehicleStatusChangeCheckEntity  transform( String oldVehicleStatusCode ,
                   VehicleStatusChangeCheckSaveInputInfo source    ) {

        if( source != null ){
            VehicleStatusChangeCheckEntity target = new VehicleStatusChangeCheckEntity() ;
            target.setId(source.getId());
            target.setAppliedOrganizationId(source.getAppliedOrganizationId());
            target.setAppliedVehicleId(source.getAppliedVehicleId());
            target.setOldVehicleStatusCode( oldVehicleStatusCode );
            target.setVehicleStatusCode(source.getVehicleStatusCode());
            target.setAppliedChangeDesc(source.getAppliedChangeDesc());
            target.setCheckStatus( source.getCheckStatus() );
            target.setCheckDesc(source.getCheckDesc());
            target.setRemarks(source.getRemarks());
            return  target ;
        }
        return  null  ;
    }



    public static  VehicleStatusChangeCheckBean  transform( VehicleStatusChangeCheckEntity  source , Map<String, String> organizationNameMap ,
                                                            Map<String, Map<String, String>>  dicsMap , EquipmentVehicleBean vehicleBean    ) {

        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }
        if( source != null ){
            VehicleStatusChangeCheckBean target = new VehicleStatusChangeCheckBean() ;
            target.setId(source.getId());
            target.setIncidentId(source.getIncidentId());
            target.setAppliedTime( source.getAppliedTime() );
            target.setAppliedOrganizationId(source.getAppliedOrganizationId());
            if (!StringUtils.isBlank(source.getAppliedOrganizationId())){
                target.setAppliedOrganizationName( organizationNameMap.get( source.getAppliedOrganizationId() ) );
            }

            target.setAppliedVehicleId(source.getAppliedVehicleId());

            if( vehicleBean != null ){
                target.setAppliedVehicleName( vehicleBean.getVehicleName() );
                target.setAppliedVehicleNumber( vehicleBean.getVehicleNumber() );
                target.setAppliedVehicleTypeCode( vehicleBean.getVehicleTypeCode() );
                target.setAppliedVehicleTypeName( vehicleBean.getVehicleTypeName() );
            }
            target.setOldVehicleStatusCode(  source.getOldVehicleStatusCode() );
            if (!StringUtils.isBlank(source.getOldVehicleStatusCode())&&dicsMap.get( "WLCLZT")!=null){
                target.setOldVehicleStatusName( dicsMap.get( "WLCLZT").get( source.getOldVehicleStatusCode()  )  );
            }

            target.setVehicleStatusCode(source.getVehicleStatusCode());
            if (!StringUtils.isBlank(source.getVehicleStatusCode())&&dicsMap.get( "WLCLZT")!=null){
                target.setVehicleStatusName( dicsMap.get( "WLCLZT").get( source.getVehicleStatusCode()  )  );
            }
            target.setAppliedChangeDesc(source.getAppliedChangeDesc());

            target.setCheckStatus( source.getCheckStatus() );
            target.setCheckTime( source.getCheckTime() );
            target.setCheckDesc(source.getCheckDesc());
            target.setCheckOrganizationId(source.getCheckOrganizationId());
            if (!StringUtils.isBlank(source.getCheckOrganizationId())){
                target.setCheckOrganizationName( organizationNameMap.get( source.getCheckOrganizationId() ) );//机构名称
            }

            target.setCheckPersonId(source.getCheckPersonId());
            target.setRemarks(source.getRemarks());
            return  target ;
        }
        return  null  ;
    }



}
