package com.dscomm.iecs.accept.utils.transform;

import com.dscomm.iecs.accept.dal.po.FireControlVoiceEntity;
import com.dscomm.iecs.accept.graphql.firetypebean.FireControlVoiceBean;
import com.dscomm.iecs.accept.restful.vo.FireControlVoice.FireControlVoiceVO;
import com.dscomm.iecs.accept.service.bean.FireControlVoiceCompareBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**800M录音记录转换*/

public class FireControlVoiceTransformUtil {

    public static FireControlVoiceEntity transform(FireControlVoiceVO inputInfo){
        FireControlVoiceEntity entity = new FireControlVoiceEntity();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        if (inputInfo != null){
           entity.setBrief(inputInfo.getBrief());
           entity.setId(inputInfo.getId());
           entity.setCreatedTime(inputInfo.getCreatetime() != null? inputInfo.getCreatetime():-1);
           entity.setUpdatedTime(inputInfo.getUpdatetime() != null? inputInfo.getUpdatetime():-1);
           entity.setChannel(inputInfo.getChannel());
           entity.setChannelName(inputInfo.getChannelName());
           entity.setContent(inputInfo.getContent());
           entity.setHref(inputInfo.getHref());
           entity.setName(inputInfo.getName());
           entity.setRemark(inputInfo.getRemark());
           entity.setSize(inputInfo.getSize());
           entity.setTerrace(inputInfo.getTerrace());
            try {
                entity.setTime(simpleDateFormat.parse(inputInfo.getTime()).getTime());
            } catch (ParseException e) {
                entity.setTime(-1L);
            }
            entity.setVoiceclass(inputInfo.getVoiceclass());
           entity.setVoiceclassname(inputInfo.getVoiceclassname());
           entity.setVoiceNum(inputInfo.getVoiceNum());
        }
        return entity;
    }

    public static FireControlVoiceBean transform(FireControlVoiceEntity entity){
        FireControlVoiceBean bean = new FireControlVoiceBean();
        if (entity != null){
            bean.setBrief(entity.getBrief());
            bean.setId(entity.getId());
            bean.setCreatedTime(entity.getCreatedTime());
            bean.setUpdatedTime(entity.getUpdatedTime());
            bean.setChannel(entity.getChannel());
            bean.setChannelName(entity.getChannelName());
            bean.setContent(entity.getContent());
            bean.setHref(entity.getHref());
            bean.setName(entity.getName());
            bean.setRemark(entity.getRemark());
            bean.setSize(entity.getSize());
            bean.setTerrace(entity.getTerrace());
            bean.setTime(entity.getTime());
            bean.setVoiceclass(entity.getVoiceclass());
            bean.setVoiceclassname(entity.getVoiceclassname());
            bean.setVoiceNum(entity.getVoiceNum());
        }
        return bean;
    }

    public static Map<String, List<FireControlVoiceCompareBean>> transform (List<Object[]> source){
        Map<String, List<FireControlVoiceCompareBean>> target = new HashMap<>();
        if (source != null && source.size()>0){
            for (Object[] objects :source){
                FireControlVoiceCompareBean bean = new FireControlVoiceCompareBean();
                bean.setRadioCallSign(objects[0].toString());
                bean.setIncident(objects[1].toString());
                bean.setNoticeTime(Long.parseLong(objects[2].toString()));
                bean.setReturnTime(objects[3] != null?Long.parseLong(objects[3].toString()):null);
                List<FireControlVoiceCompareBean> beans = new ArrayList<>();
                if (target.get(bean.getRadioCallSign()) != null && target.get(bean.getRadioCallSign()).size()>0){
                    beans.addAll(target.get(bean.getRadioCallSign()));
                }
                beans.add(bean);
                target.put(bean.getRadioCallSign(),beans);
            }
        }
        return target;
    }

}
