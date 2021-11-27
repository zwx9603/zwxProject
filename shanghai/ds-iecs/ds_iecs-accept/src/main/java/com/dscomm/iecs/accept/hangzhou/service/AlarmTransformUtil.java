package com.dscomm.iecs.accept.hangzhou.service;

import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.bean.dahua.alarm.DhAlarmBean;
import com.dscomm.iecs.accept.service.bean.dahua.alarm.JQDX;
import com.dscomm.iecs.accept.service.bean.dahua.alarm.JQZT;
import com.dscomm.iecs.accept.service.bean.dahua.alarm.XCZHR;
import com.dscomm.iecs.accept.service.bean.dahua.dispatch.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AlarmTransformUtil {


    public static synchronized DhAlarmBean transforms(IncidentBean source) {
        if (Objects.nonNull(source)) {
            DhAlarmBean target = new DhAlarmBean();

            target.setBJ_TYWYSBM(source.getId());//报警_通用唯一识别码
            target.setDDMC(source.getAlarmAddress()); // 地点名称
            target.setJQ_TYWYSBM(source.getIdCode());// 警情_通用唯一识别码
            target.setJQDJDM(source.getIncidentLevelCode()); // 警情等级代码
            target.setJQFLYDM(source.getIncidentTypeCode()); //警情分类与代码
            target.setMC(source.getTitle()); // 名称

            JQDX jqdx = new JQDX();
            jqdx.setJQ_JYQK(source.getWhetherImportant().toString());  // 警情情况 //重要警情标识 0非重要 1重要 默认0非重要
            jqdx.setDQJD(source.getLongitude());  // 地球经度
            jqdx.setDQWD(source.getLatitude()); // 地球纬度
            jqdx.setJQDX_JQDXLBDM(source.getKeyUnitTypeCode());  // 警情对象类别代码
            jqdx.setJQBSLBDM(source.getWhetherImportant().toString()); // 警情标识类别代码 //重要警情标识 0非重要 1重要 默认0非重要
            jqdx.setBJDH(source.getAlarmPhone());    // 报警电话
            jqdx.setJQDX_MC(source.getIncidentLabelName()); // 名称
            jqdx.setBJFSLBDM(null); //
            jqdx.setJQDX_JYQK(source.getWhetherImportant().toString()); // 简要情况 //重要警情标识 0非重要 1重要 默认0非重要
            jqdx.setJQDX_TYWYSBM(source.getIdCode()); //  通用唯一识别码
            jqdx.setLC(source.getBurningFloor());  // 楼层
            jqdx.setBJSJ(null != source.getAlarmStartTime() ? new Date(source.getAlarmStartTime()) : null); // 报警时间
            jqdx.setHSKZSJ(null != source.getFireControlTime() ? new Date(source.getFireControlTime()) : null);  //  火势控制时间
            jqdx.setJBPMSJ(null != source.getExtinguishTime() ? new Date(source.getExtinguishTime()) : null); // 基本扑灭时间
            jqdx.setJSMLSJ(null != source.getReceiveTime() ? new Date(source.getReceiveTime()) : null); // 接受命令时间
            jqdx.setCDSJ(null != source.getDispatchTime() ? new Date(source.getDispatchTime()) : null); // 出动时间
            jqdx.setZTFHSJ(null != source.getHalfwayReturnTime() ? new Date(source.getHalfwayReturnTime()) : null); // 中途返回时间
            jqdx.setDCSJ(null != source.getArriveTime() ? new Date(source.getArriveTime()) : null); // 到场时间
//            jqdx.setJASJ();
            jqdx.setZDZKSJ(null != source.getFightTime() ? new Date(source.getFightTime()) : null);// 战斗展开时间
            jqdx.setLASJ(null != source.getReceiveEndTime() ? new Date(source.getReceiveEndTime()) : null); // 立案时间
//            jqdx.setTSSJ(new Date(source.getReceiveTime()));  // 停水时间
//            jqdx.setCSSJ(new Date(source.getReceiveTime()));  // 出水时间
            jqdx.setGDSJ(null != source.getReturnTime() ? new Date(source.getReturnTime()) : null); // 归队时间


            target.setJQDX(jqdx); //警情对象 { //警情状态类别代码,// 日期时间}
            JQZT jqzt = new JQZT(source.getIncidentStatusCode(), null != source.getAlarmStartTime() ? new Date(source.getRegisterIncidentTime()) : null);
            target.setJQZT(jqzt); // 警情状态

            XCZHR xczhr = new XCZHR();
//            xczhr.setXCZHR_XM(); // 姓名
            xczhr.setXFJYJG_TYWYSBM(source.getRegisterOrganizationId()); //消防救援机构_通用唯一识别码
            xczhr.setXZQHDM(source.getDistrictCode()); // 行政区划代码
//            xczhr.setXCZHR_LXDH(); // 联系电话
//            xczhr.setXCZHR_XFGWLBDM(); //消防岗位类别代码
            xczhr.setJZJGLXDM(source.getBuildingStructureCode()); // 建筑结构类型代码
            xczhr.setYWQKDM(source.getSmogSituationCode()); // 烟雾情况代码
            xczhr.setXCZHR_RQSJ(null != source.getAlarmStartTime() ? new Date(source.getRegisterIncidentTime()) : null);// 日期时间
            target.setXCZHR(xczhr); //现场指挥人
            return target;
        }
        return null;
    }

    public static synchronized DispatchBean transforms(   IncidentBean incidentBean ,  HandleBean source ) {
        if (source != null) {
            DispatchBean target = new DispatchBean();
            target.setJQCJ_TYWYSBM(source.getId());  //警情处警_通用唯一识别码
            target.setJQ_TYWYSBM(source.getIncidentId()); //警情_通用唯一识别码
            target.setDPZJ_RS(null != source.getHandleExpertNum() ? source.getHandleExpertNum().toString() : null); //调派专家_人数
            if (!CollectionUtils.isEmpty(source.getHandleExpertBean())) {
                for (HandleExpertBean expertBean : source.getHandleExpertBean()) {
                    target.setDPZJ_XM(expertBean.getExpertName()); //调派专家_姓名
                    break;
                }
            }

            //构建调派车辆 调派装备信息
            List<HandleOrganizationBean> handleOrganizationBeans = source.getHandleOrganizationBean() ;
            List<HandleOrganizationVehicleBean> vehicleBeanList = new ArrayList<>() ;
            List<HandleOrganizationEquipmentBean> equipmentBeanList = new ArrayList<>() ;
            if( handleOrganizationBeans != null && handleOrganizationBeans.size() > 0 ){
                for( HandleOrganizationBean handleOrganizationBean :  handleOrganizationBeans){
                    List<HandleOrganizationVehicleBean>  handleOrganizationVehicleBean = handleOrganizationBean.getHandleOrganizationVehicleBean() ;
                    if( handleOrganizationVehicleBean != null && handleOrganizationVehicleBean.size() > 0 ){
                        vehicleBeanList.addAll( handleOrganizationVehicleBean ) ;
                    }
                    List<HandleOrganizationEquipmentBean> handleOrganizationEquipmentBean = handleOrganizationBean.getHandleOrganizationEquipmentBean() ;
                    if( handleOrganizationEquipmentBean != null && handleOrganizationEquipmentBean.size() > 0 ){
                        equipmentBeanList.addAll( handleOrganizationEquipmentBean ) ;
                    }
                }
            }

            target.setDPRY_RS(null != source.getHandlePersonNum() ? source.getHandlePersonNum().toString() : null); //调派人员_人数
            target.setDPRY_XM(null); //调派人员_姓名
            target.setDPCL_SL(null != source.getDispatchVehicleNum() ? source.getDispatchVehicleNum().toString() : null); //调派车辆_数量
            target.setDPZB_SL(null != source.getDispatchEquipmentNum() ? source.getDispatchEquipmentNum().toString() : null); //调派装备_数量 dispatchEquipmentNum
            target.setJQDPXSLBDM(source.getHandleTypeCode()); //警情调派形式类别代码
            target.setDPZL_WJNR(source.getHandleContent()); //调派指令_文件内容
            target.setFK_RQSJ(null != source.getFeedbackTime() ? new Date(source.getFeedbackTime()) : null); //反馈_日期时间
            target.setFS_RQSJ(null != source.getHandleEndTime() ? new Date(source.getHandleEndTime()) : null); //发送 日期
            target.setJS_RQSJ(null); // 接收日期
            target.setXFCL_CDSJ(null != incidentBean.getDispatchTime() ? new Date(incidentBean.getDispatchTime()) : null); //  出动时间
//            target.setXFCL_CSSJ(null != source.getFeedbackTime() ? new Date(source.getFeedbackTime()) : null); // 出水时间
//            target.setXFCL_TSSJ(null != source.getFeedbackTime() ? new Date(source.getFeedbackTime()) : null); // 停水时间
            target.setXFCL_DDSJ(null != incidentBean.getArriveTime() ? new Date(incidentBean.getArriveTime()) : null); // 到达时间
            target.setXFCL_GDSJ(null != incidentBean.getReturnTime() ? new Date(incidentBean.getReturnTime()) : null); // 归队时间
            target.setXFCL_ZTFHSJ(null != incidentBean.getHalfwayReturnTime() ? new Date(incidentBean.getHalfwayReturnTime()) : null); //中途返回时间
            target.setFSDW(new FSDW("", "")); // 发送单位

            // 接收单位
            if (!CollectionUtils.isEmpty(source.getHandleOrganizationBean())) {
                List<Jsdw> jsdwList = new ArrayList<>();
                for (HandleOrganizationBean organizationBean : source.getHandleOrganizationBean()) {
                    jsdwList.add(new Jsdw(organizationBean.getId(), organizationBean.getOrganizationName()));
                    break;
                }
                target.setJSDW(jsdwList);
            }


            // 消防车辆
            if (!CollectionUtils.isEmpty(handleOrganizationBeans)) {
                List<Xfcl> xfclList = new ArrayList<>();
                for (HandleOrganizationVehicleBean handleVehicle   : vehicleBeanList) {
                    Xfcl xfcl = new Xfcl();
                    xfcl.setXFCL_CLLXDM(handleVehicle.getVehicleTypeCode()); //车辆类型代码
                    xfcl.setXFCL_JDCHPHM(handleVehicle.getVehicleNumber());  //机动车号牌号码
                    xfcl.setXFCL_TYWYSBM(handleVehicle.getId()); //通用唯一识别码
                    xfcl.setOrgCode(handleVehicle.getOrganizationId()); //所属单位编号（需要对方补充对应字段）
                    xfcl.setXFCL_CDSJ(null != handleVehicle.getSetOutTime() ? new Date(handleVehicle.getSetOutTime()) : null); //  出动时间
                    xfcl.setXFCL_CSSJ(null != handleVehicle.getSendWaterTime() ? new Date(handleVehicle.getSendWaterTime()) : null); // 出水时间
                    xfcl.setXFCL_TSSJ(null != handleVehicle.getStopWaterTime() ? new Date(handleVehicle.getStopWaterTime()) : null); // 停水时间
                    xfcl.setXFCL_DDSJ(null != handleVehicle.getArriveTime() ? new Date(handleVehicle.getArriveTime()) : null); // 到达时间
                    xfcl.setXFCL_GDSJ(null != handleVehicle.getReturnTime() ? new Date(handleVehicle.getReturnTime()) : null); // 归队时间
                    xfcl.setXFCL_ZTFHSJ(null != handleVehicle.getHalfwayReturnTime() ? new Date(handleVehicle.getHalfwayReturnTime()) : null); //中途返回时间
                    xfclList.add(xfcl);
                }
                target.setXFCL(xfclList);
            }
            //消防装备
            if (!CollectionUtils.isEmpty(equipmentBeanList)) {
                List<Xfzb> xfzbList = new ArrayList<>();
                for (HandleOrganizationEquipmentBean handleEquipmentBean : equipmentBeanList) {
                    Xfzb xfzb = new Xfzb();
                    xfzb.setXFZB_TYWYSBM(handleEquipmentBean.getEquipmentId()); //通用唯一识别码
                    xfzb.setXFZB_MC(handleEquipmentBean.getEquipmentName());  //名称
                    xfzb.setXFZB_XFZBLXDM(handleEquipmentBean.getEquipmentTypeCode());  //消防装备类型代码
                    xfzb.setXFZB_KSSJ(null != handleEquipmentBean.getDispatchStartTime() ? new Date(handleEquipmentBean.getDispatchStartTime()) : null); //开始时间
                    xfzb.setXFZB_JSSJ(null != handleEquipmentBean.getDispatchEndTime() ? new Date(handleEquipmentBean.getDispatchEndTime()) : null); //结束时间
                    xfzbList.add(xfzb);
                }
                target.setXFZB(xfzbList); // 消防装备
            }


            return target;
        }
        return null;
    }


}
