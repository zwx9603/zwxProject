package com.dscomm.iecs.accept.utils;

import com.dscomm.iecs.ext.incident.status.*;
import com.dscomm.iecs.ext.vehicle.status.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  判断 案件状态  车辆状态是否 可以修改前置条件工具
 */
public class JudgeIncidentVehicleStatusUtil {

    // 案件状态 与 前置案件状态关系
    private static Map<String,List<String> > incidentStatusMap = new HashMap<>() ;
    // 车辆出动状态 与 前置车辆出动状态关系
    private static Map<String,List<String> > combatVehicleStatusMap = new HashMap<>() ;

    static  {
        //案件状态 与 前置案件状态关系
        //立案
        List<String> ajla = new ArrayList<>();
        incidentStatusMap.put( INCIDENT_STATUS_LA.getCode(), ajla )  ;
        //通知
        List<String> ajtz = new ArrayList<>();
        ajtz.add(  INCIDENT_STATUS_LA.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_TZ.getCode(), ajtz )  ;
        // 出动
        List<String> ajcd = new ArrayList<>();
        ajcd.add(  INCIDENT_STATUS_LA.getCode() ) ;
        ajcd.add(  INCIDENT_STATUS_TZ.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_CD.getCode(), ajcd )  ;
        // 到场
        List<String> ajdc = new ArrayList<>();
        ajdc.add(  INCIDENT_STATUS_LA.getCode() ) ;
        ajdc.add(  INCIDENT_STATUS_TZ.getCode() ) ;
        ajdc.add(  INCIDENT_STATUS_CD.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_DC.getCode(), ajdc )  ;
        // 展开
        List<String> ajzk = new ArrayList<>();
        ajzk.add(  INCIDENT_STATUS_LA.getCode() ) ;
        ajzk.add(  INCIDENT_STATUS_TZ.getCode() ) ;
        ajzk.add(  INCIDENT_STATUS_CD.getCode() ) ;
        ajzk.add(  INCIDENT_STATUS_DC.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_ZK.getCode(), ajzk )  ;
        // 出水（ 开始作战 ）
        List<String> ajkszz = new ArrayList<>();
        ajkszz.add(  INCIDENT_STATUS_LA.getCode() ) ;
        ajkszz.add(  INCIDENT_STATUS_TZ.getCode() ) ;
        ajkszz.add(  INCIDENT_STATUS_CD.getCode() ) ;
        ajkszz.add(  INCIDENT_STATUS_DC.getCode() ) ;
        ajkszz.add(  INCIDENT_STATUS_ZK.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_KSZZ.getCode(), ajkszz )  ;
        // 控制火势
        List<String> ajkzhs = new ArrayList<>();
        ajkzhs.add(  INCIDENT_STATUS_LA.getCode() ) ;
        ajkzhs.add(  INCIDENT_STATUS_TZ.getCode() ) ;
        ajkzhs.add(  INCIDENT_STATUS_CD.getCode() ) ;
        ajkzhs.add(  INCIDENT_STATUS_DC.getCode() ) ;
        ajkzhs.add(  INCIDENT_STATUS_ZK.getCode() ) ;
        ajkzhs.add(  INCIDENT_STATUS_KSZZ.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_KZHS.getCode(), ajkzhs )  ;
        // 控制熄灭
        List<String> ajkzxm = new ArrayList<>();
        ajkzxm.add(  INCIDENT_STATUS_LA.getCode() ) ;
        ajkzxm.add(  INCIDENT_STATUS_TZ.getCode() ) ;
        ajkzxm.add(  INCIDENT_STATUS_CD.getCode() ) ;
        ajkzxm.add(  INCIDENT_STATUS_DC.getCode() ) ;
        ajkzxm.add(  INCIDENT_STATUS_ZK.getCode() ) ;
        ajkzxm.add(  INCIDENT_STATUS_KSZZ.getCode() ) ;
        ajkzxm.add(  INCIDENT_STATUS_KZHS.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_KZXM.getCode(), ajkzxm )  ;
        // 清理现场
        List<String> ajqlxc = new ArrayList<>();
        ajqlxc.add(  INCIDENT_STATUS_LA.getCode() ) ;
        ajqlxc.add(  INCIDENT_STATUS_TZ.getCode() ) ;
        ajqlxc.add(  INCIDENT_STATUS_CD.getCode() ) ;
        ajqlxc.add(  INCIDENT_STATUS_DC.getCode() ) ;
        ajqlxc.add(  INCIDENT_STATUS_ZK.getCode() ) ;
        ajqlxc.add(  INCIDENT_STATUS_KSZZ.getCode() ) ;
        ajqlxc.add(  INCIDENT_STATUS_KZHS.getCode() ) ;
        ajqlxc.add(  INCIDENT_STATUS_KZXM.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_QLXC.getCode(), ajqlxc )  ;
        // 返回（结束作战）
        List<String> ajfh = new ArrayList<>();
        ajfh.add(  INCIDENT_STATUS_LA.getCode() ) ;
        ajfh.add(  INCIDENT_STATUS_TZ.getCode() ) ;
        ajfh.add(  INCIDENT_STATUS_CD.getCode() ) ;
        ajfh.add(  INCIDENT_STATUS_DC.getCode() ) ;
        ajfh.add(  INCIDENT_STATUS_ZK.getCode() ) ;
        ajfh.add(  INCIDENT_STATUS_KSZZ.getCode() ) ;
        ajfh.add(  INCIDENT_STATUS_KZHS.getCode() ) ;
        ajfh.add(  INCIDENT_STATUS_KZXM.getCode() ) ;
        ajfh.add(  INCIDENT_STATUS_QLXC.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_FH.getCode(), ajfh )  ;
        // 中返
        List<String> ajzf = new ArrayList<>();
        ajzf.add(  INCIDENT_STATUS_LA.getCode() ) ;
        ajzf.add(  INCIDENT_STATUS_TZ.getCode() ) ;
        ajzf.add(  INCIDENT_STATUS_CD.getCode() ) ;
        ajzf.add(  INCIDENT_STATUS_DC.getCode() ) ;
        ajzf.add(  INCIDENT_STATUS_ZK.getCode() ) ;
        ajzf.add(  INCIDENT_STATUS_KSZZ.getCode() ) ;
        ajzf.add(  INCIDENT_STATUS_KZHS.getCode() ) ;
        ajzf.add(  INCIDENT_STATUS_KZXM.getCode() ) ;
        ajzf.add(  INCIDENT_STATUS_QLXC.getCode() ) ;
        ajzf.add(  INCIDENT_STATUS_FH.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_ZF.getCode(), ajzf )  ;
        // 归队
        List<String> ajfd = new ArrayList<>();
        ajfd.add(  INCIDENT_STATUS_LA.getCode() ) ;
        ajfd.add(  INCIDENT_STATUS_TZ.getCode() ) ;
        ajfd.add(  INCIDENT_STATUS_CD.getCode() ) ;
        ajfd.add(  INCIDENT_STATUS_DC.getCode() ) ;
        ajfd.add(  INCIDENT_STATUS_ZK.getCode() ) ;
        ajfd.add(  INCIDENT_STATUS_KSZZ.getCode() ) ;
        ajfd.add(  INCIDENT_STATUS_KZHS.getCode() ) ;
        ajfd.add(  INCIDENT_STATUS_KZXM.getCode() ) ;
        ajfd.add(  INCIDENT_STATUS_QLXC.getCode() ) ;
        ajfd.add(  INCIDENT_STATUS_FH.getCode() ) ;
        ajfd.add(  INCIDENT_STATUS_ZF.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_FD.getCode(), ajfd )  ;

        // 结案
        List<String> ajja = new ArrayList<>();
        ajja.add(  INCIDENT_STATUS_LA.getCode() ) ;
        ajja.add(  INCIDENT_STATUS_TZ.getCode() ) ;
        ajja.add(  INCIDENT_STATUS_CD.getCode() ) ;
        ajja.add(  INCIDENT_STATUS_DC.getCode() ) ;
        ajja.add(  INCIDENT_STATUS_ZK.getCode() ) ;
        ajja.add(  INCIDENT_STATUS_KSZZ.getCode() ) ;
        ajja.add(  INCIDENT_STATUS_KZHS.getCode() ) ;
        ajja.add(  INCIDENT_STATUS_KZXM.getCode() ) ;
        ajja.add(  INCIDENT_STATUS_QLXC.getCode() ) ;
        ajja.add(  INCIDENT_STATUS_FH.getCode() ) ;
        ajja.add(  INCIDENT_STATUS_ZF.getCode() ) ;
        ajja.add(  INCIDENT_STATUS_FD.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_JA.getCode(), ajja )  ;

        // 填报
        List<String> ajtb = new ArrayList<>();
        ajtb.add(  INCIDENT_STATUS_LA.getCode() ) ;
        ajtb.add(  INCIDENT_STATUS_TZ.getCode() ) ;
        ajtb.add(  INCIDENT_STATUS_CD.getCode() ) ;
        ajtb.add(  INCIDENT_STATUS_DC.getCode() ) ;
        ajtb.add(  INCIDENT_STATUS_ZK.getCode() ) ;
        ajtb.add(  INCIDENT_STATUS_KSZZ.getCode() ) ;
        ajtb.add(  INCIDENT_STATUS_KZHS.getCode() ) ;
        ajtb.add(  INCIDENT_STATUS_KZXM.getCode() ) ;
        ajtb.add(  INCIDENT_STATUS_QLXC.getCode() ) ;
        ajtb.add(  INCIDENT_STATUS_FH.getCode() ) ;
        ajtb.add(  INCIDENT_STATUS_ZF.getCode() ) ;
        ajtb.add(  INCIDENT_STATUS_FD.getCode() ) ;
        ajtb.add(  INCIDENT_STATUS_JA.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_TB.getCode(), ajtb )  ;

        // 复审
        List<String> ajfs = new ArrayList<>();
        ajfs.add(  INCIDENT_STATUS_TB.getCode() ) ;
        ajfs.add(  INCIDENT_STATUS_GD.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_FS.getCode(), ajfs )  ;

        // 归档
        List<String> ajgd = new ArrayList<>();
        ajgd.add(  INCIDENT_STATUS_LA.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_TZ.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_CD.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_DC.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_ZK.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_KSZZ.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_KZHS.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_KZXM.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_QLXC.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_FH.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_ZF.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_FD.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_JA.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_TB.getCode() ) ;
        ajgd.add(  INCIDENT_STATUS_FS.getCode() ) ;
        incidentStatusMap.put( INCIDENT_STATUS_GD.getCode(), ajgd )  ;


        //初始化 车辆出动状态 与 前置车辆出动状态关系
        // 通知
        List<String> tz = new ArrayList<>();
        combatVehicleStatusMap.put( VEHICLE_STATUS_TZ.getCode(), tz )  ;
        // 出动
        List<String> cd = new ArrayList<>();
        cd.add( VEHICLE_STATUS_TZ.getCode() ) ;
        combatVehicleStatusMap.put( VEHICLE_STATUS_CD.getCode(), cd )  ;
        // 途中
        List<String> cdtz = new ArrayList<>();
        cdtz.add( VEHICLE_STATUS_TZ.getCode() ) ;
        cdtz.add( VEHICLE_STATUS_CD.getCode() ) ;
        combatVehicleStatusMap.put( VEHICLE_STATUS_CDTZ.getCode(), cdtz )  ;
        // 到场
        List<String> cddc = new ArrayList<>();
        cddc.add( VEHICLE_STATUS_TZ.getCode() ) ;
        cddc.add( VEHICLE_STATUS_CD.getCode() ) ;
        cddc.add( VEHICLE_STATUS_CDTZ.getCode() ) ;
        combatVehicleStatusMap.put( VEHICLE_STATUS_CDDC.getCode(), cddc )  ;
        // 出水
        List<String> cdcs = new ArrayList<>();
        cdcs.add( VEHICLE_STATUS_TZ.getCode() ) ;
        cdcs.add( VEHICLE_STATUS_CD.getCode() ) ;
        cdcs.add( VEHICLE_STATUS_CDTZ.getCode() ) ;
        cdcs.add( VEHICLE_STATUS_CDDC.getCode() ) ;
        combatVehicleStatusMap.put( VEHICLE_STATUS_CDCS.getCode(), cdcs )  ;
        // 停水
        List<String> cdts = new ArrayList<>();
        cdts.add( VEHICLE_STATUS_TZ.getCode() ) ;
        cdts.add( VEHICLE_STATUS_CD.getCode() ) ;
        cdts.add( VEHICLE_STATUS_CDTZ.getCode() ) ;
        cdts.add( VEHICLE_STATUS_CDDC.getCode() ) ;
        cdts.add( VEHICLE_STATUS_CDCS.getCode() ) ;
        combatVehicleStatusMap.put( VEHICLE_STATUS_CDTS.getCode(), cdts )  ;
        // 返回
        List<String> cdfh = new ArrayList<>();
        cdfh.add( VEHICLE_STATUS_TZ.getCode() ) ;
        cdfh.add( VEHICLE_STATUS_CD.getCode() ) ;
        cdfh.add( VEHICLE_STATUS_CDTZ.getCode() ) ;
        cdfh.add( VEHICLE_STATUS_CDDC.getCode() ) ;
        cdfh.add( VEHICLE_STATUS_CDCS.getCode() ) ;
        cdfh.add( VEHICLE_STATUS_CDTS.getCode() ) ;
        combatVehicleStatusMap.put( VEHICLE_STATUS_CDFH.getCode(), cdfh )  ;
        // 中返
        List<String> cdztfd = new ArrayList<>();
        cdztfd.add( VEHICLE_STATUS_TZ.getCode() ) ;
        cdztfd.add( VEHICLE_STATUS_CD.getCode() ) ;
        cdztfd.add( VEHICLE_STATUS_CDTZ.getCode() ) ;
        cdztfd.add( VEHICLE_STATUS_CDDC.getCode() ) ;
        cdztfd.add( VEHICLE_STATUS_CDCS.getCode() ) ;
        cdztfd.add( VEHICLE_STATUS_CDTS.getCode() ) ;
        cdztfd.add( VEHICLE_STATUS_CDFH.getCode() ) ;
        combatVehicleStatusMap.put( VEHICLE_STATUS_CDZTFD.getCode(), cdztfd )  ;
        // 返队
        List<String> cdfd = new ArrayList<>();
        cdfd.add( VEHICLE_STATUS_TZ.getCode() ) ;
        cdfd.add( VEHICLE_STATUS_CD.getCode() ) ;
        cdfd.add( VEHICLE_STATUS_CDTZ.getCode() ) ;
        cdfd.add( VEHICLE_STATUS_CDDC.getCode() ) ;
        cdfd.add( VEHICLE_STATUS_CDCS.getCode() ) ;
        cdfd.add( VEHICLE_STATUS_CDTS.getCode() ) ;
        cdfd.add( VEHICLE_STATUS_CDFH.getCode() ) ;
        cdfd.add( VEHICLE_STATUS_CDZTFD.getCode() ) ;
        combatVehicleStatusMap.put( VEHICLE_STATUS_CDFD.getCode(), cdfd )  ;
    }


    // 判断 变更案件状态代码 是否影响 现有案件状态代码
    public  static  Boolean JudgeIncidentStatus(  String incidentStatusCode  , String  changeRuleIncidentCode     ){
        Boolean res = false ;
        // 根据 变更案件状态代码 获得 前置案件状态代码
        List<String> incidentStatusList = incidentStatusMap.get( changeRuleIncidentCode ) ;
        if( incidentStatusList != null && incidentStatusList.size() > 0  ){
            res = incidentStatusList.contains( incidentStatusCode ) ;
        }
        return  res ;
    }


    // 判断是否 车辆状态 是否可以符合前置车辆状态
    public static Boolean JudgeCombatVehicleStatus(  String combatVehicleStatusCode  , String  newVehicleStatusCode   ){
        Boolean res = false ;
        // 根据 车辆变更车辆状态 获得 前置车辆状态信息
        List<String> combatVehicleStatusList = combatVehicleStatusMap.get( newVehicleStatusCode ) ;
        if( combatVehicleStatusList != null && combatVehicleStatusList.size() > 0  ){
            res = combatVehicleStatusList.contains( combatVehicleStatusCode ) ;
        }
        return  res ;
    }


}
