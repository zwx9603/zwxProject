package com.dscomm.iecs.out.utils;

import com.dscomm.iecs.accept.dal.po.AcceptanceEntity;
import com.dscomm.iecs.accept.dal.po.HandleEntity;
import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import com.dscomm.iecs.accept.dal.po.SoundRecordEntity;
import com.dscomm.iecs.accept.graphql.typebean.HandleOrganizationBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleOrganizationVehicleBean;
import com.dscomm.iecs.accept.graphql.typebean.ParticipantFeedbackBean;
import com.dscomm.iecs.accept.graphql.typebean.TelephoneBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleEntity;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleLoadBean;
import com.dscomm.iecs.out.graphql.typebean.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 16:18
 * @Describe
 */
public class TransformUtil {
    public static final Map<String,Integer> jqcjdpztlbdm=new ConcurrentHashMap<>();
    static {
        jqcjdpztlbdm.put("STATUS_SIGNED",1);
        jqcjdpztlbdm.put("STATUS_GIVEN",0);
    }


    /**
     * 报警记录
     * @param source
     * @return
     * @throws ParseException
     */
    public static AlarmRecordBean transform(AcceptanceEntity source, LogService logService, Logger logger) throws ParseException {
        if (null != source) {
            AlarmRecordBean target = new AlarmRecordBean();
            target.setBjTywysbm(source.getIdCode());//报警_通用唯一识别码
            target.setBjdh(source.getAlarmPhone());//报警电话
            target.setBjfslbdm(source.getAlarmModeCode());//报警方式类别代码
            target.setBz(source.getRemarks());//备注
           // TimeStamp ts = new TimeStamp(source.getCreatedTime());
            target.setCreateTime(TimeUtils.transformaStrTime(source.getCreatedTime()));//创建时间（string类型的datetime）
            target.setDeptCode(null);
            try {
                if (Strings.isNotBlank(source.getLongitude())) {
                    target.setDqjd(NumberFormat.getInstance().parse(source.getLongitude()));
                }
            }catch (Exception e){
                logService.infoLog(logger, "service", "getAlarmRecordListByTime", String.format("The error id of table  JCJ_SLD is : %s  and column is DQJD ", source.getId()));
                //System.out.println("The error id of table  JCJ_SLD is : " +source.getId() + "and column is DQJD ");
            }
            try {
                if (Strings.isNotBlank(source.getLatitude())) {
                    target.setDqwd(NumberFormat.getInstance().parse(source.getLatitude()));
                }
            }catch (Exception e){
               // System.out.println("The error id of table  JCJ_SLD is : " +source.getId() + "and column is DQWD");
                logService.infoLog(logger, "service", "getAlarmRecordListByTime", String.format("The error id of table  JCJ_SLD is : %s and column is DQWD", source.getId()));
            }
            target.setDzmc(source.getCrimeAddress());//地址名称
            target.setRqsj(TimeUtils.transformaStrTime(source.getAcceptanceTime()));//日期时间（string类型的datetime）
            target.setUid(source.getId());
            target.setThjlTywysbm(source.getTelephoneId());//通话记录_通用唯一识别码
            target.setUpdateTime(TimeUtils.transformaStrTime(source.getUpdatedTime()));
            target.setXfryTywysbm(source.getAcceptancePersonId());//消防人员 通用唯一识别码
            target.setXfryXm(source.getAcceptancePersonName());//消防人员姓名
            target.setXzqhdm(source.getDistrictCode());//行政区划代码

            return target;
        }
        return null;
    }

