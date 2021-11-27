package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.FireControlVoiceEntity;
import com.dscomm.iecs.accept.dal.po.TransporterEntity;
import com.dscomm.iecs.accept.dal.repository.DocumentRepository;
import com.dscomm.iecs.accept.dal.repository.HandleOrganizationVehicleRepository;
import com.dscomm.iecs.accept.dal.repository.HandleOrganizationVehicleSHRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.firetypebean.FireControlVoiceBean;
import com.dscomm.iecs.accept.graphql.firetypebean.FireControlVoiceReturnBean;
import com.dscomm.iecs.accept.graphql.inputbean.DocumentSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.DocumentSaveInputInfoSH;
import com.dscomm.iecs.accept.restful.vo.FireControlVoice.FireControlVoiceReturnVO;
import com.dscomm.iecs.accept.restful.vo.FireControlVoice.FireControlVoiceVO;
import com.dscomm.iecs.accept.service.DocumentService;
import com.dscomm.iecs.accept.service.DocumentServiceSH;
import com.dscomm.iecs.accept.service.FireControlVoiceService;
import com.dscomm.iecs.accept.service.bean.FireControlVoiceCompareBean;
import com.dscomm.iecs.accept.utils.transform.FireControlVoiceTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;

import com.dscomm.iecs.basedata.dal.repository.EquipmentVehicleRepository;
import com.dscomm.iecs.basedata.graphql.inputbean.AttachmentSaveInputInfo;
import com.dscomm.iecs.basedata.service.AttachmentService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.mx.service.client.rest.RestClientInvoke;
import org.mx.service.client.rest.RestInvokeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component("fireControlVoiceServiceImpl")
public class FireControlVoiceServiceImpl implements FireControlVoiceService {

