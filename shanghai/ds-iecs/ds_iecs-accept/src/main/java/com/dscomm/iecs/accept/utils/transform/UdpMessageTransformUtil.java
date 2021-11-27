package com.dscomm.iecs.accept.utils.transform;

import com.dscomm.iecs.accept.graphql.inputbean.outside.OutsideInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.restful.vo.UdpVO.*;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UdpMessageTransformUtil {


    public static UdpPhoneContentVO transfrom (Environment env , TelephoneBean source, String sender) throws  Exception {

        UdpPhoneContentVO contentVO = new UdpPhoneContentVO();
        if (source != null){
            if(  Strings.isNotBlank(   source.getCallingNumber()  ) ) {
                contentVO.setDHHM(  source.getCallingNumber()  ) ;
            }else{
//                contentVO.setDHHM( source.getAlarmPersonPhone()  ) ;
                contentVO.setDHHM( "" ) ;
            }
            contentVO.setYHXM(  Strings.isBlank(  source.getAlarmPersonName()  ) ? "" :  source.getAlarmPersonName()  );
            contentVO.setZJDZ( Strings.isBlank(  source.getInstalledAddress()  ) ? "" :  source.getInstalledAddress() );
            contentVO.setHRSJ(DateHandleUtils.getDateyyyyMMddHHmmss( env.getProperty("timeZone")  , source.getRingingTime() ) );
            contentVO.setX( Strings.isBlank(  source.getLongitude()  ) ? "0.0000" :  source.getLongitude()  );
            contentVO.setY( Strings.isBlank(  source.getLatitude()  ) ? "0.0000" :  source.getLatitude() );
            contentVO.setBJHM( Strings.isBlank(  source.getCalledNumber()  ) ? "" :  source.getCalledNumber() );
            contentVO.setZXBH( Strings.isBlank(  source.getSeatNumber()  ) ? "" :  source.getSeatNumber() );
        }
        return contentVO ;
    }

    public static UdpIncidentContentVO transfrom( Environment env ,  IncidentBean source  , String sender) throws  Exception {
        UdpIncidentContentVO contentVO = new UdpIncidentContentVO();
        if (source != null){
            contentVO.setZQBH( source.getIncidentNumber() );
            if(  Strings.isNotBlank(  source.getAlarmPhone()  ) ) {
                contentVO.setDHHM( source.getAlarmPhone()  ) ;
            }else{
//                contentVO.setDHHM( source.getAlarmPersonPhone()  ) ;
                contentVO.setDHHM( ""  ) ;
            }
            contentVO.setYHXM(  Strings.isBlank(  source.getAlarmPersonName() ) ? "" :  source.getAlarmPersonName() );
            contentVO.setZJDZ( Strings.isBlank(  source.getAlarmAddress() ) ? "" :  source.getAlarmAddress()  );
            if(  source.getAlarmStartTime() != null && source.getAlarmStartTime() != 0 ){
                contentVO.setHRSJ(   DateHandleUtils.getDateyyyyMMddHHmmss( env.getProperty("timeZone") , source.getAlarmStartTime() )  ) ;
            }
            contentVO.setHZDZ( Strings.isBlank(  source.getCrimeAddress()) ? "" :  source.getCrimeAddress()  );
            contentVO.setHZLX( Strings.isBlank(  source.getIncidentTypeCode()) ? "" :  source.getIncidentTypeCode().substring(0,1) );
            contentVO.setRSWZ(   ""   );
            contentVO.setZHDJ( Strings.isBlank(  source.getIncidentLevelCode()) ? "" :  source.getIncidentLevelCode() );
            contentVO.setJZLX( Strings.isBlank(  source.getBuildingStructureCode()) ? "0" :  source.getBuildingStructureCode() );
            contentVO.setQHLC( Strings.isBlank(  source.getBurningFloor()) ? "0" :  source.getBurningFloor() );
            String sfrybk = "0" ;
            if( Strings.isNotBlank( source.getTrappedCount() ) ){
                if( 0 < Integer.parseInt( source.getTrappedCount() ) ){
                    sfrybk = "1" ;
                }
            }
            contentVO.setSFRYBK(  sfrybk  );
            contentVO.setX( Strings.isBlank(  source.getLongitude() ) ? "0.0000" :  source.getLongitude()   );
            contentVO.setY( Strings.isBlank(  source.getLatitude() ) ? "0.0000" :  source.getLatitude()  );
            contentVO.setJYJQ( Strings.isBlank(  source.getIncidentDescribe() ) ? "" : source.getIncidentDescribe() );
            contentVO.setXZQH( source.getDistrictCode() );
        }
        return contentVO ;
    }



    public static UdpHandle119ContentVO transfrom( Environment env , HandleBean source,  IncidentBean incidentSource ,
                                                   List<UnTrafficAlarmBean> unTrafficAlarmBeans ,
                                                   String sender)throws  Exception {
        UdpHandle119ContentVO contentVO = new UdpHandle119ContentVO();
        List<UdpCar119VO> car119VOS = new ArrayList<>();
        if (source != null && source.getHandleOrganizationBean() != null && source.getHandleOrganizationBean().size()>0){
            contentVO.setZQBH( incidentSource.getIncidentNumber() );
            contentVO.setCJBH( source.getHandleNumber() );
            UnTrafficAlarmBean unTrafficAlarmBean = null ;
            if( unTrafficAlarmBeans != null && unTrafficAlarmBeans.size() > 0 ){
                 unTrafficAlarmBean = unTrafficAlarmBeans.get(0) ;
            }
            contentVO.setJJDBH110( unTrafficAlarmBean == null ? "" : unTrafficAlarmBean.getOriginalIncidentNumber()  );

            contentVO.setCJSJ(    DateHandleUtils.getDateyyyyMMddHHmmss( env.getProperty("timeZone") , source.getHandleStartTime() )   );
            contentVO.setXZQH( incidentSource.getDistrictCode() );

            List<Map<HandleOrganizationBean,List<HandleOrganizationVehicleBean>>> vehicleBeans = new ArrayList<>();
            for (HandleOrganizationBean handleOrganizationBean:source.getHandleOrganizationBean()){
                if (handleOrganizationBean.getHandleOrganizationVehicleBean() != null && handleOrganizationBean.getHandleOrganizationVehicleBean().size()>0){
                    Map<HandleOrganizationBean,List<HandleOrganizationVehicleBean>> map = new HashMap<>();
                    map.put(handleOrganizationBean,handleOrganizationBean.getHandleOrganizationVehicleBean());
                    vehicleBeans.add(map);
                }
            }
            if (vehicleBeans != null && vehicleBeans.size()>0){
                for (Map<HandleOrganizationBean,List<HandleOrganizationVehicleBean>> orgVehicleMap:vehicleBeans){
                    for (HandleOrganizationBean key : orgVehicleMap.keySet()){
                        for (HandleOrganizationVehicleBean vehicleBean:orgVehicleMap.get(key)){
                            UdpCar119VO carVO = new UdpCar119VO();
                            carVO.setCLID(vehicleBean.getVehicleId());
                            carVO.setCLMC(vehicleBean.getVehicleName());
                            carVO.setCPH(vehicleBean.getVehicleNumber());
                            carVO.setCLLX(vehicleBean.getVehicleTypeName());
                            carVO.setCLLXBM(vehicleBean.getVehicleTypeCode());
                            carVO.setXFJG(key.getOrganizationName());
                            carVO.setXFJGBM(key.getOrganizationId());
                            car119VOS.add(carVO);
                        }

                    }
                }
            }
            contentVO.setCJDW(car119VOS);

        }
        return contentVO ;
    }

    public static UdpFeedBackContentVO transform( Environment env , AccidentBean source,IncidentBean incidentSource,
                                                  List<UnTrafficAlarmBean> unTrafficAlarmBeans ,
                                                  String sender)throws  Exception {
        UdpFeedBackContentVO contentVO = new UdpFeedBackContentVO();
        if (source != null){
            contentVO.setZQBH(  incidentSource.getIncidentNumber() );
            if(  Strings.isNotBlank(  incidentSource.getAlarmPhone()  ) ) {
                contentVO.setDHHM( incidentSource.getAlarmPhone()  ) ;
            }else{
//                contentVO.setDHHM( incidentSource.getAlarmPersonPhone()  ) ;
                contentVO.setDHHM( ""  ) ;
            }
            contentVO.setYHXM( Strings.isBlank( incidentSource.getAlarmPersonName()) ? "" : incidentSource.getAlarmPersonName()  );
            contentVO.setZJDZ( Strings.isBlank( incidentSource.getIncidentDescribe()) ? "" : incidentSource.getIncidentDescribe()  );
            if(  incidentSource.getAlarmStartTime() != null && incidentSource.getAlarmStartTime() != 0 ){
                contentVO.setHRSJ(  DateHandleUtils.getDateyyyyMMddHHmmss( env.getProperty("timeZone") , incidentSource.getAlarmStartTime() )    );
            }
            contentVO.setHZDZ(  Strings.isBlank( source.getActualCrimeAddress()) ? "" : source.getActualCrimeAddress()  );
            contentVO.setHZLX(  Strings.isBlank( incidentSource.getIncidentTypeCode()) ? "" : incidentSource.getIncidentTypeCode().substring(0,1)   );
            contentVO.setZHDJ(   Strings.isBlank(  source.getIncidentLevelCode()) ? "" :  source.getIncidentLevelCode()  );
            contentVO.setJZLX(     Strings.isBlank(  source.getBuildingStructure()) ? "" :  source.getBuildingStructure()   );
            contentVO.setQHLC(      Strings.isBlank(  source.getBurningFloor()) ? "0" :  source.getBurningFloor()    );
            String sfrybk = "0" ;
            if( Strings.isNotBlank( source.getTrappedPersonNum() ) ){
                if( 0 < Integer.parseInt( source.getTrappedPersonNum() ) ){
                    sfrybk = "1" ;
                }
            }
            contentVO.setSFRYBK(  sfrybk  );
            contentVO.setX( Strings.isBlank(  incidentSource.getLongitude()  ) ? "0.0000" :  incidentSource.getLongitude()  );
            contentVO.setY( Strings.isBlank(  incidentSource.getLatitude()  ) ? "0.0000" :  incidentSource.getLatitude() );
            if( incidentSource.getDispatchTime() != null && incidentSource.getDispatchTime() != 0 ){
                contentVO.setCDSJ(  DateHandleUtils.getDateyyyyMMddHHmmss( env.getProperty("timeZone") , incidentSource.getDispatchTime() )
                );
            }
            if(  incidentSource.getArriveTime() != null && incidentSource.getArriveTime() != 0 ){
                contentVO.setDCSJ(
                        DateHandleUtils.getDateyyyyMMddHHmmss( env.getProperty("timeZone") , incidentSource.getArriveTime() )
                );
            }
            if(  incidentSource.getIncidentEndTime() != null && incidentSource.getIncidentEndTime() != 0  ){
                contentVO.setJSSJ(
                        DateHandleUtils.getDateyyyyMMddHHmmss( env.getProperty("timeZone") , incidentSource.getIncidentEndTime() )
                );
            }
            contentVO.setRSMJ( Strings.isBlank( source.getBuildingArea()) ? "0" : source.getBuildingArea() );
            contentVO.setRSWZ(  Strings.isBlank( source.getBurningMaterial()) ? "" : source.getBurningMaterial()    );
            contentVO.setSSRS( Strings.isBlank( source.getInjuredNum() ) ? "0" : source.getInjuredNum() );
            contentVO.setSWRS( Strings.isBlank( source.getDeathNum() ) ? "0" : source.getDeathNum()  );
            UnTrafficAlarmBean unTrafficAlarmBean = null ;
            if( unTrafficAlarmBeans != null && unTrafficAlarmBeans.size() > 0 ){
                unTrafficAlarmBean = unTrafficAlarmBeans.get(0) ;
            }
            contentVO.setJJDBH110( unTrafficAlarmBean == null ? "" : unTrafficAlarmBean.getOriginalIncidentNumber()  );
            contentVO.setXZQH( incidentSource.getDistrictCode() );




        }
        return contentVO ;
    }

    public static UdpHelpContentVO transfrom( Environment env , OutsideInputInfo queryBean , IncidentBean incidentSource, UserInfo userInfo , String sender)throws  Exception {
        UdpHelpContentVO contentVO = new UdpHelpContentVO();
        if (queryBean != null && incidentSource != null){
            contentVO.setZQBH( incidentSource.getIncidentNumber() );
            contentVO.setXZNR( Strings.isBlank( incidentSource.getIncidentDescribe()) ? "" : incidentSource.getIncidentDescribe() );
            contentVO.setQQR(userInfo.getAccount());
            contentVO.setQQZX(userInfo.getAgentNum());
            contentVO.setQQSJ(
                    DateHandleUtils.getDateyyyyMMddHHmmss( env.getProperty("timeZone") ,  queryBean.getOutsideTime() )
                     );
            contentVO.setJJDBH("");
            contentVO.setXZQH( incidentSource.getDistrictCode() );
        }
        return contentVO ;
    }

}
