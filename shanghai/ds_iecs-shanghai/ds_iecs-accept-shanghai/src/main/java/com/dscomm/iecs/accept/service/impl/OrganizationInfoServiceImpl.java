package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.VehicleNumEntity;
import com.dscomm.iecs.accept.dal.repository.OrganizationInfoRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.OrganizationInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.VehiclexxInputInfo;
import com.dscomm.iecs.accept.service.OrganizationInfoService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Component("organizationInfoServiceImpl")
public class OrganizationInfoServiceImpl implements OrganizationInfoService {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationInfoServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private DictionaryService dictionaryService;
    private OrganizationInfoRepository organizationInfoRepository;
    private List<String> dics;


    @Autowired
    public OrganizationInfoServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                       DictionaryService dictionaryService,
                                       OrganizationInfoRepository organizationInfoRepository) {

        this.logService = logService;
        this.accessor = accessor;
        this.dictionaryService = dictionaryService;
        this.organizationInfoRepository = organizationInfoRepository;
        dics = new ArrayList<>(Arrays.asList( "WZZT","WZZBLX","WZDPZT","XZQX" ) );
    }

    /*
     * 修改消防力量机构信息
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateOrganizationInfo(OrganizationInputInfo parse) {
        if (parse == null) {
            logService.infoLog(logger, "service", "updateInfo", "parse is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateInfo", "service is started...");
            Long logStart = System.currentTimeMillis();
            /*OrganizationInformationEntity organizationEntity = new OrganizationInformationEntity();
//            parse.getDlwz(); // 地理位置
            organizationEntity.setOrganizationInsideId(parse.getOrganizationInsideId()); // 机构编号
            organizationEntity.setOrganizationName(parse.getOrganizationName()); // 机构名称
            organizationEntity.setOrganizationShortName(parse.getOrganizationShortName()); // LED 名称
            organizationEntity.setOrganizationOrderNum(parse.getOrganizationOrderNum()); // LED顺序
            organizationEntity.setOrganizationRepealStatus(parse.getOrganizationRepealStatus()); // 是否是作战机构
//            parse.getTelNumber(); // 联系电话
//            organizationEntity.setSfzcq(parse.getXxms()); // 详细描述
//            parse.getZddz(); // 中队地址
            accessor.save(organizationEntity);*/
            Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics);
            String jgbh = parse.getOrganizationInsideId(); // 机构编号
            String jgmc = parse.getOrganizationName(); // 机构名称
            String organizationAddress = parse.getOrganizationAddress(); // 机构地址
            Integer organizationOrderNum = parse.getOrganizationOrderNum();// LED顺序
            String ledmc = parse.getOrganizationShortName(); // LED 名称
            String organizationDesc = parse.getOrganizationDesc(); // 详细描述
            String organizationRepealStatus = parse.getOrganizationRepealStatus(); // 是否是作战机构
            organizationInfoRepository.updateOrganizationInfo(jgbh,ledmc,organizationOrderNum,organizationDesc,organizationRepealStatus,jgmc,organizationAddress);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    /*
     * 保存消防力量车辆信息
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveVehicleInfo(VehiclexxInputInfo info) {
        if (info == null) {
            logService.infoLog(logger, "service", "saveVehicleInfo", "parse is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveVehicleInfo", "service is started...");
            Long logStart = System.currentTimeMillis();
            VehicleNumEntity vehicle = new VehicleNumEntity();
            vehicle.setZbmc(info.getCllx()); // 车辆类型
            vehicle.setCphm(info.getCphmm()); // 车牌号码
            vehicle.setSccjmc(info.getScqy()); // 生产企业
            vehicle.setCldjdm(info.getCldj()); // 车辆等级
//            vehicle.setXfjyjg_tywysbm(info.getXzjg()); // 行政机构
            vehicle.setCljc(info.getLedmc());// LED名称
            vehicle.setPx(info.getSx()); // LED顺序
            vehicle.setDthh(info.getDthh()); // 电台呼号
//            vehicle.setTczhc(info.getTczhc()); // 头车指挥车
            vehicle.setGpsbh(info.getGpsbh()); // GPS编号
            // PDA编号
            vehicle.setFzr_lxdh(info.getTelNumber()); // 联系电话
            vehicle.setZs(info.getZsl()); // 载水量
            vehicle.setZp(info.getZpl()); // 载泡量
            vehicle.setBll(info.getBll()); // 泵流量
            vehicle.setZzalpm_rj(info.getCzpll()); // 车载泡流量
            vehicle.setStb(info.getStbll()); // 手抬泵流量

            vehicle.setYtgd(info.getYtgd()); // 云梯高度 (最大举升高度)
            vehicle.setZrsl(info.getZsrs()); // 战士人数 (载人数量)
            // 最后更新时间
            // 作战功能
            vehicle.setBz(info.getBz()); // 备注
            vehicle.setZblxdm(info.getClzl()); // 车辆种类
            // 担任战备
            // 保留车辆(是否显示，0不显示，1显示)
            accessor.save(vehicle);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveVehicleInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveVehicleInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    /*
     * 修改消防力量车辆信息
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateVehicleInfo(VehiclexxInputInfo info) {
        if (info == null) {
            logService.infoLog(logger, "service", "updateVehicleInfo", "parse is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateVehicleInfo", "service is started...");
            Long logStart = System.currentTimeMillis();
            VehicleNumEntity vehicleNumEntity = new VehicleNumEntity();
            // 获取传来的数据
            String cllx = info.getCllx(); // 车辆类型
            String cphmm = info.getCphmm(); // 车牌号码
            String scqy = info.getScqy(); // 生产企业
            String cldj = info.getCldj(); // 车辆等级
            String xzjg = info.getXzjg(); // 行政机构

            String ledmc = info.getLedmc(); // LED名称
            Integer sx = info.getSx(); // LED顺序
            String dthh = info.getDthh(); // 电台呼号
            String tczhc = info.getTczhc(); // 头车指挥车
            String gpsbh = info.getGpsbh(); // GPS编号
            String pdabh = info.getPdabh(); // PDA编号
            String telNumber = info.getTelNumber(); // 联系电话
            Float zsl = info.getZsl(); // 载水量
            Float zpl = info.getZpl(); // 载泡量
            Float bll = info.getBll(); // 泵流量
            Float czpll = info.getCzpll(); // 车载泡流量
            Float stbll = info.getStbll(); // 手抬泵流量
            String ytgd = info.getYtgd(); // 云梯高度

            Integer zsrs = info.getZsrs(); // 战士人数
            String zhgxsj = info.getZhgxsj(); // 最后更新时间
            String zzgn = info.getZzgn(); // 作战功能

            String bz = info.getBz(); // 备注

            String clzl = info.getClzl(); // 车辆种类

            Integer drzb = info.getDrzb(); // 担任战备
            Integer blcl = info.getBlcl();// 保留车辆
            organizationInfoRepository.updateVehicleInfo(cllx,cphmm,ledmc,sx,dthh,tczhc,gpsbh,pdabh,telNumber,zsl,zpl,bll,czpll,stbll,ytgd,bz,drzb,blcl);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateVehicleInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateVehicleInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }
}