    private static final Logger logger = LoggerFactory.getLogger(FireControlVoiceServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private ServletService servletService;
    private EquipmentVehicleRepository equipmentVehicleRepository;
    private HandleOrganizationVehicleSHRepository handleOrganizationVehicleRepository;
    private DocumentRepository documentRepository;
    private DocumentServiceSH documentService;
    private AttachmentService attachmentService;

    @Autowired
    public FireControlVoiceServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env,
                                       ServletService servletService, EquipmentVehicleRepository equipmentVehicleRepository,
                                       HandleOrganizationVehicleSHRepository handleOrganizationVehicleRepository, DocumentRepository documentRepository,
                                       DocumentServiceSH documentService, AttachmentService attachmentService) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.servletService = servletService;
        this.equipmentVehicleRepository = equipmentVehicleRepository;
        this.handleOrganizationVehicleRepository = handleOrganizationVehicleRepository;
        this.documentRepository = documentRepository;
        this.documentService = documentService;
        this.attachmentService = attachmentService;
    }

    /**同步数据并保存*/
    @Override
    public FireControlVoiceReturnBean getFireControlVoice(String token) {
        TransporterEntity entity = accessor.getById("jcj_dtlyjl", TransporterEntity.class);
        Long lastUpdataTime = 1622476800000L;
        Long endTime = servletService.getSystemTime();
        Integer pageSize = 1000;
        List<FireControlVoiceEntity> entities = new ArrayList<>();
        List<FireControlVoiceBean> beans = new ArrayList<>();
        List<String> idList = new ArrayList<>();
        FireControlVoiceReturnBean res = new FireControlVoiceReturnBean();
        FireControlVoiceReturnVO fireControlVoiceReturnVO = new FireControlVoiceReturnVO();
        if (entity != null) {
            lastUpdataTime = entity.getZHTBSJ();
        }else {
            entity = new TransporterEntity();
            entity.setId("jcj_dtlyjl");
            entity.setDCTBSC(5);
        }
        //更新时间晚于目标时间，则调用接口同步数据
        if (lastUpdataTime != null && endTime != null && lastUpdataTime < endTime) {

            if (StringUtils.isBlank(token) ){
                String tokenVO = getToken();
                if ( !StringUtils.isBlank(tokenVO)){
                    token = tokenVO.split(",")[0].split(":")[1].substring(1,tokenVO.split(",")[0].split(":")[1].length()-1);
                }
                logService.infoLog(logger, "service", "getFireControlVoice", token);
            }
            if (lastUpdataTime == 1622476800000L){
                //首次抽取
                pageSize = 5000;
            }
            fireControlVoiceReturnVO = getData(lastUpdataTime,endTime,pageSize,token);
            if(fireControlVoiceReturnVO != null && (fireControlVoiceReturnVO.getCode().equals("1002")||fireControlVoiceReturnVO.getCode().equals("1003"))){
                //token错误
                String tokenVO = getToken();
                if (!StringUtils.isBlank(tokenVO)){
                    token = tokenVO.split(",")[0].split(":")[1].substring(1,tokenVO.split(",")[0].split(":")[1].length()-1);
                }
                endTime = servletService.getSystemTime();
                fireControlVoiceReturnVO = getData(lastUpdataTime,endTime,pageSize,token);
            }
            if (fireControlVoiceReturnVO != null && fireControlVoiceReturnVO.getCode().equals("0")){
                //调用成功
                if (fireControlVoiceReturnVO.getData() != null && fireControlVoiceReturnVO.getData().size()>0){
                    for (FireControlVoiceVO voiceVO : fireControlVoiceReturnVO.getData()){
                        FireControlVoiceEntity fireControlVoiceEntity = FireControlVoiceTransformUtil.transform(voiceVO);
                        entities.add(fireControlVoiceEntity);
                        FireControlVoiceBean fireControlVoiceBean = FireControlVoiceTransformUtil.transform(fireControlVoiceEntity);
                        beans.add(fireControlVoiceBean);
                        idList.add(fireControlVoiceBean.getId());
                    }
                }
                entity.setZHTBSJ(endTime);
                accessor.save(entities);
                accessor.save(entity);
                res.setToken(token);
                res.setData(beans);

                if(beans.size()>0){
                    createDocument(beans,idList,lastUpdataTime,endTime);
                }
            }

        }

        return res;
    }

    /**获取token*/
    private String getToken(){

        try {
            logService.infoLog(logger, "service", "getToken", "service is started...");
            Long logStart = System.currentTimeMillis();

            RestClientInvoke restClientInvoke = new RestClientInvoke();
            String url = env.getProperty("tokenUrl");
            String res = "" ;

            try {
                res = restClientInvoke.get(url, String.class);
                logger.debug("Invoke the getToken restful service.");
            } catch (RestInvokeException ex) {
                logger.error("Invoke the getToken restful service fail.", ex);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getToken", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getToken", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_800M_FAIL);
        }

    }

    /**获取电台记录*/
    private FireControlVoiceReturnVO getData(Long startTime,Long endTime,Integer pageSize,String token){

        try {
            logService.infoLog(logger, "service", "getData", "service is started...");
            Long logStart = System.currentTimeMillis();

            RestClientInvoke restClientInvoke = new RestClientInvoke();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss");
            String startTimeStr = sdf.format(new Date(startTime));
            String endTimeStr = sdf.format(new Date(endTime));
            String url = env.getProperty("fireControlVoiceUrl")+"?startTime=%s&endTime=%s&pageSize=%s";
            String currentUrl = String.format(url, startTimeStr,endTimeStr,pageSize);
            FireControlVoiceReturnVO res = new FireControlVoiceReturnVO() ;
            Map<String,Object> headers = new HashMap<>();
            headers.put("Authorization",token);

            try {
                res = restClientInvoke.get(currentUrl, FireControlVoiceReturnVO.class,headers);
                logger.debug("Invoke the getData restful service.");
            } catch (RestInvokeException ex) {
                logger.error("Invoke the getData restful service fail.", ex);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getData", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getData", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_800M_FAIL);
        }

    }

    /**检查电台记录是否与警情绑定，并生成火场文书*/
    private void createDocument(List<FireControlVoiceBean> beans,List<String> ids,Long startTime,Long endTime){

        try {
            Map<String,List<FireControlVoiceBean>> incidentMap = new HashMap<>();
            List<String> radioCallSignList = new ArrayList<>();
            //去重
            GeneralAccessor.ConditionGroup removeDuplicatesConditionGroup = GeneralAccessor.ConditionGroup.and(
                    GeneralAccessor.ConditionTuple.eq("valid",1),
                    GeneralAccessor.ConditionTuple.in("id",ids.toArray(new String[ids.size()]))
            );
            List<FireControlVoiceEntity> fireControlVoiceEntities = accessor.find(removeDuplicatesConditionGroup,FireControlVoiceEntity.class);
            if (fireControlVoiceEntities != null && fireControlVoiceEntities.size()>0){
                for (FireControlVoiceEntity fireControlVoiceEntity:fireControlVoiceEntities){
                    FireControlVoiceBean fireControlVoiceDuplicatesBean = FireControlVoiceTransformUtil.transform(fireControlVoiceEntity);
                    beans.remove(fireControlVoiceDuplicatesBean);
                }
            }

            for (FireControlVoiceBean fireControlVoiceBean:beans){
               if (!radioCallSignList.contains(fireControlVoiceBean.getTerrace())){
                   radioCallSignList.add(fireControlVoiceBean.getTerrace());
               }
            }

            //先将电台id和警情id一对多绑定
            List<Object[]> handleOrganizationVehicleEntities = handleOrganizationVehicleRepository.findHandleOrganizationVehicleByCarAndTime(radioCallSignList,startTime,endTime);
            Map<String,List<FireControlVoiceCompareBean>> compareBeanMap = FireControlVoiceTransformUtil.transform(handleOrganizationVehicleEntities);

            //再将警情id和录音记录一对多绑定
            for (FireControlVoiceBean fireControlVoiceBean:beans){
                if (compareBeanMap.get(fireControlVoiceBean.getTerrace()) != null && compareBeanMap.get(fireControlVoiceBean.getTerrace()).size()>0){
                    for (FireControlVoiceCompareBean fireControlVoiceCompareBean:compareBeanMap.get(fireControlVoiceBean.getTerrace())){
                        Boolean match = false;
                        if (fireControlVoiceCompareBean.getReturnTime() != null ){
                            if (fireControlVoiceBean.getTime()>=fireControlVoiceCompareBean.getNoticeTime() && fireControlVoiceBean.getTime()<=fireControlVoiceCompareBean.getReturnTime()){
                                match = true;
                            }
                        }else {
                            if (fireControlVoiceBean.getTime()>=fireControlVoiceCompareBean.getNoticeTime() ){
                                match = true;
                            }
                        }
                        if (match){
                            List<FireControlVoiceBean> incidentFireControlVoiceBeans = new ArrayList<>();
                            if (incidentMap.get(fireControlVoiceCompareBean.getIncident()) != null && incidentMap.get(fireControlVoiceCompareBean.getIncident()).size()>0){
                                incidentFireControlVoiceBeans.addAll(incidentMap.get(fireControlVoiceCompareBean.getIncident()));
                            }
                            incidentFireControlVoiceBeans.add(fireControlVoiceBean);
                            incidentMap.put(fireControlVoiceCompareBean.getIncident(),incidentFireControlVoiceBeans);
                        }
                    }
                }
            }

            //循环map，每个map对应一个警情保存一个火场文书。value对应的list作为附件
            for (String key:incidentMap.keySet()){
                DocumentSaveInputInfoSH documentSaveInputInfo = new DocumentSaveInputInfoSH();
                documentSaveInputInfo.setIncidentId(key);
                documentSaveInputInfo.setType("20");
                documentSaveInputInfo.setTitle("车辆电台");
                documentSaveInputInfo.setContent("电台通信");
                documentSaveInputInfo.setFeedbackPerson("移动手台");
                List<AttachmentSaveInputInfo> attachmentSaveInputInfos = new ArrayList<>();
                List<FireControlVoiceBean> fireControlVoiceBeans = incidentMap.get(key);
                for (FireControlVoiceBean bean:fireControlVoiceBeans){
                    AttachmentSaveInputInfo attachmentSaveInputInfo = new AttachmentSaveInputInfo();
                    attachmentSaveInputInfo.setIncidentId(key);
                    attachmentSaveInputInfo.setAttachmentType("09");
                    attachmentSaveInputInfo.setAttachmentFileName(bean.getName());
                    attachmentSaveInputInfo.setAttachmentFileUrl(bean.getHref());
                    attachmentSaveInputInfos.add(attachmentSaveInputInfo);
                }
                documentSaveInputInfo.setAttachmentSaveInputInfos(attachmentSaveInputInfos);
                documentService.saveDocumentSH(documentSaveInputInfo);
            }

        }catch (Exception e){
            logService.erorLog(logger, "service", "createDocument", "execution error", e);
            e.printStackTrace();
        }

    }

}
