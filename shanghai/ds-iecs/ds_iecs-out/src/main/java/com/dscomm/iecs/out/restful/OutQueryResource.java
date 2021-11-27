package com.dscomm.iecs.out.restful;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.typebean.*;
import com.dscomm.iecs.out.service.*;
import com.dscomm.iecs.out.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.mx.service.rest.vo.DataVO;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * accept restful query  接口
 */
@Path("v1/alarmRequest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OutQueryResource {

    private static final Logger logger = LoggerFactory.getLogger(OutQueryResource.class);
    private LogService logService;
    private Environment env;
    private AcceptanceOutService acceptanceOutService;
    private IncidentOutService incidentOutService;
    private HandleOutService handleOutService;
    private DocumentOutService documentOutService;
    private LocaleOutService localeOutService;
    private VehicleOutService vehicleOutService;
    private TelephoneOutService telephoneOutService;
    private SoundRecordOutService soundRecordOutService;
    private GeneralAccessor accessor;
    private OrganizationService organizationService;
    private AlarmJqcjDpTjfaService alarmJqcjDpTjfaService;
    private SessionDataStore dataStore;

    @Autowired
    public OutQueryResource(LogService logService,
                            @Qualifier("generalAccessor") GeneralAccessor accessor,
                            Environment env,
                            AcceptanceOutService acceptanceOutService,
                            IncidentOutService incidentOutService,
                            HandleOutService handleOutService,
                            DocumentOutService documentOutService,
                            LocaleOutService localeOutService,
                            VehicleOutService vehicleOutService,
                            TelephoneOutService telephoneOutService,
                            SoundRecordOutService soundRecordOutService,
                            OrganizationService organizationService,
                            AlarmJqcjDpTjfaService alarmJqcjDpTjfaService,

                            SessionDataStore dataStore) {
        this.logService = logService;
        this.env = env;
        this.acceptanceOutService = acceptanceOutService;
        this.incidentOutService = incidentOutService;
        this.handleOutService = handleOutService;
        this.documentOutService = documentOutService;
        this.localeOutService = localeOutService;
        this.vehicleOutService = vehicleOutService;
        this.telephoneOutService = telephoneOutService;
        this.soundRecordOutService = soundRecordOutService;
        this.accessor = accessor;
        this.organizationService = organizationService;
        this.alarmJqcjDpTjfaService = alarmJqcjDpTjfaService;
        this.dataStore = dataStore;
    }


    /**
     * 1 根据时间获取报警记录列表
     *
     * @return 返回更新结果
     */
    @Path("getAlarmBjjlListByTime")
    @POST
    public DataVO<List<AlarmRecordBean>> getAlarmRecordListByTime(
            @Context HttpHeaders headers,
            String outQueryVO

    ) throws ParseException {
        logger.debug(String.format("getAlarmRecordListByTime,param is:%s", outQueryVO));
        if (StringUtils.isBlank(outQueryVO)) {
            logService.erorLog(logger, "restful", "getAlarmRecordListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        String startTime = "";
        String endTime = "";
        String access_token = headers.getHeaderString("Authorization").split(" ")[1];

        String deptCode = "";
        logger.debug(String.format("token:%s", access_token));
        try {
            JSONObject responseData = JSONObject.parseObject(outQueryVO);
            startTime = responseData.getString("startTime");
            endTime = responseData.getString("endTime");
            //access_token = responseData.getString("access_token");
            deptCode = responseData.getString("deptCode");
        } catch (Exception ex) {
            logService.erorLog(logger, "restful", "getAlarmRecordListByTime", "this is error", ex);
        }

        logService.infoLog(logger, "restful", "getAlarmRecordListByTime", "restful is started...");
        Long start = System.currentTimeMillis();
        //参数判断
        if (Strings.isBlank(startTime) || Strings.isBlank(endTime) || Strings.isBlank(access_token) || Strings.isBlank(deptCode)) {
            logService.erorLog(logger, "restful", "getAlarmRecordListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        if (!TokenUtils.verify(access_token)) {
            logService.erorLog(logger, "restful", "getAlarmRecordListByTime", "this is error", new OutException(OutException.AccetpErrors.TOKEN_FAIL));
            throw new OutException(OutException.AccetpErrors.TOKEN_FAIL);
        }
        //获取用户信息
        String username = TokenUtils.getUserName(access_token);
        dataStore.get().put("deptCode",deptCode);
        //执行逻辑处理
        Long st = transferStringDateToLong(startTime);
        Long et = transferStringDateToLong(endTime);

        List<AlarmRecordBean> res = acceptanceOutService.getAlarmRecordListByTime(st, et, username);
        if (res!=null&&!res.isEmpty()){
            for (AlarmRecordBean obj : res) {
                obj.setDeptCode(deptCode);
            }
        }

        //AccessInfoVo accessInfoVo = GetOrgIdAndAccessInfoUtils.getAccessInfo(deptCode,username,accessor);

        //装配
        // ResultVo resVo = new ResultVo();
        // GetOrgIdAndAccessInfoUtils.getAfterQueryAudit(resVo,accessInfoVo,res,InterfaceTypeEnum.BJJL.getCode());
        //resVo.setAccessInfoVo(accessInfoVo);
        //resVo.setDataList(res);
        //resVo.setInterfaceType(InterfaceTypeEnum.BJJL.getCode());
        //resVo.setQueryParam(null);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "getAlarmRecordListByTime", String.format("restful is finished,execute time is :%sms", end - start));
        logger.info(String.format("数据汇聚传输接口:%s,传输数据 %s 条，传输结果:成功，执行时间：%s毫秒","getAlarmBjjlListByTime",res.size(),end - start));
        if (dataStore!=null){
            dataStore.clean();
        }
        return new DataVO<>(res);
    }


    /**
     * 2.获取警情数据信息
     *
     * @param headers
     * @return
     * @throws ParseException
     */
    @Path("getAlarmJqListByTime")
    @POST
    public DataVO<List<IncidentOutBean>> getAlarmJqListByTime(
            @Context HttpHeaders headers,
            String outQueryVO

    ) throws ParseException {
        logger.debug(String.format("getAlarmJqListByTime,param is:%s", outQueryVO));
        if (StringUtils.isBlank(outQueryVO)) {
            logService.erorLog(logger, "restful", "getAlarmJqListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        String startTime = "";
        String endTime = "";
        String access_token = headers.getHeaderString("Authorization").split(" ")[1];

        String deptCode = "";
        logger.debug(String.format("token:%s", access_token));
        try {
            JSONObject responseData = JSONObject.parseObject(outQueryVO);
            startTime = responseData.getString("startTime");
            endTime = responseData.getString("endTime");
            //access_token = responseData.getString("access_token");
            deptCode = responseData.getString("deptCode");
        } catch (Exception ex) {
            logService.erorLog(logger, "restful", "getAlarmJqListByTime", "this is error", ex);
        }


        logService.infoLog(logger, "restful", "getAlarmJqListByTime", "restful is started...");
        Long start = System.currentTimeMillis();


        //参数判断
        if (Strings.isBlank(startTime) || Strings.isBlank(endTime) || Strings.isBlank(access_token) || Strings.isBlank(deptCode)) {
            logService.erorLog(logger, "restful", "getAlarmJqListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        if (!TokenUtils.verify(access_token)) {
            logService.erorLog(logger, "restful", "getAlarmJqListByTime", "this is error", new OutException(OutException.AccetpErrors.TOKEN_FAIL));
            throw new OutException(OutException.AccetpErrors.TOKEN_FAIL);
        }
        //获取用户信息
        String username = TokenUtils.getUserName(access_token);
        //执行逻辑处理
        dataStore.get().put("deptCode",deptCode);
        Long st = transferStringDateToLong(startTime);
        Long et = transferStringDateToLong(endTime);
        //获取组织
        //OrganizationEntity organizationEntity = GetOrgIdAndAccessInfoUtils.getOrganization(deptCode,accessor);
        //String orgId = organizationEntity.getIdCode();
        List<IncidentOutBean> res = incidentOutService.getIncidentListByTime(st, et, username);
        //AccessInfoVo accessInfoVo = GetOrgIdAndAccessInfoUtils.getAccessInfo(deptCode,username,accessor);

        //装配
        //ResultVo resVo = new ResultVo();
        //GetOrgIdAndAccessInfoUtils.getAfterQueryAudit(resVo,accessInfoVo,res,InterfaceTypeEnum.JQXX.getCode());
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "getAlarmJqListByTime", String.format("restful is finished,execute time is :%sms", end - start));
        logger.info(String.format("数据汇聚传输接口:%s,传输数据 %s 条，传输结果:成功，执行时间：%s毫秒","getAlarmJqListByTime",res.size(),end - start));
        if (dataStore!=null){
            dataStore.clean();
        }
        return new DataVO<>(res);
    }


    /**
     * 3.警情调派信息
     *
     * @param headers
     * @return
     * @throws ParseException
     */
    @Path("getAlarmJqcjDpListByTime")
    @POST
    public DataVO<List<HandleDateBean>> getAlarmJqcjDpListByTime(
            @Context HttpHeaders headers,
            String outQueryVO
    ) throws ParseException {
        logger.debug(String.format("getAlarmJqcjDpListByTime,param is:%s", outQueryVO));
        if (StringUtils.isBlank(outQueryVO)) {
            logService.erorLog(logger, "restful", "getAlarmJqcjDpListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        String startTime = "";
        String endTime = "";
        String access_token = headers.getHeaderString("Authorization").split(" ")[1];

        String deptCode = "";
        logger.debug(String.format("token:%s", access_token));
        try {
            JSONObject responseData = JSONObject.parseObject(outQueryVO);
            startTime = responseData.getString("startTime");
            endTime = responseData.getString("endTime");
            //access_token = responseData.getString("access_token");
            deptCode = responseData.getString("deptCode");
        } catch (Exception ex) {
            logService.erorLog(logger, "restful", "getAlarmJqcjDpListByTime", "this is error", ex);
        }

        logService.infoLog(logger, "restful", "getAlarmJqcjDpListByTime", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(startTime) || Strings.isBlank(endTime) || Strings.isBlank(access_token) || Strings.isBlank(deptCode)) {
            logService.erorLog(logger, "restful", "getAlarmJqcjDpListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        if (!TokenUtils.verify(access_token)) {
            logService.erorLog(logger, "restful", "getAlarmJqcjDpListByTime", "this is error", new OutException(OutException.AccetpErrors.TOKEN_FAIL));
            throw new OutException(OutException.AccetpErrors.TOKEN_FAIL);
        }
        //获取用户信息
        String username = TokenUtils.getUserName(access_token);
        //执行逻辑处理
        dataStore.get().put("deptCode",deptCode);
        Long st = transferStringDateToLong(startTime);
        Long et = transferStringDateToLong(endTime);
        //获取组织
        //OrganizationEntity organizationEntity = GetOrgIdAndAccessInfoUtils.getOrganization(deptCode,accessor);
        //String orgId = organizationEntity.getIdCode();
        List<HandleDateBean> res = handleOutService.getDispatchListByTime(st, et, username);
        //AccessInfoVo accessInfoVo = GetOrgIdAndAccessInfoUtils.getAccessInfo(deptCode,username,accessor);

        //装配
        //ResultVo resVo = new ResultVo();
        //GetOrgIdAndAccessInfoUtils.getAfterQueryAudit(resVo,accessInfoVo,res,InterfaceTypeEnum.JQDPXX.getCode());
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "getAlarmJqcjDpListByTime", String.format("restful is finished,execute time is :%sms", end - start));
        logger.info(String.format("数据汇聚传输接口:%s,传输数据 %s 条，传输结果:成功，执行时间：%s毫秒","getAlarmJqcjDpListByTime",res.size(),end - start));
        if (dataStore!=null){
            dataStore.clean();
        }
        return new DataVO<>(res);
    }


    /**
     * 4.获取车辆列表
     *
     * @param headers
     * @return
     * @throws ParseException
     */
    @Path("getAlarmJqclList")
    @POST
    public DataVO<List<VehicleOutBean>> getAlarmJqclList(
            @Context HttpHeaders headers,
            String outQueryVO
    ) {
        logger.debug(String.format("getAlarmJqclList,param is:%s", outQueryVO));
        if (StringUtils.isBlank(outQueryVO)) {
            logService.erorLog(logger, "restful", "getAlarmJqclList", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        String access_token = headers.getHeaderString("Authorization").split(" ")[1];

        String deptCode = "";
        logger.debug(String.format("token:%s", access_token));
        try {
            JSONObject responseData = JSONObject.parseObject(outQueryVO);
            deptCode = responseData.getString("deptCode");
        } catch (Exception ex) {
            logService.erorLog(logger, "restful", "getAlarmJqclList", "this is error", ex);
        }

        logService.infoLog(logger, "restful", "getAlarmJqclList", "restful is started...");
        Long start = System.currentTimeMillis();
        if (Strings.isBlank(access_token) || Strings.isBlank(deptCode)) {
            logService.erorLog(logger, "restful", "getAlarmJqclList", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        if (!TokenUtils.verify(access_token)) {
            logService.erorLog(logger, "restful", "getAlarmJqclList", "this is error", new OutException(OutException.AccetpErrors.TOKEN_FAIL));
            throw new OutException(OutException.AccetpErrors.TOKEN_FAIL);
        }
        //获取用户信息
        String username = TokenUtils.getUserName(access_token);
        //执行逻辑处理
        dataStore.get().put("deptCode",deptCode);
        //获取组织
        //OrganizationEntity organizationEntity = GetOrgIdAndAccessInfoUtils.getOrganization(deptCode,accessor);
        //String orgId = organizationEntity.getIdCode();
        List<VehicleOutBean> res = vehicleOutService.getVehicleByTime(username);
        //AccessInfoVo accessInfoVo = GetOrgIdAndAccessInfoUtils.getAccessInfo(deptCode,username,accessor);

        //装配
        //ResultVo resVo = new ResultVo();
        //GetOrgIdAndAccessInfoUtils.getAfterQueryAudit(resVo,accessInfoVo,res,InterfaceTypeEnum.JQXLList.getCode());

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "getAlarmJqclList", String.format("restful is finished,execute time is :%sms", end - start));
        logger.info(String.format("数据汇聚传输接口:%s,传输数据 %s 条，传输结果:成功，执行时间：%s毫秒","getAlarmJqclList",res.size(),end - start));
        if (dataStore!=null){
            dataStore.clean();
        }
        return new DataVO<>(res);
    }


    /**
     * 录音记录
     *
     * @param headers
     * @return
     * @throws ParseException
     */

    @Path("getAlarmJqlyListByTime")
    @POST
    public DataVO<List<SoundRecordOutBean>> getAlarmJqlyListByTime(
            @Context HttpHeaders headers,
            String outQueryVO
    ) throws ParseException {
        logger.debug(String.format("getAlarmJqlyListByTime,param is:%s", outQueryVO));
        if (StringUtils.isBlank(outQueryVO)) {
            logService.erorLog(logger, "restful", "getAlarmJqlyListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        String startTime = "";
        String endTime = "";
        String access_token = headers.getHeaderString("Authorization").split(" ")[1];

        String deptCode = "";
        logger.debug(String.format("token:%s", access_token));
        try {
            JSONObject responseData = JSONObject.parseObject(outQueryVO);
            startTime = responseData.getString("startTime");
            endTime = responseData.getString("endTime");
            //access_token = responseData.getString("access_token");
            deptCode = responseData.getString("deptCode");
        } catch (Exception ex) {
            logService.erorLog(logger, "restful", "getAlarmJqlyListByTime", "this is error", ex);
        }

        logService.infoLog(logger, "restful", "getAlarmJqlyListByTime", "restful is started...");
        Long start = System.currentTimeMillis();
        //参数判断
        if (Strings.isBlank(startTime) || Strings.isBlank(endTime) || Strings.isBlank(access_token) || Strings.isBlank(deptCode)) {
            logService.erorLog(logger, "restful", "getAlarmJqlyListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        if (!TokenUtils.verify(access_token)) {
            logService.erorLog(logger, "restful", "getAlarmJqlyListByTime", "this is error", new OutException(OutException.AccetpErrors.TOKEN_FAIL));
            throw new OutException(OutException.AccetpErrors.TOKEN_FAIL);
        }
        //获取用户信息
        String username = TokenUtils.getUserName(access_token);
        //执行逻辑处理
        dataStore.get().put("deptCode",deptCode);
        Long st = transferStringDateToLong(startTime);
        Long et = transferStringDateToLong(endTime);
        //获取组织
        //OrganizationEntity organizationEntity = GetOrgIdAndAccessInfoUtils.getOrganization(deptCode,accessor);
        // String orgId = organizationEntity.getIdCode();
        List<SoundRecordOutBean> res = soundRecordOutService.getSoundRecordListByTime(st, et, username);

        //AccessInfoVo accessInfoVo = GetOrgIdAndAccessInfoUtils.getAccessInfo(deptCode,username,accessor);

        //装配
        //ResultVo resVo = new ResultVo();
        //GetOrgIdAndAccessInfoUtils.getAfterQueryAudit(resVo,accessInfoVo,res,InterfaceTypeEnum.JQLYList.getCode());

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "getAlarmJqlyListByTime", String.format("restful is finished,execute time is :%sms", end - start));
        logger.info(String.format("数据汇聚传输接口:%s,传输数据 %s 条，传输结果:成功，执行时间：%s毫秒","getAlarmJqlyListByTime",res.size(),end - start));
        if (dataStore!=null){
            dataStore.clean();
        }
        return new DataVO<>(res);
    }


    /**
     * 7、获取警情文书
     * 获取警情文书
     *
     * @param headers
     * @return
     * @throws ParseException
     */
    @Path("getAlarmJqwsListByTime")
    @POST
    public DataVO<List<DocumentOutBean>> getAlarmJqwsListByTime(
            @Context HttpHeaders headers,
            String outQueryVO
    ) throws ParseException {
        logger.debug(String.format("getAlarmJqwsListByTime,param is:%s", outQueryVO));
        if (StringUtils.isBlank(outQueryVO)) {
            logService.erorLog(logger, "restful", "getAlarmJqwsListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        String startTime = "";
        String endTime = "";
        String access_token = headers.getHeaderString("Authorization").split(" ")[1];

        String deptCode = "";
        logger.debug(String.format("token:%s", access_token));
        try {
            JSONObject responseData = JSONObject.parseObject(outQueryVO);
            startTime = responseData.getString("startTime");
            endTime = responseData.getString("endTime");
            //access_token = responseData.getString("access_token");
            deptCode = responseData.getString("deptCode");
        } catch (Exception ex) {
            logService.erorLog(logger, "restful", "getAlarmJqwsListByTime", "this is error", ex);
        }

        logService.infoLog(logger, "restful", "getAlarmJqwsListByTime", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(startTime) || Strings.isBlank(endTime) || Strings.isBlank(access_token) || Strings.isBlank(deptCode)) {
            logService.erorLog(logger, "restful", "getAlarmJqwsListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        if (!TokenUtils.verify(access_token)) {
            logService.erorLog(logger, "restful", "getAlarmJqwsListByTime", "this is error", new OutException(OutException.AccetpErrors.TOKEN_FAIL));
            throw new OutException(OutException.AccetpErrors.TOKEN_FAIL);
        }
        //获取用户信息
        String username = TokenUtils.getUserName(access_token);
        //执行逻辑处理
        dataStore.get().put("deptCode",deptCode);
        Long st = transferStringDateToLong(startTime);
        Long et = transferStringDateToLong(endTime);
        //获取组织
        //OrganizationEntity organizationEntity = GetOrgIdAndAccessInfoUtils.getOrganization(deptCode,accessor);
        //String orgId = organizationEntity.getIdCode();
        List<DocumentOutBean> res = documentOutService.getAlarmJqwsListByTime(st, et, username);

        //AccessInfoVo accessInfoVo = GetOrgIdAndAccessInfoUtils.getAccessInfo(deptCode,username,accessor);

        //装配
        //ResultVo resVo = new ResultVo();
        //GetOrgIdAndAccessInfoUtils.getAfterQueryAudit(resVo,accessInfoVo,res,InterfaceTypeEnum.JQWSXX.getCode());

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "getAlarmJqwsListByTime", String.format("restful is finished,execute time is :%sms", end - start));
        logger.info(String.format("数据汇聚传输接口:%s,传输数据 %s 条，传输结果:成功，执行时间：%s毫秒","getAlarmJqwsListByTime",res.size(),end - start));
        if (dataStore!=null){
            dataStore.clean();
        }
        return new DataVO<>(res);
    }

    /**
     * 8、获取现场信息
     * 获取现场信息
     *
     * @param headers
     * @return
     * @throws ParseException
     */
    @Path("getAlarmJqxcListByTime")
    @POST
    public DataVO<List<LocaleOutBean>> getAlarmJqxcListByTime(
            @Context HttpHeaders headers,
            String outQueryVO
    ) throws ParseException {
        logger.debug(String.format("getAlarmJqxcListByTime,param is:%s", outQueryVO));
        if (StringUtils.isBlank(outQueryVO)) {
            logService.erorLog(logger, "restful", "getAlarmJqxcListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        String startTime = "";
        String endTime = "";
        String access_token = headers.getHeaderString("Authorization").split(" ")[1];

        String deptCode = "";
        logger.debug(String.format("token:%s", access_token));
        try {
            JSONObject responseData = JSONObject.parseObject(outQueryVO);
            startTime = responseData.getString("startTime");
            endTime = responseData.getString("endTime");
            //access_token = responseData.getString("access_token");
            deptCode = responseData.getString("deptCode");
        } catch (Exception ex) {
            logService.erorLog(logger, "restful", "getAlarmJqxcListByTime", "this is error", ex);
        }

        logService.infoLog(logger, "restful", "getAlarmJqxcListByTime", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(startTime) || Strings.isBlank(endTime) || Strings.isBlank(access_token) || Strings.isBlank(deptCode)) {
            logService.erorLog(logger, "restful", "getAlarmJqxcListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        if (!TokenUtils.verify(access_token)) {
            logService.erorLog(logger, "restful", "getAlarmJqxcListByTime", "this is error", new OutException(OutException.AccetpErrors.TOKEN_FAIL));
            throw new OutException(OutException.AccetpErrors.TOKEN_FAIL);
        }
        //获取用户信息
        String username = TokenUtils.getUserName(access_token);
        //执行逻辑处理
        dataStore.get().put("deptCode",deptCode);
        Long st = transferStringDateToLong(startTime);
        Long et = transferStringDateToLong(endTime);
        //获取组织
        //OrganizationEntity organizationEntity = GetOrgIdAndAccessInfoUtils.getOrganization(deptCode,accessor);
        // String orgId = organizationEntity.getIdCode();
        List<LocaleOutBean> res = localeOutService.getAlarmJqxcListByTime(st, et, username);

        //AccessInfoVo accessInfoVo = GetOrgIdAndAccessInfoUtils.getAccessInfo(deptCode,username,accessor);

        //装配
        //ResultVo resVo = new ResultVo();
        //GetOrgIdAndAccessInfoUtils.getAfterQueryAudit(resVo,accessInfoVo,res,InterfaceTypeEnum.JQXCXX.getCode());

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "getAlarmJqxcListByTime", String.format("restful is finished,execute time is :%sms", end - start));
        logger.info(String.format("数据汇聚传输接口:%s,传输数据 %s 条，传输结果:成功，执行时间：%s毫秒","getAlarmJqxcListByTime",res.size(),end - start));
        if (dataStore!=null){
            dataStore.clean();
        }
        return new DataVO<>(res);
    }

    /**
     * 9、获取通话记录信息
     *
     * @param headers
     * @return
     * @throws ParseException
     */
    @Path("getAlarmThjlListByTime")
    @POST
    public DataVO<List<TelephoneOutBean>> getAlarmThjlListByTime(
            @Context HttpHeaders headers,
            String outQueryVO
    ) throws ParseException {
        logger.debug(String.format("getAlarmThjlListByTime,param is:%s", outQueryVO));
        if (StringUtils.isBlank(outQueryVO)) {
            logService.erorLog(logger, "restful", "getAlarmThjlListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        String startTime = "";
        String endTime = "";
        String access_token = headers.getHeaderString("Authorization").split(" ")[1];

        String deptCode = "";
        logger.debug(String.format("token:%s", access_token));
        try {
            JSONObject responseData = JSONObject.parseObject(outQueryVO);
            startTime = responseData.getString("startTime");
            endTime = responseData.getString("endTime");
            //access_token = responseData.getString("access_token");
            deptCode = responseData.getString("deptCode");
        } catch (Exception ex) {
            logService.erorLog(logger, "restful", "getAlarmThjlListByTime", "this is error", ex);
        }

        logService.infoLog(logger, "restful", "getAlarmThjlListByTime", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(startTime) || Strings.isBlank(endTime) || Strings.isBlank(access_token) || Strings.isBlank(deptCode)) {
            logService.erorLog(logger, "restful", "getAlarmThjlListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        if (!TokenUtils.verify(access_token)) {
            logService.erorLog(logger, "restful", "getAlarmThjlListByTime", "this is error", new OutException(OutException.AccetpErrors.TOKEN_FAIL));
            throw new OutException(OutException.AccetpErrors.TOKEN_FAIL);
        }
        //获取用户信息
        String username = TokenUtils.getUserName(access_token);
        //执行逻辑处理
        dataStore.get().put("deptCode",deptCode);
        Long st = transferStringDateToLong(startTime);
        Long et = transferStringDateToLong(endTime);
        //获取组织
        // OrganizationEntity organizationEntity = GetOrgIdAndAccessInfoUtils.getOrganization(deptCode,accessor);
        //String orgId = organizationEntity.getIdCode();
        List<TelephoneOutBean> res = telephoneOutService.getAlarmThjlListByTime(st, et, username);

        //AccessInfoVo accessInfoVo = GetOrgIdAndAccessInfoUtils.getAccessInfo(deptCode,username,accessor);

        //装配
        //ResultVo resVo = new ResultVo();
        //GetOrgIdAndAccessInfoUtils.getAfterQueryAudit(resVo,accessInfoVo,res,InterfaceTypeEnum.THJLList.getCode());

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "getAlarmThjlListByTime", String.format("restful is finished,execute time is :%sms", end - start));
        logger.info(String.format("数据汇聚传输接口:%s,传输数据 %s 条，传输结果:成功，执行时间：%s毫秒","getAlarmThjlListByTime",res.size(),end - start));
        if (dataStore!=null){
            dataStore.clean();
        }
        return new DataVO<>(res);
    }

    /**
     * 9、获取警情调派方案信息
     *
     * @param headers
     * @return
     * @throws ParseException
     */
    @Path("getAlarmJqcjDpTjfaListByTime")
    @POST
    public DataVO<List<AlarmJqcjDpTjfaBean>> getAlarmJqcjDpTjfaListByTime(
            @Context HttpHeaders headers,
            String outQueryVO
    ) throws ParseException {
        logger.debug(String.format("getAlarmThjlListByTime,param is:%s", outQueryVO));
        if (StringUtils.isBlank(outQueryVO)) {
            logService.erorLog(logger, "restful", "getAlarmThjlListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        String startTime = "";
        String endTime = "";
        String access_token = headers.getHeaderString("Authorization").split(" ")[1];

        String deptCode = "";
        logger.debug(String.format("token:%s", access_token));
        try {
            JSONObject responseData = JSONObject.parseObject(outQueryVO);
            startTime = responseData.getString("startTime");
            endTime = responseData.getString("endTime");
            //access_token = responseData.getString("access_token");
            deptCode = responseData.getString("deptCode");
        } catch (Exception ex) {
            logService.erorLog(logger, "restful", "getAlarmThjlListByTime", "this is error", ex);
        }

        logService.infoLog(logger, "restful", "getAlarmThjlListByTime", "restful is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        if (Strings.isBlank(startTime) || Strings.isBlank(endTime) || Strings.isBlank(access_token) || Strings.isBlank(deptCode)) {
            logService.erorLog(logger, "restful", "getAlarmThjlListByTime", "this is error", new OutException(OutException.AccetpErrors.DATA_NULL));
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        if (!TokenUtils.verify(access_token)) {
            logService.erorLog(logger, "restful", "getAlarmThjlListByTime", "this is error", new OutException(OutException.AccetpErrors.TOKEN_FAIL));
            throw new OutException(OutException.AccetpErrors.TOKEN_FAIL);
        }
        //获取用户信息
        String username = TokenUtils.getUserName(access_token);
        //执行逻辑处理
        dataStore.get().put("deptCode",deptCode);
        Long st = transferStringDateToLong(startTime);
        Long et = transferStringDateToLong(endTime);
        //获取组织
        // OrganizationEntity organizationEntity = GetOrgIdAndAccessInfoUtils.getOrganization(deptCode,accessor);
        //String orgId = organizationEntity.getIdCode();
        List<AlarmJqcjDpTjfaBean> res = alarmJqcjDpTjfaService.getAlarmJqcjDpTjfaListByTime(st, et, username);

        //AccessInfoVo accessInfoVo = GetOrgIdAndAccessInfoUtils.getAccessInfo(deptCode,username,accessor);

        //装配
        //ResultVo resVo = new ResultVo();
        //GetOrgIdAndAccessInfoUtils.getAfterQueryAudit(resVo,accessInfoVo,res,InterfaceTypeEnum.THJLList.getCode());

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "getAlarmThjlListByTime", String.format("restful is finished,execute time is :%sms", end - start));
        logger.info(String.format("数据汇聚传输接口:%s,传输数据 %s 条，传输结果:成功，执行时间：%s毫秒","getAlarmJqcjDpTjfaListByTime",res.size(),end - start));
        if (dataStore!=null){
            dataStore.clean();
        }
        return new DataVO<>(res);
    }

    private static Long transferStringDateToLong(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = sdf.parse(date);
        return dt.getTime();
    }


}