    /**
     * 警情列表转换
     * @param source
     * @return
     */
    public static IncidentOutBean transform(IncidentEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap, LogService logService, Logger logger) throws ParseException {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            IncidentOutBean target = new IncidentOutBean();
            target.setBjTywysbm(source.getAcceptanceId());//报警_通用唯一识别码
            target.setBjdh(source.getAlarmPhone());//报警电话
            target.setBjfslbdm(source.getAlarmModeCode());//报警方式类别代码

            //TimeStamp ts = new TimeStamp(source.getAlarmStartTime());
            target.setBjsj(TimeUtils.transformaStrTime(source.getAlarmStartTime()));//报警时间string(date-time)
            target.setCdsj(TimeUtils.transformaStrTime(source.getDispatchTime()));//出动时间
            target.setCssj(TimeUtils.transformaStrTime(source.getFightStartTime()));//出水时间
            target.setDcsj(TimeUtils.transformaStrTime(source.getArriveTime()));//到场时间
            target.setDdmc(source.getCrimeAddress());//地点名称

            try {
                if (Strings.isNotBlank(source.getLongitude())) {
                    target.setDqjd(NumberFormat.getInstance().parse(source.getLongitude()));
                }
            }catch (Exception e){
                logService.infoLog(logger, "service", "getIncidentListByTime", String.format("The error id of table JCJ_AJXX is: %s and column is DQJD", source.getId()));
                //System.out.println("The error id of table JCJ_AJXX is: "+source.getId() + " and column is DQJD");
            }
            try {
                if (Strings.isNotBlank(source.getLatitude())) {
                    target.setDqwd(NumberFormat.getInstance().parse(source.getLatitude()));
                }
            }catch (Exception e){
                logService.infoLog(logger, "service", "getIncidentListByTime", String.format("The error id of table JCJ_AJXX is: %s and column is DQWD", source.getId()));
                //System.out.println("The error id of table JCJ_AJXX is: "+source.getId() + " and column is DQWD");
            }
            try {
                if (source.getStoreysOfBuildings()!=null) {
                    target.setLccs(source.getStoreysOfBuildings().toString());//楼层层数
                }
            }catch (Exception e){
                logService.infoLog(logger, "service", "getIncidentListByTime", String.format("The error id of table JCJ_AJXX is: %s and column is LC", source.getId()));
                //System.out.println("The error id of table JCJ_AJXX is: "+source.getId() + " and column is LC");
            }
            try {
                if (Strings.isNotBlank(source.getBurningFloor())) {
                    target.setRscs(Integer.valueOf(source.getBurningFloor()));//燃烧层数
                }
            }catch (Exception e){
                logService.infoLog(logger, "service", "getIncidentListByTime", String.format("The error id of table JCJ_AJXX is: %s and column is RSLC", source.getId()));
                //System.out.println("The error id of table JCJ_AJXX is: "+source.getId() + " and column is RSLC");
            }
            try {
                if (Strings.isNotBlank(source.getInjuredCount())&&Strings.isNotBlank(source.getDeathCount())) {
                    int swqk = Integer.parseInt(source.getInjuredCount()) + Integer.parseInt(source.getDeathCount());
                    target.setRyswqk(Integer.toString(swqk));//人员伤亡情况
                }

            }catch (Exception e){
                logService.infoLog(logger, "service", "getIncidentListByTime", String.format("The error id of table JCJ_AJXX is: %s and column is RYSSS or RYSWS", source.getId()));
                //System.out.println("The error id of table JCJ_AJXX is: "+source.getId() + " and column is RYSSS or RYSWS");
            }

            target.setFwdtqk(null);//房屋倒塌情况
            target.setCreateTime(TimeUtils.transformaStrTime(source.getCreatedTime()));//创建时间
            target.setGdsj(TimeUtils.transformaStrTime(source.getReturnTime()));//归队时间
            target.setHskzsj(TimeUtils.transformaStrTime(source.getFireControlTime()));//火势控制时间
            target.setJasj(TimeUtils.transformaStrTime(source.getIncidentEndTime()));//结案时间
            target.setJbpmsj(TimeUtils.transformaStrTime(source.getExtinguishTime()));//基本扑灭时间
            target.setJqJyqk(source.getIncidentDescribe());//警情简要情况
            target.setJqSsqydm(source.getDistrictCode());//所属区域代码
            target.setJqTywysbm(source.getIdCode());//警情_通用唯一识别码
           // target.setJqXlh(source.getIncidentNumber());//警情_序列号
            if(!org.mx.StringUtils.isBlank(source.getSquadronOrganizationId())){
                target.setJqXqzd(organizationNameMap.get(source.getSquadronOrganizationId()));//辖区中队
            }


            target.setJqXqzdTywysbm(source.getSquadronOrganizationId());//辖区中队_通用唯一识别码
            target.setJqbslbdm(source.getIncidentLevelCode());//警情标识类别代码
            if(!org.mx.StringUtils.isBlank(source.getIncidentLevelCode())&&dicsMap.get("AJDJ")!=null){
                target.setJqdj(dicsMap.get("AJDJ").get(source.getIncidentLevelCode()));//警情等级
            }


            target.setJqdjdm(source.getIncidentLevelCode());//警情等级代码
            target.setJqdxJqdxlbdm(source.getKeyUnitTypeCode());//警情对象类别代码
            target.setJqdxJyqk(source.getIncidentDescribe());//警情对象简要情况
            target.setJqdxMc(source.getKeyUnit());//警情对象名称
            target.setJqdxTywysbm(source.getKeyUnitId());//警情对象通用唯一识别码
            target.setJqflydm(source.getIncidentTypeCode());//警情分类与代码
            if(!org.mx.StringUtils.isBlank(source.getIncidentTypeSubitemCode())&&dicsMap.get("AJLXZX")!=null){
                target.setJqlb(dicsMap.get("AJLXZX").get(source.getIncidentTypeSubitemCode()));//警情类别
            }


            target.setJqlbdm(source.getIncidentTypeSubitemCode());//警情类别代码
            if(!org.mx.StringUtils.isBlank(source.getIncidentTypeCode())&&dicsMap.get("AJLX")!=null){
                target.setJqlx(dicsMap.get("AJLX").get(source.getIncidentTypeCode()));//警情类型
            }


            target.setJqlxdm(source.getIncidentTypeCode());//警情类型代码
            target.setJqztLbdm(source.getIncidentStatusCode());//警情状态类别代码
            target.setJqztRqsj(TimeUtils.transformaStrTime(source.getUpdatedTime()));//警情状态日期时间
            target.setJsmlsj(TimeUtils.transformaStrTime(source.getReceiveTime()));//接受命令时间
            target.setJtssqk(null);//交通设施情况
            if(!org.mx.StringUtils.isBlank(source.getBuildingStructureCode())&&dicsMap.get("JZJG")!=null){
                target.setJzjglx(dicsMap.get("JZJG").get(source.getBuildingStructureCode()));//建筑结构类型
            }


            target.setJzjglxdm(source.getBuildingStructureCode());//建筑结构类型代码
            target.setLasj(TimeUtils.transformaStrTime(source.getRegisterIncidentTime()));//立案时间
            target.setLc(source.getStoreysOfBuildings());//integer(int32)楼层

            target.setMc(source.getTitle());//名称

            target.setRsdx(source.getDisposalObjectCode());//燃烧对象
            target.setRybkqk(source.getTrappedCount());//人员被困情况
            target.setRyslqk(null);//人员失联情况

            target.setTssj(TimeUtils.transformaStrTime(source.getFightEdnTime()));//停水时间
            target.setTxssqk(null);//通讯设施情况
            target.setUid(source.getId());//警情_唯一ID：yyyymmddhhmmssabcd
            target.setXczhrLxdh(source.getContactPersonPhone());//联系电话
            target.setXczhrRqsj(TimeUtils.transformaStrTime(source.getSecurityStartTime()));
            target.setXczhrXfgwlbdm(null);//消防岗位类别代码
            target.setXczhrXm(source.getSecurityContactPerson());
            target.setXfjyjgTywysbm(source.getSquadronOrganizationId());//消防救援机构_通用唯一识别码
            target.setXzqhdm(source.getDistrictCode());//行政区划代码
            target.setYwqkdm(source.getSmogSituationCode());//烟雾情况代码
            if(!org.mx.StringUtils.isBlank(source.getSmogSituationCode())&&dicsMap.get("YWQK")!=null){
                target.setYwqk(dicsMap.get("YWQK").get(source.getSmogSituationCode()));//烟雾情况
            }


            target.setZdzksj(TimeUtils.transformaStrTime(source.getFightTime()));//战斗展开时间
            target.setZqcs(source.getDisasterSites());//灾情场所
            target.setZtfhsj(TimeUtils.transformaStrTime(source.getHalfwayReturnTime()));//中途返回时间
            target.setJqbh(source.getIncidentNumber());

            if (Strings.isNotBlank(source.getIncidentTypeCode())){
                target.setJqlxdmcjxq(source.getIncidentTypeCode()+ dicsMap.get("AJLX").get(source.getIncidentTypeCode()));
            }

            return target;
        }
        return null;
    }

    /**
     * 处警数据转换
     * @param source
     * @return
     */
    public static HandleDateBean transform(HandleEntity source, LogService logService, Logger logger) throws ParseException {
        if (null!=source){
            HandleDateBean target = new HandleDateBean();
            target.setCzrMc(source.getHandlePersonName());//操作员_姓名
            target.setCzrTywysbm(source.getHandlePersonNumber());//操作员_通用唯一识别码
            target.setDpdzSl(strToInteger(source.getDispatchOrganization()));//警情处警调派_队站数量
            target.setJqTywysbm(source.getIncidentId());//警情_通用唯一识别码
            target.setJqcjdpDplx(source.getHandleTypeCode());//警情处警调派_调派类型
            target.setJqcjdpDpsj(TimeUtils.transformaStrTime(source.getHandleStartTime()));//警情处警调派_调派事件
            target.setJqcjdpDpyj(null);//警情处警调派_调派依据JSON
            target.setJqcjdpTywysbm(source.getIdCode());//警情处警调派_通用唯一识别码
            target.setJqcjdpXgyy(null);//警情处警调派_修改原因
            target.setJqcjdpclGgyj(null);//车辆更改依据
            target.setCreateTime(TimeUtils.transformaStrTime(source.getCreatedTime()));
            target.setUid(source.getId());
            try {
                if (Strings.isNotBlank(source.getHandleStatus()))
                target.setJqcjdpztlbdm(jqcjdpztlbdm.get(source.getHandleStatus()));//警情处警调派_状态
            }catch (Exception e){
                logService.infoLog(logger, "repository", "getAlarmJqcjDpListByTime", String.format("the error id of table JCJ_CJJL is : %s and column is CJDZT ", source.getId()));
                //System.out.println("the error id of table JCJ_CJJL is :" + source.getId() + " and column is CJDZT ");
            }
            target.setJqdjTywysbm(null);//是否警情升级

            return target;
        }
        return null;
    }


    /**
     * 调派机构转换
     * @param source
     * @return
     */
    public static DispatchOrganizationBean transform(HandleOrganizationOutBean source, LogService logService, Logger logger) throws ParseException {
        if (null!=source){
            DispatchOrganizationBean target = new DispatchOrganizationBean();
            try {
                if (Strings.isNotBlank(source.getLongitude())) {
                    target.setDpzdjd(NumberFormat.getInstance().parse(source.getLongitude()));//调派中队经度
                    target.setDqjd(NumberFormat.getInstance().parse(source.getLongitude()));//地球经度
                }
            }catch (Exception e){
                logService.infoLog(logger, "repository", "getAlarmJqcjDpListByTime", String.format("the error id of table jcj_cjxx is :%s and column is DQJD", source.getId()));
                //System.out.println("the error id of table jcj_cjxx is :" + source.getId() + " and column is DQJD");
            }
            try {
                if (Strings.isNotBlank(source.getLatitude())) {
                    target.setDpzdwd(NumberFormat.getInstance().parse(source.getLatitude()));// 调派中队纬度
                    target.setDqwd(NumberFormat.getInstance().parse(source.getLatitude()));//地球纬度
                }
            }catch (Exception e){
                logService.infoLog(logger, "repository", "getAlarmJqcjDpListByTime", String.format("the error id of table jcj_cjxx is :%s and column is DQWD", source.getId()));
                //System.out.println("the error id of table jcj_cjxx is :" + source.getId() + " and column is DQWD");
            }

            target.setDpzdmc(source.getOrganizationName());//调派中队名称
            target.setJqcjdpTywysbm(source.getHandleId());//警情处警调派_通用唯一识别码
            target.setJqcjdpdzTywysbm(source.getOrganizationId());//警情处警调派队站_通用唯一识别码
            target.setSfjbxq(null);//是否具备辖区
            target.setTmcs(null);//调派路线
            target.setXfjyjgTywysbm(source.getOrganizationId());//消防救援机构_通用唯一识别码
            target.setXfjyjgjl(null);//导航距离
            target.setXfjyjgjsjj(TimeUtils.transformaStrTime(source.getNoticeTime()));//中队接收时间
            target.setXfjyjgjszt(jqcjdpztlbdm.get(source.getHandlePoliceStatus())==null?null:String.valueOf(jqcjdpztlbdm.get(source.getHandlePoliceStatus())));//中队接收状态
            target.setXfjyjgtjhldsl(null);//红绿灯数量
            target.setXfjyjgxqfw(source.getDistrictName());//辖区范围
            target.setXfjyjgyjdcsj(null);//预计时间(单位：秒)
            target.setCreateTime(source.getCreateTime());
            target.setUid(source.getUid());
            return target;

        }

        return null;
    }


    /**
     * 调派车辆转换
     * @param source
     * @return
     */
    public static DispatchVehicleBean transform(HandleOrganizationVehicleOutBean source){
        if (source!=null){
            DispatchVehicleBean target = new DispatchVehicleBean();
            target.setCllxMc(source.getVehicleTypeName());//车辆类型名称
            target.setCllxTywysbm(source.getVehicleTypeCode());//车辆类型代码
            target.setJdchphm(source.getVehicleNumber());//机动车号牌号码
            target.setJgcs(null);//举高参数
            target.setJqcjdpTywysbm(source.getHandleId());//警情处警调派_通用唯一识别码
            target.setJqcjdpclDpsj(source.getIncidentId());//警情处警调派车辆_调派事件
            target.setJqcjdpclGgyj(null);//调派车辆_更改依据
            target.setJqcjdpclGgzt(null);//更改状态
            target.setJqcjdpclGxfs(null);//警情处警调派车辆_更新方式
            target.setJqcjdpclTywysbm(source.getVehicleId());//警情处警调派车辆_通用唯一识别码
            target.setJqcjdpclXgyybz(null);//调派车辆_修改原因备注
            target.setJqcjdpclZtgxbz(null);//调派车辆_状态更新备注
            target.setJqcjdpclztlbdm(source.getVehicleStatusCode());//警情处警调派车辆状态类别代码
            target.setJqcjdpdzMc(source.getOrganizationName());//警情处警调派中队_名称
            target.setJqcjdpdzTywysbm(source.getOrganizationId());//警情处警调派中队_通用唯一识别码
            target.setPmzzl(null);//泡沫装载量
            target.setUpdateTime(null);//更新时间
            target.setXfjyclTywysbm(source.getVehicleId());//消防救援车辆_通用唯一识别码
            target.setZzsrj(null);//装载水_容积
            target.setCreateTime(source.getCreateTime());
            target.setUid(source.getUid());
            return target;

        }

        return null;
    }


    /**
     * 参战人员数据转换
     * @param source
     * @return
     */
    public static DispatchPerson transform(ParticipantFeedbackOutBean source){
        if (source!=null){
            DispatchPerson target = new DispatchPerson();
            target.setJqcjdpTywysbm(source.getHandleId());//警情处警调派_通用唯一识别码
            target.setJqcjdpdzMc(source.getOrganizationName());//人员所属中队_名称
            target.setJqcjdpdzTywysbm(source.getOrganizationId());//人员所属中队_通用唯一识别码
            target.setJqcjdpryDpsj(source.getIncidentId());//警情处警调派人员_调派事件
            target.setJqcjdpryGgyj(null);//调派人员_更改依据
            target.setJqcjdpryGgzt(null);//调派人员_更改状态
            target.setJqcjdpryGxfs(null);//警情处警调派人员_更新方式
            target.setJqcjdpryTywysbm(source.getPersonId());//警情处警调派人员_通用唯一识别码
            target.setJqcjdpryXgyybz(source.getRemarks());//调派人员_修改原因备注
            target.setJqcjdpryZtgxbz(null);//调派人员_状态更新备注
            target.setXfjyryTywysbm(source.getPersonId());//消防救援人员_通用唯一识别码
            target.setXfjyryXm(source.getPersonName());//消防救援人员姓名
            target.setCreateTime(source.getCreateTime());
            target.setUid(source.getUid());
            return target;

        }

        return null;
    }


    /**
     * 装备转换
     * @param source
     * @return
     */
    public static DispatchEquipment transform(EquipmentVehicleLoadOutBean source){
        if (source!=null){
            DispatchEquipment target = new DispatchEquipment();
            target.setJqcjdpTywysbm(null);//警情处警调派_通用唯一识别码
            target.setJqcjdpdzTywysbm(source.getOrganizationId());//装备所属队站_通用唯一识别码
            target.setJqcjdpdzmc(source.getOrganizationName());//装备所属队站_名称
            target.setJqcjdpzbDpsj(null);//警情处警调派装备_调派事件
            target.setJqcjdpzbGgyj(null);//调派装备_更改依据
            target.setJqcjdpzbGgzt(null);//更改状态
            target.setJqcjdpzbGxfs(null);//警情处警调派装备_更新方式
            target.setJqcjdpzbTywysbm(source.getEquipmentCode());//警情处警调派装备_通用唯一识别码
            target.setJqcjdpzbXgyybz(null);//调派装备_修改原因备注
            target.setJqcjdpzbZtgxbz(null);//调派装备_状态更新备注
            target.setJqcjdpzbztlbdm(source.getEquipmentTypeCode());//警情处警调派装备状态类别代码
            target.setJqcjdpdzmc(source.getEquipmentName());//消防救援装备_名称
            target.setXfjyzbTywysbm(source.getEquipmentCode());//消防救援装备_通用唯一识别码
            target.setCreateTime(source.getCreateTime());
            target.setUid(source.getUid());
            return target;

        }

        return  null;
    }

    /**
     * 车辆信息转化
     * @param source
     * @return
     */
    public static VehicleOutBean transform(EquipmentVehicleEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap, LogService logService, Logger logger) throws ParseException {
        if (source!=null){
            VehicleOutBean target = new VehicleOutBean();
            target.setUid(source.getId());
            target.setCreateTime(TimeUtils.transformaStrTime(source.getCreatedTime()));
            target.setBedyl(source.getPumpFlow());//泵额定压力(MPA
            target.setBz(source.getRemarks());//备注
            target.setBfrq(TimeUtils.transformaStrTime(source.getScrapTime()));//报废日期 string(date-time)
            target.setCcrq(TimeUtils.transformaStrTime(source.getProductionTime()));//出厂日期 string(date-time)
            target.setClcs(null);//车辆参数
            target.setCllxTywysbm(source.getVehicleTypeCode());//车辆类型_通用唯一识别码
            target.setClqwztlbdm(source.getVehicleStatusCode());//车辆勤务状态类别代码
            target.setClsbdh(source.getVehicleCode());//车辆识别代号
            target.setClwzDqjd(null);//地球经度
            target.setClwzDqwd(null);//地球纬度
            target.setClwzWzcjsj(null);//位置采集时间string(date-time)
            target.setClxhTywysbm(source.getSpecificationsNumber());//车辆型号_通用唯一识别码
            target.setClxnzbJyqk(source.getVehicleDesc());//车辆性能指标_简要情况
            target.setClxszhm(null);//行驶证号码
            target.setClxszzp(null);//车辆行驶证照片
            target.setClzp(null);//车辆照片
            target.setClztbm(source.getVehicleStatusCode());//车辆状态编码
            target.setDwsbSimkkh(source.getLocationSIMNumber());//SIM卡卡号
            target.setDwsbTywysbm(source.getLocationId());//通用唯一识别码
            target.setDwzjhm(null);//单位证件号码
            target.setDwzjlx(null);//单位证件类型
            target.setFzrLxdh(source.getContactsPhone());//联系电话
            target.setFzrTywysbm(source.getContactsId());//通用唯一识别码
            target.setFzrXm(source.getContactsName());//姓名
            target.setGgxh(source.getSpecificationsNumber());//规格型号
            target.setJdccsyslbdm(source.getColor());//机动车车身颜色类别代码
            target.setJdcfdjddjh(source.getEngineNumber());//机动车发动机（电动机）号
            target.setJdchphm(source.getVehicleNumber());//机动车号牌号码
            if (null != source.getMaxLiftingHeight()) {
                target.setJgcs(source.getMaxLiftingHeight().toString());//举高参数
            }

            target.setJsy(null);//驾驶员
            target.setJsylxdh(null);//驾驶员联系电话
            target.setLxfs(source.getContactsPhone());//联系方式
            target.setPmlx(null);//泡沫类型
            target.setPx(source.getVehicleOrderNum());//排序
            target.setSccjDwmc(source.getManufacturerName());//生产厂家_单位名称
            target.setSfts(null);//是否特殊
            target.setSfzx(null);//是否在线
            target.setShfwDwmc(source.getAfterService());//售后服务_单位名称
            //target.setSssfmc("浙江省");//所属省份名称
            target.setXfclTywysbm(source.getIdCode());//消防车辆_通用唯一识别码
            target.setXfcldjdm(source.getVehicleLevelCode());//消防车辆等级代码
            target.setXfclzzgndm(null);//消防车辆作战功能代码
            target.setXfjyjgTywysbm(source.getOrganizationId());//消防救援机构_通用唯一识别码
            target.setXfzblxdm(source.getVehicleTypeCode());//消防装备器材分类与代码
            target.setXsfx(null);//行驶方向
            target.setXssd(null);//行驶速度
            target.setYtsm(null);//用途说明
            target.setYxj(null);//优先级
            target.setZdlxr(null);//中队联系人
            target.setEdzrs(source.getPassengersNum()==null?null:source.getPassengersNum().intValue());//核定载人数
            try{
                if (Strings.isNotBlank(source.getFoam())) {
                    target.setPmzzl(NumberFormat.getInstance().parse(source.getFoam()));//泡沫装载量（m³）
                }
            }catch (Exception ex){
                logService.infoLog(logger, "service", "getAlarmJqclList", String.format("the error id of table WL_CLXX is :%s and column is CZPM ", source.getId()));
                //System.out.println("the error id of table WL_CLXX is :"+source.getId() + "and column is CZPM ");
            }
            try{
                if (Strings.isNotBlank(source.getReferencePrice())) {
                    target.setCkjJe(NumberFormat.getInstance().parse(source.getReferencePrice()));//参考价_金额
                }
            }catch (Exception ex){
                logService.infoLog(logger, "service", "getAlarmJqclList", String.format("the error id of table WL_CLXX is :%s and column is CKJ_JE ", source.getId()));
                //System.out.println("the error id of table WL_CLXX is :"+source.getId() + "and column is CKJ_JE ");
            }
            try{
                if (Strings.isNotBlank(source.getVehicleIdentification())) {
                    target.setSfdycdclTywysbm(Boolean.getBoolean(source.getVehicleIdentification()));//是否第一出动车辆_判断标识
                }
            }catch (Exception ex){
                logService.infoLog(logger, "service", "getAlarmJqclList", String.format("the error id of table WL_CLXX is :%s and column is SFDYCDCL_TYWYSBM ", source.getId()));
                //System.out.println("the error id of table WL_CLXX is :"+source.getId() + "and column is SFDYCDCL_TYWYSBM ");
            }
            try{
                if (Strings.isNotBlank(source.getGpsNumber())) {
                    target.setSfygpsdw(Boolean.getBoolean(source.getGpsNumber()));//是否有GPS定位
                }
            }catch (Exception ex){
                logService.infoLog(logger, "service", "getAlarmJqclList", String.format("the error id of table WL_CLXX is :%s and column is GPSBH ", source.getId()));
                //System.out.println("the error id of table WL_CLXX is :"+source.getId() + "and column is GPSBH ");
            }
            try{
                if (null != source.getWhetherTraining()) {
                    target.setSfyyxlPdbz(transformBoolean(source.getWhetherTraining()));//是否用于训练_判断标识
                }
            }catch (Exception ex){
                logService.infoLog(logger, "service", "getAlarmJqclList", String.format("the error id of table WL_CLXX is :%s and column is SFYYXL_PDBZ ", source.getId()));
                //System.out.println("the error id of table WL_CLXX is :"+source.getId() + "and column is SFYYXL_PDBZ ");
            }
            try{
                if (null != source.getWhetherAssembling()) {
                    target.setSfzpPdbz(transformBoolean(source.getWhetherAssembling()));//是否装配_判断标识
                }
            }catch (Exception ex){
                logService.infoLog(logger, "service", "getAlarmJqclList", String.format("the error id of table WL_CLXX is :%s and column is SFZP_PDBZ ", source.getId()));
                //System.out.println("the error id of table WL_CLXX is :"+source.getId() + "and column is SFZP_PDBZ ");
            }
            try{
                if (null != source.getCarrierBubbleA()) {
                    target.setZzalpmRj(source.getCarrierBubbleA());//装载A类泡沫_容积
                }
            }catch (Exception ex){
                logService.infoLog(logger, "service", "getAlarmJqclList", String.format("the error id of table WL_CLXX is :%s and column is ZZALPM_RJ ", source.getId()));
                //System.out.println("the error id of table WL_CLXX is :"+source.getId() + "and column is ZZALPM_RJ ");
            }
            try{
                if (null != source.getCarrierBubbleB()) {
                    target.setZzblpmRj(source.getCarrierBubbleB());//装载B类泡沫_容积
                }
            }catch (Exception ex){
                logService.infoLog(logger, "service", "getAlarmJqclList", String.format("the error id of table WL_CLXX is :%s and column is ZZBLPM_RJ ", source.getId()));
                //System.out.println("the error id of table WL_CLXX is :"+source.getId() + "and column is ZZBLPM_RJ ");
            }
            try{
                if (null != source.getWaterCarrier()) {
                    target.setZzsRj( source.getWaterCarrier());//装载水_容积
                }

            }catch (Exception ex){
                logService.infoLog(logger, "service", "getAlarmJqclList", String.format("the error id of table WL_CLXX is :%s and column is ZZS_RJ ", source.getId()));
                //System.out.println("the error id of table WL_CLXX is :"+source.getId() + "and column is ZZS_RJ ");
            }
            if (Strings.isNotBlank(source.getOrganizationId())) {
                target.setSsdwmc(organizationNameMap.get(source.getOrganizationId()));//所属单位名称
            }
            if (Strings.isNotBlank(source.getOrganizationId())) {
                target.setXfjyjgMc(organizationNameMap.get(source.getOrganizationId()));//消防救援机构_名称
            }
            if (null != dicsMap.get("WLCLLX") && StringUtils.isNotBlank(source.getVehicleTypeCode())) {
                String a = source.getVehicleTypeCode();
                target.setCllxMc(dicsMap.get("WLCLLX").get(source.getVehicleTypeCode()));//车辆类型_名称
            }
            target.setZbrq(TimeUtils.transformaStrTime(source.getEquipTime()));//装备日期 string(date-time
            return target;
        }

        return null;
    }

    /**
     * 录音信息转换
     * @param source
     * @return
     */
    public static SoundRecordOutBean transform(SoundRecordEntity source) throws ParseException {
        if (source!=null){
            SoundRecordOutBean target = new SoundRecordOutBean();
            target.setJqTywysbm(source.getIncidentId());//警情_通用唯一识别码：yyyymmddhhmmssabcd
            target.setLybh(source.getRecordNo());//录音编号
            target.setLydz(source.getRecordUrl());//录音地址
            target.setUid(source.getId());
            target.setCreateTime(TimeUtils.transformaStrTime(source.getCreatedTime()));
            return target;
        }
        return null;
    }



    public static Boolean transformBoolean(Integer var){
        if (var==null||var.equals(0)){
            return false;
        }
        return true;
    }

    private Integer floatToInt(Float fl){
        return fl!=null?fl.intValue():0;
    }

    private static Integer strToInteger(String source){
        if (StringUtils.isBlank(source)){
            return 0;
        }else {
            return Integer.valueOf(source);
        }
    }

}
